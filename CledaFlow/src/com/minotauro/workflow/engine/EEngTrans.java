/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.engine;

import java.util.ArrayList;
import java.util.List;

import com.minotauro.workflow.model.MNetEdge;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MWrkPlace;
import com.minotauro.workflow.model.MWrkTrans;

/**
 * @author Demián Gutierrez
 */
public class EEngTrans {

  public static final int DIRECTION_FWD = 0;
  public static final int DIRECTION_BCK = 1;

  public static final int MOVE_TYPE_REM = 0;
  public static final int MOVE_TYPE_ADD = 1;

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected MNetTrans netTrans;
  protected MWrkTrans wrkTrans;

  protected List<EEngEdge> engEdgeSrcList = new ArrayList<EEngEdge>(); // This is src
  protected List<EEngEdge> engEdgeTgtList = new ArrayList<EEngEdge>(); // This is tgt

  // --------------------------------------------------------------------------------

  public EEngTrans() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public MNetTrans getNetTrans() {
    return netTrans;
  }

  public void setNetTrans(MNetTrans netTrans) {
    this.netTrans = netTrans;
  }

  // --------------------------------------------------------------------------------

  public MWrkTrans getWrkTrans() {
    return wrkTrans;
  }

  public void setWrkTrans(MWrkTrans wrkTrans) {
    this.wrkTrans = wrkTrans;
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
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  protected boolean moveTokens( //
      List<EEngEdge> engEdgeList, int moveType, boolean testOnly) {

    for (EEngEdge engEdge : engEdgeList) {
      EEngPlace/**/engPlace/**/= engEdge.getEngPlace();
      MNetEdge/* */netEdge/* */= engEdge.getNetEdge();
      MWrkPlace/**/wrkPlace/**/= engPlace.getWrkPlace();

      if (moveType == MOVE_TYPE_REM) {
        if (engEdge.getNetEdge().isInhibitor()) {
          if (wrkPlace.getTokens() != 0) {
            return false;
          }
        } else {
          if (wrkPlace.getTokens() <= 0) {
            return false;
          }
        }

        if (!testOnly && !netEdge.isInhibitor()) {
          wrkPlace.setTokens(wrkPlace.getTokens() - 1);
        }
      } else {

        // ----------------------------------------
        // Useless, but to be consistent
        // ----------------------------------------

        if (!testOnly && !netEdge.isInhibitor()) {
          wrkPlace.setTokens(wrkPlace.getTokens() + 1);
        }
      }
    }

    return true;
  }

  // --------------------------------------------------------------------------------

  public boolean preFireTrans(int direction, boolean testOnly) {
    List<EEngEdge> engEdgeTgtList;

    if (direction == DIRECTION_FWD) {
      engEdgeTgtList = getEngEdgeTgtList();
    } else {
      engEdgeTgtList = getEngEdgeSrcList();
    }

    if (!moveTokens(engEdgeTgtList, MOVE_TYPE_REM, true)) {
      return false;
    }

    if (!testOnly) {
      moveTokens(engEdgeTgtList, MOVE_TYPE_REM, false);
    }

    return true;
  }

  public boolean posFireTrans(int direction, boolean testOnly) {
    List<EEngEdge> engEdgeSrcList;

    if (direction == DIRECTION_FWD) {
      engEdgeSrcList = getEngEdgeSrcList();
    } else {
      engEdgeSrcList = getEngEdgeTgtList();
    }

    if (!moveTokens(engEdgeSrcList, MOVE_TYPE_ADD, true)) {
      return false;
    }

    if (!testOnly) {
      moveTokens(engEdgeSrcList, MOVE_TYPE_ADD, false);
    }

    return true;
  }

  // --------------------------------------------------------------------------------
  // ----- toString Methods
  // --------------------------------------------------------------------------------

  protected String engEdgeListToString(List<EEngEdge> engEdgeList) {
    StringBuffer ret = new StringBuffer();

    for (EEngEdge engEdge : engEdgeList) {
      EEngPlace engPlace = engEdge.getEngPlace();

      ret.append("\t");
      ret.append(engPlace.toString(false));
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
    ret.append(netTrans != null ? Integer.toString(netTrans.getId()) : null);
    ret.append(",");
    ret.append(netTrans);
    ret.append("],[");
    ret.append(wrkTrans != null ? Integer.toString(wrkTrans.getId()) : null);
    ret.append(",");
    ret.append(wrkTrans);
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

  public String toString() {
    return toString(true);
  }
}
