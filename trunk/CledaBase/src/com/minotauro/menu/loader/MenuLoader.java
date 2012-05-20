/*
 * Created on 16/08/2011
 */
package com.minotauro.menu.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.menu.model.MMenu;
import com.minotauro.menu.model.MMenu.Scope;
import com.minotauro.menu.model.MMenu.Type;
import com.minotauro.menu.xml.Entry;
import com.minotauro.menu.xml.Event;
import com.minotauro.menu.xml.Group;
import com.minotauro.menu.xml.I18NCls;
import com.minotauro.menu.xml.MenuBase;
import com.minotauro.menu.xml.MenuList;
import com.minotauro.menu.xml.MetaData;
import com.minotauro.menu.xml.ObjectFactory;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model.UserUtil;

/**
 * @author Demián Gutierrez
 */
public class MenuLoader {

  protected List<MMenu> menuList = new ArrayList<MMenu>();

  protected Map<String, MMenu> menuByRouteMap =
      new HashMap<String, MMenu>();

  protected Map<String, String> i18nClsByKey =
      new HashMap<String, String>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public MenuLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------

  protected void loadContentList(List<Object> list) throws Exception {

    for (Object xmlContent : list) {

      if (xmlContent instanceof String) {
        continue;
      }

      if (xmlContent instanceof I18NCls) {
        loadI18NCls((I18NCls) xmlContent);
        continue;
      }

      MenuBase xmlMenuBase = (MenuBase) xmlContent;

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (menuByRouteMap.get(xmlMenuBase.getRoute()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MMenu.class, xmlMenuBase.getRoute()));
      }

      // ----------------------------------------
      // Create the MMenu
      // ----------------------------------------

      MMenu menu = new MMenu();

      menu.setRoute(xmlMenuBase.getRoute());
      menu.setOrder(xmlMenuBase.getOrder());

      // ----------------------------------------

      if (xmlContent instanceof Entry) {
        loadEntry((Entry) xmlMenuBase, menu);
      }

      if (xmlContent instanceof Group) {
        loadGroup((Group) xmlMenuBase, menu);
      }

      if (xmlContent instanceof Event) {
        loadEvent((Event) xmlMenuBase, menu);
      }

      // ----------------------------------------

      menuByRouteMap.put(xmlMenuBase.getRoute(), menu);

      menuList.add(menu);
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadI18NCls(I18NCls xmlI18NCls) throws Exception {
    if (i18nClsByKey.get(xmlI18NCls.getKey()) != null) {
      throw new Exception( //
          _I18NLoader.duplicatedField(I18NCls.class, xmlI18NCls.getKey()));
    }

    i18nClsByKey.put(xmlI18NCls.getKey(), xmlI18NCls.getCls());
  }

  // --------------------------------------------------------------------------------

  protected String getI18NCls(MenuBase xmlMenuBase) {
    if (xmlMenuBase instanceof Entry) {
      return ((Entry) xmlMenuBase).getI18NCls();
    }

    if (xmlMenuBase instanceof Event) {
      return ((Event) xmlMenuBase).getI18NCls();
    }

    throw new IllegalArgumentException(
        "xmlMenuBase instanceof " + xmlMenuBase.getClass());
  }

  // --------------------------------------------------------------------------------

  protected String getI18NClsFromCache(MenuBase xmlMenuBase) {
    String i18nCls = getI18NCls(xmlMenuBase);

    if (StringUtils.isBlank(i18nCls)) {
      return i18nClsByKey.get(StringUtils.EMPTY);
    }

    if (i18nClsByKey.get(i18nCls) != null) {
      return i18nClsByKey.get(i18nCls);
    }

    return i18nCls;
  }

  // --------------------------------------------------------------------------------

  protected void loadEntry(Entry xmlEntry, MMenu menu) {
    menu.setType(Type.ENTRY);

    menu.setI18nCls(getI18NClsFromCache(xmlEntry));
    menu.setI18nKey(xmlEntry.getI18NKey());
  }

  // --------------------------------------------------------------------------------

  protected void loadGroup(Group xmlGroup, MMenu menu) {
    menu.setType(Type.GROUP);
  }

  // --------------------------------------------------------------------------------

  protected void loadEvent(Event xmlEvent, MMenu menu) {
    menu.setType(Type.EVENT);

    menu.setI18nCls(getI18NClsFromCache(xmlEvent));
    menu.setI18nKey(xmlEvent.getI18NKey());

    menu.setHandler(xmlEvent.getHandler());

    menu.setScope(Scope.valueOf(
        xmlEvent.getScope().toString()));

    loadPriv(xmlEvent, menu);
    loadRole(xmlEvent, menu);

    loadMetaData(menu, xmlEvent.getMetaData());
  }

  // --------------------------------------------------------------------------------

  protected void loadPriv(Event xmlEvent, MMenu menu) {
    if (StringUtils.isBlank(xmlEvent.getPriv())) {
      return;
    }

    MPriv priv = UserUtil.findPriv(session, xmlEvent.getPriv());

    if (priv == null) {
      throw new IllegalArgumentException(
          menu.getRoute() + " / " + xmlEvent.getPriv());
    }

    menu.setPrivRef(priv);
  }

  // --------------------------------------------------------------------------------

  protected void loadRole(Event xmlEvent, MMenu menu) {
    if (StringUtils.isBlank(xmlEvent.getRole())) {
      return;
    }

    MRole role = UserUtil.findRole(session, xmlEvent.getRole());

    if (role == null) {
      throw new IllegalArgumentException(
          menu.getRoute() + " / " + xmlEvent.getRole());
    }

    menu.setRoleRef(role);
  }

  // --------------------------------------------------------------------------------

  protected void loadMetaData( //
      MMetaDataParent metaDataParent, List<MetaData> metaDataList) {

    for (MetaData metaData : metaDataList) {
      metaDataParent.setMetaData(metaData.getKey(), metaData.getVal());
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MMenu>
  // --------------------------------------------------------------------------------

  public List<MMenu> load(InputStream is) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    MenuList xmlMenuList = (MenuList) unmarshaller.unmarshal(is);

    loadContentList(xmlMenuList.getContent());

    return menuList;
  }

  // --------------------------------------------------------------------------------

  public static void loadMenu(InputStream is) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    MenuLoader menuLoader = new MenuLoader(session);
    List<MMenu> list = menuLoader.load(is);

    for (MMenu menu : list) {
      session.saveOrUpdate(menu);
    }

    session.getTransaction().commit();
    session.close();
  }
}
