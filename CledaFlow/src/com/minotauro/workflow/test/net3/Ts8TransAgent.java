package com.minotauro.workflow.test.net3;

import org.hibernate.Session;

import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.test.base.DummyOneTransAgent;

public class Ts8TransAgent extends DummyOneTransAgent {

  public Ts8TransAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    ThreadUtils.notify(Global.syncToken);
  }
}
