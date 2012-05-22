package com.minotauro.echo.filter.entry;

import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 17, 2008
 */
public class DecimalFilterEntry extends NumberFilterEntry {

  public DecimalFilterEntry(GUIFilterBean filterBean) {
    super(filterBean, NumberFilterDataType.DECIMAL);
  }
}