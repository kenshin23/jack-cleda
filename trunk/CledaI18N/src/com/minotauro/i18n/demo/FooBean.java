/*
 * Created on 10/10/2007
 */
package com.minotauro.i18n.demo;

/**
 * @author Demián Gutierrez
 */
public class FooBean {

  protected String name;
  protected String desc;

  protected boolean empty;

  protected String camelCase;

  protected String noSetter;
  protected String noGetter;

  // ----------------------------------------

  public FooBean() {
    // Empty
  }

  // ----------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ----------------------------------------

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  // ----------------------------------------

  public boolean isEmpty() {
    return empty;
  }

  public void setEmpty(boolean empty) {
    this.empty = empty;
  }

  // ----------------------------------------

  public String getCamelCase() {
    return camelCase;
  }

  public void setCamelCase(String camelCase) {
    this.camelCase = camelCase;
  }

  // ----------------------------------------

  // ----------------------------------------
  // intentionally removed
  // ----------------------------------------
  //  public String getNoGetter() {
  //    return noGetter;
  //  }
  // ----------------------------------------

  public void setNoGetter(String noGetter) {
    this.noGetter = noGetter;
  }

  // ----------------------------------------

  public String getNoSetter() {
    return noSetter;
  }

  // ----------------------------------------
  // intentionally removed
  // ----------------------------------------
  //  public void setNoSetter(String noSetter) {
  //    this.noSetter = noSetter;
  //  }
  // ----------------------------------------
}
