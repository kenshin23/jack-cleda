/*
 * Created on 22/01/2008
 */
package com.minotauro.workflow.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.api.WorkflowAgent;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class AgentUtil {

  private AgentUtil() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected static WorkflowAgent resolveAgent(MNetTransSet netTransSet) //
      throws WorkflowException {

    if (netTransSet.getAgentType() == null) {
      return null;
    }

    try {
      return (WorkflowAgent) Class.forName( //
          netTransSet.getAgentType()).newInstance();
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static MWrkTrans callExecute(MWrkTransSet wrkTransSet, Session session) //
      throws WorkflowException {

    WorkflowAgent workflowAgent = resolveAgent(wrkTransSet.getNetTransSetRef());

    if (workflowAgent == null) {
      throw new WorkflowException("workflowAgent == null");
    }

    workflowAgent.init(wrkTransSet.getWorkflowRef().getDocumentRef(), session);

    // ----------------------------------------
    // Prepares the wrkTransByName map
    // ----------------------------------------

    Map<String, MWrkTrans> wrkTransByName = new HashMap<String, MWrkTrans>();

    for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
      wrkTransByName.put(wrkTrans.getNetTransRef().getName(), wrkTrans);
    }

    // ----------------------------------------

    try {
      return workflowAgent.execute(wrkTransByName);
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void callExecutePre(MWrkTransSet wrkTransSet, Session session) //
      throws WorkflowException {

    WorkflowAgent workflowAgent = resolveAgent(wrkTransSet.getNetTransSetRef());

    if (workflowAgent == null) {
      return;
    }

    workflowAgent.init(wrkTransSet.getWorkflowRef().getDocumentRef(), session);

    try {
      workflowAgent.executePre(wrkTransSet);
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void callExecutePos(MWrkTransSet wrkTransSet, Session session) //
      throws WorkflowException {

    WorkflowAgent workflowAgent = resolveAgent(wrkTransSet.getNetTransSetRef());

    if (workflowAgent == null) {
      return;
    }

    workflowAgent.init(wrkTransSet.getWorkflowRef().getDocumentRef(), session);

    try {
      workflowAgent.executePos(wrkTransSet);
    } catch (Exception e) {
      throw new WorkflowException(e);
    }
  }
}
