/*
 * Created on 07/12/2007
 */
package com.minotauro.cleda.task.core;

/**
 * @author Demián Gutierrez
 */
public class SchedulerException extends Exception {

  public SchedulerException() {
    // Empty
  }

  public SchedulerException(String message) {
    super(message);
  }

  public SchedulerException(Throwable cause) {
    super(cause);
  }

  public SchedulerException(String message, Throwable cause) {
    super(message, cause);
  }
}
