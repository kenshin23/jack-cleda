// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class WorkflowSchedulerEngineImplI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.WorkflowSchedulerEngineImpl";

  private WorkflowSchedulerEngineImplI18N() {
    // Empty
  }

  public static String begFire(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "begFire", new Object[]{arg0, arg1, arg2});
  }

  public static String endFire(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "endFire", new Object[]{arg0, arg1, arg2});
  }

  public static String wrkTransSetNotFound(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "wrkTransSetNotFound", new Object[]{arg0});
  }

}
