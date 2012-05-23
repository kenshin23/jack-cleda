/*
 * Created on 22/07/2008
 */
package com.minotauro.workflow.api;

import com.minotauro.workflow.exception.WorkflowException;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowSchedulerEngine {

  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException;
}
