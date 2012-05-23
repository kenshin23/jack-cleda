package com.minotauro.workflow.test.base;

import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.workflow.api.WorkflowAgent;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

public class DummyOneTransAgent implements WorkflowAgent {

  protected static final Logger log = LoggerFactory.getLogger( //
      DummyOneTransAgent.class.getName());

  // ----------------------------------------

  public DummyOneTransAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    // Empty
  }

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {

    MWrkTransSet wrkTransSet = wrkTransByName.values().toArray( //
        new MWrkTrans[0])[0].getWrkTransSetRef();

    log.debug("execute: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());

    MWrkTrans ret = null;

    for (MWrkTrans wrkTrans : wrkTransByName.values()) {
      if (ret == null) {
        ret = wrkTrans;
      }

      log.debug("trans: " + wrkTrans.getId() + ";" + wrkTrans.getStatus() + ";" + //
          wrkTrans.getNetTransRef().getName());
    }

    return ret;
  }

  // ----------------------------------------

  public void executePre(MWrkTransSet wrkTransSet) //
      throws Exception {
    log.debug("executePre: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());
  }

  public void executePos(MWrkTransSet wrkTransSet) //
      throws Exception {
    log.debug("executePos: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());
  }
}
