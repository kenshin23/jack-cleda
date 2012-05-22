/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.base;

import java.util.ArrayList;
import java.util.List;

import com.minotauro.echo.desktop.ProcessContext;

/**
 * @author Demián Gutierrez
 */
public class ValidatorList extends BaseValidator {

  protected List<BaseValidator> baseValidatorList = //
  new ArrayList<BaseValidator>();

  // --------------------------------------------------------------------------------

  public ValidatorList() {
    super(null, null);
  }

  // --------------------------------------------------------------------------------

  public BaseValidator add(BaseValidator validator) {
    baseValidatorList.add(validator);
    validator.setParent(this);
    return validator;
  }

  // --------------------------------------------------------------------------------

  @Override
  public List<String> validateAll(
      String command, ProcessContext processContext) {

    List<String> msgList = new ArrayList<String>();

    FieldValidProxy fieldValidProxy = getFieldValidProxy();

    if (!fieldValidProxy.isFieldValidable(getKey(), command)) {
      return msgList;
    }

    for (BaseValidator validator : baseValidatorList) {
      msgList.addAll(validator.validateAll(
          command, processContext));
    }

    return msgList;
  }

  // --------------------------------------------------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    throw new UnsupportedOperationException();
  }
}
