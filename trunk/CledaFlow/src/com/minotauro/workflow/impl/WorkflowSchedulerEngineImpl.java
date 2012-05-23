/*
 * Created on 22/07/2008
 */
package com.minotauro.workflow.impl;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.task.core.SchedulerException;
import com.minotauro.cleda.task.core.SchedulerFacade;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.core.SchedulerProxy;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.api.WorkflowFactory;
import com.minotauro.workflow.api.WorkflowSchedulerEngine;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.WorkflowSchedulerEngineImplI18N;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.prop.MWrkTransSetProp;

/**
 * @author Demián Gutierrez
 */
public class WorkflowSchedulerEngineImpl implements WorkflowSchedulerEngine {

  protected static final Logger log = LoggerFactory.getLogger( //
      WorkflowSchedulerEngineImpl.class.getName());

  // --------------------------------------------------------------------------------

  protected String wflowId;
  protected String schedId;
  protected String hibeCfg;

  // --------------------------------------------------------------------------------

  public WorkflowSchedulerEngineImpl() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException {

    this.wflowId = wflowId;
    this.schedId = schedId;
    this.hibeCfg = hibeCfg;

    SchedulerProxy schedulerProxy = new SchedulerProxy() {
      @Override
      public void executeTask(MTask task) //
          throws SchedulerException {
        doExecuteTask(task);
      }
    };

    schedulerProxy.setProxyId(wflowId);

    try {
      SchedulerFactory.getInstance().initFacade(schedId);

      SchedulerFacade schedulerFacade = //
      SchedulerFactory.getInstance().getFacade(schedId);

      schedulerFacade.addSchedulerProxy(schedulerProxy);
    } catch (SchedulerException e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------
  // Misc
  // --------------------------------------------------------------------------------

  protected void doExecuteTask(MTask task) //
      throws SchedulerException {
    try {
      failsafeDoExecuteTask(task);
    } catch (Exception e) {
      log.warn(e.getMessage(), e);

      throw new SchedulerException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeDoExecuteTask(MTask task) throws Exception {

    // ----------------------------------------
    // Get the corresponding MWrkTransSet
    // ----------------------------------------

    MWrkTransSet wrkTransSet = findWrkTransSet(task);

    if (wrkTransSet == null) {
      log.debug(WorkflowSchedulerEngineImplI18N. //
          wrkTransSetNotFound(task.getId()));
      return;
    }

    // ----------------------------------------
    // Fire the MWrkTransSet
    // ----------------------------------------

    log.debug(WorkflowSchedulerEngineImplI18N.begFire( //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId(), task.getId()));

    // ----------------------------------------

    WorkflowFacade workflowFacade = //
    (WorkflowFacade) WorkflowFactory.getInstance().getFacade(wflowId);

    // ----------------------------------------
    // TODO: Check
    //    workflowFacade.dolockWorkflow(
    //        wrkTransSet.getWorkflowRef());
    // ----------------------------------------

    Session session = null;

    try {
      session = CledaConnector.getInstance().getSession(hibeCfg);
      session.beginTransaction();

      workflowFacade.init(session, null, null);
      workflowFacade.selectWorkflow(
          wrkTransSet.getWorkflowRef().getDocumentRef());
      workflowFacade.fireAutoTrans(wrkTransSet);

      CledaConnector.comAndClose(session);

      workflowFacade.flushScheduler();
    } catch (Exception e) {
      CledaConnector.rolAndThrow(session, e);
    } finally {
      // ----------------------------------------
      // TODO: Check
      //      workflowFacade.unlockWorkflow( //
      //          wrkTransSet.getWorkflowRef());
      // ----------------------------------------
    }

    // ----------------------------------------

    log.debug(WorkflowSchedulerEngineImplI18N.endFire( //
        wrkTransSet.getNetTransSetRef().getName(), //
        wrkTransSet.getId(), task.getId()));
  }

  // --------------------------------------------------------------------------------

  protected MWrkTransSet findWrkTransSet(MTask task) throws Exception {
    Session session = null;

    try {
      session = CledaConnector.getInstance().getSession(hibeCfg);
      session.beginTransaction();

      String agentTask = Integer.toString(task.getId());

      MWrkTransSet wrkTransSet = MBase.loadByField( //
          session, MWrkTransSet.class, //
          MWrkTransSetProp.AGENT_TASK, agentTask);

      return wrkTransSet;
    } catch (Exception e) {
      CledaConnector.rolAndThrow(session, e);
    } finally {
      CledaConnector.comAndClose(session);
    }

    return null;
  }
}
