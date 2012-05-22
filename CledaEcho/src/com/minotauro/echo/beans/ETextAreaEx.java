package com.minotauro.echo.beans;

import nextapp.echo.app.Color;
import nextapp.echo.app.TextArea;
import nextapp.echo.app.text.Document;
import nextapp.echo.app.text.StringDocument;

public class ETextAreaEx extends TextArea {

  protected static final Color DISABLED_BACKGROUND = new Color(238, 235, 231);
  protected static final Color DISABLED_FOREGROUND = new Color(117, 117, 117);

  public ETextAreaEx() {
    super(new StringDocument());
    style();
  }

  public ETextAreaEx(Document document) {
    super(document);
    style();
  }

  public ETextAreaEx(Document document, //
      String text, int columns, int rows) {
    super(document, text, columns, rows);
    style();
  }

  public ETextAreaEx(String text) {
    setText(text);
    style();
  }

  protected void style() {
    setDisabledBackground(DISABLED_BACKGROUND);
    setDisabledForeground(DISABLED_FOREGROUND);
  }
}
