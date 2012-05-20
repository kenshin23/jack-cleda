/*
 * Created on 28/07/2005
 */
package com.minotauro.base.model;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.CallbackException;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.classic.Lifecycle;

/**
 * @author Alejandro Salas
 */
@MappedSuperclass
class Copy_MBase_CheckWarnings implements Lifecycle {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected int id;

  protected boolean systemEntry;

  protected boolean busyEntry;

  // --------------------------------------------------------------------------------

  public Copy_MBase_CheckWarnings() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  // --------------------------------------------------------------------------------

  public boolean isSystemEntry() {
    return systemEntry;
  }

  public void setSystemEntry(boolean systemEntry) {
    this.systemEntry = systemEntry;
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Lifecycle
  // --------------------------------------------------------------------------------

  public boolean onSave(Session session) //
      throws CallbackException {

    return false;
  }

  public boolean onUpdate(Session session) //
      throws CallbackException {

    return false;
  }

  public boolean onDelete(Session session) //
      throws CallbackException {

    return false;
  }

  public void onLoad(Session session, Serializable id) {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  @Transient
  public boolean isBusyEntry() {
    return busyEntry;
  }

  @Transient
  public String getIdAsString() {
    return Integer.toString(id);
  }

  @Transient
  public boolean isDeleteAllowed() {
    return !isBusyEntry() && !systemEntry;
  }

  // --------------------------------------------------------------------------------

  public Copy_MBase_CheckWarnings reload(Session session) throws ObjectNotFoundException {
    if (id != 0) {
      return (Copy_MBase_CheckWarnings) session.load(getClass(), getId());
    }

    return this;
  }

  // --------------------------------------------------------------------------------

  public static <E> E loadByField( //
      Session session, Class<E> clazz, //
      String fieldKey, Object fieldVal) {

    String hql = "FROM {0} AS item";

    if (fieldKey != null) {
      hql += " WHERE {1}=:fieldVal";
    }

    hql = MessageFormat.format(hql, new Object[]{clazz.getName(), fieldKey});
    Query query = session.createQuery(hql);

    if (fieldKey != null) {
      query.setParameter("fieldVal", fieldVal);
    }

    return (E) query.uniqueResult();
  }

  public Copy_MBase_CheckWarnings loadByField( //
      Session session, String fieldKey, Object fieldVal) {

    return Copy_MBase_CheckWarnings.loadByField(session, getClass(), fieldKey, fieldVal);
  }

  // --------------------------------------------------------------------------------

  public static <E> List<E> listByField( //
      Session session, Class<E> clazz, //
      String fieldKey, Object fieldVal, String orderBy) {

    StringBuffer strbuf = new StringBuffer();

    strbuf.append("FROM {0} AS item");

    if (fieldKey != null) {
      strbuf.append(" WHERE {1}=:fieldVal");
    }

    if (orderBy != null) {
      strbuf.append(" ORDER BY ");
      strbuf.append(orderBy);
    }

    String hql = MessageFormat.format( //
        strbuf.toString(), new Object[]{clazz.getName(), fieldKey});
    Query query = session.createQuery(hql);

    if (fieldKey != null) {
      query.setParameter("fieldVal", fieldVal);
    }

    return query.list();
  }

  public static <E> List<E> listByField(Session session, Class<E> clazz) {
    return listByField(session, clazz, null, null, null);
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public List listByField( //
      Session session, String fieldKey, Object fieldVal, String orderBy) {

    return Copy_MBase_CheckWarnings.listByField(session, getClass(), fieldKey, fieldVal, orderBy);
  }

  @SuppressWarnings("unchecked")
  public List listByField( //
      Session session) {

    return Copy_MBase_CheckWarnings.listByField(session, getClass());
  }

  // --------------------------------------------------------------------------------

  public String truncateString(String string, int length) {
    if (string == null) {
      return null;
    }

    length = string.length() < length ? string.length() : length;
    return string.substring(0, length);
  }

  // --------------------------------------------------------------------------------

  @Override
  public int hashCode() {
    return id == 0 ? super.hashCode() : id;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }

    return hashCode() == obj.hashCode();
  }

  // --------------------------------------------------------------------------------

  public int superHashCode() {
    return super.hashCode();
  }
}
