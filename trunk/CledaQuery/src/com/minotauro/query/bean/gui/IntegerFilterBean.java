package com.minotauro.query.bean.gui;

import com.minotauro.query.bean.base.GUIFilterBeanImpl;

/** 
 * Dummy bean, needed for filter registry 
 * @author Alejandro Salas 
 * <br> Created on Mar 17, 2008
 */
public class IntegerFilterBean extends GUIFilterBeanImpl {

  public IntegerFilterBean(String property, String label, int order) {
    super(property, label, order);
  }
}