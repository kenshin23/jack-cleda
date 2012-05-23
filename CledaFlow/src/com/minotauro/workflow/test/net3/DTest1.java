package com.minotauro.workflow.test.net3;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.workflow.model.MDocument;

@Entity
@Table(name = "t_doc_test_1")
@Proxy(lazy = false)
public class DTest1 extends MDocument {

  protected int curCount;
  protected int lftCount;
  protected int rghCount;
  protected int totCount;

  // ----------------------------------------

  public DTest1() {
    // Empty
  }

  // ----------------------------------------

  public int getCurCount() {
    return curCount;
  }

  public void setCurCount(int curCount) {
    this.curCount = curCount;
  }

  // ----------------------------------------

  public int getLftCount() {
    return lftCount;
  }

  public void setLftCount(int lftCount) {
    this.lftCount = lftCount;
  }

  // ----------------------------------------

  public int getRghCount() {
    return rghCount;
  }

  public void setRghCount(int rghCount) {
    this.rghCount = rghCount;
  }

  // ----------------------------------------

  public int getTotCount() {
    return totCount;
  }

  public void setTotCount(int totCount) {
    this.totCount = totCount;
  }
}
