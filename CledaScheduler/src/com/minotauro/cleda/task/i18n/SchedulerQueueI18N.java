// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class SchedulerQueueI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.SchedulerQueue";

  private SchedulerQueueI18N() {
    // Empty
  }

  public static String addTaskToInqueue(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "addTaskToInqueue", new Object[]{arg0, arg1, arg2});
  }

  public static String addToInqueueTaskList(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "addToInqueueTaskList",
        new Object[]{arg0, arg1, arg2});
  }

  public static String addToRunningTaskList(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "addToRunningTaskList",
        new Object[]{arg0, arg1, arg2});
  }

  public static String begUpdateTask(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
      throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "begUpdateTask",
        new Object[]{arg0, arg1, arg2, arg3, arg4});
  }

  public static String crashByDefaultEvent(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "crashByDefaultEvent", new Object[]{arg0, arg1});
  }

  public static String crashByDefaultState(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "crashByDefaultState", new Object[]{arg0});
  }

  public static String endUpdateTask(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4)
      throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "endUpdateTask",
        new Object[]{arg0, arg1, arg2, arg3, arg4});
  }

  public static String execTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "execTask", new Object[]{arg0, arg1, arg2});
  }

  public static String executeTask(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "executeTask", new Object[]{arg0, arg1});
  }

  public static String foundInBothLists(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "foundInBothLists", new Object[]{arg0});
  }

  public static String givingThreadToTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance()
        .locateValue(locale, RES_NAME, "givingThreadToTask", new Object[]{arg0, arg1, arg2});
  }

  public static String inqueueContains(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "inqueueContains", new Object[]{arg0});
  }

  public static String inqueueRemove(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "inqueueRemove", new Object[]{arg0});
  }

  public static String nullTaskForExec() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "nullTaskForExec", new Object[]{});
  }

  public static String reloadEmpty() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "reloadEmpty", new Object[]{});
  }

  public static String reloadTasks(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "reloadTasks", new Object[]{arg0});
  }

  public static String removeFromInqueue(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "removeFromInqueue", new Object[]{arg0});
  }

  public static String removeFromRunning(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "removeFromRunning", new Object[]{arg0});
  }

  public static String runningContains(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "runningContains", new Object[]{arg0});
  }

  public static String runningRemove(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "runningRemove", new Object[]{arg0});
  }

  public static String skpTaskToInqueue(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "skpTaskToInqueue", new Object[]{arg0, arg1, arg2});
  }

  public static String taskListEmpty(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "taskListEmpty", new Object[]{arg0, arg1});
  }

  public static String taskListQuery(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "taskListQuery", new Object[]{arg0});
  }

  public static String threadName(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "threadName", new Object[]{arg0});
  }

  public static String trimTaskAtEnd(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "trimTaskAtEnd", new Object[]{arg0, arg1});
  }

}
