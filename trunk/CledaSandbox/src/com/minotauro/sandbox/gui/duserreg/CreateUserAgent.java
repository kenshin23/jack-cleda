package com.minotauro.sandbox.gui.duserreg;

import java.util.Map;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.sandbox.model.DUserReg;
import com.minotauro.sandbox.model.DUserRegProf;
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MUser;
import com.minotauro.user.model.MUserProf;
import com.minotauro.workflow.api.WorkflowAgent;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

public class CreateUserAgent implements WorkflowAgent {

  protected static final Logger log = LoggerFactory.getLogger( //
      CreateUserAgent.class.getName());

  // ----------------------------------------

  protected DUserReg document;

  protected Session session;

  // ----------------------------------------

  public CreateUserAgent() {
    // Empty
  }

  // ----------------------------------------

  public void init(MDocument document, Session session) {
    this.document = (DUserReg) document;

    this.session = session;
  }

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception {

    MWrkTransSet wrkTransSet = wrkTransByName.values().toArray( //
        new MWrkTrans[0])[0].getWrkTransSetRef();

    log.debug("execute: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());

    if (wrkTransSet.getWrkTransList().size() > 1) {
      throw new IllegalStateException(
          "wrkTransSet.getWrkTransList().size() > 1");
    }

    MUser user = new MUser();

    user.setUser(document.getUser());
    user.setPass(document.getPass());
    user.setLanguage("en");
    user.setCountry("US");
    user.setVariant("");

    for (DUserRegProf userRegProf : document.getUserRegProfList()) {
      MProf prof = userRegProf.getProfRef();

      MUserProf userProf = new MUserProf();

      user.getUserProfList().add(userProf);
      userProf.setUserRef(user);

      prof.getUserProfList().add(userProf);
      userProf.setProfRef(prof);
    }

    session.saveOrUpdate(user);

    return wrkTransSet.getWrkTransList().get(0);
  }

  // ----------------------------------------

  public void executePre(MWrkTransSet wrkTransSet) //
      throws Exception {
    log.debug("executePre: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());
  }

  public void executePos(MWrkTransSet wrkTransSet) //
      throws Exception {
    log.debug("executePos: " + wrkTransSet.getId() + ";" + //
        wrkTransSet.getNetTransSetRef().getName());
  }
}
