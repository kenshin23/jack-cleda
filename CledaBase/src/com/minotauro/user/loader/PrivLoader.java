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
import com.minotauro.user.model.MPriv;
import com.minotauro.user.xml.priv.ObjectFactory;
import com.minotauro.user.xml.priv.Priv;
import com.minotauro.user.xml.priv.PrivList;

/**
 * @author Demián Gutierrez
 */
public class PrivLoader {

  protected List<MPriv> privList = new ArrayList<MPriv>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public PrivLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  protected void loadPrivList(List<Priv> xmlPrivList) throws Exception {

    Map<String, MPriv> privByNameMap = //
    new HashMap<String, MPriv>();

    // ----------------------------------------

    for (Priv xmlPriv : xmlPrivList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (privByNameMap.get(xmlPriv.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MPriv.class, xmlPriv.getName()));
      }

      // ----------------------------------------
      // Create the MPriv
      // ----------------------------------------

      MPriv priv = new MPriv();

      priv.setName(xmlPriv.getName());
      priv.setDescription(xmlPriv.getDescription().getContent());

      privByNameMap.put(xmlPriv.getName(), priv);

      privList.add(priv);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MPriv>
  // --------------------------------------------------------------------------------

  public List<MPriv> load(InputStream is) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    PrivList xmlPrivList = (PrivList) unmarshaller.unmarshal(is);

    loadPrivList(xmlPrivList.getPriv());

    return privList;
  }

  // --------------------------------------------------------------------------------

  public static void loadPriv(InputStream is) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    PrivLoader privLoader = new PrivLoader(session);
    List<MPriv> list = privLoader.load(is);

    for (MPriv priv : list) {
      session.saveOrUpdate(priv);
    }

    session.getTransaction().commit();
    session.close();
  }
}
