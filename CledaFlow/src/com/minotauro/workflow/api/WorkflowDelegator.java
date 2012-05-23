/*
 * Created on 14/12/2007
 */
package com.minotauro.workflow.api;

import org.hibernate.Session;

import com.minotauro.user.model.MUser;
import com.minotauro.workflow.model.MNetTransSetRole;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowDelegator {

  public void init(Session session);

  // ----------------------------------------

  public MUser delegate(MNetTransSetRole netTransSetRole);
}
