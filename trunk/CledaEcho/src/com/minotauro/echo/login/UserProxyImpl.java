/*
 * Created on 07/09/2011
 */
package com.minotauro.echo.login;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.minotauro.cleda.config.CommonsConfig;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.util.HashUtils;
import com.minotauro.user.model.MUser;
import com.minotauro.user.model._PropMUser;

/**
 * @author Demián Gutierrez
 */
public class UserProxyImpl implements UserProxy {

  public UserProxyImpl() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public List<MUser> getUserList() {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(MUser.class).
        addOrder(Order.asc(_PropMUser.USER));

    List<MUser> userList = criteria.list();

    session.getTransaction().commit();
    session.close();

    return userList;
  }

  // --------------------------------------------------------------------------------

  public MUser loadUser(String user, String pass) {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    String evtUser = user;
    String evtPass = HashUtils.getHash(HashUtils.SHA_1, pass);

    Criteria criteria;

    if (CommonsConfig.isDebug()) {
      criteria = session.createCriteria(MUser.class).
          add(Restrictions.eq(_PropMUser.USER, evtUser));
    } else {
      criteria = session.createCriteria(MUser.class).
          add(Restrictions.eq(_PropMUser.USER, evtUser)).
          add(Restrictions.eq(_PropMUser.PASS, evtPass));
    }

    MUser ret = (MUser) criteria.uniqueResult();

    if (ret != null) {
      ret.loadPrivList();
      ret.loadRoleList();
    }

    session.getTransaction().commit();
    session.close();

    return ret;
  }
}
