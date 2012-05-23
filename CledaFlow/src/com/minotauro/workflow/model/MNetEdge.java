/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_edge")
@Proxy(lazy = false)
public class MNetEdge extends MBase {

  public static final int DIRECTION_PLACE_TO_TRANS = 0;
  public static final int DIRECTION_TRANS_TO_PLACE = 1;

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int/*    */direction;
  private boolean/**/inhibitor;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPlace netPlaceRef;

  private MNetTrans netTransRef;

  // ----------------------------------------

  public MNetEdge() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  // ----------------------------------------

  public boolean isInhibitor() {
    return inhibitor;
  }

  public void setInhibitor(boolean inhibitor) {
    this.inhibitor = inhibitor;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetPlace getNetPlaceRef() {
    return netPlaceRef;
  }

  public void setNetPlaceRef(MNetPlace netPlaceRef) {
    this.netPlaceRef = netPlaceRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetTrans getNetTransRef() {
    return netTransRef;
  }

  public void setNetTransRef(MNetTrans netTransRef) {
    this.netTransRef = netTransRef;
  }
}
