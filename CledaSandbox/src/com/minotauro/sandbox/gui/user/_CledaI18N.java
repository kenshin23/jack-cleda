/*
 * Created on 24/12/2006
 */
package com.minotauro.sandbox.gui.user;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();
    m.i18n(FrmMProfEdit.class.getSimpleName());
    m.i18n(FrmMProfList.class.getSimpleName());
    m.i18n(FrmMProfPrivList.class.getSimpleName());
    m.i18n(FrmMProfRoleList.class.getSimpleName());
    m.i18n(FrmMUserEdit.class.getSimpleName());
    m.i18n(FrmMUserList.class.getSimpleName());
    m.i18n(FrmMUserProfList.class.getSimpleName());

    m.i18n("Menu");
  }
}
