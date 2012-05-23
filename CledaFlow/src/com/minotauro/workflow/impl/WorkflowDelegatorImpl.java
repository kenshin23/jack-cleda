/*
 * Created on 07/01/2008
 */
package com.minotauro.workflow.impl;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.minotauro.user.model.MUser;
import com.minotauro.workflow.api.WorkflowDelegator;
import com.minotauro.workflow.model.MActorCurr;
import com.minotauro.workflow.model.MNetTransSetRole;

/**
 * @author Demián Gutierrez
 */
public class WorkflowDelegatorImpl implements WorkflowDelegator {

  protected Map<MUser, Integer> loadByUserMap = new HashMap<MUser, Integer>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public WorkflowDelegatorImpl() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  public MUser delegate(MNetTransSetRole netTransSetRole) {
    String hql = "SELECT user, COUNT(user.id), accu " + //
        "FROM {0}                       AS accu " + //
        "RIGHT JOIN accu.userRef        AS user " + //
        "      JOIN user.userProfList   AS uspr " + //
        "      JOIN uspr.profRef        AS prof " + //
        "      JOIN prof.profRoleList   AS prro " + //
        "      JOIN prro.roleRef        AS role " + //
        "WHERE role.name=:roleName " + //
        "GROUP BY user ORDER BY COUNT(user.id) ASC";

    hql = MessageFormat.format(hql, new Object[]{MActorCurr.class.getName()});

    Query query = session.createQuery(hql);
    query.setParameter("roleName", netTransSetRole.getRoleRef().getName());

    MUser user = null;

    Iterator<?> itt = query.iterate();

    if (itt.hasNext()) {
      Object[] array = (Object[]) itt.next();

      user = (MUser) array[0];
    }

    return user;
  }
}
