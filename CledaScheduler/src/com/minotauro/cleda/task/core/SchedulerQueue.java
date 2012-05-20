/*
 * Created on 28/02/2007
 */
package com.minotauro.cleda.task.core;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.task.i18n.FormatDate;
import com.minotauro.cleda.task.i18n.SchedulerQueueI18N;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.task.model.MTask.Status;
import com.minotauro.cleda.task.prop.MTaskProp;
import com.minotauro.cleda.threads.ThreadUtils;

/**
 * @author Demián Gutierrez
 */
public class SchedulerQueue {

  public enum State {
    QUEU, INDB, RUNN, DLCK, NLCK, DELE, DONE
  }

  public enum Event {
    RUNN, DONE, FAIL, DLCK, NLCK, GONE
  }

  // --------------------------------------------------------------------------------

  protected static final Logger log = LoggerFactory.getLogger( //
      SchedulerQueue.class.getName());

  // --------------------------------------------------------------------------------

  protected Map<String, SchedulerProxy> schedulerProxyMap = //
  Collections.synchronizedMap(new HashMap<String, SchedulerProxy>());

  protected List<SchedulerRunner> inqueueTaskList = //
  Collections.synchronizedList(new ArrayList<SchedulerRunner>());

  protected List<SchedulerRunner> runningTaskList = //
  Collections.synchronizedList(new ArrayList<SchedulerRunner>());

  protected SchedulerCycle schedulerCycle;

  protected String hibeCfg;
  protected String schedId;

  // --------------------------------------------------------------------------------

  public SchedulerQueue(String schedId, String hibeCfg) {
    this.hibeCfg = hibeCfg;
    this.schedId = schedId;
  }

  // --------------------------------------------------------------------------------

  public void setSchedulerCycle(SchedulerCycle schedulerCycle) {
    this.schedulerCycle = schedulerCycle;
  }

  // --------------------------------------------------------------------------------
  // Start / stop
  // --------------------------------------------------------------------------------

  public synchronized void start() {
    reloadTaskList();
  }

  // --------------------------------------------------------------------------------

  public synchronized void stop() {
    // Dummy
  }

  // --------------------------------------------------------------------------------
  // SchedulerProxy Methods
  // --------------------------------------------------------------------------------

  public synchronized void addSchedulerProxy(SchedulerProxy schedulerProxy) //
      throws SchedulerException {

    if (StringUtils.isBlank(schedulerProxy.getProxyId())) {
      throw new IllegalArgumentException();
    }

    schedulerProxyMap.put(schedulerProxy.getProxyId(), schedulerProxy);
    reloadTaskList();
  }

  // --------------------------------------------------------------------------------

  public synchronized void delSchedulerProxy(SchedulerProxy schedulerProxy) //
      throws SchedulerException {

    schedulerProxyMap.remove(schedulerProxy.getProxyId());
    reloadTaskList();
  }

  // --------------------------------------------------------------------------------

  public synchronized SchedulerProxy[] getSchedulerProxy() {
    return schedulerProxyMap.values().toArray(new SchedulerProxy[0]);
  }

  // --------------------------------------------------------------------------------
  // Task Methods
  // --------------------------------------------------------------------------------

  public synchronized SchedulerRunner getNextTask() {
    if (inqueueTaskList.isEmpty()) {
      return null;
    }

    return inqueueTaskList.get(0);
  }

  // --------------------------------------------------------------------------------

  public void addTask(MTask task) {
    SchedulerRunner schedulerRunner = //
    new SchedulerRunner(hibeCfg, task);

    schedulerRunner.setState(State.QUEU);
    addToInqueueTaskList(schedulerRunner);

    ThreadUtils.notify(schedulerCycle);
  }

  // --------------------------------------------------------------------------------

