/*
 * Created on 17/02/2008
 */
package com.minotauro.cleda.task.core;

import java.util.Calendar;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.task.core.SchedulerQueue.Event;
import com.minotauro.cleda.task.core.SchedulerQueue.State;
import com.minotauro.cleda.task.i18n.FormatDate;
import com.minotauro.cleda.task.i18n.SchedulerFacadeI18N;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.task.model.MTask.Status;
import com.minotauro.cleda.task.prop.MTaskProp;
import com.minotauro.cleda.threads.ThreadUtils;

/**
 * @author Demián Gutierrez
 */
public class SchedulerFacade {

  protected static final Logger log = LoggerFactory.getLogger( //
      SchedulerFacade.class.getName());

  // --------------------------------------------------------------------------------

  protected SchedulerCycle schedulerCycle;
  protected SchedulerQueue schedulerQueue;

  protected String hibeCfg;
  protected String schedId;

  // --------------------------------------------------------------------------------

  public SchedulerFacade(String schedId, String hibeCfg) {

    if (schedId == null || hibeCfg == null) {
      throw new NullPointerException();
    }

    this.hibeCfg = hibeCfg;
    this.schedId = schedId;

    schedulerCycle = new SchedulerCycle(schedId, hibeCfg);
    schedulerQueue = new SchedulerQueue(schedId, hibeCfg);

    schedulerCycle.setSchedulerQueue(schedulerQueue);
    schedulerQueue.setSchedulerCycle(schedulerCycle);
  }

  // --------------------------------------------------------------------------------
  // Start / stop Methods
  // --------------------------------------------------------------------------------

  public synchronized void start() {
    schedulerQueue.start();
    schedulerCycle.start();
  }

  // --------------------------------------------------------------------------------

  public synchronized void stop() {
    long t;

    t = System.currentTimeMillis();
    schedulerCycle.stop();
    log.debug(SchedulerFacadeI18N.stopCycle( //
        System.currentTimeMillis() - t));

    t = System.currentTimeMillis();
    schedulerQueue.stop();
    log.debug(SchedulerFacadeI18N.stopQueue( //
        System.currentTimeMillis() - t));
  }

  // --------------------------------------------------------------------------------
  // SchedulerProxy Methods
  // --------------------------------------------------------------------------------

  public void addSchedulerProxy(SchedulerProxy schedulerProxy) //
      throws SchedulerException {
    schedulerQueue.addSchedulerProxy(schedulerProxy);
  }

  // --------------------------------------------------------------------------------

  public void delSchedulerProxy(SchedulerProxy schedulerProxy) //
      throws SchedulerException {
    schedulerQueue.delSchedulerProxy(schedulerProxy);
  }

  // --------------------------------------------------------------------------------

  public SchedulerProxy[] getSchedulerProxy() {
    return schedulerQueue.getSchedulerProxy();
  }

  // --------------------------------------------------------------------------------
  // Task Methods
  // --------------------------------------------------------------------------------

  protected void failsafeAddTask(MTask task, Status status) {

    // ----------------------------------------
    // creates and updates the task
    // ----------------------------------------

    synchronized (schedulerQueue) {
      Session session = CledaConnector.getInstance().getSession(hibeCfg);
      session.beginTransaction();

      task.setNextDate(task.getProgDate());
      task.setSchedId(schedId);
      task.setStatus(status);

      if (status == Status.SUSP) {
        task.setSuspDate(Calendar.getInstance());
      } else {
        task.setSuspDate(null);
      }

      session.saveOrUpdate(task);

      session.getTransaction().commit();
      session.close();

      // ----------------------------------------
      // the sync avoids reload here
      // ----------------------------------------

      if (status == Status.WAIT) {
        schedulerQueue.addTask(task);
      }
    }
  }

  // --------------------------------------------------------------------------------

  public int addSuspTask(MTask task) //
      throws SchedulerException {

    failsafeAddTask(task, Status.SUSP);

    log.debug(SchedulerFacadeI18N.addSuspTask( //
        task.getTaskIdAsString(), //
        FormatDate.formatTaskDate(task.getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    return task.getId();
  }

  // --------------------------------------------------------------------------------

  public int addWaitTask(MTask task) //
      throws SchedulerException {

    failsafeAddTask(task, Status.WAIT);

    log.debug(SchedulerFacadeI18N.addWaitTask( //
        task.getTaskIdAsString(), //
        FormatDate.formatTaskDate(task.getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    return task.getId();
  }

  // --------------------------------------------------------------------------------

  public void delTask(int id, boolean lock) //
      throws SchedulerException {

    log.debug(SchedulerFacadeI18N.delTaskBeg(id));

    // ----------------------------------------
    // loads the requested task
    // ----------------------------------------

    Session session = CledaConnector.getInstance().getSession(hibeCfg);
    session.beginTransaction();

    MTask task = MBase.loadByField( //
        session, MTask.class, MTaskProp.ID, id);

    session.getTransaction().commit();
    session.close();

    // ----------------------------------------
    // crash if not found
    // ----------------------------------------

    if (task == null) {
      SchedulerException e = new SchedulerException( //
          SchedulerFacadeI18N.taskNotFound(id));
      log.debug(e.getMessage(), e);
      throw e;
    }

    // ----------------------------------------
    // be sure it will be no more executed
    // ----------------------------------------

    SchedulerRunner schedulerRunner;

    synchronized (schedulerQueue) {
      schedulerRunner = schedulerQueue.getSchedulerRunner(task);

      if (lock) {
        schedulerQueue.updateState(schedulerRunner, Event.DLCK);
      } else {
        schedulerQueue.updateState(schedulerRunner, Event.NLCK);
      }
    }

    synchronized (schedulerRunner) {
      while (lock && schedulerRunner.getState() != State.DELE) {
        log.debug(SchedulerFacadeI18N.waitForTask( //
            task.getTaskIdAsString(), //
            schedulerRunner.getState()));

        ThreadUtils.dowait(schedulerRunner, 10000);
      }
    }

    // ----------------------------------------
    // remove requested task
    // ----------------------------------------

    session = CledaConnector.getInstance().getSession(hibeCfg);
    session.beginTransaction();

    task.setStatus(Status.DELE);
    session.saveOrUpdate(task);

    session.getTransaction().commit();
    session.close();

    if (lock) {
      schedulerQueue.updateState(schedulerRunner, Event.GONE);
    }

    log.debug(SchedulerFacadeI18N.delTaskEnd( //
        schedulerRunner.getTaskIdAsString(), //
        FormatDate.formatTaskDate(task.getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));
  }

  // --------------------------------------------------------------------------------
  // Misc Methods
  // --------------------------------------------------------------------------------

  public boolean isTaskListEmpty() {
    return schedulerQueue.isTaskListEmpty();
  }
}
