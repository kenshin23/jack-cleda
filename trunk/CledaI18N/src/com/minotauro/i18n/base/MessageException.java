/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

/**
 * @author Demián Gutierrez
 */
public class MessageException extends RuntimeException {

  public MessageException() {
    // Empty
  }

  public MessageException(String message) {
    super(message);
  }

  public MessageException(Throwable cause) {
    super(cause);
  }

  public MessageException(String message, Throwable cause) {
    super(message, cause);
  }
}
