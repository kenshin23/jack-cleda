/*
 * Created on 24/12/2006
 */
package com.minotauro.user.model;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();

    m.prop(MUser.class);
    m.prop(MUserProf.class);
    m.prop(MProf.class);
    m.prop(MProfPriv.class);
    m.prop(MPriv.class);
    m.prop(MProfRole.class);
    m.prop(MRole.class);
  }
}
