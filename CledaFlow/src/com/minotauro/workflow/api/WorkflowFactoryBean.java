/*
 * Created on 09/04/2008
 */
package com.minotauro.workflow.api;

import com.minotauro.workflow.exception.WorkflowException;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowFactoryBean {

  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException;

  // --------------------------------------------------------------------------------

  public String getWflowId();

  public String getSchedId();

  public String getHibeCfg();

  // --------------------------------------------------------------------------------

  public WorkflowSchedulerFacade getScheduler() //
      throws WorkflowException;
}
