// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class EEngPetriTransI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.EEngPetriTrans";

  private EEngPetriTransI18N() {
    // Empty
  }

  public static String agentNotDefined(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "agentNotDefined", new Object[]{arg0});
  }

  public static String netTransWasNull(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "netTransWasNull", new Object[]{arg0});
  }

}
