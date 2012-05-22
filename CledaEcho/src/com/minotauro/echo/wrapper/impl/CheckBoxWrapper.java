/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.wrapper.impl;

import nextapp.echo.app.CheckBox;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

/**
 * @author Demián Gutierrez
 */
public class CheckBoxWrapper implements ComponentWrapper {

  public CheckBoxWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    CheckBox checkBox = (CheckBox) component;

    return checkBox.isSelected();
  }

  public void setValue(Object component, Object value) {
    CheckBox checkBox = (CheckBox) component;
    Boolean val = (Boolean) value;

    if (val == null) {
      val = Boolean.FALSE;
    }

    checkBox.setSelected(val.booleanValue());
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    CheckBox checkBox = (CheckBox) component;

    return checkBox.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    CheckBox checkBox = (CheckBox) component;
    checkBox.setEnabled(editable);
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
