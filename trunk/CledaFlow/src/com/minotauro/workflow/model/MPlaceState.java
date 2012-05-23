/*
 * Created on 12/02/2006
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
@Table(name = "t_wrk_place_state")
@Proxy(lazy = false)
public class MPlaceState extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int tokens;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPlace netPlaceRef;

  private MNetStateSet netStateSetRef;

  // ----------------------------------------

  public MPlaceState() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public int getTokens() {
    return tokens;
  }

  public void setTokens(int tokens) {
    this.tokens = tokens;
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
  public MNetStateSet getNetStateSetRef() {
    return netStateSetRef;
  }

  public void setNetStateSetRef(MNetStateSet netStateSetRef) {
    this.netStateSetRef = netStateSetRef;
  }
}