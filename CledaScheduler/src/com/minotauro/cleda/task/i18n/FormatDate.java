// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.cleda.task.i18n;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class FormatDate {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.cleda.task.i18n.FormatDate";

  private FormatDate() {
    // Empty
  }

  public static SimpleDateFormat getTaskDateFormatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "taskDate");
  }

  public static String formatTaskDate(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "taskDate", val);
  }

  public static Calendar parseTaskDate(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "taskDate", val);
  }

}
