// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.filter.base;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmFilterEditor {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.filter.base.FrmFilterEditor";

  private _I18NFrmFilterEditor() {
    // Empty
  }

  public static String add() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "add", new Object[]{});
  }

  public static String remove() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "remove", new Object[]{});
  }

  public static String resetFilter() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "resetFilter", new Object[]{});
  }

  public static String search() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "search", new Object[]{});
  }

  public static String searchAll() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "searchAll", new Object[]{});
  }

  public static String windowTitle() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "windowTitle", new Object[]{});
  }

}
