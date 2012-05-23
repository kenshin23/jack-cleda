/*
 * Created on 09/07/2008
 */
package com.minotauro.workflow.api;

import java.util.List;

import org.hibernate.Session;

import com.minotauro.user.model.MRole;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowFacade {

  // --------------------------------------------------------------------------------
  // Init
  // --------------------------------------------------------------------------------

  public void init(Session session, MUser user, String worklist)
      throws WorkflowException;

  // --------------------------------------------------------------------------------
  // Create / select methods
  // --------------------------------------------------------------------------------

  public void createWorkflow(MDocument document,
      String netPetriName, String netStateName)
      throws WorkflowException;

  public void selectWorkflow(MDocument document)
      throws WorkflowException;

  // --------------------------------------------------------------------------------

  public void flushScheduler()
      throws WorkflowException;

  // --------------------------------------------------------------------------------

  // -------------------------------------------------
  // XXX: Check the need of this and try to remove it
  // -------------------------------------------------

  //  public void dolockWorkflow(MWorkflow workflow)
  //      throws WorkflowException;
  //
  //  public void unlockWorkflow(MWorkflow workflow)
  //      throws WorkflowException;

  // --------------------------------------------------------------------------------

  public List<MActor> delegateActors()
      throws WorkflowException;

  // --------------------------------------------------------------------------------
  // Fire trans methods
  // --------------------------------------------------------------------------------

  public boolean fireAutoTrans(MWrkTransSet wrkTransSet)
      throws WorkflowException;

  public boolean fireUserTrans(MWrkTrans wrkTrans)
      throws WorkflowException;

  // --------------------------------------------------------------------------------
  // Field enabled / visible methods
  // --------------------------------------------------------------------------------

  public boolean isEditFieldEnabled(String key, MWrkTransSet wrkTransSet);

  public boolean isEditFieldVisible(String key, MWrkTransSet wrkTransSet);

  // --------------------------------------------------------------------------------

  public boolean isViewFieldVisible(String key);

  // --------------------------------------------------------------------------------

  public boolean isEditFieldValidable(String key, MWrkTrans wrkTrans);

  // --------------------------------------------------------------------------------
  // Worklist
  // --------------------------------------------------------------------------------

  public int countTasks()
      throws WorkflowException;

  public List<MWrkTransSet> listTasks(int offset, int limit)
      throws WorkflowException;

  // --------------------------------------------------------------------------------
  // Misc Methods
  // --------------------------------------------------------------------------------

  public MRole getInitRole();

  public MWrkTransSet getEnabledWrkTransSetForRole(MRole reqRole)
      throws WorkflowException;

  // --------------------------------------------------------------------------------

  public List<MWrkTransLog> getWrkTransLogList()
      throws WorkflowException;
}
