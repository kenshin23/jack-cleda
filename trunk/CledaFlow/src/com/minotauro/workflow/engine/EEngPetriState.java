/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import java.util.Calendar;

import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MNetStateGrp;
import com.minotauro.workflow.model.MNetStateSet;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriState {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriState(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  protected void updateWrkTransState(MWrkTrans wrkTrans) //
      throws WorkflowException {

    MNetTrans netTrans = wrkTrans.getNetTransRef();
    EEngTrans engTrans = engPetriData.getEngByModelTrans(netTrans);

    wrkTrans.setStatus(MWrkTrans.STATUS_ENABLED);

    boolean status = true;

    status &= engTrans.preFireTrans(EEngTrans.DIRECTION_FWD, true);
    status &= engTrans.posFireTrans(EEngTrans.DIRECTION_FWD, true);

    if (!status) {
      wrkTrans.setStatus(MWrkTrans.STATUS_DISABLED);
    }
  }

  // --------------------------------------------------------------------------------

  public void updateWorkflowState() //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    MNetStateSet netStateSet = workflow.calculatePlaceSet();
    MNetStateGrp netStateGrp = netStateSet.getNetStateGrpRef();

    workflow.setNetStateSetRef(netStateSet);
    workflow.setNetStateGrpRef(netStateGrp);
  }

  // --------------------------------------------------------------------------------

  protected void createWrkTransLog(MWrkTransSet wrkTransSet) throws WorkflowException {
    MWrkTransLog wrkTransLog = new MWrkTransLog();

    wrkTransLog.setBegDate(Calendar.getInstance());

    wrkTransSet.getWrkTransLogList().add(wrkTransLog);
    wrkTransLog.setWrkTransSetRef(wrkTransSet);
  }

  // TODO: Improve!!!
  protected void updateWrkTransLog(MWrkTransSet wrkTransSet) throws WorkflowException {
    MNetTrans firingNetTrans = engPetriData.getFiringNetTrans();
    EEngTrans firingEngTrans = engPetriData.getEngByModelTrans(firingNetTrans);

    MNetTransSet firingNetTransSet = firingNetTrans.getNetTransSetRef();

    for (MWrkTransLog currWrkTransLog : wrkTransSet.getWrkTransLogList()) {
      if (currWrkTransLog.getEndDate() == null) {
        currWrkTransLog.setEndDate(Calendar.getInstance());

        if (firingNetTransSet.equals(wrkTransSet.getNetTransSetRef())) {
          firingEngTrans.getWrkTrans().getWrkTransLogList().add(currWrkTransLog);
          currWrkTransLog.setWrkTransRef(firingEngTrans.getWrkTrans());

          if (firingNetTransSet.getNetTransSetRoleRef() != null) {
            currWrkTransLog.setUserRef(engPetriData.getUser());
          }
        }
        break;
      }
    }
  }

  // --------------------------------------------------------------------------------

  public void updateWrkTransState() //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {
      int curStatus = wrkTransSet.getStatus();

      wrkTransSet.setStatus(MWrkTransSet.STATUS_DISABLED);

      // ----------------------------------------
      // Update wrkTransSet Status
      // ----------------------------------------

      for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
        updateWrkTransState(wrkTrans);

        if (wrkTrans.getStatus() == MWrkTrans.STATUS_ENABLED) {
          wrkTransSet.setStatus(MWrkTransSet.STATUS_ENABLED);
        }
      }

      int newStatus = wrkTransSet.getStatus();

      // ----------------------------------------
      // Update wrkTransSet Agent / Scheduler
      // ----------------------------------------

      if (curStatus != newStatus) {
        if (wrkTransSet.getStatus() == MWrkTransSet.STATUS_ENABLED) {
          prepareWrkTransSetAgent(wrkTransSet);
          prepareWrkTransSetTimer(wrkTransSet);
          addWrkTransSetScheduler(wrkTransSet);

          createWrkTransLog(wrkTransSet);
        } else {
          if (wrkTransSet.getAgentTask() != null) {
            delWrkTransSetScheduler(wrkTransSet);
          }

          updateWrkTransLog(wrkTransSet);
        }
      }
    }
  }

  // --------------------------------------------------------------------------------

  protected void addWrkTransSetScheduler(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    if (wrkTransSet.getAgentDate() != null) {
      engPetriData.getAddToSchedulerList().add(wrkTransSet);
    }
  }

  // --------------------------------------------------------------------------------

  protected void delWrkTransSetScheduler(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    MNetTransSet netTransSet = engPetriData.getFiringNetTrans().getNetTransSetRef();

    if (netTransSet.equals(wrkTransSet.getNetTransSetRef())) {
      return;
    }

    wrkTransSet.setAgentPrev(wrkTransSet.getAgentTask());

    if (wrkTransSet.getAgentTask() != null) {
      engPetriData.getDelToSchedulerList().add(wrkTransSet);
    }

    wrkTransSet.setAgentTask(null);
    wrkTransSet.setAgentDate(null);

    engPetriData.getAddToSchedulerList().remove(wrkTransSet);
  }

  // --------------------------------------------------------------------------------

  protected void prepareWrkTransSetAgent(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    try {
      engPetriData.getEngineAgentDelegator().executePre(wrkTransSet);
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void prepareWrkTransSetTimer(MWrkTransSet wrkTransSet) // 
      throws WorkflowException {

    MNetTransSet netTransSet = wrkTransSet.getNetTransSetRef();

    if (wrkTransSet.getAgentDate() != null) {
      return;
    }

    if (netTransSet.getAgentTime() == 0) {
      return;
    }

    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MILLISECOND, netTransSet.getAgentTime());
    wrkTransSet.setAgentDate(cal);
  }
}
