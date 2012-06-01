/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Demián Gutierrez
 */
public class MessageBase {

  private static MessageBase instance;

  // --------------------------------------------------------------------------------

  public static MessageBase getInstance() {
    if (instance == null) {
      instance = new MessageBase();
    }

    return instance;
  }

  // --------------------------------------------------------------------------------

  private MessageFactory messageFactory;

  // --------------------------------------------------------------------------------

  protected MessageBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  private void initMessageFactory() throws MessageException {
    try {
      failsafeInitMessageFactory();
    } catch (Exception e) {
      throw new MessageException(e);
    }
  }

  // --------------------------------------------------------------------------------

  private void failsafeInitMessageFactory() throws Exception {

    if (messageFactory != null) {
      return;
    }

    // ----------------------------------------
    // Inits the message factory class
    // ----------------------------------------

    String className = MessageConfig.getMessageFactory();

    if (className == null) {
      throw new MessageException( //
          "className == null : message.properties -> message.factory");
    }

    Class<?> clazz = Class.forName(className);

    if (!MessageFactory.class.isAssignableFrom(clazz)) {
      throw new ClassCastException( //
          "MessageFactory.class.isAssignableFrom(clazz) : message.properties");
    }

    messageFactory = (MessageFactory) clazz.newInstance();
  }

  // --------------------------------------------------------------------------------

  private String getResource(Locale defaultLocale, String res, String key) //
      throws MessageException {

    initMessageFactory();

    String ret = messageFactory.locateValue(defaultLocale, res, key);

    if (ret == null) {
      throw new MessageException("ret == null : " + key);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public Locale getLocale() //
      throws MessageException {

    return messageFactory.getLocale();
  }

  // --------------------------------------------------------------------------------

  public String locateValue(Locale locale, String res, String key, Object[] arg) //
      throws MessageException {
    return MessageFormat.format(getResource(locale, res, key), arg);
  }

  // --------------------------------------------------------------------------------

  public DecimalFormat getNumberFormatter(Locale locale, String res, String key) {
    return new DecimalFormat(getResource(locale, res, key));
  }

  public String formatNumberValue(Locale locale, String res, String key, double val) //
      throws MessageException {

    return getNumberFormatter(locale, res, key).format(val);
  }

  public double parseNumberValue(Locale locale, String res, String key, String val) //
      throws MessageException, ParseException {

    return getNumberFormatter(locale, res, key).parse(val).doubleValue();
  }

  // --------------------------------------------------------------------------------

  public SimpleDateFormat getDateFormatter(Locale locale, String res, String key) {
    return new SimpleDateFormat(getResource(locale, res, key));
  }

  public String formatCalendarValue(Locale locale, String res, String key, Calendar val) //
      throws MessageException {

    return getDateFormatter(locale, res, key).format(val.getTime());
  }

  public Calendar parseCalendarValue(Locale locale, String res, String key, String val) //
      throws MessageException, ParseException {

    Calendar cal = Calendar.getInstance();
    cal.setTime(getDateFormatter(locale, res, key).parse(val));

    return cal;
  }
}
