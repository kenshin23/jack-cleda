package com.minotauro.sandbox.init.menutest;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.menu.loader.MenuLoader;
import com.minotauro.user.loader.PrivLoader;
import com.minotauro.user.loader.ProfLoader;
import com.minotauro.user.loader.RoleLoader;
import com.minotauro.user.loader.UserLoader;

public class LoadData {

  public static void main(String[] args) throws Exception {
    CledaConnector.getInstance().dropDB();

    PrivLoader.loadPriv(LoadData.class.getResourceAsStream("priv.xml"));
    RoleLoader.loadRole(LoadData.class.getResourceAsStream("role.xml"));

    ProfLoader.loadProf(LoadData.class.getResourceAsStream("prof.xml"));

    UserLoader.loadUser(LoadData.class.getResourceAsStream("user.xml"));

    MenuLoader.loadMenu(LoadData.class.getResourceAsStream("menu.xml"));
  }
}
