/*
 * Created on 14/01/2007
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
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model.UserUtil;
import com.minotauro.user.xml.prof.ObjectFactory;
import com.minotauro.user.xml.prof.Priv;
import com.minotauro.user.xml.prof.Prof;
import com.minotauro.user.xml.prof.ProfList;
import com.minotauro.user.xml.prof.Role;

/**
 * @author Demián Gutierrez
 */
public class ProfLoader {

  protected List<MProf> profList = new ArrayList<MProf>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public ProfLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  protected void loadPrivList(MProf prof, List<Priv> xmlPrivList) throws Exception {

    Set<String> privNameSet = //
    new HashSet<String>();

    // ----------------------------------------

    for (Priv xmlPriv : xmlPrivList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (privNameSet.contains(xmlPriv.getName())) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MPriv.class, xmlPriv.getName()));
      }

      // ----------------------------------------
      // Create the relationship
      // ----------------------------------------

      UserUtil.addPrivToProf(session, prof, xmlPriv.getName());
      privNameSet.add(xmlPriv.getName());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadRoleList(MProf prof, List<Role> xmlRoleList) throws Exception {

    Set<String> roleNameSet = //
    new HashSet<String>();

    // ----------------------------------------

    for (Role xmlRole : xmlRoleList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (roleNameSet.contains(xmlRole.getName())) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MRole.class, xmlRole.getName()));
      }

      // ----------------------------------------
      // Create the relationship
      // ----------------------------------------

      UserUtil.addRoleToProf(session, prof, xmlRole.getName());
      roleNameSet.add(xmlRole.getName());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadProfList(List<Prof> xmlProfList) throws Exception {

    Map<String, MProf> profByNameMap = //
    new HashMap<String, MProf>();

    // ----------------------------------------

    for (Prof xmlProf : xmlProfList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (profByNameMap.get(xmlProf.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MProf.class, xmlProf.getName()));
      }

      // ----------------------------------------
      // Create the MProf
      // ----------------------------------------

      MProf prof = new MProf();

      prof.setName(xmlProf.getName());
      prof.setDescription(xmlProf.getDescription().getContent());

      loadPrivList(prof, xmlProf.getPriv());
      loadRoleList(prof, xmlProf.getRole());

      profByNameMap.put(xmlProf.getName(), prof);

      profList.add(prof);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MProf>
  // --------------------------------------------------------------------------------

  public List<MProf> load(InputStream is) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    ProfList xmlProfList = (ProfList) unmarshaller.unmarshal(is);

    loadProfList(xmlProfList.getProf());

    return profList;
  }

  // --------------------------------------------------------------------------------

  public static void loadProf(InputStream is) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    ProfLoader profLoader = new ProfLoader(session);
    List<MProf> list = profLoader.load(is);

    for (MProf prof : list) {
      session.saveOrUpdate(prof);
    }

    session.getTransaction().commit();
    session.close();
  }
}
