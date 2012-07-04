/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author Demián Gutierrez
 */
public class LowerThanValidator extends BaseValidator {

  public LowerThanValidator(String complabel, Object component) {
    super(complabel, component);
  }

  // ----------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {
	  
	  int key = 5;

// Al descomentar sirve como validador dual de integer con Greater
    try {
    	Integer.parseInt(value.toString());
    } catch (NumberFormatException e) {
     		return _I18NBaseValidator.integerValidator(getComplabel());
    }
//----------------------------

    if(Integer.parseInt(value.toString()) > key)
    	return _I18NBaseValidator.LowerThanValidator(getComplabel());
    	
    return null;
  }
}
