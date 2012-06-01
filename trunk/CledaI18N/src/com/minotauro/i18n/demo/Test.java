package com.minotauro.i18n.demo;

import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.minotauro.i18n.util.ReflectionI18N;

public class Test {

  public static void run() throws Exception {
    System.err.println(_PropFooBean.NAME);
    System.err.println(_PropFooBean.DESC);
    System.err.println(_PropFooBean.EMPTY);

    System.err.println(_PropFooBean.CAMEL_CASE);

    // ----------------------------------------
    // should not have been generated
    // System.err.println(_PropFooBean.NO_GETTER);
    // System.err.println(_PropFooBean.NO_SETTER);
    // ----------------------------------------

    System.err.println("****************************************");

    System.err.println(_I18NTestI18NFoo.hello("1", "2"));
    System.err.println(ReflectionI18N.i18n( //
        _I18NTestI18NFoo.class.getName(), "hello", new Object[]{"1", "2"}));

    System.err.println(_I18NTestI18NFoo.world("1"));
    System.err.println(ReflectionI18N.i18n( //
        _I18NTestI18NFoo.class.getName(), "world", new Object[]{"1"}));

    System.err.println(_I18NTestI18NFoo.xxx());
    System.err.println(ReflectionI18N.i18n( //
        _I18NTestI18NFoo.class.getName(), "xxx"));

    System.err.println("****************************************");

    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(10));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(100));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(1000));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(10000));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(100000));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(1000000));

    System.err.println("****************************************");

    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(10.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(100.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(1000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(10000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(100000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault2NumberFormat(1000000.11111));

    System.err.println("****************************************");

    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(10));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(100));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(1000));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(10000));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(100000));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(1000000));

    System.err.println("****************************************");

    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(10.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(100.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(1000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(10000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(100000.11111));
    System.err.println(_NumbTestNumbFoo.formatDefault4NumberFormat(1000000.11111));

    System.err.println("****************************************");

    // ----------------------------------------
    // XXX: Shoud test parsing better
    // ----------------------------------------

    System.err.println(_NumbTestNumbFoo.parseDefault4NumberFormat("1,000,000.10"));

    System.err.println("****************************************");

    Calendar calendar = Calendar.getInstance();

    System.err.println(_DateTestDateFoo.formatDateOnly(calendar));
    System.err.println(_DateTestDateFoo.formatDateLong(calendar));
    System.err.println(_DateTestDateFoo.formatDateTime(calendar));
    System.err.println(_DateTestDateFoo.formatTimeOnly(calendar));

    System.err.println(_DateTestDateFoo.formatMonthYear(calendar));
    System.err.println(_DateTestDateFoo.formatMonthOnly(calendar));

    System.err.println("****************************************");

    // ----------------------------------------
    // XXX: Shoud test parsing better
    // ----------------------------------------

    String strDate;

    if (StringUtils.equals("es", Locale.getDefault().getLanguage())) {
      strDate = "21-10-2007";
    } else {
      strDate = "21/10/2007";
    }

    Calendar calDate = _DateTestDateFoo.parseDateOnly(strDate);
    System.err.println(_DateTestDateFoo.formatDateOnly(calDate) + " -> " + strDate);
  }

  // --------------------------------------------------------------------------------

  public static void main(String[] args) throws Exception {
    System.err.println("---------------------------------------- es_VE");
    Locale.setDefault(new Locale("es", "VE"));
    run();

    System.err.println("---------------------------------------- en_US");
    Locale.setDefault(new Locale("en", "US"));
    run();
  }
}
