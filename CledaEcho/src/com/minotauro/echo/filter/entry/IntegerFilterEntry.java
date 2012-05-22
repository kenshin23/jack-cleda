package com.minotauro.echo.filter.entry;

import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 17, 2008
 */
public class IntegerFilterEntry extends NumberFilterEntry {

  public IntegerFilterEntry(GUIFilterBean filterBean) {
    super(filterBean, NumberFilterDataType.INTEGER);
  }
}