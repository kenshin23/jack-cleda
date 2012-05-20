/*
 * Created on 14/01/2007
 */
package com.minotauro.user.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.user.model.MRole;
import com.minotauro.user.xml.role.ObjectFactory;
import com.minotauro.user.xml.role.Role;
import com.minotauro.user.xml.role.RoleList;

/**
 * @author Demián Gutierrez
 */
public class RoleLoader {

  protected List<MRole> roleList = new ArrayList<MRole>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public RoleLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  protected void loadRoleList(List<Role> xmlRoleList) throws Exception {

    Map<String, MRole> roleByNameMap = //
    new HashMap<String, MRole>();

    // ----------------------------------------

    for (Role xmlRole : xmlRoleList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (roleByNameMap.get(xmlRole.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MRole.class, xmlRole.getName()));
      }

      // ----------------------------------------
      // Create the MRole
      // ----------------------------------------

      MRole role = new MRole();

      role.setName(xmlRole.getName());
      role.setDescription(xmlRole.getDescription().getContent());

      roleByNameMap.put(xmlRole.getName(), role);

      roleList.add(role);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MRole>
  // --------------------------------------------------------------------------------

  public List<MRole> load(InputStream is) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    RoleList xmlRoleList = (RoleList) unmarshaller.unmarshal(is);

    loadRoleList(xmlRoleList.getRole());

    return roleList;
  }

  // --------------------------------------------------------------------------------

  public static void loadRole(InputStream is) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    RoleLoader roleLoader = new RoleLoader(session);
    List<MRole> list = roleLoader.load(is);

    for (MRole role : list) {
      session.saveOrUpdate(role);
    }

    session.getTransaction().commit();
    session.close();
  }
}
