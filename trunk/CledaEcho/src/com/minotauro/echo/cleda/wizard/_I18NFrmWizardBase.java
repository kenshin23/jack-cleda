// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.cleda.wizard;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NFrmWizardBase {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.cleda.wizard.FrmWizardBase";

  private _I18NFrmWizardBase() {
    // Empty
  }

  public static String fnsh() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "fnsh", new Object[]{});
  }

  public static String next() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "next", new Object[]{});
  }

  public static String prev() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "prev", new Object[]{});
  }

}
