/*
 * Created on 24/12/2006
 */
package com.minotauro.sandbox.gui.mcrudb;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();
    m.i18n(FrmMCrudBEdit.class.getSimpleName());
    m.i18n(FrmMCrudBList.class.getSimpleName());
  }
}
