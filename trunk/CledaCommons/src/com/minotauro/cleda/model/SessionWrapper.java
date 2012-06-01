/*
 * Created on 09/01/2008
 */
package com.minotauro.cleda.model;

import org.hibernate.Session;

/**
 * @author Demián Gutierrez
 */
public class SessionWrapper {

  private String hibernateCfg;

  private Session session;

  private int rollback = 0;
  private int begCount = 0;

  // --------------------------------------------------------------------------------

  public SessionWrapper(String hibernateCfg, Session session) {
    this.hibernateCfg/**/= hibernateCfg;
    this.session/*     */= session;
  }

  // --------------------------------------------------------------------------------

  public String getHibernateCfg() {
    return hibernateCfg;
  }

  public Session getSession() {
    return session;
  }

  // --------------------------------------------------------------------------------

  public void beg() {
    if (begCount == 0) {
      session.beginTransaction();
    }

    begCount++;
  }

  // --------------------------------------------------------------------------------

  public void com() {
    begCount--;

    transactionEnd();
  }

  public void rol() {
    begCount--;
    rollback++;

    transactionEnd();
  }

  // --------------------------------------------------------------------------------

  private void transactionEnd() {
    if (begCount != 0) {
      return;
    }

    if (rollback > 0) {
      session.getTransaction().rollback();
    } else {
      session.getTransaction().commit();
    }

    session.close();
  }
}
