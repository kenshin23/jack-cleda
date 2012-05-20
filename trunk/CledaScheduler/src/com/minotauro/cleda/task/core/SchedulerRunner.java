/*
 * Created on 08/02/2008
 */
package com.minotauro.cleda.task.core;

import java.util.Calendar;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.task.core.SchedulerQueue.Event;
import com.minotauro.cleda.task.core.SchedulerQueue.State;
import com.minotauro.cleda.task.i18n.FormatDate;
import com.minotauro.cleda.task.i18n.SchedulerRunnerI18N;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.task.model.MTask.Status;
import com.minotauro.cleda.threads.ThreadUtils;

/**
 * @author Demián Gutierrez
 */
public class SchedulerRunner implements Runnable {

  protected static final Logger log = LoggerFactory.getLogger( //
      SchedulerRunner.class.getName());

  // --------------------------------------------------------------------------------

  protected SchedulerProxy schedulerProxy;
  protected SchedulerQueue schedulerQueue;
  protected SchedulerCycle schedulerCycle;

  protected String hibeCfg;

  protected State state;

  protected MTask task;

  // --------------------------------------------------------------------------------

  public SchedulerRunner(String hibeCfg, MTask task) {
    this.hibeCfg/**/= hibeCfg;
    this.task/*   */= task;
  }

  // --------------------------------------------------------------------------------

  public void init( //
      SchedulerQueue schedulerQueue, //
      SchedulerProxy schedulerProxy, //
      SchedulerCycle schedulerCycle) {

    this.schedulerQueue = schedulerQueue;
    this.schedulerProxy = schedulerProxy;
    this.schedulerCycle = schedulerCycle;
  }

  // --------------------------------------------------------------------------------

  public State getState() {
    return state;
  }

  public void setState(State state) {
    this.state = state;
  }

  // --------------------------------------------------------------------------------

  public MTask getTask() {
    return task;
  }

  // --------------------------------------------------------------------------------

  public void run() {
    executeTask();
  }

  // --------------------------------------------------------------------------------

  protected void executeTask() {

    log.debug(SchedulerRunnerI18N.executeTask( //
        task.getIdAsString(), //
        FormatDate.formatTaskDate(task.getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    if (schedulerProxy == null) {
      log.warn(SchedulerRunnerI18N.proxyNotFound( //
          task.getIdAsString(), task.getProxyId()));

      return;
    }

    // ----------------------------------------
    // do execute the task
    // ----------------------------------------

    String errorMsg = null;

    try {
      schedulerProxy.executeTask(task);
    } catch (SchedulerException e) {
      errorMsg = ExceptionUtils.getFullStackTrace(e);
    } catch (RuntimeException e) {
      errorMsg = ExceptionUtils.getFullStackTrace(e);
    }

    // ----------------------------------------
    // handle status
    // ----------------------------------------

    if (errorMsg != null) {
      log.warn(errorMsg);
    }

    if (errorMsg != null) {
      task.setStatus(Status.FAIL);
    } else {
      task.setStatus(Status.DONE);
    }

    task.setErrorMsg(errorMsg);

    // ----------------------------------------
    // should never crash
    // ----------------------------------------

    try {
      updateTask(task);
    } catch (Exception e) {
      log.error(SchedulerRunnerI18N.updateFailed( //
          task.getIdAsString()), e);
    }

    // ----------------------------------------
    // handle status
    // ----------------------------------------

    if (task.retry()) {
      schedulerQueue.updateState( //
          this, Event.FAIL);
    } else {
      schedulerQueue.updateState( //
          this, Event.DONE);
    }

    // ----------------------------------------
    // we are done, tell the cycle
    // ----------------------------------------

    schedulerCycle.unlockExecutor();

    ThreadUtils.notify(schedulerCycle);
  }

  // --------------------------------------------------------------------------------

  protected void updateTask(MTask task) //
      throws Exception, RuntimeException {

    Session session = CledaConnector.getInstance().getSession(hibeCfg);
    session.beginTransaction();

    // ----------------------------------------
    // we are done or waiting
    // ----------------------------------------

    if (task.updateAndRetry()) {
      task.setStatus(Status.RTRY);
    } else {
      task.setStatus(Status.DONE);
    }

    session.saveOrUpdate(task);

    // ----------------------------------------

    session.getTransaction().commit();
    session.close();
  }

  // --------------------------------------------------------------------------------

  @Override
  public int hashCode() {
    return task.hashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null || obj.getClass() != getClass()) {
      return false;
    }

    SchedulerRunner schedulerRunner = //
    (SchedulerRunner) obj;

    return task.equals( //
        schedulerRunner.getTask());
  }

  // --------------------------------------------------------------------------------

  public int superHashCode() {
    return super.hashCode();
  }

  // --------------------------------------------------------------------------------

  public String getTaskIdAsString() {
    return "[ " + task.getTaskIdAsString() + //
        " - 0x" + StringUtils.leftPad( //
            Integer.toHexString(superHashCode()), 8, '0') //
        + " ]";
  }
}
