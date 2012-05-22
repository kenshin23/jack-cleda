/*
 * Created on 07/01/2008
 */
package com.minotauro.echo.validator.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Daniel Lobo
 */
public class RegexValidator extends BaseValidator {

  private String regex;
  private String msg;

  public static final String VALIDATE_NAMES_AND_NOT_EMPTY = "^[A-Z]+(\\s[A-Z]+)*$";
  public static final String NOT_NUMBER = "^[A-Z]*(\\s[A-Z]+)*$";
  public static final String ONLY_NUMBER = "^\\d*$";

  // --------------------------------------------------------------------------------

  public RegexValidator(String complabel, Object component, String regex) {
    super(complabel, component);
    this.regex = regex;
  }

  public RegexValidator(Object component, String regex, String msg) {
    super("", component);
    this.regex = regex;
    this.msg = msg;
  }

  // --------------------------------------------------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(value.toString());

    if (matcher.matches()) {
      return null;
    }

    return StringUtils.isBlank(msg) ? _I18NBaseValidator.regexValidator(getComplabel()) : msg;
  }
}
