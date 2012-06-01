/*
 * Created on 10/10/2007
 */
package com.minotauro.i18n.demo;

import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class _CledaI18N extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    _CledaI18N m = new _CledaI18N();
    m.i18n("TestI18NFoo");
    m.numb("TestNumbFoo");
    m.date("TestDateFoo");
    m.prop(FooBean.class);
  }
}
