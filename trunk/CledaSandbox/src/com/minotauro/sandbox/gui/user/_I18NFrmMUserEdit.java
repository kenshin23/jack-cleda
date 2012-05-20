// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.sandbox.gui.user;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmMUserEdit {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.sandbox.gui.user.FrmMUserEdit";

  private _I18NFrmMUserEdit() {
    // Empty
  }

  public static String nameAlreadyExists() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "nameAlreadyExists", new Object[]{});
  }

  public static String prof() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "prof", new Object[]{});
  }

  public static String title(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{arg0});
  }

  public static String user() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "user", new Object[]{});
  }

}
