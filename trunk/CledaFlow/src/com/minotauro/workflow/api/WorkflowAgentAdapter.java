/*
 * Created on 09/01/2008
 */
package com.minotauro.workflow.api;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWrkTrans;

/**
 * @author Demián Gutierrez
 */
public abstract class WorkflowAgentAdapter implements WorkflowAgent {

  protected MDocument/**/document;
  protected Session/*  */session;

  // --------------------------------------------------------------------------------

  public WorkflowAgentAdapter() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(MDocument document, Session session) {
    this.document/**/= document;
    this.session/* */= session;
  }

  // --------------------------------------------------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public void executePre(MNetTransSet netTransSet) //
      throws Exception {
    // Empty
  }

  public void executePos(MNetTransSet netTransSet) //
      throws Exception {
    // Empty
  }
}
