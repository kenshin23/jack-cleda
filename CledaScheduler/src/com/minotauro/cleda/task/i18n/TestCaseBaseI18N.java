// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class TestCaseBaseI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.TestCaseBase";

  private TestCaseBaseI18N() {
    // Empty
  }

  public static String begAddingTasks() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "begAddingTasks", new Object[]{});
  }

  public static String checkTaskStatus(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5,
      Object arg6, Object arg7, Object arg8, Object arg9) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "checkTaskStatus",
        new Object[]{arg0, arg1, arg2, arg3, arg4, arg5, arg6, arg7, arg8, arg9});
  }

  public static String endAddingTasks() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "endAddingTasks", new Object[]{});
  }

  public static String errorReport(Object arg0, Object arg1, Object arg2, Object arg3) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "errorReport", new Object[]{arg0, arg1, arg2, arg3});
  }

  public static String execTask(Object arg0, Object arg1, Object arg2, Object arg3, Object arg4, Object arg5)
      throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "execTask",
        new Object[]{arg0, arg1, arg2, arg3, arg4, arg5});
  }

  public static String simulatedFail() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "simulatedFail", new Object[]{});
  }

  public static String timeOut() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "timeOut", new Object[]{});
  }

  public static String totalTestTime(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "totalTestTime", new Object[]{arg0});
  }

}
