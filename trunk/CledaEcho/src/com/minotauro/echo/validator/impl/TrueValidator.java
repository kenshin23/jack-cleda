/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Demián Gutierrez
 */
public class TrueValidator extends BaseValidator {

  public TrueValidator(String complabel, Object component) {
    super(complabel, component);
  }

  // ----------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    Boolean bool = (Boolean) value;

    if (!bool.booleanValue()) {
      return _I18NBaseValidator.trueValidator(getComplabel());
    }

    return null;
  }
}
