/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.EEngPetriTransI18N;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriTrans {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriTrans(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  protected boolean internalFireTrans(MNetTrans netTrans, int direction) //
      throws WorkflowException {

    engPetriData.setFiringNetTrans(netTrans);

    EEngTrans engTrans = //
    engPetriData.getEngByModelTrans(netTrans);

    boolean ret = true;

    ret &= engTrans.preFireTrans(direction, false);
    engPetriData.getEngPetriState().updateWrkTransState();
    ret &= engTrans.posFireTrans(direction, false);
    engPetriData.getEngPetriState().updateWrkTransState();

    // ----------------------------------------
    // Update WrkTrans States
    // ----------------------------------------

    engPetriData.setFiringNetTrans(null);

    // ----------------------------------------
    // Calls the executePos of the Agent
    // ----------------------------------------

    EngineAgentDelegator engineAgentDelegator = //
    engPetriData.getEngineAgentDelegator();

    engineAgentDelegator.executePos( //
        engTrans.getWrkTrans().getWrkTransSetRef());

    // ----------------------------------------
    // Checks for enabled auto trans
    // ----------------------------------------

    if (ret) {
      createWrkTransLog(engTrans);
      checkForAutoTrans();
    }

    return ret;
  }

  // TODO:
  private void createWrkTransLog(EEngTrans engTrans) {
    MWrkTrans wrkTrans = engTrans.getWrkTrans();

    MWrkTransLog wrkTransLog = new MWrkTransLog();
    //    wrkTransLog.set
  }

  // --------------------------------------------------------------------------------

  protected boolean internalFireTrans(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    MNetTransSet netTransSet = wrkTransSet.getNetTransSetRef();

    MWrkTrans wrkTrans;

    if (netTransSet.getAgentType() != null) {
      EngineAgentDelegator engineAgentDelegator = //
      engPetriData.getEngineAgentDelegator();

      wrkTrans = engineAgentDelegator.execute(wrkTransSet);
    } else {
      if (wrkTransSet.getWrkTransList().size() != 1) {
        throw new WorkflowException( //
            EEngPetriTransI18N.agentNotDefined( //
                wrkTransSet.getNetTransSetRef().getName()));
      }

      wrkTrans = wrkTransSet.getWrkTransList().get(0);
    }

    if (wrkTrans == null) {
      throw new WorkflowException( //
          EEngPetriTransI18N.netTransWasNull( //
              wrkTransSet.getNetTransSetRef().getName()));
    }

    // ----------------------------------------
    // Fires the trans
    // ----------------------------------------

    return internalFireTrans( //
        wrkTrans.getNetTransRef(), EEngTrans.DIRECTION_FWD);
  }

  // --------------------------------------------------------------------------------

  public boolean fireTrans(MNetTrans netTrans, int direction) //
      throws WorkflowException {

    boolean ret = internalFireTrans(netTrans, direction);

    // ----------------------------------------
    // Update Workflow States
    // ----------------------------------------

    engPetriData.getEngPetriState().updateWorkflowState();

    MWorkflow workflow = engPetriData.getWorkflow();
    workflow.updateActorFromCurrNetStateGrp();

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void checkForAutoTrans() //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {

      // ----------------------------------------
      // Skip disabled non auto or timed trans
      // ----------------------------------------

      if (wrkTransSet.getStatus()/*   */!= MWrkTransSet.STATUS_ENABLED || //
          wrkTransSet.getActorRef()/* */!= null || //
          wrkTransSet.getAgentDate()/**/!= null) {
        continue;
      }

      internalFireTrans(wrkTransSet);
    }
  }
}
