/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.engine;

import com.minotauro.workflow.model.MNetEdge;

/**
 * @author Demián Gutierrez
 */
public class EEngEdge {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private MNetEdge netEdge;

  private Object srcObject;
  private Object tgtObject;

  // --------------------------------------------------------------------------------

  public EEngEdge() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public MNetEdge getNetEdge() {
    return netEdge;
  }

  public void setNetEdge(MNetEdge netEdge) {
    this.netEdge = netEdge;
  }

  // --------------------------------------------------------------------------------

  public Object getSrcObject() {
    return srcObject;
  }

  public void setSrcObject(Object srcObject) {
    this.srcObject = srcObject;
  }

  // --------------------------------------------------------------------------------

  public Object getTgtObject() {
    return tgtObject;
  }

  public void setTgtObject(Object tgtObject) {
    this.tgtObject = tgtObject;
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  public EEngPlace getEngPlace() {
    return (EEngPlace) (srcObject instanceof EEngPlace ? srcObject : tgtObject);
  }

  public EEngTrans getEngTrans() {
    return (EEngTrans) (srcObject instanceof EEngTrans ? srcObject : tgtObject);
  }
}
