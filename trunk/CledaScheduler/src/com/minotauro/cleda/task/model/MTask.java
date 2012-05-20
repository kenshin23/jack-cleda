/*
 * Created on 06/12/2007
 */
package com.minotauro.cleda.task.model;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_sch_task")
@Proxy(lazy = false)
public class MTask extends MBase {

  public enum Status {
    SUSP, WAIT, RTRY, DELE, DONE, FAIL
  }

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected Calendar suspDate;
  protected Calendar progDate;
  protected Calendar nextDate;
  protected Calendar lastDate;

  protected String errorMsg;

  protected String schedId;
  protected String proxyId;

  protected Status status;

  // --------------------------------------------------------------------------------

  protected int retryTries;
  protected int retryTimes;
  protected int retryMilis;

  // --------------------------------------------------------------------------------

  public MTask() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public MTask(String proxyId, Calendar progDate) {
    this.proxyId/* */= proxyId;
    this.progDate/**/= progDate;
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  @Transient
  public Calendar getSuspDate() {
    return suspDate;
  }

  public void setSuspDate(Calendar suspDate) {
    this.suspDate = suspDate;
  }

  // --------------------------------------------------------------------------------

  public long getSuspDateMillis() {
    return suspDate == null ? 0 : suspDate.getTimeInMillis();
  }

  public void setSuspDateMillis(long millis) {
    suspDate = Calendar.getInstance();
    suspDate.setTimeInMillis(millis);
  }

  // --------------------------------------------------------------------------------

  @Transient
  public Calendar getProgDate() {
    return progDate;
  }

  public void setProgDate(Calendar progDate) {
    this.progDate = progDate;
  }

  // --------------------------------------------------------------------------------

  public long getProgDateMillis() {
    return progDate == null ? 0 : progDate.getTimeInMillis();
  }

  public void setProgDateMillis(long millis) {
    progDate = Calendar.getInstance();
    progDate.setTimeInMillis(millis);
  }

  // --------------------------------------------------------------------------------

  @Transient
  public Calendar getNextDate() {
    return nextDate;
  }

  public void setNextDate(Calendar nextDate) {
    this.nextDate = nextDate;
  }

  // --------------------------------------------------------------------------------

  public long getNextDateMillis() {
    return nextDate == null ? 0 : nextDate.getTimeInMillis();
  }

  public void setNextDateMillis(long millis) {
    nextDate = Calendar.getInstance();
    nextDate.setTimeInMillis(millis);
  }

  // --------------------------------------------------------------------------------

  @Transient
  public Calendar getLastDate() {
    return lastDate;
  }

  public void setLastDate(Calendar lastDate) {
    this.lastDate = lastDate;
  }

  // --------------------------------------------------------------------------------

  public long getLastDateMillis() {
    return lastDate == null ? 0 : lastDate.getTimeInMillis();
  }

  public void setLastDateMillis(long millis) {
    lastDate = Calendar.getInstance();
    lastDate.setTimeInMillis(millis);
  }

  // --------------------------------------------------------------------------------

  @Column(length = 5000)
  public String getErrorMsg() {
    return errorMsg;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  // --------------------------------------------------------------------------------

  public String getSchedId() {
    return schedId;
  }

  public void setSchedId(String schedId) {
    this.schedId = schedId;
  }

  // --------------------------------------------------------------------------------

  public String getProxyId() {
    return proxyId;
  }

  public void setProxyId(String proxyId) {
    this.proxyId = proxyId;
  }

  // --------------------------------------------------------------------------------

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  // --------------------------------------------------------------------------------

  public int getRetryTries() {
    return retryTries;
  }

  public void setRetryTries(int retryTries) {
    this.retryTries = retryTries;
  }

  // --------------------------------------------------------------------------------

  public int getRetryTimes() {
    return retryTimes;
  }

  public void setRetryTimes(int retryTimes) {
    this.retryTimes = retryTimes;
  }

  // --------------------------------------------------------------------------------

  public int getRetryMilis() {
    return retryMilis;
  }

  public void setRetryMilis(int retryMilis) {
    this.retryMilis = retryMilis;
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  public boolean updateAndRetry() {
    Calendar currDate = Calendar.getInstance();

    lastDate = (Calendar) currDate.clone();

    retryTries++;

    currDate.add(Calendar.MILLISECOND, retryMilis);
    nextDate = currDate;

    return retry();
  }

  // --------------------------------------------------------------------------------

  public boolean retry() {
    return (status == Status.FAIL || status == Status.RTRY) //
        && retryTries <= retryTimes;
  }

  // --------------------------------------------------------------------------------

  @Transient
  public String getTaskIdAsString() {
    return "ID=" + getId() + " - 0x" + //
        StringUtils.leftPad(Integer.toHexString(superHashCode()), 8, '0');
  }
}
