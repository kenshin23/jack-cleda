/*
 * Created on 07/07/2008
 */
package com.minotauro.echo.grid;

import nextapp.echo.app.Insets;
import nextapp.echo.app.Label;
import nextapp.echo.app.Row;

import com.minotauro.echo.util.ELookAndFeel;

/**
 * @author Demián Gutierrez
 */
public class ESectionLabel extends Row {

  public ESectionLabel(String text) {
    setBackground(ELookAndFeel.DOC_SECTION);
    setInsets(new Insets(3, 6, 3, 3));

    Label lblSection = new Label(text);
    add(lblSection);
  }
}
