/*
 * Created on 14/06/2007
 */
package com.minotauro.echo.wrapper.impl;

import nextapp.echo.app.SelectField;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

/**
 * @author Daniel Lobo
 */
public class SelectFieldWrapper implements ComponentWrapper {

  public SelectFieldWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    SelectField selectField = (SelectField) component;

    // XXX: Check with a label/value bean
    Object object = selectField.getSelectedItem();

    // ----------------------------------------
    // XXX: Don't know if the check has sense
    // ----------------------------------------

    if (object == null) {
      return "";
    }

    return object;
  }

  public void setValue(Object component, Object value) {
    SelectField selectField = (SelectField) component;

    // ----------------------------------------
    // XXX: Don't know if the check has sense
    // ----------------------------------------

    if (value == null) {
      value = "";
    }

    selectField.setSelectedItem(value);
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    SelectField selectField = (SelectField) component;

    return selectField.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    SelectField selectField = (SelectField) component;
    selectField.setEnabled(editable);
  }

  // --------------------------------------------------------------------------------
  // Dummy
  // --------------------------------------------------------------------------------

  @Override
  public boolean getVisible(Object component) {
    return true;
  }

  @Override
  public void setVisible(Object component, boolean visible) {
    // Empty
  }
}
