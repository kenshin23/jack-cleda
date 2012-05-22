/*
 * Created on 07/01/2008
 */
package com.minotauro.echo.validator.impl;

/**
 * @author Daniel Lobo
 */
public class IdNumberValidator extends RegexValidator {

  public static final String REGEX_TO_VALIDATE_ID_NUMBER = "^\\d+$";

  public IdNumberValidator(String fieldLabel, Object component) {
    super(component, REGEX_TO_VALIDATE_ID_NUMBER, _I18NBaseValidator.idNumberValidator());
  }
}
