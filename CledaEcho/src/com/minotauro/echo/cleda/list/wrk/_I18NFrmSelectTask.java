// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.list.wrk;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmSelectTask {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.list.wrk.FrmSelectTask";

  private _I18NFrmSelectTask() {
    // Empty
  }

  public static String select() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "select", new Object[]{});
  }

  public static String task() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "task", new Object[]{});
  }

  public static String title() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{});
  }

}
