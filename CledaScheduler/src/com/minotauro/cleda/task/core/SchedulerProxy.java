/*
 * Created on 15/04/2007
 */
package com.minotauro.cleda.task.core;

import com.minotauro.cleda.task.model.MTask;

/**
 * @author Demián Gutierrez
 */
public abstract class SchedulerProxy {

  protected String proxyId;

  // ----------------------------------------

  public SchedulerProxy() {
    // Empty
  }

  // ----------------------------------------

  public String getProxyId() {
    return proxyId;
  }

  public void setProxyId(String proxyId) {
    this.proxyId = proxyId;
  }

  // ----------------------------------------

  public abstract void executeTask(MTask task) //
      throws SchedulerException;
}
