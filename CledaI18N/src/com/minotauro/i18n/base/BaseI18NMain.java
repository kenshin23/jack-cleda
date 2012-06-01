/*
 * Created on 10/10/2007
 */
package com.minotauro.i18n.base;

import java.util.Locale;

import org.apache.commons.lang.SystemUtils;

import com.minotauro.cleda.util.CledaLocaleUtils;
import com.minotauro.i18n.base.CodeGenerator.Mode;

/**
 * @author Demián Gutierrez
 */
public class BaseI18NMain {

  public static final String DEFAULT_LANGUAGE/**/= "en";
  public static final String DEFAULT_COUNTRY/* */= "US";
  public static final String DEFAULT_VARIANT/* */= null;

  // --------------------------------------------------------------------------------

  public BaseI18NMain() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected String getBasePath() {
    StringBuffer ret = new StringBuffer();

    ret.append(SystemUtils.USER_DIR);
    ret.append("/src/");
    ret.append(getClass().getPackage().getName().replace( //
        ".", SystemUtils.FILE_SEPARATOR));

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public void prop(Class<?> clazz) throws Exception {
    com.minotauro.bean.property.CodeGenerator codeGenerator;

    codeGenerator = new com.minotauro.bean.property.CodeGenerator( //
        clazz, getClass().getPackage().getName(), getBasePath());
    codeGenerator.run();
  }

  // --------------------------------------------------------------------------------

  public void i18n(String name, String language, String country, String variant) throws Exception {
    i18n(name, CledaLocaleUtils.createLocale(language, country, variant));
  }

  public void i18n(String name, Locale locale) throws Exception {
    CodeGenerator codeGenerator = new CodeGenerator( //
        locale, //
        getBasePath(), name, getBasePath(), //
        getClass().getPackage().getName(), //
        MessageBase.class.getName(), Mode.I18N);
    codeGenerator.run();
  }

  public void i18n(String name) throws Exception {
    i18n(name, DEFAULT_LANGUAGE, DEFAULT_COUNTRY, DEFAULT_VARIANT);
  }

  // --------------------------------------------------------------------------------

  public void numb(String name, String language, String country, String variant) throws Exception {
    numb(name, CledaLocaleUtils.createLocale(language, country, variant));
  }

  public void numb(String name, Locale locale) throws Exception {
    CodeGenerator codeGenerator = new CodeGenerator( //
        locale, //
        getBasePath(), name, getBasePath(), //
        getClass().getPackage().getName(), //
        MessageBase.class.getName(), Mode.NUMB);
    codeGenerator.run();
  }

  public void numb(String name) throws Exception {
    numb(name, DEFAULT_LANGUAGE, DEFAULT_COUNTRY, DEFAULT_VARIANT);
  }

  // --------------------------------------------------------------------------------

  public void date(String name, String language, String country, String variant) throws Exception {
    date(name, CledaLocaleUtils.createLocale(language, country, variant));
  }

  public void date(String name, Locale locale) throws Exception {
    CodeGenerator codeGenerator = new CodeGenerator( //
        locale, //
        getBasePath(), name, getBasePath(), //
        getClass().getPackage().getName(), //
        MessageBase.class.getName(), Mode.DATE);
    codeGenerator.run();
  }

  public void date(String name) throws Exception {
    date(name, DEFAULT_LANGUAGE, DEFAULT_COUNTRY, DEFAULT_VARIANT);
  }
}
