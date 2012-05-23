/*
 * Created on 14/01/2007
 */
package com.minotauro.workflow.test.base;

import java.io.FileInputStream;

import com.minotauro.user.loader.ProfLoader;
import com.minotauro.user.loader.RoleLoader;
import com.minotauro.user.loader.UserLoader;
import com.minotauro.workflow.loaders.DocTypeLoader;
import com.minotauro.workflow.loaders.NetPetriLoader;
import com.minotauro.workflow.loaders.WorklistLoader;

/**
 * @author Demián Gutierrez
 */
public class Init {

  public static void main(String[] args) throws Exception {
    // CledaConnector.getInstance().dropDB();

    if (args.length == 0) {
      throw new Exception("args.length == 0");
    }

    String prefix = args[0];

    int i = 1;

    // ----------------------------------------

    String role/*    */= args.length > i + 1 ? args[i++] : null;
    String prof/*    */= args.length > i + 1 ? args[i++] : null;
    String user/*    */= args.length > i + 1 ? args[i++] : null;

    String doctype/* */= args.length > i + 1 ? args[i++] : null;

    String worklist/**/= args.length > i + 1 ? args[i++] : null;
    String netpetri/**/= args.length > i + 1 ? args[i++] : null;

    // ----------------------------------------

    role/*    */= prefix + (role/*    */== null ? "/role.xml"/*    */: "/" + role);
    prof/*    */= prefix + (prof/*    */== null ? "/prof.xml"/*    */: "/" + prof);
    user/*    */= prefix + (user/*    */== null ? "/user.xml"/*    */: "/" + user);

    doctype/* */= prefix + (doctype/* */== null ? "/doctype.xml"/* */: "/" + doctype);

    worklist/**/= prefix + (worklist/**/== null ? "/worklist.xml"/**/: "/" + worklist);
    netpetri/**/= prefix + (netpetri/**/== null ? "/netpetri.xml"/**/: "/" + netpetri);

    // ----------------------------------------

    RoleLoader.loadRole(new FileInputStream(role));
    ProfLoader.loadProf(new FileInputStream(prof));
    UserLoader.loadUser(new FileInputStream(user));

    DocTypeLoader.loadDocType(new FileInputStream(doctype));

    WorklistLoader.loadWorklist(new FileInputStream(worklist));
    NetPetriLoader.loadNetPetri(new FileInputStream(netpetri));
  }
}
