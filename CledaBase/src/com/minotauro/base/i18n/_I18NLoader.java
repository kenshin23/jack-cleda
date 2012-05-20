// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.base.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NLoader {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.base.i18n.Loader";

  private _I18NLoader() {
    // Empty
  }

  public static String alreadyDefined(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "alreadyDefined", new Object[]{arg0, arg1});
  }

  public static String duplicatedField(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "duplicatedField", new Object[]{arg0, arg1});
  }

  public static String invalidState(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "invalidState", new Object[]{arg0});
  }

  public static String invalidType(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "invalidType", new Object[]{arg0});
  }

  public static String notFound(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "notFound", new Object[]{arg0, arg1});
  }

}
