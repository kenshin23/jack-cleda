/*
 * Created on 04/11/2006
 */
package com.minotauro.bean.property;

import java.util.List;

/**
 * @author Demián Gutierrez
 */
public class ObjectBean {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String resName;
  private String clsName;
  private String pckName;
  private String extName;

  private List<PropertyBean> propertyBeanList;

  // ----------------------------------------

  public ObjectBean() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
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

  public List<PropertyBean> getPropertyBeanList() {
    return propertyBeanList;
  }

  public void setPropertyBeanList(List<PropertyBean> propertyBeanList) {
    this.propertyBeanList = propertyBeanList;
  }
}
