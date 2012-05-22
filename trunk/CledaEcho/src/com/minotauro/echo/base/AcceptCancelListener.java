/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.base;

import java.util.EventListener;

import nextapp.echo.app.event.ActionEvent;

/**
 * @author Demián Gutierrez
 */
public interface AcceptCancelListener extends EventListener {

  public void btnAcceptClicked(ActionEvent evt);

  public void btnCancelClicked(ActionEvent evt);
}
