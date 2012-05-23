/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.EEngPetriSelectI18N;
import com.minotauro.workflow.model.MNetEdge;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkPlace;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriSelect {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriSelect(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  protected void selectNetPetriPlace() {
    MNetPetri netPetri = engPetriData.getNetPetri();

    for (MNetPlace netPlace : netPetri.getNetPlaceList()) {
      EEngPlace engPlace = new EEngPlace();
      engPlace.setNetPlace(netPlace);

      engPetriData.putEngByModelPlace(netPlace, engPlace);
      engPetriData.getEngPlaceList().add(engPlace);
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectNetPetriTrans() {
    MNetPetri netPetri = engPetriData.getNetPetri();

    for (MNetTransSet netTransSet : netPetri.getNetTransSetList()) {
      for (MNetTrans netTrans : netTransSet.getNetTransList()) {
        EEngTrans engTrans = new EEngTrans();
        engTrans.setNetTrans(netTrans);

        engPetriData.putEngByModelTrans(netTrans, engTrans);
        engPetriData.getEngTransList().add(engTrans);
      }
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectNetPetriEdge( //
      MNetEdge netEdge, EEngPlace engPlace, EEngTrans engTrans) //
      throws WorkflowException {

    // ----------------------------------------
    // Create / Init the EEngEdge
    // ----------------------------------------

    EEngEdge engEdge = new EEngEdge();
    engEdge.setNetEdge(netEdge);

    switch (netEdge.getDirection()) {
      case MNetEdge.DIRECTION_PLACE_TO_TRANS :
        engEdge.setSrcObject(engPlace);
        engEdge.setTgtObject(engTrans);

        engPlace.getEngEdgeSrcList().add(engEdge);
        engTrans.getEngEdgeTgtList().add(engEdge);
        break;
      case MNetEdge.DIRECTION_TRANS_TO_PLACE :
        engEdge.setSrcObject(engTrans);
        engEdge.setTgtObject(engPlace);

        engTrans.getEngEdgeSrcList().add(engEdge);
        engPlace.getEngEdgeTgtList().add(engEdge);
        break;
      default :
        throw new WorkflowException( //
            EEngPetriSelectI18N.invalidEdgeDirection( //
                netEdge.getDirection()));
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectNetPetriEdge() //
      throws WorkflowException {

    // ----------------------------------------
    // For each place
    // ----------------------------------------

    for (EEngPlace engPlace : engPetriData.getEngPlaceList()) {
      MNetPlace netPlace = engPlace.getNetPlace();

      // ----------------------------------------
      // For each edge in place
      // ----------------------------------------

      for (MNetEdge netEdge : netPlace.getNetEdgeList()) {
        MNetTrans netTrans = netEdge.getNetTransRef();

        EEngTrans engTrans = //
        engPetriData.getEngByModelTrans(netTrans);

        selectNetPetriEdge(netEdge, engPlace, engTrans);
      }
    }
  }

  // --------------------------------------------------------------------------------

  public void selectNetPetri(MNetPetri netPetri) //
      throws WorkflowException {

    if (engPetriData.getNetPetri() != null) {
      throw new WorkflowException(EEngPetriSelectI18N. //
          netPetriNqNull(netPetri.getName()));
    }

    engPetriData.setNetPetri(netPetri);

    // ----------------------------------------
    // Place / Trans first
    // ----------------------------------------

    selectNetPetriPlace();
    selectNetPetriTrans();

    // ----------------------------------------
    // Edges at the end
    // ----------------------------------------

    selectNetPetriEdge();
  }

  // --------------------------------------------------------------------------------

  protected void selectWorkflowPlace() //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkPlace wrkPlace : workflow.getWrkPlaceList()) {
      MNetPlace netPlace = wrkPlace.getNetPlaceRef();

      EEngPlace engPlace = //
      engPetriData.getEngByModelPlace(netPlace);

      engPlace.setWrkPlace(wrkPlace);
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectWorkflowTrans() //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {
      for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
        MNetTrans netTrans = wrkTrans.getNetTransRef();

        EEngTrans engTrans = //
        engPetriData.getEngByModelTrans(netTrans);

        engTrans.setWrkTrans(wrkTrans);
      }
    }
  }

  // --------------------------------------------------------------------------------

  public void selectWorkflow(MWorkflow workflow) //
      throws WorkflowException {

    if (engPetriData.getWorkflow() != null) {
      throw new WorkflowException(EEngPetriSelectI18N. //
          workflowNqNull(workflow.getId()));
    }

    engPetriData.setWorkflow(workflow);

    // ----------------------------------------
    // NetPetri first
    // ----------------------------------------

    selectNetPetri(workflow.getNetPetriRef());

    // ----------------------------------------
    // Place / Trans at the end
    // ----------------------------------------

    selectWorkflowPlace();
    selectWorkflowTrans();
  }
}
