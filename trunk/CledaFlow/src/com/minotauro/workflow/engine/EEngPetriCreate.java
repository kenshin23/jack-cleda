/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import java.util.HashMap;
import java.util.Map;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.EEngPetriCreateI18N;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MNetStateSet;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MNetTransSetRole;
import com.minotauro.workflow.model.MPlaceState;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkPlace;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriCreate {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriCreate(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  protected void createWorkflowPlace() //
      throws WorkflowException {

    MNetPetri netPetri = engPetriData.getNetPetri();
    MWorkflow workflow = engPetriData.getWorkflow();

    for (MNetPlace netPlace : netPetri.getNetPlaceList()) {
      MWrkPlace wrkPlace = new MWrkPlace();
      wrkPlace.setNetPlaceRef(netPlace);

      workflow.getWrkPlaceList().add(wrkPlace);
      wrkPlace.setWorkflowRef(workflow);

      EEngPlace engPlace = //
      engPetriData.getEngByModelPlace(netPlace);

      engPlace.setWrkPlace(wrkPlace);
    }
  }

  // --------------------------------------------------------------------------------

  protected void createWorkflowTrans() //
      throws WorkflowException {

    MNetPetri netPetri = engPetriData.getNetPetri();
    MWorkflow workflow = engPetriData.getWorkflow();

    for (MNetTransSet netTransSet : netPetri.getNetTransSetList()) {
      MWrkTransSet wrkTransSet = new MWrkTransSet();

      wrkTransSet.setNetTransSetRef(netTransSet);

      workflow.getWrkTransSetList().add(wrkTransSet);
      wrkTransSet.setWorkflowRef(workflow);

      for (MNetTrans netTrans : netTransSet.getNetTransList()) {
        MWrkTrans wrkTrans = new MWrkTrans();
        wrkTrans.setNetTransRef(netTrans);

        wrkTransSet.getWrkTransList().add(wrkTrans);
        wrkTrans.setWrkTransSetRef(wrkTransSet);

        EEngTrans engTrans = //
        engPetriData.getEngByModelTrans(netTrans);

        engTrans.setWrkTrans(wrkTrans);
      }
    }
  }

  // --------------------------------------------------------------------------------

  protected void createWorkflowActor() //
      throws WorkflowException {

    Map<MNetTransSetRole, MActor> actorByNetTransSetRole = //
    new HashMap<MNetTransSetRole, MActor>();

    // ----------------------------------------
    // Create the WorkflowDelegators
    // ----------------------------------------

    MNetPetri netPetri = engPetriData.getNetPetri();
    MWorkflow workflow = engPetriData.getWorkflow();

    for (MNetTransSetRole netTransSetRole : netPetri.getNetTransSetRoleList()) {
      MActor actor = workflow.createActorFromCurrNetStateGrp();

      actor.setNetTransSetRoleRef(netTransSetRole);

      workflow.getActorList().add(actor);
      actor.setWorkflowRef(workflow);

      actorByNetTransSetRole.put(netTransSetRole, actor);
    }

    // ----------------------------------------
    // Relation between Actor and WrkTransSet
    // ----------------------------------------

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {
      MNetTransSetRole netTransSetRole = //
      wrkTransSet.getNetTransSetRef().getNetTransSetRoleRef();

      // ----------------------------------------
      // Skip agent
      // ----------------------------------------

      if (netTransSetRole == null) {
        continue;
      }

      MActor actor = actorByNetTransSetRole.get(netTransSetRole);

      actor.getWrkTransSetList().add(wrkTransSet);
      wrkTransSet.setActorRef(actor);
    }
  }

  // --------------------------------------------------------------------------------

  protected void createWorkflowState(MNetStateSet netStateSet) //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    if (workflow.getId() != 0) {
      throw new WorkflowException(EEngPetriCreateI18N. //
          workflowIdNon0(workflow.getId()));
    }

    for (MPlaceState placeState : netStateSet.getPlaceStateList()) {
      MNetPlace netPlace = placeState.getNetPlaceRef();

      EEngPlace engPlace = //
      engPetriData.getEngByModelPlace(netPlace);

      MWrkPlace wrkPlace = engPlace.getWrkPlace();
      wrkPlace.setTokens(placeState.getTokens());
    }
  }

  // --------------------------------------------------------------------------------

  public void createWorkflow( //
      MNetStateSet netStateSet, MDocument document) //
      throws WorkflowException {

    MNetPetri netPetri = engPetriData.getNetPetri();
    MWorkflow workflow = engPetriData.getWorkflow();

    if (workflow != null) {
      throw new WorkflowException(EEngPetriCreateI18N. //
          workflowNqNull(workflow.getId()));
    }

    if (netPetri == null) {
      throw new WorkflowException(EEngPetriCreateI18N. //
          netPetriEqNull(workflow.getId()));
    }

    // ----------------------------------------
    // Create the workflow
    // ----------------------------------------

    workflow = new MWorkflow();
    engPetriData.setWorkflow(workflow);

    workflow.setNetPetriRef(netPetri);

    workflow.setDocumentRef(document);
    document.setWorkflowRef(workflow);

    workflow.setUserRef( //
        engPetriData.getUser());

    createWorkflowPlace();
    createWorkflowTrans();

    // ----------------------------------------
    // Update all states / actors
    // ----------------------------------------

    createWorkflowState(netStateSet);

    engPetriData.getEngPetriState().updateWrkTransState();
    engPetriData.getEngPetriState().updateWorkflowState();

    createWorkflowActor();
  }
}
