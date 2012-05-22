/*
 * Created on 07/01/2008
 */
package com.minotauro.echo.validator.impl;

/**
 * @author Daniel Lobo
 */
public class EmailValidator extends RegexValidator {

  public static final String REGEX_TO_VALIDATE_EMAIL = "^[A-Z0-9._%+-]+@(?:[A-Z0-9-]+\\.)+[A-Z]{2,4}$";

  public EmailValidator(String fieldLabel, Object component) {
    super(component, REGEX_TO_VALIDATE_EMAIL, _I18NBaseValidator.emailValidator());
  }
}
