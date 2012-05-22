/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.wrapper.impl;

import nextapp.echo.app.TextArea;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

/**
 * @author Demián Gutierrez
 */
public class TextAreaWrapper implements ComponentWrapper {

  public TextAreaWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    TextArea textArea = (TextArea) component;

    return textArea.getText();
  }

  public void setValue(Object component, Object value) {
    TextArea textArea = (TextArea) component;

    if (value == null) {
      value = "";
    }

    textArea.setText(value.toString());
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    TextArea textArea = (TextArea) component;

    return textArea.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    TextArea textArea = (TextArea) component;
    textArea.setEnabled(editable);
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
