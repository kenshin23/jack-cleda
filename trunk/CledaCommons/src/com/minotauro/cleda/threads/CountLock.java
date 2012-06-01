/*
 * Created on 12/03/2008
 */
package com.minotauro.cleda.threads;

/**
 * @author Demián Gutierrez
 */
public class CountLock {

  protected int cur;
  protected int min;
  protected int max;

  // --------------------------------------------------------------------------------

  public CountLock(int min, int max) {
    this.cur = min;
    this.min = min;
    this.max = max;
  }

  // --------------------------------------------------------------------------------

  public synchronized void dolock() {
    while (cur == max) {
      ThreadUtils.dowait(this, 10000);
    }

    cur++;

    if (cur > max) {
      throw new RuntimeException("cur > max");
    }
  }

  // --------------------------------------------------------------------------------

  public synchronized void unlock() {
    cur--;

    if (cur < min) {
      throw new RuntimeException("cur < min");
    }

    ThreadUtils.notify(this);
  }
}
