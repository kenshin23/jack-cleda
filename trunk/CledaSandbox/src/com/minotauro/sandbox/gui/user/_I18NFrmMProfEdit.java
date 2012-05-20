// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.sandbox.gui.user;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmMProfEdit {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.sandbox.gui.user.FrmMProfEdit";

  private _I18NFrmMProfEdit() {
    // Empty
  }

  public static String description() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "description", new Object[]{});
  }

  public static String name() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "name", new Object[]{});
  }

  public static String nameAlreadyExists() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "nameAlreadyExists", new Object[]{});
  }

  public static String priv() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "priv", new Object[]{});
  }

  public static String role() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "role", new Object[]{});
  }

  public static String title(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{arg0});
  }

}
