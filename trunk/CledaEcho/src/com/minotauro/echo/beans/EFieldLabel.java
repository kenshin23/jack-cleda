/*
 * Created on 05/02/2007
 */
package com.minotauro.echo.beans;

import nextapp.echo.app.Extent;
import nextapp.echo.app.Font;
import nextapp.echo.app.Label;

/**
 * @author Demián Gutierrez
 */
public class EFieldLabel extends Label {

  protected String suffix = ":";

  protected String textns;

  // --------------------------------------------------------------------------------

  public EFieldLabel() {
    this(null);
  }

  // --------------------------------------------------------------------------------

  public EFieldLabel(String textns) {
    this.textns = textns;

    updateText();
    applyStyle();
  }

  public EFieldLabel(String textns, String suffix) {
    this.textns = textns;
    this.suffix = suffix;

    updateText();
    applyStyle();
  }

  // --------------------------------------------------------------------------------

  private void applyStyle() {
    setFont(new Font(Font.SANS_SERIF, Font.BOLD, new Extent(10, Extent.PT)));
  }

  // --------------------------------------------------------------------------------

  private void updateText() {
    super.setText(textns + (suffix != null ? suffix : ""));
  }

  // --------------------------------------------------------------------------------

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;

    updateText();
  }

  // --------------------------------------------------------------------------------

  public String getTextNoSuffix() {
    return textns;
  }

  public void setTextNoSuffix(String textns) {
    this.textns = textns;
  }
}
