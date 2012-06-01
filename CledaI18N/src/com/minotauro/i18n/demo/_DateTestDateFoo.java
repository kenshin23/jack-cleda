// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.i18n.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _DateTestDateFoo {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.i18n.demo.TestDateFoo";

  private _DateTestDateFoo() {
    // Empty
  }

  public static SimpleDateFormat getDateLongFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "dateLong");
  }

  public static String formatDateLong(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "dateLong", val);
  }

  public static Calendar parseDateLong(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "dateLong", val);
  }

  public static SimpleDateFormat getDateOnlyFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "dateOnly");
  }

  public static String formatDateOnly(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "dateOnly", val);
  }

  public static Calendar parseDateOnly(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "dateOnly", val);
  }

  public static SimpleDateFormat getDateTimeFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "dateTime");
  }

  public static String formatDateTime(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "dateTime", val);
  }

  public static Calendar parseDateTime(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "dateTime", val);
  }

  public static SimpleDateFormat getMonthOnlyFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "monthOnly");
  }

  public static String formatMonthOnly(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "monthOnly", val);
  }

  public static Calendar parseMonthOnly(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "monthOnly", val);
  }

  public static SimpleDateFormat getMonthYearFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "monthYear");
  }

  public static String formatMonthYear(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "monthYear", val);
  }

  public static Calendar parseMonthYear(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "monthYear", val);
  }

  public static SimpleDateFormat getTimeOnlyFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "timeOnly");
  }

  public static String formatTimeOnly(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "timeOnly", val);
  }

  public static Calendar parseTimeOnly(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "timeOnly", val);
  }

}
