/*
 * Created on 24/12/2006
 */
package com.minotauro.echo.cleda.edit.wrk;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();
    m.i18n(FrmEditWrk.class.getSimpleName());
    m.i18n(FrmDocHistory.class.getSimpleName());
  }
}
