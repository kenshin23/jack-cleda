/*
 * Created on 09/02/2008
 */
package com.minotauro.cleda.threads;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Demián Gutierrez
 */
public class ThreadUtils {

  public static final int CURR_METHOD = 1;
  public static final int PREV_METHOD = 2;

  // --------------------------------------------------------------------------------

  public static Logger log = LoggerFactory.getLogger(ThreadUtils.class);

  // --------------------------------------------------------------------------------

  private ThreadUtils() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static String getCallerStackTrace(int level) {
    Exception e = new Exception();
    StackTraceElement[] stackTrace = e.getStackTrace();

    if (stackTrace.length > level) {
      return stackTrace[level].toString();
    }

    return "no caller stack trace";
  }

  // --------------------------------------------------------------------------------

  public static String getCallerStackTrace() {
    return getCallerStackTrace(PREV_METHOD);
  }

  // --------------------------------------------------------------------------------

  private static void failsafeDowait(Object obj, long timeout, int level) //
      throws InterruptedException {

    log.debug("BEG, dowait: " + getCallerStackTrace(level) + //
        "(" + obj + ") - (" + timeout + ")");

    synchronized (obj) {
      obj.wait(timeout);
    }

    log.debug("END, dowait: " + getCallerStackTrace(level) + //
        "(" + obj + ") - (" + timeout + ")");
  }

  // --------------------------------------------------------------------------------

  public static void dowait(Object obj, long timeout, int level) {
    try {
      failsafeDowait(obj, timeout, level);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void dowait(Object obj, long timeout) {
    try {
      failsafeDowait(obj, timeout, 3);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void dowait(Object obj) {
    try {
      failsafeDowait(obj, 0,/*   */3);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void notify(Object obj) {

    log.debug("notify: " + getCallerStackTrace(3) + //
        "(" + obj + ")");

    synchronized (obj) {
      obj.notify();
    }
  }

  // --------------------------------------------------------------------------------

  public static void sleep(long timeout) {
    try {
      Thread.sleep(timeout);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static String threadToString(Thread thread) {
    return "( " + thread + " - 0x" + StringUtils.leftPad( //
        Integer.toHexString(thread.hashCode()), 8, '0') + " )";
  }

  // --------------------------------------------------------------------------------

  public static String threadToString() {
    return threadToString(Thread.currentThread());
  }
}
