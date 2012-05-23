/*
 * Created on 25/07/2008
 */
package com.minotauro.workflow.engine;

import org.hibernate.Session;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public interface EngineAgentDelegator {

  public void init(Session session) //
      throws WorkflowException;

  // ----------------------------------------

  public MWrkTrans execute(MWrkTransSet wrkTransSet) //
      throws WorkflowException;

  // ----------------------------------------

  public void executePre(MWrkTransSet wrkTransSet) //
      throws WorkflowException;

  public void executePos(MWrkTransSet wrkTransSet) //
      throws WorkflowException;
}
