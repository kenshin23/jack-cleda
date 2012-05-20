// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.sandbox.gui.duserreg;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmDUserRegEdit {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.sandbox.gui.duserreg.FrmDUserRegEdit";

  private _I18NFrmDUserRegEdit() {
    // Empty
  }

  public static String accept() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "accept", new Object[]{});
  }

  public static String deny() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "deny", new Object[]{});
  }

  public static String title(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{arg0});
  }

  public static String user() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "user", new Object[]{});
  }

}
