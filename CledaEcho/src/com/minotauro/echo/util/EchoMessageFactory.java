/*
 * Created on 10/05/2007
 */
package com.minotauro.echo.util;

import java.util.Locale;

import com.minotauro.i18n.base.DefaultMessageFactory;
import com.minotauro.i18n.base.MessageException;

/**
 * @author Demián Gutierrez
 */
public class EchoMessageFactory extends DefaultMessageFactory {

  public EchoMessageFactory() {
    // Empty
  }

  //  public String locateValue(String res, String key) throws MessageException {
  //    ResourceBundle bundle = ResourceBundle.getBundle(res, getLocale());
  //    return bundle.getString(key);
  //  }

  public Locale getLocale() throws MessageException {
    // TODO: Fix
    //    Locale locale = BaseAppInstance.getActive().getLocale();
    //
    //    if (locale == null) {
    Locale locale;
    locale = Locale.getDefault();
    //    }

    return locale;
  }
}
