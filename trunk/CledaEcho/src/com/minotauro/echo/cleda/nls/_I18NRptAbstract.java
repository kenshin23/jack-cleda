// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.nls;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NRptAbstract {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.nls.RptAbstract";

  private _I18NRptAbstract() {
    // Empty
  }

  public static String date() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "date", new Object[]{});
  }

  public static String page() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "page", new Object[]{});
  }

  public static String userLogin() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "userLogin", new Object[]{});
  }

}
