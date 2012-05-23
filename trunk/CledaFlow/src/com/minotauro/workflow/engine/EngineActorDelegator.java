/*
 * Created on 25/07/2008
 */
package com.minotauro.workflow.engine;

import org.hibernate.Session;

import com.minotauro.user.model.MUser;
import com.minotauro.workflow.api.WorkflowDelegator;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MNetTransSetRole;

/**
 * @author Demián Gutierrez
 */
public interface EngineActorDelegator {

  public void init(Session session) //
      throws WorkflowException;

  // ----------------------------------------

  public MUser delegate( //
      WorkflowDelegator workflowDelegator, MNetTransSetRole netTransSetRole) //
      throws Exception;
}
