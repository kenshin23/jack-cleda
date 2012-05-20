/*
 * Created on 03/05/2006
 */
package com.minotauro.base.model;

import java.io.Serializable;

import javax.persistence.Column;

import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;

import com.minotauro.base.i18n._I18NBaseState;

/**
 * @author Alejandro Salas
 */
@Deprecated
public abstract class MBaseState extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String code;
  private String name;

  protected boolean enabled = true;

  // --------------------------------------------------------------------------------

  public MBaseState() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = truncateString(code, 254);
  }

  // --------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = truncateString(name, 254);
  }

  // --------------------------------------------------------------------------------

  @Column(columnDefinition = "INTEGER")
  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  public String getStateStr() {
    StringBuffer ret = new StringBuffer();

    ret.append(enabled ? _I18NBaseState.enabled() : _I18NBaseState.disabled());
    ret.append(" / ");
    ret.append(getBusyEntry() ? _I18NBaseState.busy() : _I18NBaseState.free());

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  protected boolean testQuery(Session session, int id, String queryStr) {
    Query query = session.createQuery(queryStr);
    query.setInteger("id", id);
    Integer count = (Integer) query.uniqueResult();

    return count.intValue() != 0;
  }

  // --------------------------------------------------------------------------------

  public void onLoad(Session session, Serializable id) {
    FlushMode flushMode = session.getFlushMode();
    session.setFlushMode(FlushMode.COMMIT);

    busyEntry = busy(session, Integer.parseInt(id.toString()));

    session.setFlushMode(flushMode);
  }

  // --------------------------------------------------------------------------------

  protected abstract boolean busy(Session session, int id);
}
