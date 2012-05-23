// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class EEngPetriSelectI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.EEngPetriSelect";

  private EEngPetriSelectI18N() {
    // Empty
  }

  public static String invalidEdgeDirection(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "invalidEdgeDirection", new Object[]{arg0});
  }

  public static String netPetriNqNull(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "netPetriNqNull", new Object[]{arg0});
  }

  public static String workflowNqNull(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "workflowNqNull", new Object[]{arg0});
  }

}
