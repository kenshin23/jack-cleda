/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.impl;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.validator.base.BaseValidator;

/**
 * @author E.G JACK™
 */
public class LowerThanValidator extends BaseValidator {
	int val;

	public LowerThanValidator(String complabel, Object component, int num) {
		super(complabel, component);
		val = num;
	}

	// ----------------------------------------

	@Override
	public String doValidate(Object value, ProcessContext processContext) {

		// Al descomentar sirve como validador dual de integer con Greater
		try {
			Integer.parseInt(value.toString());
		} catch (NumberFormatException e) {
			return _I18NBaseValidator.integerValidator(getComplabel());
		}
		// ----------------------------

		if (Integer.parseInt(value.toString()) > val)
			return _I18NBaseValidator.LowerThanValidator(getComplabel());

		return null;
	}
}
