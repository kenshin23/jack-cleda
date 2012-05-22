/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

import java.util.List;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.FieldValidProxy;
import com.minotauro.echo.validator.base.ValidatorList;

/**
 * @author Demián Gutierrez
 */
public class FormValidator {

  protected FieldValidProxy fieldValidProxy;

  protected FormModel formModel;

  // --------------------------------------------------------------------------------

  public FormValidator() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public FieldValidProxy getFieldValidProxy() {
    return fieldValidProxy;
  }

  public void setFieldValidProxy(FieldValidProxy fieldValidProxy) {
    this.fieldValidProxy = fieldValidProxy;
  }

  // --------------------------------------------------------------------------------

  public FormModel getFormModel() {
    return formModel;
  }

  public void setFormModel(FormModel formModel) {
    this.formModel = formModel;
  }

  // --------------------------------------------------------------------------------

  public ValidatorList getValidatorList() {
    return formModel.initValidatorList();
  }

  // --------------------------------------------------------------------------------

  public List<String> validateAll(
      String command, ProcessContext processContext) {

    ValidatorList validatorList = getValidatorList();
    validatorList.setFieldValidProxy(fieldValidProxy);

    return validatorList.validateAll(command, processContext);
  }
}
