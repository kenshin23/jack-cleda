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
@Table(name = "t_wrk_wrk_place")
@Proxy(lazy = false)
public class MWrkPlace extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int tokens;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPlace netPlaceRef;

  private MWorkflow workflowRef;

  // ----------------------------------------

  public MWrkPlace() {
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
  public MWorkflow getWorkflowRef() {
    return workflowRef;
  }

  public void setWorkflowRef(MWorkflow workflowRef) {
    this.workflowRef = workflowRef;
  }
}