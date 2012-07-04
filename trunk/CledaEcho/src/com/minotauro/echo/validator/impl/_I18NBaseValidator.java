// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.echo.validator.impl;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class _I18NBaseValidator {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.echo.validator.impl.BaseValidator";

  private _I18NBaseValidator() {
    // Empty
  }

  public static String GreaterThanValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "GreaterThanValidator", new Object[]{arg0});
  }

  public static String LowerThanValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "LowerThanValidator", new Object[]{arg0});
  }

  public static String duplicatedField(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "duplicatedField", new Object[]{arg0});
  }

  public static String emailValidator() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "emailValidator", new Object[]{});
  }

  public static String fieldsAlreadyExists(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "fieldsAlreadyExists", new Object[]{arg0});
  }

  public static String idNumberValidator() throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "idNumberValidator", new Object[]{});
  }

  public static String integerValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "integerValidator", new Object[]{arg0});
  }

  public static String notEmptyValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "notEmptyValidator", new Object[]{arg0});
  }

  public static String regexValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "regexValidator", new Object[]{arg0});
  }

  public static String trueValidator(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "trueValidator", new Object[]{arg0});
  }

}
