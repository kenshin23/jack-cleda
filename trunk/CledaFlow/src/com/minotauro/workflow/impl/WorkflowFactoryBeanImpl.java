/*
 * Created on 09/04/2008
 */
package com.minotauro.workflow.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.workflow.api.WorkflowFactoryBean;
import com.minotauro.workflow.api.WorkflowSchedulerEngine;
import com.minotauro.workflow.api.WorkflowSchedulerFacade;
import com.minotauro.workflow.config.FlowConfig;
import com.minotauro.workflow.exception.WorkflowException;

/**
 * @author Demián Gutierrez
 */
public class WorkflowFactoryBeanImpl implements WorkflowFactoryBean {

  protected static final Logger log = LoggerFactory.getLogger( //
      WorkflowFactoryBeanImpl.class.getName());

  // --------------------------------------------------------------------------------

  protected WorkflowSchedulerEngine workflowSchedulerEngine;

  // --------------------------------------------------------------------------------

  protected String wflowId;
  protected String schedId;
  protected String hibeCfg;

  // --------------------------------------------------------------------------------

  public WorkflowFactoryBeanImpl() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException {

    try {
      failsafeInit(wflowId, schedId, hibeCfg);
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public void failsafeInit(String wflowId, String schedId, String hibeCfg) //
      throws Exception {

    this.wflowId = wflowId;
    this.schedId = schedId;
    this.hibeCfg = hibeCfg;

    Class<WorkflowSchedulerEngine> clazz = (Class<WorkflowSchedulerEngine>) //
    Class.forName(FlowConfig.getWorkflowSchedulerEngine(wflowId));

    workflowSchedulerEngine = clazz.newInstance();
    workflowSchedulerEngine.init(wflowId, schedId, hibeCfg);
  }

  // --------------------------------------------------------------------------------

  public String getWflowId() {
    return wflowId;
  }

  public String getSchedId() {
    return schedId;
  }

  public String getHibeCfg() {
    return hibeCfg;
  }

  // --------------------------------------------------------------------------------

  public WorkflowSchedulerFacade getScheduler() //
      throws WorkflowException {

    try {
      return failsafeGetScheduler();
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public WorkflowSchedulerFacade failsafeGetScheduler() //
      throws Exception {

    Class<WorkflowSchedulerFacade> clazz = (Class<WorkflowSchedulerFacade>) //
    Class.forName(FlowConfig.getWorkflowSchedulerFacade(wflowId));

    WorkflowSchedulerFacade workflowSchedulerFacade = clazz.newInstance();
    workflowSchedulerFacade.init(wflowId, schedId, hibeCfg);

    return workflowSchedulerFacade;
  }
}
