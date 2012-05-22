// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.list.dta;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NHandlerDelAll {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.list.dta.HandlerDelAll";

  private _I18NHandlerDelAll() {
    // Empty
  }

  public static String confirmDeleteAll() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "confirmDeleteAll", new Object[]{});
  }

  public static String delNotAllowedAll() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "delNotAllowedAll", new Object[]{});
  }

  public static String selectAtLeastOne() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "selectAtLeastOne", new Object[]{});
  }

}
