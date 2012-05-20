// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.base.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NBaseState {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.base.i18n.BaseState";

  private _I18NBaseState() {
    // Empty
  }

  public static String busy() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "busy", new Object[]{});
  }

  public static String disabled() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "disabled", new Object[]{});
  }

  public static String enabled() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "enabled", new Object[]{});
  }

  public static String free() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "free", new Object[]{});
  }

}
