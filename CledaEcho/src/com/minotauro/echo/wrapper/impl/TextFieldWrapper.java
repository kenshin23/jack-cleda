/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.wrapper.impl;

import nextapp.echo.app.TextField;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

/**
 * @author Demián Gutierrez
 */
public class TextFieldWrapper implements ComponentWrapper {

  public TextFieldWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    TextField textField = (TextField) component;

    return textField.getText();
  }

  public void setValue(Object component, Object value) {
    TextField textField = (TextField) component;

    if (value == null) {
      value = "";
    }

    textField.setText(value.toString());
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    TextField textField = (TextField) component;

    return textField.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    TextField textField = (TextField) component;
    textField.setEnabled(editable);
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
