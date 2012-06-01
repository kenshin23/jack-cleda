/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import java.util.Locale;

/**
 * @author Demián Gutierrez
 */
public interface MessageFactory {

  public String locateValue(Locale defaultLocale, String res, String key) //
      throws MessageException;

  public Locale getLocale() //
      throws MessageException;
}
