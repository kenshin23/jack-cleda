// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.i18n.bundled;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _NumbFormatNumb {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.i18n.bundled.FormatNumb";

  private _NumbFormatNumb() {
    // Empty
  }

  public static DecimalFormat getNumberFormatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "number");
  }

  public static String formatNumber(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "number", val);
  }

  public static double parseNumber(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "number", val);
  }

  public static DecimalFormat getNumber2Formatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "number2");
  }

  public static String formatNumber2(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "number2", val);
  }

  public static double parseNumber2(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "number2", val);
  }

  public static DecimalFormat getNumber4Formatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "number4");
  }

  public static String formatNumber4(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "number4", val);
  }

  public static double parseNumber4(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "number4", val);
  }

}
