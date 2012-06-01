/*
 * Created on 20/02/2008
 */
package com.minotauro.cleda.threads;

/**
 * @author Demián Gutierrez
 */
public class NotRunningException extends Exception {

  public NotRunningException() {
    // Empty
  }

  public NotRunningException(String message) {
    super(message);
  }

  public NotRunningException(Throwable cause) {
    super(cause);
  }

  public NotRunningException(String message, Throwable cause) {
    super(message, cause);
  }
}
