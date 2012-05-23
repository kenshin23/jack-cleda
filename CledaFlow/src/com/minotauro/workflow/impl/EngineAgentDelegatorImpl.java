/*
 * Created on 25/07/2008
 */
package com.minotauro.workflow.impl;

import org.hibernate.Session;

import com.minotauro.workflow.engine.EngineAgentDelegator;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.util.AgentUtil;

/**
 * @author Demián Gutierrez
 */
public class EngineAgentDelegatorImpl implements EngineAgentDelegator {

  protected Session session;

  // ----------------------------------------

  public EngineAgentDelegatorImpl() {
    // Empty
  }

  // ----------------------------------------

  public void init(Session session) //
      throws WorkflowException {
    this.session = session;
  }

  // ----------------------------------------

  public MWrkTrans execute(MWrkTransSet wrkTransSet) //
      throws WorkflowException {
    return AgentUtil.callExecute(wrkTransSet, session);
  }

  // ----------------------------------------

  public void executePre(MWrkTransSet wrkTransSet) //
      throws WorkflowException {
    AgentUtil.callExecutePre(wrkTransSet, session);
  }

  public void executePos(MWrkTransSet wrkTransSet) //
      throws WorkflowException {
    AgentUtil.callExecutePos(wrkTransSet, session);
  }
}
