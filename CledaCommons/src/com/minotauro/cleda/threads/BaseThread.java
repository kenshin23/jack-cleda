/*
 * Created on 20/02/2008
 */
package com.minotauro.cleda.threads;

/**
 * @author Demián Gutierrez
 */
public abstract class BaseThread implements Runnable {

  protected Boolean blocked = new Boolean(true);

  protected boolean running;

  protected String name;

  // --------------------------------------------------------------------------------

  public BaseThread() {
    // Empty
  }

  public BaseThread(String name) {
    this.name = name;
  }

  // --------------------------------------------------------------------------------

  public void start() {
    if (running) {
      throw new IllegalStateException();
    }

    Thread thread;

    if (name != null) {
      thread = new Thread(this, name);
    } else {
      thread = new Thread(this);
    }

    running = true;

    thread.start();
  }

  // --------------------------------------------------------------------------------

  public void stop() {
    if (!running) {
      return;
    }

    synchronized (blocked) {
      running = false;

      ThreadUtils.notify(this);
      ThreadUtils.dowait(blocked);
    }
  }

  // --------------------------------------------------------------------------------

  protected void checkNotRunning() //
      throws NotRunningException {

    // ----------------------------------------
    // throw Exception if not running
    // ----------------------------------------

    synchronized (blocked) {
      if (!running) {
        throw new NotRunningException();
      }
    }
  }

  // --------------------------------------------------------------------------------

  public void run() {
    try {
      while (running) {
        failsafeRun();
      }
    } catch (Exception e) {
      if (!(e instanceof NotRunningException)) {
        throw new RuntimeException(e);
      }
    }

    ThreadUtils.notify(blocked);
  }

  // --------------------------------------------------------------------------------

  protected abstract void failsafeRun() //
      throws InterruptedException, NotRunningException;
}
