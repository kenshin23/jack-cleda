/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.validator.base;

import java.util.ArrayList;
import java.util.List;

import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.wrapper.base.ComponentWrapperMap;

/**
 * @author Demián Gutierrez
 */
public abstract class BaseValidator {

  protected FieldValidProxy fieldValidProxy;

  protected BaseValidator parent;

  protected String complabel;
  protected Object component;

  protected String key;

  // --------------------------------------------------------------------------------

  public BaseValidator(String complabel, Object component) {
    this.complabel = complabel;
    this.component = component;
  }

  // --------------------------------------------------------------------------------
  // Props Methods
  // --------------------------------------------------------------------------------

  public FieldValidProxy getFieldValidProxy() {
    if (fieldValidProxy != null) {
      return fieldValidProxy;
    }

    if (parent == null) {
      return DumbFieldValidProxy.DUMB_FIELD_VALID_PROXY;
    }

    return parent.getFieldValidProxy();
  }

  public void setFieldValidProxy(FieldValidProxy fieldValidProxy) {
    this.fieldValidProxy = fieldValidProxy;
  }

  // --------------------------------------------------------------------------------

  public BaseValidator getParent() {
    return parent;
  }

  public void setParent(BaseValidator parent) {
    this.parent = parent;
  }

  // --------------------------------------------------------------------------------

  public String getComplabel() {
    if (complabel != null) {
      return complabel;
    }

    if (parent == null) {
      throw new NullPointerException("parent == null");
    }

    return parent.getComplabel();
  }

  // --------------------------------------------------------------------------------

  public Object getComponent() {
    if (component != null) {
      return component;
    }

    if (parent == null) {
      throw new NullPointerException("parent == null");
    }

    return parent.getComponent();
  }

  // --------------------------------------------------------------------------------

  public String getKey() {
    if (key != null) {
      return key;
    }

    if (parent == null) {
      return null;
    }

    return parent.getKey();
  }

  public void setKey(String key) {
    this.key = key;
  }

  // --------------------------------------------------------------------------------
  // Validate methods
  // --------------------------------------------------------------------------------

  public List<String> validateAll(
      String command, ProcessContext processContext) {

    List<String> msgList = new ArrayList<String>();

    FieldValidProxy fieldValidProxy = getFieldValidProxy();

    if (!fieldValidProxy.isFieldValidable(getKey(), command)) {
      return msgList;
    }

    Object value = component != null
        ? ComponentWrapperMap.getInstance().getValue(component)
        : null;

    String msg = doValidate(value, processContext);

    if (msg != null) {
      msgList.add(msg);
    }

    return msgList;
  }

  // --------------------------------------------------------------------------------

  public abstract String doValidate(
      Object value, ProcessContext processContext);
}
