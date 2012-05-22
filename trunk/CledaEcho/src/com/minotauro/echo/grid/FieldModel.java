/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

import java.util.List;

import com.minotauro.echo.wrapper.base.ComponentWrapperMap;

/**
 * @author Demián Gutierrez
 */
public class FieldModel extends BaseModel {

  protected String property;

  protected Object fieldCmp;

  // --------------------------------------------------------------------------------

  public FieldModel() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  // --------------------------------------------------------------------------------

  public Object getFieldCmp() {
    return fieldCmp;
  }

  public void setFieldCmp(Object fieldCmp) {
    this.fieldCmp = fieldCmp;
  }

  // --------------------------------------------------------------------------------

  public Object getFieldVal() {
    return //
    ComponentWrapperMap.getInstance().getValue(fieldCmp);
  }

  public void setFieldVal(Object fieldVal) {
    ComponentWrapperMap.getInstance().setValue(fieldCmp,
        fieldVal);
  }

  // --------------------------------------------------------------------------------

  public void setFieldEnabled(boolean fieldEnabled) {
    ComponentWrapperMap.getInstance().setEnabled(fieldCmp,
        fieldEnabled);
  }

  // --------------------------------------------------------------------------------

  public void setFieldVisible(boolean fieldVisible) {
    ComponentWrapperMap.getInstance().setVisible(fieldCmp,
        fieldVisible);
  }

  // --------------------------------------------------------------------------------
  // BaseModel Methods
  // --------------------------------------------------------------------------------

  @Override
  public void addChild(BaseModel child) {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  @Override
  public void setChildrenList(List<BaseModel> childrenList) {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  @Override
  public List<BaseModel> getVisibleChildrenList() {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  @Override
  public boolean isVisible() {
    return isProxyVisible() && isLocalVisible();
  }
}
