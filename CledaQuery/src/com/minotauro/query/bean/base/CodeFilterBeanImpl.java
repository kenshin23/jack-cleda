package com.minotauro.query.bean.base;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.minotauro.query.QueryCreator.RelationType;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 10, 2008
 */
public class CodeFilterBeanImpl extends FilterBeanBase {

  private Object value;
  private RelationType relationType;

  private String customCondition;

  /**
   * Creates a standard code filter. Eg: foo > 2
   * @param property The property that will be filtered on the underlying object
   * @param relationType The logical operator that will be applied to the filter (<, >, ==, etc)
   * @param value The actual value which will be tested 
   */
  public CodeFilterBeanImpl(String property, RelationType relationType, Object value) {
    super(property);
    this.value = value;
    this.relationType = relationType;
  }

  //----------------------------------------

  /**
   * Creates a code filter with a custom condition
   * @param customCondition The condition for this bean
   * @param parameterMap All parameters to be included with the condition, if any. Can be null as long as the condition doesn't
   * request any parameters.
   */
  public CodeFilterBeanImpl(String customCondition, Map<String, Object> parameterMap) {
    this.customCondition = customCondition;
    this.parameterMap = parameterMap;
  }

  //----------------------------------------

  public String getCustomCondition() {
    return customCondition;
  }

  public void setCustomCondition(String customCondition) {
    this.customCondition = customCondition;
  }

  //----------------------------------------

  public RelationType getRelationType() {
    return relationType;
  }

  public void setRelationType(RelationType relationType) {
    this.relationType = relationType;
  }

  //----------------------------------------

  public Object getValue() {
    return value;
  }

  public void setValue(Object value) {
    this.value = value;
  }

  //----------------------------------------

  @Override
  public boolean isActive() {
    return relationType != RelationType.NO_FILTER;
  }

  @Override
  public String createQuery(int index) {
    this.index = index;
    if (customCondition != null) {
      this.index += StringUtils.countMatches(customCondition, ":");
      return customCondition;
    }

    StringBuilder queryStrBld = new StringBuilder(property);
    parameterMap.clear();

    queryStrBld.append(relationType.getOperator());
    handleParams(queryStrBld, relationType, getRealParamName(this.index++), value);

    return queryStrBld.toString();
  }

  @Override
  public CodeFilterBeanImpl clone() {
    CodeFilterBeanImpl clone = (CodeFilterBeanImpl) super.clone();
    clone.value = cloneValue(value);

    return clone;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (!(obj instanceof CodeFilterBeanImpl)) {
      return false;
    }

    return property.equals(((CodeFilterBeanImpl) obj).property);
  }
}