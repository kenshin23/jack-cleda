/*
 * Created on 07/09/2011
 */
package com.minotauro.echo.login;

import java.util.List;

import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public interface UserProxy {

  public List<MUser> getUserList();

  public MUser loadUser(String user, String pass);
}
