/*
 * Created on 21/08/2006
 */
package com.minotauro.workflow.exception;

/**
 * @author Demián Gutierrez
 */
public class WorkflowException extends Exception {

  public WorkflowException() {
    // Empty
  }

  public WorkflowException(String message) {
    super(message);
  }

  public WorkflowException(String message, Throwable cause) {
    super(message, cause);
  }

  public WorkflowException(Throwable cause) {
    super(cause);
  }
}