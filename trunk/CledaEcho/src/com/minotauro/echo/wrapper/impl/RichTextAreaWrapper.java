/*
 * Created on 14/06/2007
 */
package com.minotauro.echo.wrapper.impl;

import nextapp.echo.extras.app.RichTextArea;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

/**
 * @author Demián Gutierrez
 */
public class RichTextAreaWrapper implements ComponentWrapper {

  public RichTextAreaWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected String trim(String src) {

    // ----------------------------------------
    // Replace all <br> and trim the string
    // ----------------------------------------

    String str = src.replaceAll("<(\\s)*[Bb][Rr](\\s)*/(\\s)*>", "").trim();

    return str.isEmpty() ? str : src;
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    RichTextArea textArea = (RichTextArea) component;

    return trim(textArea.getText());
  }

  public void setValue(Object component, Object value) {
    RichTextArea textArea = (RichTextArea) component;

    if (value == null) {
      value = "";
    }

    textArea.setText(value.toString());
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    RichTextArea textArea = (RichTextArea) component;

    return textArea.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    RichTextArea textArea = (RichTextArea) component;
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
