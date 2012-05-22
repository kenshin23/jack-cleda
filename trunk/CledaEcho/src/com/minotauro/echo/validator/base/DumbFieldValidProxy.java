/*
 * Created on 07/08/2008
 */
package com.minotauro.echo.validator.base;

/**
 * @author Demián Gutierrez
 */
public class DumbFieldValidProxy implements FieldValidProxy {

  public static final DumbFieldValidProxy DUMB_FIELD_VALID_PROXY = //
  new DumbFieldValidProxy();

  public DumbFieldValidProxy() {
    // Empty
  }

  @Override
  public boolean isFieldValidable(String key, String command) {
    return true;
  }
}
