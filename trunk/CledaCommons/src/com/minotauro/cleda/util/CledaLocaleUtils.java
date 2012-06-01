/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.util;

import java.util.Locale;

/**
 * @author Demián Gutierrez
 */
public class CledaLocaleUtils {

  private CledaLocaleUtils() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static Locale createLocale( //
      String language, String country, String variant) {

    if (language != null && country != null && variant != null) {
      return new Locale(language, country, variant);
    }

    if (language != null && country != null) {
      return new Locale(language);
    }

    if (language != null) {
      return new Locale(language);
    }

    throw new IllegalArgumentException();
  }
}
