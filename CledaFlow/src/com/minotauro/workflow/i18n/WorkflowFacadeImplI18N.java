// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class WorkflowFacadeImplI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.WorkflowFacadeImpl";

  private WorkflowFacadeImplI18N() {
    // Empty
  }

  public static String firingAuto(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "firingAuto", new Object[]{arg0});
  }

  public static String firingUser(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "firingUser", new Object[]{arg0});
  }

  public static String netPetriNullForName(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "netPetriNullForName", new Object[]{arg0});
  }

  public static String wrkTransNullForTrans(Object arg0, Object arg1) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "wrkTransNullForTrans", new Object[]{arg0, arg1});
  }

}
