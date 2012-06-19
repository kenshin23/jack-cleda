package com.minotauro.query.bean.gui;

import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.base.GUIFilterBeanImpl;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 17, 2008
 */
public class ReferenceFilterBean extends GUIFilterBeanImpl {

  private Object data;

  public ReferenceFilterBean(String property, String label, int order) {
    super(property, label, order);
  }

  @Override
  public boolean isActive() {
    //  Because it's a single combo, NO_FILTER comes in the value1 field
    relationType1 = value1.equals(RelationType.NO_FILTER.name()) ? RelationType.NO_FILTER : RelationType.EQUAL;
    return super.isActive();
  }

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }
}