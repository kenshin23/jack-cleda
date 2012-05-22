// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.list.dta;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NHandlerDelOne {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.list.dta.HandlerDelOne";

  private _I18NHandlerDelOne() {
    // Empty
  }

  public static String confirmDeleteOne() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "confirmDeleteOne", new Object[]{});
  }

  public static String delNotAllowedOne() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "delNotAllowedOne", new Object[]{});
  }

}
