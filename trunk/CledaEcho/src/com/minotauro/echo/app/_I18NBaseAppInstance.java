// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.app;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NBaseAppInstance {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.app.BaseAppInstance";

  private _I18NBaseAppInstance() {
    // Empty
  }

  public static String notImplementedYet() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "notImplementedYet", new Object[]{});
  }

}
