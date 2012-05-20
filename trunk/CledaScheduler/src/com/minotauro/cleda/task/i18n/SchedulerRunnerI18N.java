// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class SchedulerRunnerI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.SchedulerRunner";

  private SchedulerRunnerI18N() {
    // Empty
  }

  public static String executeTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "executeTask", new Object[]{arg0, arg1, arg2});
  }

  public static String proxyNotFound(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "proxyNotFound", new Object[]{arg0, arg1});
  }

  public static String updateFailed(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "updateFailed", new Object[]{arg0});
  }

}
