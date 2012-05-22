// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.util.gui;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NDlgValidation {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.util.gui.DlgValidation";

  private _I18NDlgValidation() {
    // Empty
  }

  public static String accept() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "accept", new Object[]{});
  }

  public static String title() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "title", new Object[]{});
  }

}
