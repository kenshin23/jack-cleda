package com.minotauro.echo.beans;

import java.util.EventListener;

import nextapp.echo.app.event.ActionEvent;

public interface OkListener extends EventListener {

  public void btnOkClicked(ActionEvent event);
}