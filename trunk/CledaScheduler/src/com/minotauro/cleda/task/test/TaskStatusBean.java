/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.task.test;

import com.minotauro.cleda.task.model.MTask;

/**
 * @author Demián Gutierrez
 */
public class TaskStatusBean {

  public enum TaskStatus {
    INIT, FAIL, DONE;
  }

  // ----------------------------------------

  private TaskStatus taskStatus = TaskStatus.INIT;

  private boolean neverDone;

  private boolean failed;
  private boolean frozen;
  private boolean dolock;

  private int frozCount;
  private int execCount;
  private int currCount;

  private int relatedId;

  private MTask task;

  // ----------------------------------------

  public TaskStatusBean() {
    // Empty
  }

  // ----------------------------------------

  public TaskStatus getTaskStatus() {
    return taskStatus;
  }

  public void setTaskStatus(TaskStatus taskStatus) {
    this.taskStatus = taskStatus;
  }

  // ----------------------------------------

  public boolean isNeverDone() {
    return neverDone;
  }

  public void setNeverDone(boolean neverDone) {
    this.neverDone = neverDone;
  }

  // ----------------------------------------

  public boolean isFailed() {
    return failed;
  }

  public void setFailed(boolean failed) {
    this.failed = failed;
  }

  // ----------------------------------------

  public boolean isFrozen() {
    return frozen;
  }

  public void setFrozen(boolean frozen) {
    this.frozen = frozen;
  }

  // ----------------------------------------

  public boolean isDolock() {
    return dolock;
  }

  public void setDolock(boolean dolock) {
    this.dolock = dolock;
  }

  // ----------------------------------------

  public int getFrozCount() {
    return frozCount;
  }

  public void setFrozCount(int frozCount) {
    this.frozCount = frozCount;
  }

  // ----------------------------------------

  public int getExecCount() {
    return execCount;
  }

  public void setExecCount(int execCount) {
    this.execCount = execCount;
  }

  // ----------------------------------------

  public int getCurrCount() {
    return currCount;
  }

  public void setCurrCount(int currCount) {
    this.currCount = currCount;
  }

  // ----------------------------------------

  public int getRelatedId() {
    return relatedId;
  }

  public void setRelatedId(int relatedId) {
    this.relatedId = relatedId;
  }

  // ----------------------------------------

  public MTask getTask() {
    return task;
  }

  public void setTask(MTask task) {
    this.task = task;
  }
}
