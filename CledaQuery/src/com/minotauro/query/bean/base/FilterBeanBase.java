package com.minotauro.query.bean.base;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.FilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 7, 2008
 */
public abstract class FilterBeanBase implements FilterBean {

  protected String property;
  protected Map<String, Object> parameterMap = new HashMap<String, Object>();
  protected int index;

  protected FilterBeanBase() {
    // Empty
  }

  //----------------------------------------

  protected FilterBeanBase(String property) {
    this.property = property;
  }

  //----------------------------------------

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  //----------------------------------------

  public Map<String, Object> getParameterMap() {
    return parameterMap;
  }

  public void setParameterMap(Map<String, Object> parameterMap) {
    this.parameterMap = parameterMap;
  }

  //----------------------------------------

  public int getIndex() {
    return index;
  }

  //----------------------------------------

  protected void handleParams(StringBuilder queryStrBld, RelationType relationType, String paramName, Object value) {
    switch (relationType) {
      case IS_NULL :
      case NOT_NULL :
      case CUSTOM :
        // Add no params
        break;
      case LIKE :
        value = "%" + value + "%";
      default :
        queryStrBld.append(paramName);
        parameterMap.put(paramName, value);
        break;
    }
  }

  //----------------------------------------

  /**
   *  Adding an index to differentiate several parameters for the same property.
   *  Must replace dots, otherwise hibernate think it's some kind of nested property (throws a NullPointerException).    
   */
  public String getRealParamName(int index) {
    return (property + index).replace('.', '_');
  }

  @Override
  public FilterBeanBase clone() {
    FilterBeanBase clone;
    try {
      clone = (FilterBeanBase) super.clone();
      clone.parameterMap = new HashMap<String, Object>();
      for (Entry<String, Object> entry : parameterMap.entrySet()) {
        clone.parameterMap.put(entry.getKey(), cloneValue(entry.getValue()));
      }

    } catch (CloneNotSupportedException e) {
      // This shouldn't happen, since we are Cloneable
      throw new InternalError();
    }

    return clone;
  }

  /**
   * Attempts to clone a value through reflection, if an exception occurs, then the same value will be returned
   * @param value
   * @return clonedValue
   */
  protected Object cloneValue(Object value) {
    if (value == null) {
      return null;
    }

    Object clone;

    Class<?> clazz = value.getClass();
    try {
      Method method = clazz.getMethod("clone");
      clone = method.invoke(value);
    } catch (Exception e) {
      clone = value;
    }

    return clone;
  }
}