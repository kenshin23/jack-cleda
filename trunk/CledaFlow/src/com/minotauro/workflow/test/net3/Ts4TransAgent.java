package com.minotauro.workflow.test.net3;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.test.base.DummyOneTransAgent;

public class Ts4TransAgent extends DummyOneTransAgent {

  protected DTest1 test1;

  // ----------------------------------------

  public Ts4TransAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    test1 = (DTest1) document;
  }

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {

    test1.setLftCount(test1.getLftCount() + 1);
    System.err.println("************>TS4,t7 (lft)");

    synchronized (Global.syncToken) {
      Global.lftCount++;
    }

    return super.execute(wrkTransByName);
  }
}
