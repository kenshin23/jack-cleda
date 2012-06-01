/*
 * Created on 24/04/2007
 */
package com.minotauro.cleda.util;

/**
 * @author Demián Gutierrez
 */
public class LabelValueBean<T> {

  protected String label;
  protected T value;

  // ----------------------------------------

  public LabelValueBean() {
    // Empty
  }

  public LabelValueBean(String label, T value) {
    this.label = label;
    this.value = value;
  }

  // ----------------------------------------

  public String getLabel() {
    return label;
  }

  public void setLabel(String label) {
    this.label = label;
  }

  // ----------------------------------------

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  // ----------------------------------------

  @Override
  public String toString() {
    return label;
  }
}
