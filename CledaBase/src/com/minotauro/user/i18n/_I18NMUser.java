// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.user.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NMUser {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.user.i18n.MUser";

  private _I18NMUser() {
    // Empty
  }

  public static String country() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "country", new Object[]{});
  }

  public static String duplicatePassword() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "duplicatePassword", new Object[]{});
  }

  public static String language() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "language", new Object[]{});
  }

  public static String locale() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "locale", new Object[]{});
  }

  public static String login() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "login", new Object[]{});
  }

  public static String password() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "password", new Object[]{});
  }

  public static String profiles() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "profiles", new Object[]{});
  }

  public static String variant() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "variant", new Object[]{});
  }

}
