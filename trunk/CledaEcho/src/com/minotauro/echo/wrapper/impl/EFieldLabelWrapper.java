package com.minotauro.echo.wrapper.impl;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.wrapper.base.ComponentWrapper;

public class EFieldLabelWrapper implements ComponentWrapper {

  public EFieldLabelWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    EFieldLabel fieldLabel = (EFieldLabel) component;

    return fieldLabel.getTextNoSuffix();
  }

  public void setValue(Object component, Object value) {
    EFieldLabel fieldLabel = (EFieldLabel) component;

    if (value == null) {
      value = "";
    }

    fieldLabel.setTextNoSuffix(value.toString());
  }

  // --------------------------------------------------------------------------------
  // Dummy
  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    return true;
  }

  public void setEnabled(Object component, boolean editable) {
    // Empty
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
