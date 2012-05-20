/*
 * Created on 08/02/2006
 */
package com.minotauro.audit;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.EntityMode;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.type.Type;

import com.minotauro.audit.model.Auditable;
import com.minotauro.audit.model.MFieldAudit;
import com.minotauro.audit.model.MObjectAudit;
import com.minotauro.audit.model.MObjectAudit.OperationType;
import com.minotauro.audit.model.MTransactionAudit;
import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class AuditInterceptor extends EmptyInterceptor implements Serializable {

  private static DateFormat auditDateFormat = //
  new SimpleDateFormat("dd-MM-yyyy kk:mm:ss");

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private MTransactionAudit transactionAudit = new MTransactionAudit();
  private Calendar calendar = Calendar.getInstance();

  private String hibernateCfg;

  private MUser user;

  // --------------------------------------------------------------------------------

  public AuditInterceptor(String hibernateCfg, MUser user) {
    this.hibernateCfg/**/= hibernateCfg;
    this.user/*        */= user;
  }

  public AuditInterceptor(MUser user) {
    this.hibernateCfg/**/= CledaConnector.getInstance().getDefaultHibernateCfg();
    this.user/*        */= user;
  }

  // --------------------------------------------------------------------------------

  public String getAuditValue(Object state, Type type) {
    Object value = state;
    Class<?> clazz = type.getReturnedClass();

    // ----------------------------------------
    // Handle calendars / entities
    // ----------------------------------------

    if (value != null) {
      if (clazz.equals(Calendar.class)) {
        Calendar cal = (Calendar) value;
        value = auditDateFormat.format(cal.getTime());
      } else if (type.isEntityType()) {
        value = ((MBase) value).getId();
      }

      value = value.toString();
    }

    return (String) value;
  }

  // --------------------------------------------------------------------------------
  // ----- Interceptor Methods
  // --------------------------------------------------------------------------------

  public void afterTransactionCompletion(Transaction transaction) {

    // ----------------------------------------
    // No need to save
    // ----------------------------------------

    if (transactionAudit.getObjectAuditList().isEmpty()) {
      return;
    }

    // ----------------------------------------
    // Save the transaction audit
    // ----------------------------------------

    Session session = CledaConnector.getInstance().getSession(hibernateCfg);
    Transaction tx = session.beginTransaction();

    transactionAudit.setDate(calendar);
    transactionAudit.setUserRef(user);

    session.saveOrUpdate(transactionAudit);

    tx.commit();
    session.close();
  }

  // --------------------------------------------------------------------------------

  public void onDelete( //
      Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) //
      throws CallbackException {

    // ----------------------------------------
    // Not auditable
    // ----------------------------------------

    if (entity.getClass().getAnnotation(Auditable.class) == null) {
      return;
    }

    // ----------------------------------------
    // Create object audit
    // ----------------------------------------

    MObjectAudit objectAudit = new MObjectAudit();

    objectAudit.setObjectType(entity.getClass().getName());
    objectAudit.setObjectId(((Integer) id).intValue());
    objectAudit.setOperationType(OperationType.DELETE);

    objectAudit.setTransactionAuditRef(transactionAudit);
    transactionAudit.getObjectAuditList().add(objectAudit);
  }

  // --------------------------------------------------------------------------------

  public boolean onFlushDirty( //
      Object entity, Serializable id, Object[] currState, Object[] prevState, String[] propertyNames, Type[] types) //
      throws CallbackException {

    // ----------------------------------------
    // Not auditable
    // ----------------------------------------

    if (entity.getClass().getAnnotation(Auditable.class) == null) {
      return false;
    }

    // ----------------------------------------
    // Get previous state
    // ----------------------------------------

    Session session = CledaConnector.getInstance().getSession(hibernateCfg);
    Object preUpdateEntity = session.get(entity.getClass(), id);

    ClassMetadata classMetadata = session.getSessionFactory().getClassMetadata(preUpdateEntity.getClass());
    prevState = classMetadata.getPropertyValues(preUpdateEntity, EntityMode.POJO);

    session.close();

    // ----------------------------------------
    // Create object audit
    // ----------------------------------------

    MObjectAudit objectAudit = new MObjectAudit();

    objectAudit.setObjectType(entity.getClass().getName());
    objectAudit.setObjectId(((Integer) id).intValue());
    objectAudit.setOperationType(OperationType.UPDATE);

    // ----------------------------------------
    // Create field audit objects
    // ----------------------------------------

    for (int i = 0; i < propertyNames.length; i++) {

      // ----------------------------------------
      // Don't audit collections
      // ----------------------------------------

      if (types[i].isCollectionType()) {
        continue;
      }

      String prev = getAuditValue(prevState[i], types[i]);
      String curr = getAuditValue(currState[i], types[i]);

      Object lft;
      Object rgt;

      if (prevState[i] != null) {
        lft = prev;
        rgt = curr;
      } else if (currState[i] != null) {
        lft = curr;
        rgt = prev;
      } else {
        continue;
      }

      if (lft.equals(rgt)) {
        continue;
      }

      MFieldAudit fieldAudit = new MFieldAudit();
      fieldAudit.setName(propertyNames[i]);
      fieldAudit.setPrevValue(prev);
      fieldAudit.setNextValue(curr);

      fieldAudit.setObjectAuditRef(objectAudit);
      objectAudit.getFieldAuditList().add(fieldAudit);
    }

    if (!objectAudit.getFieldAuditList().isEmpty()) {
      objectAudit.setTransactionAuditRef(transactionAudit);
      transactionAudit.getObjectAuditList().add(objectAudit);
    }

    return false;
  }

  // --------------------------------------------------------------------------------

  public boolean onSave( //
      Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) //
      throws CallbackException {

    // ----------------------------------------
    // Not auditable
    // ----------------------------------------

    if (entity.getClass().getAnnotation(Auditable.class) == null) {
      return false;
    }

    // ----------------------------------------
    // Create object audit
    // ----------------------------------------

    MObjectAudit objectAudit = new MObjectAudit();

    objectAudit.setObjectType(entity.getClass().getName());
    objectAudit.setObjectId(((Integer) id).intValue());
    objectAudit.setOperationType(OperationType.INSERT);

    objectAudit.setTransactionAuditRef(transactionAudit);
    transactionAudit.getObjectAuditList().add(objectAudit);

    // ----------------------------------------
    // Create field audit objects
    // ----------------------------------------

    for (int i = 0; i < propertyNames.length; i++) {

      // ----------------------------------------
      // Don't audit collections
      // ----------------------------------------

      if (types[i].isCollectionType()) {
        continue;
      }

      String value = getAuditValue(state[i], types[i]);

      MFieldAudit fieldAudit = new MFieldAudit();

      fieldAudit.setName(propertyNames[i]);
      fieldAudit.setNextValue(value);

      fieldAudit.setObjectAuditRef(objectAudit);
      objectAudit.getFieldAuditList().add(fieldAudit);
    }

    return false;
  }
}
