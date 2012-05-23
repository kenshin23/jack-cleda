// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class WorkflowLockI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.WorkflowLock";

  private WorkflowLockI18N() {
    // Empty
  }

  public static String lockBeanNullForId(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "lockBeanNullForId", new Object[]{arg0});
  }

  public static String posDoLock(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "posDoLock", new Object[]{arg0, arg1});
  }

  public static String posUnLock(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "posUnLock", new Object[]{arg0, arg1});
  }

  public static String preDoLock(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "preDoLock", new Object[]{arg0, arg1});
  }

  public static String preUnLock(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "preUnLock", new Object[]{arg0, arg1});
  }

}
