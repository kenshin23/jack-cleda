package com.minotauro.echo.beans;

import java.util.EventObject;

import com.minotauro.echo.filter.base.FrmFilterEditor;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 11, 2007
 */
public class FilterEvent extends EventObject {

  public FilterEvent(FrmFilterEditor source) {
    super(source);
  }
}