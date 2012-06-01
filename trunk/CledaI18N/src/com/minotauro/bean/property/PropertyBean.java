/*
 * Created on 04/11/2006
 */
package com.minotauro.bean.property;

/**
 * @author Demián Gutierrez
 */
public class PropertyBean {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  public String propertyName;
  public String propertyValue;

  // ----------------------------------------

  public PropertyBean() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public String getPropertyName() {
    return propertyName;
  }

  public void setPropertyName(String propertyName) {
    this.propertyName = propertyName;
  }

  // ----------------------------------------

  public String getPropertyValue() {
    return propertyValue;
  }

  public void setPropertyValue(String propertyValue) {
    this.propertyValue = propertyValue;
  }
}
