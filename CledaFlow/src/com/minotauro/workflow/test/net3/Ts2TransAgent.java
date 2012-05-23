package com.minotauro.workflow.test.net3;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.test.base.DummyOneTransAgent;

public class Ts2TransAgent extends DummyOneTransAgent {

  protected DTest1 test1;

  // ----------------------------------------

  public Ts2TransAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    test1 = (DTest1) document;
  }

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {

    MWrkTransSet wrkTransSet = wrkTransByName.values().toArray( //
        new MWrkTrans[0])[0].getWrkTransSetRef();

    log.debug("execute: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName() + //
        ";" + test1.getCurCount());

    MWrkTrans ret = null;

    if (test1.getCurCount() < test1.getTotCount()) {
      ret = wrkTransByName.get("t2");
      System.err.println("--------->t2 (again)");
    } else {
      ret = wrkTransByName.get("t3");
      System.err.println("--------->t3 (end)");
    }

    synchronized (Global.syncToken) {
      Global.curCount++;
    }

    test1.setCurCount(test1.getCurCount() + 1);

    log.debug("ret: " + ret.getNetTransRef().getName());

    return ret;
  }
}
