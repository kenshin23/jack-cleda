/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Demián Gutierrez
 */
public class IntegerValidator extends BaseValidator {

  public IntegerValidator(String complabel, Object component) {
    super(complabel, component);
  }

  // ----------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    try {
      Integer.parseInt(value.toString());
    } catch (NumberFormatException e) {
      return _I18NBaseValidator.integerValidator(getComplabel());
    }

    return null;
  }
}
