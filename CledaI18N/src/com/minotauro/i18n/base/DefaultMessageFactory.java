/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * @author Demián Gutierrez
 */
public class DefaultMessageFactory implements MessageFactory {

  public DefaultMessageFactory() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public String locateValue(Locale defaultLocale, String res, String key) //
      throws MessageException {

    // ----------------------------------------
    // Locale from interface (default if null)
    // ----------------------------------------

    Locale locale = getLocale();

    if (locale == null) {
      locale = defaultLocale;
    }

    // ----------------------------------------
    // Bundle for locale (use default if fail)
    // ----------------------------------------

    ResourceBundle bundle;

    try {
      bundle = ResourceBundle.getBundle(res, locale);
    } catch (MissingResourceException e) {
      bundle = ResourceBundle.getBundle(res, defaultLocale);
    }

    return bundle.getString(key);
  }

  // --------------------------------------------------------------------------------

  public Locale getLocale() //
      throws MessageException {

    return Locale.getDefault();
  }
}
