// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class SchedulerCycleI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.SchedulerCycle";

  private SchedulerCycleI18N() {
    // Empty
  }

  public static String execTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "execTask", new Object[]{arg0, arg1, arg2});
  }

  public static String threadName() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "threadName", new Object[]{});
  }

  public static String waitingForTask() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "waitingForTask", new Object[]{});
  }

  public static String waitingForTime(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "waitingForTime", new Object[]{arg0});
  }

}