  public synchronized boolean isTaskListEmpty() {

    log.debug(SchedulerQueueI18N.taskListEmpty( //
        inqueueTaskList.size(), //
        runningTaskList.size()));

    return inqueueTaskList.isEmpty() //
        && runningTaskList.isEmpty();
  }

  // --------------------------------------------------------------------------------
  // Reload task list Methods
  // --------------------------------------------------------------------------------

  protected synchronized Query initTaskListQuery(Session session) {

    // ----------------------------------------
    // create query
    // ----------------------------------------

    StringBuffer strbuf = new StringBuffer();

    strbuf.append(MessageFormat.format( //
        "FROM {0} AS item WHERE (", //
        MTask.class.getName()));

    // ----------------------------------------
    // filter by proxy
    // ----------------------------------------

    Iterator<SchedulerProxy> itt = schedulerProxyMap.values().iterator();

    for (int i = 0; itt.hasNext(); i++) {
      strbuf.append(MessageFormat.format( //
          "{0} = :{0}{1,number,#}", //
          MTaskProp.PROXY_ID, i));

      itt.next();

      if (itt.hasNext()) {
        strbuf.append(" OR ");
      }
    }

    // ----------------------------------------
    // filter by running tasks
    // ----------------------------------------

    SchedulerRunner[] schedulerRunnerArray = //
    runningTaskList.toArray(new SchedulerRunner[0]);

    if (schedulerRunnerArray.length != 0) {
      strbuf.append(") AND (");
    }

    for (int i = 0; i < schedulerRunnerArray.length; i++) {
      strbuf.append(MessageFormat.format( //
          "{0} != :{0}{1,number,#}", //
          MTaskProp.ID, i));

      if (i + 1 < schedulerRunnerArray.length) {
        strbuf.append(" AND ");
      }
    }

    // ----------------------------------------

    strbuf.append(MessageFormat.format( //
        ") AND ({0} = :{0}1 OR {0} = :{0}2)", //
        MTaskProp.STATUS));

    strbuf.append(MessageFormat.format( //
        " AND {0} = :{0}", //
        MTaskProp.SCHED_ID));

    strbuf.append(MessageFormat.format( //
        " ORDER BY {0}", //
        MTaskProp.NEXT_DATE_MILLIS));

    // ----------------------------------------

    log.debug(SchedulerQueueI18N.taskListQuery(strbuf.toString()));

    Query query = session.createQuery(strbuf.toString());

    // ----------------------------------------
    // set parameters
    // ----------------------------------------

    itt = schedulerProxyMap.values().iterator();

    for (int i = 0; itt.hasNext(); i++) {
      SchedulerProxy schedulerProxy = itt.next();
      query.setParameter(MTaskProp.PROXY_ID + i, //
          schedulerProxy.getProxyId());
    }

    // ----------------------------------------

    for (int i = 0; i < schedulerRunnerArray.length; i++) {
      query.setParameter(MTaskProp.ID + i, //
          schedulerRunnerArray[i].getTask().getId());
    }

    query.setParameter(MTaskProp.STATUS + "1", Status.WAIT);
    query.setParameter(MTaskProp.STATUS + "2", Status.RTRY);

    query.setParameter(MTaskProp.SCHED_ID, schedId);

    // ----------------------------------------
    // set queue length
    // ----------------------------------------

    query.setMaxResults(SchedulerConfig.getQueueMaxLength());

    return query;
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public synchronized void reloadTaskList() {
    if (schedulerProxyMap.isEmpty()) {
      return;
    }

    Session session = CledaConnector.getInstance().getSession(hibeCfg);
    session.beginTransaction();

    // ----------------------------------------
    // adds tasks to inqueue
    // ----------------------------------------

    inqueueTaskList.clear();

    Iterator<MTask> itt = initTaskListQuery(session).iterate();

    while (itt.hasNext()) {
      MTask task = itt.next();

      SchedulerRunner schedulerRunner = //
      new SchedulerRunner(hibeCfg, task);

      schedulerRunner.setState(State.QUEU);
      inqueueTaskList.add(schedulerRunner);
    }

    // ----------------------------------------

    if (!inqueueTaskList.isEmpty()) {
      log.debug(SchedulerQueueI18N.reloadTasks( //
          FormatDate.formatTaskDate( //
              inqueueTaskList.get(0).getTask().getNextDate())));
    } else {
      log.debug(SchedulerQueueI18N.reloadEmpty());
    }

    session.getTransaction().commit();
    session.close();

    // ----------------------------------------
    // Notify the cycle
    // ----------------------------------------

    ThreadUtils.notify(schedulerCycle);
  }

  // --------------------------------------------------------------------------------

  protected synchronized void addToInqueueTaskList(SchedulerRunner schedulerRunner) {

    log.debug(SchedulerQueueI18N.addToInqueueTaskList( //
        schedulerRunner.getTaskIdAsString(), //
        FormatDate.formatTaskDate(schedulerRunner.getTask().getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    // ----------------------------------------

    int maxLength = SchedulerConfig.getQueueMaxLength();

    // ----------------------------------------
    // check the task to avoid duplicated
    // ----------------------------------------

    if (log.isDebugEnabled() && inqueueTaskList.contains(schedulerRunner)) {
      RuntimeException e = new RuntimeException( //
          SchedulerQueueI18N.inqueueContains( //
              schedulerRunner.getTaskIdAsString()));
      log.debug(e.getMessage(), e);
      throw e;
    }

    if (log.isDebugEnabled() && runningTaskList.contains(schedulerRunner)) {
      RuntimeException e = new RuntimeException( //
          SchedulerQueueI18N.runningContains( //
              schedulerRunner.getTaskIdAsString()));
      log.debug(e.getMessage(), e);
      throw e;
    }

    // ----------------------------------------
    // finds the position for the task
    // ----------------------------------------

    Calendar newCal = schedulerRunner.getTask().getNextDate();

    int index = 0;

    // ----------------------------------------

    Iterator<SchedulerRunner> itt = inqueueTaskList.iterator();

    for (index = 0; itt.hasNext(); index++) {
      Calendar curCal = itt.next().getTask().getNextDate();

      if (index > maxLength || //
          newCal.before(curCal)) {
        break;
      }
    }

    // ----------------------------------------
    // adds the task (but never at the end)
    // ----------------------------------------

    if (inqueueTaskList.size() == 0 || //
        index < inqueueTaskList.size()) {

      inqueueTaskList.add(index, schedulerRunner);

      log.debug(SchedulerQueueI18N.addTaskToInqueue( //
          schedulerRunner.getTaskIdAsString(), //
          FormatDate.formatTaskDate(schedulerRunner.getTask().getProgDate()), //
          index));
    } else {
      log.debug(SchedulerQueueI18N.skpTaskToInqueue( //
          schedulerRunner.getTaskIdAsString(), //
          FormatDate.formatTaskDate(schedulerRunner.getTask().getProgDate()), //
          index));
    }

    // ----------------------------------------
    // trims the list to queue max length
    // ----------------------------------------

    while (maxLength < inqueueTaskList.size()) {
      schedulerRunner = inqueueTaskList.get(inqueueTaskList.size() - 1);

      log.debug(SchedulerQueueI18N.trimTaskAtEnd( //
          schedulerRunner.getTaskIdAsString(), //
          FormatDate.formatTaskDate(schedulerRunner.getTask().getProgDate())));

      inqueueTaskList.remove(inqueueTaskList.size() - 1);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void addToRunningTaskList(SchedulerRunner schedulerRunner) {

    log.debug(SchedulerQueueI18N.addToRunningTaskList( //
        schedulerRunner.getTaskIdAsString(), //
        FormatDate.formatTaskDate(schedulerRunner.getTask().getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    runningTaskList.add(schedulerRunner);
  }

  // --------------------------------------------------------------------------------

  protected synchronized void removeFromInqueue( //
      SchedulerRunner schedulerRunner) {

    log.debug(SchedulerQueueI18N.removeFromInqueue( //
        schedulerRunner.getTaskIdAsString()));

    if (!inqueueTaskList.remove(schedulerRunner)) {
      RuntimeException e = new RuntimeException( //
          SchedulerQueueI18N.inqueueRemove( //
              schedulerRunner.getTaskIdAsString()));
      log.debug(e.getMessage(), e);
      throw e;
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void removeFromRunning( //
      SchedulerRunner schedulerRunner) {

    log.debug(SchedulerQueueI18N.removeFromRunning( //
        schedulerRunner.getTaskIdAsString()));

    if (!runningTaskList.remove(schedulerRunner)) {
      RuntimeException e = new RuntimeException( //
          SchedulerQueueI18N.runningRemove( //
              schedulerRunner.getTaskIdAsString()));
      log.debug(e.getMessage(), e);
      throw e;
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void fromInqueueToRunning( //
      SchedulerRunner schedulerRunner) {

    removeFromInqueue(schedulerRunner);
    addToRunningTaskList(schedulerRunner);

    if (inqueueTaskList.isEmpty()) {
      reloadTaskList();
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void fromRunningToInqueue( //
      SchedulerRunner schedulerRunner) {

    removeFromRunning(schedulerRunner);
    addToInqueueTaskList(schedulerRunner);
  }

  // --------------------------------------------------------------------------------

  protected synchronized SchedulerRunner getSchedulerRunner( //
      MTask task, List<SchedulerRunner> schedulerRunnerList) {

    for (SchedulerRunner schedulerRunner : schedulerRunnerList) {
      if (task.equals(schedulerRunner.getTask())) {
        return schedulerRunner;
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  public synchronized SchedulerRunner getSchedulerRunner(MTask task) {

    SchedulerRunner fromInqueue = getSchedulerRunner(task, inqueueTaskList);
    SchedulerRunner fromRunning = getSchedulerRunner(task, runningTaskList);

    if (fromInqueue != null && fromRunning != null) {
      RuntimeException e = new RuntimeException( //
          SchedulerQueueI18N.foundInBothLists( //
              task.getTaskIdAsString()));
      log.debug(e.getMessage(), e);
      throw e;
    }

    // ----------------------------------------
    // found
    // ----------------------------------------

    SchedulerRunner ret = fromInqueue != null ? fromInqueue : fromRunning;

    if (ret != null) {
      return ret;
    }

    // ----------------------------------------
    // not found
    // ----------------------------------------

    ret = new SchedulerRunner(null, task);
    ret.setState(State.INDB);

    addToRunningTaskList(ret);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected synchronized SchedulerRunner getExecuteTask() {
    SchedulerRunner schedulerRunner = getNextTask();

    if (schedulerRunner == null) {
      log.debug(SchedulerQueueI18N.nullTaskForExec());
      return null;
    }

    log.debug(SchedulerQueueI18N.execTask( //
        schedulerRunner.getTaskIdAsString(), //
        FormatDate.formatTaskDate(schedulerRunner.getTask().getNextDate()), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    // ----------------------------------------
    // recheck date
    // ----------------------------------------

    if (Calendar.getInstance().before( //
        schedulerRunner.getTask().getNextDate())) {
      return null;
    }

    updateState(schedulerRunner, Event.RUNN);

    SchedulerProxy schedulerProxy = schedulerProxyMap.get( //
        schedulerRunner.getTask().getProxyId());

    schedulerRunner.init(this, schedulerProxy, schedulerCycle);

    // ----------------------------------------

    log.debug(SchedulerQueueI18N.executeTask( //
        schedulerRunner.getTaskIdAsString(), //
        FormatDate.formatTaskDate(Calendar.getInstance())));

    return schedulerRunner;
  }

  // --------------------------------------------------------------------------------
  // The state machine
  // --------------------------------------------------------------------------------

  protected synchronized void crashByDefault( //
      SchedulerRunner schedulerRunner, Event evt) {

    RuntimeException e = new RuntimeException( //
        SchedulerQueueI18N.crashByDefaultEvent( //
            schedulerRunner.getTaskIdAsString(), evt));
    log.debug(e.getMessage(), e);
    throw e;
  }

  // --------------------------------------------------------------------------------

  protected synchronized void crashByDefault( //
      SchedulerRunner schedulerRunner) {

    RuntimeException e = new RuntimeException( //
        SchedulerQueueI18N.crashByDefaultState( //
            schedulerRunner.getTaskIdAsString()));
    log.debug(e.getMessage(), e);
    throw e;
  }

  // --------------------------------------------------------------------------------

  public synchronized void updateState( //
      SchedulerRunner schedulerRunner, Event evt) {

    log.debug(SchedulerQueueI18N.begUpdateTask( //
        schedulerRunner.getTaskIdAsString(), //
        schedulerRunner.getState(), //
        evt, //
        inqueueTaskList.contains(schedulerRunner), //
        runningTaskList.contains(schedulerRunner)));

    switch (evt) {
      case RUNN :
        updateStateRUNN(schedulerRunner);
        break;
      case FAIL :
        updateStateFAIL(schedulerRunner);
        break;
      case DONE :
        updateStateDONE(schedulerRunner);
        break;
      case DLCK :
        updateStateDLCK(schedulerRunner);
        break;
      case NLCK :
        updateStateNLCK(schedulerRunner);
        break;
      case GONE :
        updateStateGONE(schedulerRunner);
        break;
      default :
        crashByDefault(schedulerRunner, evt);
    }

    log.debug(SchedulerQueueI18N.endUpdateTask( //
        schedulerRunner.getTaskIdAsString(), //
        schedulerRunner.getState(), //
        evt, //
        inqueueTaskList.contains(schedulerRunner), //
        runningTaskList.contains(schedulerRunner)));

    ThreadUtils.notify(schedulerRunner);
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateRUNN(SchedulerRunner schedulerRunner) {

    switch (schedulerRunner.getState()) {
      case QUEU :
        fromInqueueToRunning(schedulerRunner);
        schedulerRunner.setState(State.RUNN);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateFAIL(SchedulerRunner schedulerRunner) {
    switch (schedulerRunner.getState()) {
      case RUNN :
        fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.QUEU);
        break;
      case DLCK :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case NLCK :
        removeFromRunning(schedulerRunner);
        schedulerRunner.setState(State.DONE);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateDONE(SchedulerRunner schedulerRunner) {
    switch (schedulerRunner.getState()) {
      case RUNN :
        removeFromRunning(schedulerRunner);
        schedulerRunner.setState(State.DONE);
        break;
      case DLCK :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case NLCK :
        removeFromRunning(schedulerRunner);
        schedulerRunner.setState(State.DONE);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateDLCK(SchedulerRunner schedulerRunner) {
    switch (schedulerRunner.getState()) {
      case QUEU :
        fromInqueueToRunning(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case INDB :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case RUNN :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.DLCK);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateNLCK(SchedulerRunner schedulerRunner) {
    switch (schedulerRunner.getState()) {
      case QUEU :
        fromInqueueToRunning(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case INDB :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.DELE);
        break;
      case RUNN :
        // fromRunningToInqueue(schedulerRunner);
        schedulerRunner.setState(State.NLCK);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }

  // --------------------------------------------------------------------------------

  protected synchronized void updateStateGONE(SchedulerRunner schedulerRunner) {
    switch (schedulerRunner.getState()) {
      case DELE :
        removeFromRunning(schedulerRunner);
        schedulerRunner.setState(State.DONE);
        break;
      default :
        crashByDefault(schedulerRunner);
    }
  }
}
