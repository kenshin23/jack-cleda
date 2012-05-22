/*
 * Created on 04/09/2008
 */
package com.minotauro.echo.grid;

import nextapp.echo.app.Button;
import nextapp.echo.app.Insets;

/**
 * @author Demián Gutierrez
 */
public class ETabLabel extends Button {

  public ETabLabel(String text) {
    super(text);

    setInsets(new Insets(2, 2, 2, 2));
  }
}
