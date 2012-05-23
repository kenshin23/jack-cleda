package com.minotauro.workflow.test.net3;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.test.base.DummyOneTransAgent;

public class Ts5TransAgent extends DummyOneTransAgent {

  protected DTest1 test1;

  // ----------------------------------------

  public Ts5TransAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    test1 = (DTest1) document;
  }

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {

    test1.setRghCount(test1.getRghCount() + 1);
    System.err.println("************>TS5,t8 (rgh)");

    synchronized (Global.syncToken) {
      Global.rghCount++;
    }

    return super.execute(wrkTransByName);
  }
}
