/*
 * Created on 08/01/2008
 */
package com.minotauro.workflow.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.task.core.SchedulerException;
import com.minotauro.cleda.task.core.SchedulerFacade;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.workflow.api.WorkflowSchedulerFacade;
import com.minotauro.workflow.config.FlowConfig;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.WorkflowSchedulerFacadeImplI18N;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class WorkflowSchedulerFacadeImpl implements WorkflowSchedulerFacade {

  protected static final Logger log = LoggerFactory.getLogger( //
      WorkflowSchedulerFacadeImpl.class.getName());

  // --------------------------------------------------------------------------------

  protected Map<MWrkTransSet, MTask> addTaskList = //
  new HashMap<MWrkTransSet, MTask>();

  // --------------------------------------------------------------------------------

  protected String wflowId;
  protected String schedId;
  protected String hibeCfg;

  // --------------------------------------------------------------------------------

  public WorkflowSchedulerFacadeImpl() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // WorkflowScheduler
  // --------------------------------------------------------------------------------

  @Override
  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException {

    this.wflowId = wflowId;
    this.schedId = schedId;
    this.hibeCfg = hibeCfg;
  }

  // --------------------------------------------------------------------------------

  public void addSusp(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    try {
      failsafeAddSusp(wrkTransSet);
    } catch (SchedulerException e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public void failsafeAddSusp(MWrkTransSet wrkTransSet) //
      throws SchedulerException {

    log.debug(WorkflowSchedulerFacadeImplI18N.posAddSusp( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade( //
        schedId);

    MTask task = new MTask(wflowId, wrkTransSet.getAgentDate());
    addTaskList.put(wrkTransSet, task);

    task.setRetryTries(FlowConfig.getRetryTries(wflowId));
    task.setRetryMilis(FlowConfig.getRetryMilis(wflowId));

    wrkTransSet.setAgentTask( //
        Integer.toString(schedulerFacade.addSuspTask(task)));

    log.debug(WorkflowSchedulerFacadeImplI18N.preAddSusp( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));
  }

  // --------------------------------------------------------------------------------

  public void addWait(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    try {
      failsafeAddWait(wrkTransSet);
    } catch (SchedulerException e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public void failsafeAddWait(MWrkTransSet wrkTransSet) //
      throws SchedulerException, WorkflowException {

    log.debug(WorkflowSchedulerFacadeImplI18N.preAddWait( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));

    MTask task = addTaskList.remove(wrkTransSet);

    if (task == null) {
      throw new WorkflowException(WorkflowSchedulerFacadeImplI18N. //
          taskNull(wrkTransSet.getNetTransSetRef().getName(), //
              wrkTransSet.getId()));
    }

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade( //
        schedId);

    schedulerFacade.addWaitTask(task);

    log.debug(WorkflowSchedulerFacadeImplI18N.posAddWait( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));
  }

  // --------------------------------------------------------------------------------

  public void delTask(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    try {
      failsafeDelTask(wrkTransSet);
    } catch (SchedulerException e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public void failsafeDelTask(MWrkTransSet wrkTransSet) //
      throws SchedulerException, WorkflowException {

    log.debug(WorkflowSchedulerFacadeImplI18N.preDelTask( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));

    if (wrkTransSet.getAgentPrev() == null) {
      throw new WorkflowException(WorkflowSchedulerFacadeImplI18N. //
          agentPrevNull(wrkTransSet.getNetTransSetRef().getName(), //
              wrkTransSet.getId()));
    }

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade( //
        schedId);

    schedulerFacade.delTask( //
        Integer.parseInt(wrkTransSet.getAgentPrev()), false);

    wrkTransSet.setAgentTask(null);
    wrkTransSet.setAgentDate(null);

    log.debug(WorkflowSchedulerFacadeImplI18N.posDelTask( //
        wrkTransSet.getAgentTask(), //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId()));
  }
}
