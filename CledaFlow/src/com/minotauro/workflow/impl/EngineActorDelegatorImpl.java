/*
 * Created on 25/07/2008
 */
package com.minotauro.workflow.impl;

import org.hibernate.Session;

import com.minotauro.user.model.MUser;
import com.minotauro.workflow.api.WorkflowDelegator;
import com.minotauro.workflow.engine.EngineActorDelegator;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MNetTransSetRole;

/**
 * @author Demián Gutierrez
 */
public class EngineActorDelegatorImpl implements EngineActorDelegator {

  protected Session session;

  // ----------------------------------------

  public EngineActorDelegatorImpl() {
    // Empty
  }

  // ----------------------------------------

  public void init(Session session) //
      throws WorkflowException {
    this.session = session;
  }

  // ----------------------------------------

  public MUser delegate( //
      WorkflowDelegator workflowDelegator, MNetTransSetRole netTransSetRole) //
      throws Exception {

    workflowDelegator.init(session);

    return workflowDelegator.delegate(netTransSetRole);
  }
}
