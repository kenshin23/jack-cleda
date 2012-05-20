/*
 * Created on 15/04/2007
 */
package com.minotauro.cleda.task.core;

import java.util.Calendar;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.task.i18n.FormatDate;
import com.minotauro.cleda.task.i18n.SchedulerCycleI18N;
import com.minotauro.cleda.task.i18n.SchedulerQueueI18N;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.threads.BaseThread;
import com.minotauro.cleda.threads.CountLock;
import com.minotauro.cleda.threads.NotRunningException;
import com.minotauro.cleda.threads.ThreadUtils;

/**
 * @author Demián Gutierrez
 */
public class SchedulerCycle extends BaseThread {

  protected static final Logger log = LoggerFactory.getLogger( //
      SchedulerCycle.class.getName());

  // --------------------------------------------------------------------------------

  protected SchedulerQueue schedulerQueue;

  protected ThreadPoolExecutor executor;

  protected CountLock poolCountLock;

  protected String hibeCfg;
  protected String schedId;

  // --------------------------------------------------------------------------------

  public SchedulerCycle(String schedId, String hibeCfg) {
    super(SchedulerCycleI18N.threadName());

    this.hibeCfg = hibeCfg;
    this.schedId = schedId;
  }

  // --------------------------------------------------------------------------------

  public void setSchedulerQueue(SchedulerQueue schedulerQueue) {
    this.schedulerQueue = schedulerQueue;
  }

  // --------------------------------------------------------------------------------

  @Override
  public void start() {

    if (executor != null) {
      throw new IllegalStateException();
    }

    // ----------------------------------------
    // setup thread factory
    // ----------------------------------------

    ThreadFactory threadFactory = new ThreadFactory() {
      protected int count;

      public Thread newThread(Runnable r) {
        return new Thread(r, //
            SchedulerQueueI18N.threadName( //
                Integer.toString(++count)));
      }
    };

    // ----------------------------------------
    // creates the thread pool executor
    // ----------------------------------------

    int size = SchedulerConfig.getThreadPoolSize();

    poolCountLock = new CountLock(0, size);

    executor = new ThreadPoolExecutor( //
        size, size, 0, TimeUnit.SECONDS, //
        new ArrayBlockingQueue<Runnable>(size), //
        threadFactory);

    super.start();
  }

  // --------------------------------------------------------------------------------

  @Override
  public void stop() {
    if (executor == null) {
      throw new IllegalStateException();
    }

    super.stop();

    executor.shutdown();

    try {
      if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
        executor.shutdownNow();
      }
    } catch (InterruptedException e) {
      executor.shutdownNow();
    }

    executor = null;
  }

  // --------------------------------------------------------------------------------

  protected void failsafeRun() throws NotRunningException {

    // ----------------------------------------
    // nothing to do
    // ----------------------------------------

    if (schedulerQueue.getNextTask() == null) {
      log.debug(SchedulerCycleI18N.waitingForTask());

      ThreadUtils.dowait(this, 10000);
      checkNotRunning();

      return;
    }

    // ----------------------------------------

    SchedulerRunner schedulerRunner = //
    schedulerQueue.getNextTask();

    if (schedulerRunner == null) {
      return;
    }

    // ----------------------------------------
    // curr / next task time delta
    // ----------------------------------------

    MTask nextTask = schedulerRunner.getTask();

    long diff = nextTask.getNextDate().getTimeInMillis() - //
        Calendar.getInstance().getTimeInMillis();

    // ----------------------------------------
    // execute the task or wait for diff
    // ----------------------------------------

    if (diff > 0) {
      log.debug(SchedulerCycleI18N.waitingForTime(diff));

      ThreadUtils.dowait(this, diff);
      checkNotRunning();
    } else {
      log.debug(SchedulerCycleI18N.execTask( //
          schedulerRunner.getTaskIdAsString(), //
          FormatDate.formatTaskDate(nextTask.getNextDate()), //
          FormatDate.formatTaskDate(Calendar.getInstance())));

      executeTask();
    }
  }

  // --------------------------------------------------------------------------------
  // Execute Methods
  // --------------------------------------------------------------------------------

  protected synchronized void executeTask(SchedulerRunner schedulerRunner) {
    if (executor == null) {
      throw new IllegalStateException();
    }

    executor.execute(schedulerRunner);
  }

  // --------------------------------------------------------------------------------

  protected void executeTask() {
    dolockExecutor();

    SchedulerRunner schedulerRunner = schedulerQueue.getExecuteTask();

    if (schedulerRunner != null) {
      log.debug(SchedulerQueueI18N.givingThreadToTask( //
          schedulerRunner.getTaskIdAsString(), //
          FormatDate.formatTaskDate(schedulerRunner.getTask().getNextDate()), //
          FormatDate.formatTaskDate(Calendar.getInstance())));

      executeTask(schedulerRunner);
    } else {
      unlockExecutor();
    }
  }

  // --------------------------------------------------------------------------------

  protected void dolockExecutor() {
    poolCountLock.dolock();
  }

  protected void unlockExecutor() {
    poolCountLock.unlock();
  }
}
