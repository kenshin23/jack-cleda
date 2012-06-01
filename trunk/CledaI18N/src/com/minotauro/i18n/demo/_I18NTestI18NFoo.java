// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.i18n.demo;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NTestI18NFoo {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.i18n.demo.TestI18NFoo";

  private _I18NTestI18NFoo() {
    // Empty
  }

  public static String hello(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "hello", new Object[]{arg0, arg1});
  }

  public static String world(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "world", new Object[]{arg0});
  }

  public static String xxx() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "xxx", new Object[]{});
  }

}
