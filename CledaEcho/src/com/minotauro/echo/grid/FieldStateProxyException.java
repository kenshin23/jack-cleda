/*
 * Created on 04/09/2008
 */
package com.minotauro.echo.grid;

/**
 * @author Demián Gutierrez
 */
public class FieldStateProxyException extends RuntimeException {

  public FieldStateProxyException() {
    // Empty
  }

  public FieldStateProxyException(String message) {
    super(message);
  }

  public FieldStateProxyException(Throwable cause) {
    super(cause);
  }

  public FieldStateProxyException(String message, Throwable cause) {
    super(message, cause);
  }
}
