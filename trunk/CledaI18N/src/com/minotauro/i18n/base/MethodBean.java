/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import org.apache.commons.lang.StringUtils;

/**
 * @author Demián Gutierrez
 */
public class MethodBean {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;
  private String key;

  private int args;

  // ----------------------------------------

  public MethodBean() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ----------------------------------------

  public String getCapitalizedName() {
    return StringUtils.capitalize(name);
  }

  // ----------------------------------------

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  // ----------------------------------------

  public int getArgs() {
    return args;
  }

  public void setArgs(int args) {
    this.args = args;
  }
}
