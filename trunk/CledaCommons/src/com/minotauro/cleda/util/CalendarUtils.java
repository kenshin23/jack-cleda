/*
 * Created on 25/01/2007
 */
package com.minotauro.cleda.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

/** 
 * @author Alejandro Salas 
 */
public class CalendarUtils {

  private CalendarUtils() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static Calendar calendarFromString(String string, DateFormat df) {
    Calendar ret = null;

    if (string != null) {
      try {
        ret = Calendar.getInstance();
        ret.setTime(df.parse(string));
      } catch (ParseException e) {
        ret = null;
      }
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static String stringFromCalendar(Calendar calendar, DateFormat df) {
    if (calendar != null) {
      return df.format(calendar.getTime());
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  public static Calendar getDateOnlyCalendar(Calendar cal) {
    Calendar ret;

    if (cal == null) {
      ret = Calendar.getInstance();
    } else {
      ret = (Calendar) cal.clone();
    }

    ret.set(Calendar.HOUR_OF_DAY,/**/0);
    ret.set(Calendar.MINUTE,/*     */0);
    ret.set(Calendar.SECOND,/*     */0);
    ret.set(Calendar.MILLISECOND,/**/0);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static Calendar getDateOnlyCalendar() {
    return getDateOnlyCalendar(null);
  }

  // --------------------------------------------------------------------------------

  public static Calendar getHourMinuteOnlyCalendar(Calendar cal) {
    Calendar ret;

    if (cal == null) {
      ret = Calendar.getInstance();
      cal = (Calendar) ret.clone();
    } else {
      ret = (Calendar) cal.clone();
    }

    ret.setTimeInMillis(0);

    ret.set(Calendar.HOUR_OF_DAY,/**/cal.get(Calendar.HOUR_OF_DAY));
    ret.set(Calendar.MINUTE,/*     */cal.get(Calendar.MINUTE));

    return ret;
  }

  // ----------------------------------------

  public static Calendar getHourMinuteOnlyCalendar() {
    return getHourMinuteOnlyCalendar(null);
  }

  // ----------------------------------------

  public static Calendar getCalendarPlusDelta(Calendar cal, int field, int delta) {
    Calendar ret = (Calendar) cal.clone();
    ret.add(field, delta);
    return ret;
  }
}
