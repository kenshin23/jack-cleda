/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Demián Gutierrez
 */
public class ObjectBean {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String language;

  private String country;
  private String variant;

  private String resName;
  private String clsName;
  private String pckName;
  private String extName;

  private List<MethodBean> methodBeanList = new ArrayList<MethodBean>();

  // ----------------------------------------

  public ObjectBean() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  // ----------------------------------------

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  // ----------------------------------------

  public String getVariant() {
    return variant;
  }

  public void setVariant(String variant) {
    this.variant = variant;
  }

  // ----------------------------------------

  public String getResName() {
    return resName;
  }

  public void setResName(String resName) {
    this.resName = resName;
  }

  // ----------------------------------------

  public String getClsName() {
    return clsName;
  }

  public void setClsName(String clsName) {
    this.clsName = clsName;
  }

  // ----------------------------------------

  public String getPckName() {
    return pckName;
  }

  public void setPckName(String pckName) {
    this.pckName = pckName;
  }

  // ----------------------------------------

  public String getExtName() {
    return extName;
  }

  public void setExtName(String extName) {
    this.extName = extName;
  }

  // ----------------------------------------

  public List<MethodBean> getMethodBeanList() {
    return methodBeanList;
  }

  public void setMethodBeanList(List<MethodBean> methodBeanList) {
    this.methodBeanList = methodBeanList;
  }
}
