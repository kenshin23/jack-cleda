/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.engine;

import java.util.ArrayList;
import java.util.List;

import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MWrkPlace;

/**
 * @author Demián Gutierrez
 */
public class EEngPlace {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected MNetPlace netPlace;
  protected MWrkPlace wrkPlace;

  protected List<EEngEdge> engEdgeSrcList = new ArrayList<EEngEdge>(); // This is src
  protected List<EEngEdge> engEdgeTgtList = new ArrayList<EEngEdge>(); // This is tgt

  // ----------------------------------------

  public EEngPlace() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public MNetPlace getNetPlace() {
    return netPlace;
  }

  public void setNetPlace(MNetPlace netPlace) {
    this.netPlace = netPlace;
  }

  // --------------------------------------------------------------------------------

  public MWrkPlace getWrkPlace() {
    return wrkPlace;
  }

  public void setWrkPlace(MWrkPlace wrkPlace) {
    this.wrkPlace = wrkPlace;
  }

  // --------------------------------------------------------------------------------

  public List<EEngEdge> getEngEdgeSrcList() {
    return engEdgeSrcList;
  }

  public void setEngEdgeSrcList(List<EEngEdge> engEdgeSrcList) {
    this.engEdgeSrcList = engEdgeSrcList;
  }

  // --------------------------------------------------------------------------------

  public List<EEngEdge> getEngEdgeTgtList() {
    return engEdgeTgtList;
  }

  public void setEngEdgeTgtList(List<EEngEdge> engEdgeTgtList) {
    this.engEdgeTgtList = engEdgeTgtList;
  }

  // --------------------------------------------------------------------------------
  // ----- toString Methods
  // --------------------------------------------------------------------------------

  protected String engEdgeListToString(List<EEngEdge> engEdgeList) {
    StringBuffer ret = new StringBuffer();

    for (EEngEdge engEdge : engEdgeList) {
      EEngTrans engTrans = engEdge.getEngTrans();

      ret.append("\t");
      ret.append(engTrans.toString(false));
      ret.append("\n");
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public String toString(boolean showEdges) {
    StringBuffer ret = new StringBuffer();

    ret.append(super.toString());
    ret.append("\n");
    ret.append("{[");
    ret.append("Id=");
    ret.append(netPlace != null ? Integer.toString(netPlace.getId()) : null);
    ret.append(",");
    ret.append(netPlace);
    ret.append("],[");
    ret.append("Id=");
    ret.append(wrkPlace != null ? Integer.toString(wrkPlace.getId()) : null);
    ret.append(",");
    ret.append("Tk=");
    ret.append(wrkPlace != null ? Integer.toString(wrkPlace.getTokens()) : null);
    ret.append(",");
    ret.append(wrkPlace);
    ret.append("]}");

    if (showEdges) {
      ret.append("\n");
      ret.append("\t=>(this src)");
      ret.append(engEdgeListToString(engEdgeSrcList));

      ret.append("\n");
      ret.append("\t=>(this tgt)");
      ret.append(engEdgeListToString(engEdgeTgtList));

      ret.append("\n");
      ret.append("----------------------------------------");
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public String toString() {
    return toString(true);
  }
}
