/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.minotauro.user.model.MRole;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.api.WorkflowDelegator;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MNetTransSetRole;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetriActor {

  protected EEngPetri engPetriData;

  // --------------------------------------------------------------------------------

  public EEngPetriActor(EEngPetri engPetriData) {
    this.engPetriData = engPetriData;
  }

  // --------------------------------------------------------------------------------

  public MRole getInitRole() {
    MNetPetri netPetri = engPetriData.getNetPetri();

    for (MNetTransSet netTransSet : netPetri.getNetTransSetList()) {
      if (netTransSet.isInit()) {
        return netTransSet.getNetTransSetRoleRef().getRoleRef();
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  public MWrkTransSet getEnabledWrkTransSetForRole(MRole reqRole) //
      throws WorkflowException {

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {

      // ----------------------------------------
      // Ignore not enabled
      // ----------------------------------------

      if (wrkTransSet.getStatus() == MWrkTransSet.STATUS_DISABLED) {
        continue;
      }

      // ----------------------------------------
      // Ignore auto trans
      // ----------------------------------------

      MNetTransSetRole netTransSetRole = //
      wrkTransSet.getNetTransSetRef().getNetTransSetRoleRef();

      if (netTransSetRole == null) {
        continue;
      }

      // ----------------------------------------
      // Ignore not matching roles
      // ----------------------------------------

      MRole curRole = netTransSetRole.getRoleRef();

      if (!reqRole.equals(curRole)) {
        continue;
      }

      return wrkTransSet;
    }

    return null;
  }

  public List<MWrkTransLog> getWrkTransLogList() //
      throws WorkflowException {
    List<MWrkTransLog> ret = new ArrayList<MWrkTransLog>();

    MWorkflow workflow = engPetriData.getWorkflow();

    for (MWrkTransSet wrkTransSet : workflow.getWrkTransSetList()) {
      for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
        for (MWrkTransLog wrkTransLog : wrkTrans.getWrkTransLogList()) {
          ret.add(wrkTransLog);
        }
      }
    }

    Collections.sort(ret, new Comparator<MWrkTransLog>() {
      public int compare(MWrkTransLog wtl1, MWrkTransLog wtl2) {
        return wtl1.getEndDate().compareTo(wtl2.getEndDate());
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  public List<MActor> delegateActors() //
      throws WorkflowException {

    try {
      return failsafeDelegateActors();
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected List<MActor> failsafeDelegateActors() //
      throws Exception {

    List<MActor> ret = new ArrayList<MActor>();

    for (MActor actor : engPetriData.getWorkflow().getActorList()) {

      Class<?> clazz = Class.forName(actor.getNetTransSetRoleRef().getDelegator());
      WorkflowDelegator workflowDelegator = (WorkflowDelegator) clazz.newInstance();

      EngineActorDelegator engineActorDelegator = //
      engPetriData.getEngineActorDelegator();

      MUser user = engineActorDelegator.delegate( //
          workflowDelegator, actor.getNetTransSetRoleRef());

      actor.setUserRef(user);

      if (user == null) {
        ret.add(actor);
      }
    }

    return ret;
  }
}
