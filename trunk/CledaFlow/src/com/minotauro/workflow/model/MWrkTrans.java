/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.workflow.prop.MWrkTransLogProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_wrk_trans")
@Proxy(lazy = false)
public class MWrkTrans extends MBase {

  public static final int STATUS_DISABLED = 0;
  public static final int STATUS_ENABLED = 1;

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int status;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetTrans netTransRef;

  private MWrkTransSet wrkTransSetRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MWrkTransLog> wrkTransLogList = new ArrayList<MWrkTransLog>();

  // ----------------------------------------

  public MWrkTrans() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetTrans getNetTransRef() {
    return netTransRef;
  }

  public void setNetTransRef(MNetTrans netTransRef) {
    this.netTransRef = netTransRef;
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
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MWrkTransLogProp.WRK_TRANS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MWrkTransLog> getWrkTransLogList() {
    return wrkTransLogList;
  }

  public void setWrkTransLogList(List<MWrkTransLog> wrkTransLogList) {
    this.wrkTransLogList = wrkTransLogList;
  }
}