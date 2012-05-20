// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.user.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NMPriv {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.user.i18n.MPriv";

  private _I18NMPriv() {
    // Empty
  }

  public static String description() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "description", new Object[]{});
  }

  public static String name() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "name", new Object[]{});
  }

}
