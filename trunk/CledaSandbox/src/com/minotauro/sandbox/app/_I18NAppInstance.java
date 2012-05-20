// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.sandbox.app;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NAppInstance {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.sandbox.app.AppInstance";

  private _I18NAppInstance() {
    // Empty
  }

  public static String admin() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "admin", new Object[]{});
  }

  public static String exit() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "exit", new Object[]{});
  }

  public static String windowTitle() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "windowTitle", new Object[]{});
  }

}
