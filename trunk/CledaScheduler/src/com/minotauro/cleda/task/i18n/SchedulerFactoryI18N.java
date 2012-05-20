// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class SchedulerFactoryI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.SchedulerFactory";

  private SchedulerFactoryI18N() {
    // Empty
  }

  public static String initFacade(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "initFacade", new Object[]{arg0, arg1});
  }

  public static String initRepeat(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "initRepeat", new Object[]{arg0, arg1});
  }

  public static String shutdownAll() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "shutdownAll", new Object[]{});
  }

}
