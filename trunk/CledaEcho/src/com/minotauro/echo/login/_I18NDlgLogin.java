// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.login;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NDlgLogin {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.login.DlgLogin";

  private _I18NDlgLogin() {
    // Empty
  }

  public static String error() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "error", new Object[]{});
  }

  public static String pass() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "pass", new Object[]{});
  }

  public static String select() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "select", new Object[]{});
  }

  public static String send() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "send", new Object[]{});
  }

  public static String title() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{});
  }

  public static String user() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "user", new Object[]{});
  }

}
