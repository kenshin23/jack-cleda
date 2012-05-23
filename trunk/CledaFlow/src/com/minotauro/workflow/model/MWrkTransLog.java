/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_wrk_trans_log")
@Proxy(lazy = false)
public class MWrkTransLog extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private Calendar begDate;

  private Calendar endDate;

  private String notice;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MUser userRef;

  private MWrkTransSet wrkTransSetRef;

  private MWrkTrans wrkTransRef;

  // ----------------------------------------

  public MWrkTransLog() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public Calendar getBegDate() {
    return begDate;
  }

  public void setBegDate(Calendar begDate) {
    this.begDate = begDate;
  }

  // ----------------------------------------

  public Calendar getEndDate() {
    return endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  // ----------------------------------------

  public String getNotice() {
    return notice;
  }

  public void setNotice(String notice) {
    this.notice = notice;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MUser getUserRef() {
    return userRef;
  }

  public void setUserRef(MUser userRef) {
    this.userRef = userRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MWrkTransSet getWrkTransSetRef() {
    return wrkTransSetRef;
  }

  public void setWrkTransSetRef(MWrkTransSet wrkTransSetRef) {
    this.wrkTransSetRef = wrkTransSetRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MWrkTrans getWrkTransRef() {
    return wrkTransRef;
  }

  public void setWrkTransRef(MWrkTrans wrkTransRef) {
    this.wrkTransRef = wrkTransRef;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  @Transient
  public boolean isAuto() {
    MNetTransSet netTransSet =
        getWrkTransSetRef().getNetTransSetRef();

    return netTransSet.getNetTransSetRoleRef() == null;
  }
}
