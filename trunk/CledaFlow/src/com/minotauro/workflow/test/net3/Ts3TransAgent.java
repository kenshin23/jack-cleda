package com.minotauro.workflow.test.net3;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.test.base.DummyOneTransAgent;

public class Ts3TransAgent extends DummyOneTransAgent {

  protected DTest1 test1;

  // ----------------------------------------

  public Ts3TransAgent() {
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
        wrkTransSet.getNetTransSetRef().getName());

    MWrkTrans ret = null;

    if (test1.getLftCount() < (test1.getTotCount() / 2) && //
        test1.getLftCount() < (test1.getTotCount() / 2)) {
      ret = wrkTransByName.get("t5");
      System.err.println("************>t5 (bth)");
    } else {
      if/*    */(test1.getLftCount() < (test1.getTotCount() / 2)) {
        ret = wrkTransByName.get("t4");
        System.err.println("************>t4 (lft)");
      } else {
        ret = wrkTransByName.get("t6");
        System.err.println("************>t6 (rgh)");
      }
    }

    log.debug("ret: " + ret.getNetTransRef().getName());

    return ret;
  }
}
