// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.i18n.demo;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _NumbTestNumbFoo {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.i18n.demo.TestNumbFoo";

  private _NumbTestNumbFoo() {
    // Empty
  }

  public static DecimalFormat getDefault2NumberFormatFormatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "default2NumberFormat");
  }

  public static String formatDefault2NumberFormat(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "default2NumberFormat", val);
  }

  public static double parseDefault2NumberFormat(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "default2NumberFormat", val);
  }

  public static DecimalFormat getDefault4NumberFormatFormatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "default4NumberFormat");
  }

  public static String formatDefault4NumberFormat(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "default4NumberFormat", val);
  }

  public static double parseDefault4NumberFormat(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "default4NumberFormat", val);
  }

  public static DecimalFormat getDefaultNumberFormatFormatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "defaultNumberFormat");
  }

  public static String formatDefaultNumberFormat(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "defaultNumberFormat", val);
  }

  public static double parseDefaultNumberFormat(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "defaultNumberFormat", val);
  }

}
