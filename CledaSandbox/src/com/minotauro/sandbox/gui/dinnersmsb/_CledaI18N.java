/*
 * Created on 24/12/2006
 */
package com.minotauro.sandbox.gui.dinnersmsb;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N d = new _CledaI18N();
    d.i18n(FrmDInnerSmsEdit.class.getSimpleName());
    d.i18n(FrmDInnerSmsList.class.getSimpleName());
  }
}
