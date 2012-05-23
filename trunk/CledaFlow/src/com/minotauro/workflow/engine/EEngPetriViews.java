/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.minotauro.workflow.model.MDocSectionEditState;
import com.minotauro.workflow.model.MDocSectionValidateState;
import com.minotauro.workflow.model.MDocSectionViewState;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetStateGrp;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriViews {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriViews(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  public int getEditState(MWrkTransSet wrkTransSet, String name) {
    MWorkflow workflow = engPetriData.getWorkflow();
    MNetTransSet netTransSet = wrkTransSet.getNetTransSetRef();
    MNetPetri netPetri = workflow.getNetPetriRef();

    // ----------------------------------------
    // Try to find a matching state
    // ----------------------------------------

    List<MDocSectionEditState> dsvsList = //
    netTransSet.getDocSectionEditStateList();

    for (MDocSectionEditState dsvs : dsvsList) {
      if (StringUtils.equals(name, dsvs.getDocSectionRef().getName())) {
        return dsvs.getState();
      }
    }

    // ----------------------------------------
    // Get defaults
    // ----------------------------------------

    return netTransSet.getDefaultDocSectionEditState() != //
    MDocSectionViewState.STATE_NOTDEF_INT //
        ? netTransSet.getDefaultDocSectionEditState()
        : netPetri.getDefaultDocSectionEditState();
  }

  // --------------------------------------------------------------------------------

  public int getViewState(String name) {
    MWorkflow workflow = engPetriData.getWorkflow();
    MNetStateGrp netStateGrp = workflow.getNetStateGrpRef();
    MNetPetri netPetri = workflow.getNetPetriRef();

    // ----------------------------------------
    // Try to find a matching state
    // ----------------------------------------

    List<MDocSectionViewState> dsvsList = //
    netStateGrp.getDocSectionViewStateList();

    for (MDocSectionViewState dsvs : dsvsList) {
      if (StringUtils.equals(name, dsvs.getDocSectionRef().getName())) {
        return dsvs.getState();
      }
    }

    // ----------------------------------------
    // Get defaults
    // ----------------------------------------

    return netStateGrp.getDefaultDocSectionViewState() != //
    MDocSectionViewState.STATE_NOTDEF_INT //
        ? netStateGrp.getDefaultDocSectionViewState()
        : netPetri.getDefaultDocSectionViewState();
  }

  // --------------------------------------------------------------------------------

  public int getValidateState(MWrkTrans wrkTrans, String name) {
    MNetTrans netTrans = wrkTrans.getNetTransRef();
    MNetPetri netPetri = netTrans.getNetTransSetRef().getNetPetriRef();

    // ----------------------------------------
    // Try to find a matching state
    // ----------------------------------------

    List<MDocSectionValidateState> dsvsList = //
    netTrans.getDocSectionValidateStateList();

    for (MDocSectionValidateState dsvs : dsvsList) {
      if (StringUtils.equals(name, dsvs.getDocSectionRef().getName())) {
        return dsvs.getState();
      }
    }

    // ----------------------------------------
    // Get defaults
    // ----------------------------------------

    return netTrans.getDefaultDocSectionValidateState() != //
    MDocSectionValidateState.STATE_NOTDEF_INT //
        ? netTrans.getDefaultDocSectionValidateState()
        : netPetri.getDefaultDocSectionValidateState();
  }
}
