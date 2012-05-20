// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.sandbox.gui.user;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NMenu {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.sandbox.gui.user.Menu";

  private _I18NMenu() {
    // Empty
  }

  public static String admin() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "admin", new Object[]{});
  }

  public static String exit() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "exit", new Object[]{});
  }

  public static String prof() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "prof", new Object[]{});
  }

  public static String user() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "user", new Object[]{});
  }

}
