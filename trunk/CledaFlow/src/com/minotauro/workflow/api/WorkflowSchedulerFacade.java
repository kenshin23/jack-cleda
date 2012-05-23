/*
 * Created on 22/07/2008
 */
package com.minotauro.workflow.api;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowSchedulerFacade {

  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException;

  // --------------------------------------------------------------------------------

  public void addSusp(MWrkTransSet wrkTransSet) //
      throws WorkflowException;

  public void addWait(MWrkTransSet wrkTransSet) //
      throws WorkflowException;

  public void delTask(MWrkTransSet wrkTransSet) //
      throws WorkflowException;
}
