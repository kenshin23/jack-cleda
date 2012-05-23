// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package com.minotauro.workflow.i18n;

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class EEngPetriI18N {

  public static final Locale locale = new Locale("en", "", "");

  public static final String RES_NAME = "com.minotauro.workflow.i18n.EEngPetri";

  private EEngPetriI18N() {
    // Empty
  }

  public static String engPlaceNotFoundForNetPlace(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "engPlaceNotFoundForNetPlace", new Object[]{arg0});
  }

  public static String engTransNotFoundForNetTrans(Object arg0) throws MessageException {
    return MessageBase.getInstance().locateValue(locale, RES_NAME, "engTransNotFoundForNetTrans", new Object[]{arg0});
  }

}
