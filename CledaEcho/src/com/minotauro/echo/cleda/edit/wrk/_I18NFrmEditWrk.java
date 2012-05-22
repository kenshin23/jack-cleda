// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.edit.wrk;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmEditWrk {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.edit.wrk.FrmEditWrk";

  private _I18NFrmEditWrk() {
    // Empty
  }

  public static String action() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "action", new Object[]{});
  }

  public static String actionNotEmptyValidator() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "actionNotEmptyValidator", new Object[]{});
  }

  public static String history() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "history", new Object[]{});
  }

  public static String select() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "select", new Object[]{});
  }

}
