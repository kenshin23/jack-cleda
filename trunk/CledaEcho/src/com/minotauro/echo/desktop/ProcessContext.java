package com.minotauro.echo.desktop;

import org.hibernate.Session;

import com.minotauro.cleda.model.CledaConnector;

public class ProcessContext {

  protected Session session;

  // --------------------------------------------------------------------------------

  public ProcessContext() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void begSession() {
    session = CledaConnector.getInstance().getSession();
  }

  // --------------------------------------------------------------------------------

  public void endSession() {
    session.close();
  }

  // --------------------------------------------------------------------------------

  public void begTransaction() {
    session.beginTransaction();
  }

  // --------------------------------------------------------------------------------

  public void rolTransaction() {
    session.getTransaction().rollback();
  }

  // --------------------------------------------------------------------------------

  public void comTransaction() {
    session.getTransaction().commit();
  }

  // --------------------------------------------------------------------------------

  public Session getSession() {
    return session;
  }
}
