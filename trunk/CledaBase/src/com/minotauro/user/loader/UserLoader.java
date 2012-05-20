/*
 * Created on 14/01/2008
 */
package com.minotauro.user.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.util.HashUtils;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MUser;
import com.minotauro.user.model.UserUtil;
import com.minotauro.user.xml.user.ObjectFactory;
import com.minotauro.user.xml.user.Prof;
import com.minotauro.user.xml.user.User;
import com.minotauro.user.xml.user.UserList;

/**
 * @author Demián Gutierrez
 */
public class UserLoader {

  protected List<MUser> userList = new ArrayList<MUser>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public UserLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  protected void loadProfList(MUser user, List<Prof> xmlProfList) throws Exception {

    Set<String> profNameSet = //
    new HashSet<String>();

    // ----------------------------------------

    for (Prof xmlProf : xmlProfList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (profNameSet.contains(xmlProf.getName())) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MProf.class, xmlProf.getName()));
      }

      // ----------------------------------------
      // Create the relationship
      // ----------------------------------------

      UserUtil.addProfToUser(session, user, xmlProf.getName());
      profNameSet.add(xmlProf.getName());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadUserList(List<User> xmlUserList) throws Exception {

    Map<String, MUser> userByNameMap = //
    new HashMap<String, MUser>();

    // ----------------------------------------

    for (User xmlUser : xmlUserList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (userByNameMap.get(xmlUser.getUser()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MUser.class, xmlUser.getUser()));
      }

      // ----------------------------------------
      // Create the MUser
      // ----------------------------------------

      MUser user = new MUser();

      user.setUser(xmlUser.getUser());

      user.setPass(HashUtils.getHash(HashUtils.SHA_1, xmlUser.getPass()));

      user.setLanguage(xmlUser.getLanguage());
      user.setCountry(xmlUser.getCountry());
      user.setVariant(xmlUser.getVariant());

      loadProfList(user, xmlUser.getProf());

      userByNameMap.put(xmlUser.getUser(), user);

      userList.add(user);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MUser>
  // --------------------------------------------------------------------------------

  public List<MUser> load(InputStream is) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    UserList xmlUserList = (UserList) unmarshaller.unmarshal(is);

    loadUserList(xmlUserList.getUser());

    return userList;
  }

  // --------------------------------------------------------------------------------

  public static void loadUser(InputStream is) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    UserLoader userLoader = new UserLoader(session);
    List<MUser> list = userLoader.load(is);

    for (MUser user : list) {
      session.saveOrUpdate(user);
    }

    session.getTransaction().commit();
    session.close();
  }
}
