package com.minotauro.user.i18n;

import com.minotauro.i18n.base.BaseI18NMain;
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MUser;

/** 
 * @author Alejandro Salas 
 * <br> Created on Feb 8, 2008
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();

    m.i18n(MUser.class.getSimpleName());
    m.i18n(MProf.class.getSimpleName());
    m.i18n(MPriv.class.getSimpleName());
  }
}