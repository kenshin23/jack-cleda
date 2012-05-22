/*
 * Created on 26/06/2008
 */
package com.minotauro.echo.grid;

/**
 * @author Demián Gutierrez
 */
public interface FieldStateProxy {

  public boolean isFieldEnabled(String key) throws FieldStateProxyException;

  public boolean isFieldVisible(String key) throws FieldStateProxyException;
}
