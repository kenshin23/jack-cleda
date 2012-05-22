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
public class NotEmptyValidator extends BaseValidator {

  protected String blank;

  // ----------------------------------------

  public NotEmptyValidator(String complabel, Object component) {
    super(complabel, component);
  }

  // ----------------------------------------

  public NotEmptyValidator(String complabel, Object component, //
      String blank) {

    super(complabel, component);

    this.blank = blank;
  }

  // ----------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    if (StringUtils.isBlank((String) value) || //
        (blank != null && StringUtils.equals(blank, value.toString()))) {
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
