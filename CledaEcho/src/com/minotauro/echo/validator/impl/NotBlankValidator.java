/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import org.apache.commons.lang.StringUtils;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Demián Gutierrez
 */
public class NotBlankValidator extends BaseValidator {

  protected String blank;

  // --------------------------------------------------------------------------------
  // TODO: Check if it can be done easily with not empty validator
  // --------------------------------------------------------------------------------

  public NotBlankValidator(String complabel, Object component, String blank) {
    super(complabel, component);
    this.blank = blank;
  }

  // ----------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    if (value == null || StringUtils.isBlank(value.toString())
        || (blank != null && StringUtils.equals(blank, value.toString()))) {
      return _I18NBaseValidator.notEmptyValidator(getComplabel());
    }

    return null;
  }

  // ----------------------------------------

  public String getBlank() {
    return blank;
  }

  public void setBlank(String blank) {
    this.blank = blank;
  }
}
