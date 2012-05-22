/*
 * Created on 24/04/2007
 */
package com.minotauro.echo.login;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class LoginEvent extends ActionEvent {

  protected MUser user;

  // --------------------------------------------------------------------------------

  public LoginEvent(Object source, MUser user) {
    super(source, null);

    this.user = user;
  }

  // --------------------------------------------------------------------------------

  public MUser getUser() {
    return user;
  }
}
