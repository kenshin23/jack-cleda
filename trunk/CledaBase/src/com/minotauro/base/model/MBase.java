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
public class MBase implements Lifecycle {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected int id;

  protected boolean systEntry;

  protected boolean busyEntry;

  // --------------------------------------------------------------------------------

  public MBase() {
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

  public boolean getSystEntry() {
    return systEntry;
  }

  public void setSystEntry(boolean systEntry) {
    this.systEntry = systEntry;
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
  public boolean getBusyEntry() {
    return busyEntry;
  }

  @Transient
  public String getIdAsString() {
    return Integer.toString(id);
  }

  @Transient
  public boolean getDeleteAllowed() {
    return !getBusyEntry() && !systEntry;
  }

  // --------------------------------------------------------------------------------

  public MBase reload(Session session) {
    if (id == 0) {
      return this;
    }

    try {
      return (MBase) session.load(getClass(), getId());
    } catch (ObjectNotFoundException e) {
      return null;
    }
  }

  // --------------------------------------------------------------------------------

  public static <E extends MBase> E loadByField( //
      Session session, Class<E> clazz, //
      String fieldKey, Object fieldVal) {

    String hql = "FROM {0} AS item";

    if (fieldKey != null) {
      hql += " WHERE {1}=:fieldVal";
    }

    hql = MessageFormat.format( //
        hql, new Object[]{clazz.getName(), fieldKey});

    Query query = session.createQuery(hql);

    if (fieldKey != null) {
      query.setParameter("fieldVal", fieldVal);
    }

    return (E) query.uniqueResult();
  }

  // --------------------------------------------------------------------------------

  public <E extends MBase> E loadByField( //
      Session session, String fieldKey, Object fieldVal) {

    return (E) MBase.loadByField( //
        session, getClass(), fieldKey, fieldVal);
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

  //  public static <E extends MBase> List<E> listByField(Session session, Class<E> clazz) {
  public static <E> List<E> listByField(Session session, Class<E> clazz) {
    return listByField(session, clazz, null, null, null);
  }

  // --------------------------------------------------------------------------------

  //  @SuppressWarnings("unchecked")
  //  public <E extends MBase> List<E> listByField(Session session) {
  //    return (List<E>) MBase.listByField(session, getClass());
  //  }

  @SuppressWarnings("unchecked")
  public List listByField( //
      Session session, String fieldKey, Object fieldVal, String orderBy) {

    return MBase.listByField(session, getClass(), fieldKey, fieldVal, orderBy);
  }

  //  @SuppressWarnings("unchecked")
  //  public <E extends MBase> List<E> listByField(Session session) {
  //    return (List<E>) MBase.listByField(session, getClass());
  //  }

  @SuppressWarnings("unchecked")
  public List listByField( //
      Session session) {

    return MBase.listByField(session, getClass());
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

  @Override
  public String toString() {
    return getClass().getName() + "@" + //
        Integer.toHexString(super.hashCode()) + "/" + hashCode();
  }
}
