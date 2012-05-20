package com.minotauro.sandbox.init.simple;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.menu.loader.MenuLoader;
import com.minotauro.user.loader.PrivLoader;
import com.minotauro.user.loader.ProfLoader;
import com.minotauro.user.loader.RoleLoader;
import com.minotauro.user.loader.UserLoader;
import com.minotauro.workflow.loaders.DocTypeLoader;
import com.minotauro.workflow.loaders.NetPetriLoader;
import com.minotauro.workflow.loaders.WorklistLoader;

public class LoadData {

  public static void main(String[] args) throws Exception {
    CledaConnector.getInstance().dropDB();

    InitMCrudX.createMCrudA("crudA", 1);
    InitMCrudX.createMCrudB("crudB", 2);
    InitMCrudX.createMCrudC("crudC", 2);

    PrivLoader.loadPriv(LoadData.class.getResourceAsStream("priv.xml"));
    RoleLoader.loadRole(LoadData.class.getResourceAsStream("role.xml"));
    ProfLoader.loadProf(LoadData.class.getResourceAsStream("prof.xml"));
    UserLoader.loadUser(LoadData.class.getResourceAsStream("user.xml"));

    WorklistLoader.loadWorklist(LoadData.class.getResourceAsStream("worklist.xml"));

    PrivLoader.loadPriv(LoadData.class.getResourceAsStream("w_main_priv.xml"));
    RoleLoader.loadRole(LoadData.class.getResourceAsStream("w_main_role.xml"));
    ProfLoader.loadProf(LoadData.class.getResourceAsStream("w_main_prof.xml"));
    UserLoader.loadUser(LoadData.class.getResourceAsStream("w_main_user.xml"));

    DocTypeLoader.loadDocType(LoadData.class.getResourceAsStream("w_main_doc-type.xml"));
    NetPetriLoader.loadNetPetri(LoadData.class.getResourceAsStream("w_main_net-petri-def.xml"));

    RoleLoader.loadRole(LoadData.class.getResourceAsStream("w_user_reg_role.xml"));
    ProfLoader.loadProf(LoadData.class.getResourceAsStream("w_user_reg_prof.xml"));
    UserLoader.loadUser(LoadData.class.getResourceAsStream("w_user_reg_user.xml"));

    DocTypeLoader.loadDocType(LoadData.class.getResourceAsStream("w_user_reg_doc-type.xml"));
    NetPetriLoader.loadNetPetri(LoadData.class.getResourceAsStream("w_user_reg_net-petri-def.xml"));

    MenuLoader.loadMenu(LoadData.class.getResourceAsStream("menu.xml"));
    MenuLoader.loadMenu(LoadData.class.getResourceAsStream("w_main_menu.xml"));
    MenuLoader.loadMenu(LoadData.class.getResourceAsStream("w_user_reg_menu.xml"));
  }
}
