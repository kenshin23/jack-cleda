/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

import java.util.ArrayList;
import java.util.List;

import com.minotauro.echo.validator.base.ValidatorList;
import com.minotauro.echo.wrapper.base.ComponentWrapperMap;

/**
 * @author Demián Gutierrez
 */
public abstract class BaseModel {

  public enum ValidationMode {
    ENABLED, VISIBLE, ALWAYS
  }

  // --------------------------------------------------------------------------------

  protected ValidationMode validationMode =
      ValidationMode.ENABLED;

  protected ValidatorList validatorList =
      new ValidatorList();

  // --------------------------------------------------------------------------------

  protected List<BaseModel> childrenList =
      new ArrayList<BaseModel>();

  // --------------------------------------------------------------------------------

  protected FieldStateProxy fieldStateProxy;

  protected BaseModel parent;

  protected Object labelCmp;

  protected String key;

  protected boolean localEnabled = true;
  protected boolean localVisible = true;

  // --------------------------------------------------------------------------------

  public BaseModel() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public ValidationMode getValidationMode() {
    return validationMode;
  }

  public void setValidationMode(ValidationMode validationMode) {
    this.validationMode = validationMode;
  }

  // --------------------------------------------------------------------------------

  public ValidatorList getValidatorList() {
    return validatorList;
  }

  public void setValidatorList(ValidatorList validatorList) {
    this.validatorList = validatorList;
  }

  // --------------------------------------------------------------------------------

  public List<BaseModel> getChildrenList() {
    return childrenList;
  }

  public void setChildrenList(List<BaseModel> childrenList) {
    this.childrenList = childrenList;
  }

  // --------------------------------------------------------------------------------

  public void addChild(BaseModel child) {
    childrenList.add(child);
    child.setParent(this);
  }

  // --------------------------------------------------------------------------------

  public FieldStateProxy getFieldStateProxy() {
    if (fieldStateProxy != null) {
      return fieldStateProxy;
    }

    return parent != null
        ? parent.getFieldStateProxy()
        : null;
  }

  public void setFieldStateProxy(FieldStateProxy fieldStateProxy) {
    this.fieldStateProxy = fieldStateProxy;
  }

  // --------------------------------------------------------------------------------

  public BaseModel getParent() {
    return parent;
  }

  public void setParent(BaseModel parent) {
    this.parent = parent;
  }

  // --------------------------------------------------------------------------------

  public Object getLabelCmp() {
    return labelCmp;
  }

  public void setLabelCmp(Object labelCmp) {
    this.labelCmp = labelCmp;
  }

  // --------------------------------------------------------------------------------

  public Object getLabelVal() {
    return //
    ComponentWrapperMap.getInstance().getValue(labelCmp);
  }

  public void setLabelVal(Object labelValue) {
    ComponentWrapperMap.getInstance().setValue(labelCmp,
        labelValue);
  }

  // --------------------------------------------------------------------------------

  public void setLabelEnabled(boolean labelEnabled) {
    ComponentWrapperMap.getInstance().setEnabled(labelCmp,
        labelEnabled);
  }

  // --------------------------------------------------------------------------------

  public void setLabelVisible(boolean labelVisible) {
    ComponentWrapperMap.getInstance().setVisible(labelCmp,
        labelVisible);
  }

  // --------------------------------------------------------------------------------

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  // --------------------------------------------------------------------------------

  public ValidatorList initValidatorList() {
    ValidatorList ret = new ValidatorList();

    boolean enabled = isVisible() && isEnabled();
    boolean visible = isVisible();

    switch (validationMode) {
      case ENABLED :
        if (enabled) {
          ret.add(validatorList);
        }
        break;
      case VISIBLE :
        if (visible) {
          ret.add(validatorList);
        }
        break;
      default :
        ret.add(validatorList);
    }

    for (BaseModel baseModel : getChildrenList()) {
      ValidatorList validatorList = baseModel.initValidatorList();

      if (validatorList != null) {
        ret.add(validatorList);
      }
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public List<BaseModel> getVisibleChildrenList() {
    List<BaseModel> ret = new ArrayList<BaseModel>();

    for (BaseModel child : childrenList) {
      if (child.isProxyVisible()) {
        ret.add(child);
      }
    }

    return ret;
  }

  // --------------------------------------------------------------------------------
  // Enabled / Visible Methods
  // --------------------------------------------------------------------------------

  public boolean isProxyEnabled() {
    if (!parent.isProxyEnabled()) {
      return false;
    }

    if (key == null) {
      return true;
    }

    FieldStateProxy fieldStateProxy =
        getFieldStateProxy();

    return fieldStateProxy != null
        ? fieldStateProxy.isFieldEnabled(getKey())
        : true;
  }

  // --------------------------------------------------------------------------------

  public boolean isProxyVisible() {
    if (!parent.isProxyVisible()) {
      return false;
    }

    if (key == null) {
      return true;
    }

    FieldStateProxy fieldStateProxy =
        getFieldStateProxy();

    return fieldStateProxy != null
        ? fieldStateProxy.isFieldVisible(getKey())
        : true;
  }

  // --------------------------------------------------------------------------------

  public boolean isLocalEnabled() {
    return localEnabled;
  }

  public void setLocalEnabled(boolean localEnabled) {
    this.localEnabled = localEnabled;
  }

  // --------------------------------------------------------------------------------

  public boolean isLocalVisible() {
    return localVisible;
  }

  public void setLocalVisible(boolean localVisible) {
    this.localVisible = localVisible;
  }

  // --------------------------------------------------------------------------------

  public boolean isEnabled() {
    return isProxyEnabled() && isLocalEnabled();
  }

  // --------------------------------------------------------------------------------

  public boolean isVisible() {
    for (BaseModel baseModel : getChildrenList()) {
      if (baseModel.isVisible()) {
        return isProxyVisible() && isLocalVisible();
      }
    }

    return false;
  }
}
