// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class SchedulerFacadeI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.SchedulerFacade";

  private SchedulerFacadeI18N() {
    // Empty
  }

  public static String addSuspTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "addSuspTask", new Object[]{arg0, arg1, arg2});
  }

  public static String addWaitTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "addWaitTask", new Object[]{arg0, arg1, arg2});
  }

  public static String delTaskBeg(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "delTaskBeg", new Object[]{arg0});
  }

  public static String delTaskEnd(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "delTaskEnd", new Object[]{arg0, arg1, arg2});
  }

  public static String stopCycle(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "stopCycle", new Object[]{arg0});
  }

  public static String stopQueue(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "stopQueue", new Object[]{arg0});
  }

  public static String stopState(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "stopState", new Object[]{arg0});
  }

  public static String taskNotFound(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "taskNotFound", new Object[]{arg0});
  }

  public static String waitForTask(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "waitForTask", new Object[]{arg0, arg1});
  }

}
