package com.minotauro.query.bean.base;

import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 10, 2008
 */
public class GUIFilterBeanImpl extends FilterBeanBase implements GUIFilterBean {

  protected String label;
  protected int order; // order is used by reflection!

  protected Object value1;
  protected Object value2;

  protected RelationType relationType1;
  protected RelationType relationType2;

  public GUIFilterBeanImpl(String property, String label, int order) {
    super(property);
    this.label = label;
    this.order = order;
  }

  //----------------------------------------

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  //----------------------------------------

  public RelationType getRelationType1() {
    return relationType1;
  }

  public void setRelationType1(RelationType relationType1) {
    this.relationType1 = relationType1;
  }

  //----------------------------------------

  public RelationType getRelationType2() {
    return relationType2;
  }

  public void setRelationType2(RelationType relationType2) {
    this.relationType2 = relationType2;
  }

  //----------------------------------------

  public Object getValue1() {
    return value1;
  }

  public void setValue1(Object value1) {
    this.value1 = value1;
  }

  //----------------------------------------

  public Object getValue2() {
    return value2;
  }

  public void setValue2(Object value2) {
    this.value2 = value2;
  }

  //----------------------------------------

  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  //----------------------------------------

  @Override
  public boolean isActive() {
    return relationType1 != RelationType.NO_FILTER;
  }

  @Override
  public String createQuery(int index) {
    StringBuilder queryStrBld = new StringBuilder(property);
    parameterMap.clear();

    queryStrBld.append(relationType1.getOperator());
    handleParams(queryStrBld, relationType1, getRealParamName(index++), value1);

    if (relationType2 != null && relationType2 != RelationType.NO_FILTER) {
      queryStrBld.append(" AND ");
      queryStrBld.append(relationType2.getOperator());

      handleParams(queryStrBld, relationType2, getRealParamName(index++), value2);
    }

    this.index = index;
    return queryStrBld.toString();
  }

  public void reset() {
    value1 = null;
    value2 = null;
    relationType1 = null;
    relationType2 = null;
  }

  @Override
  public String toString() {
    return label;
  }

  @Override
  public GUIFilterBeanImpl clone() {
    GUIFilterBeanImpl clone = (GUIFilterBeanImpl) super.clone();
    clone.value1 = cloneValue(value1);
    clone.value2 = cloneValue(value2);

    return clone;
  }
}