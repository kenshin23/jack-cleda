// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class WorkflowSchedulerFacadeImplI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.WorkflowSchedulerFacadeImpl";

  private WorkflowSchedulerFacadeImplI18N() {
    // Empty
  }

  public static String agentPrevNull(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "agentPrevNull", new Object[]{arg0, arg1});
  }

  public static String posAddSusp(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "posAddSusp", new Object[]{arg0, arg1, arg2});
  }

  public static String posAddWait(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "posAddWait", new Object[]{arg0, arg1, arg2});
  }

  public static String posDelTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "posDelTask", new Object[]{arg0, arg1, arg2});
  }

  public static String preAddSusp(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "preAddSusp", new Object[]{arg0, arg1, arg2});
  }

  public static String preAddWait(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "preAddWait", new Object[]{arg0, arg1, arg2});
  }

  public static String preDelTask(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "preDelTask", new Object[]{arg0, arg1, arg2});
  }

  public static String taskNull(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "taskNull", new Object[]{arg0, arg1});
  }

}
