// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class WorkflowFactoryI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.WorkflowFactory";

  private WorkflowFactoryI18N() {
    // Empty
  }

  public static String initFacade(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "initFacade", new Object[]{arg0, arg1, arg2});
  }

  public static String initRepeat(Object arg0, Object arg1, Object arg2) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "initRepeat", new Object[]{arg0, arg1, arg2});
  }

}
