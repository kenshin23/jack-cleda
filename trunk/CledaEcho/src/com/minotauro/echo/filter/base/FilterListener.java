package com.minotauro.echo.filter.base;

import java.util.EventListener;

import com.minotauro.echo.beans.FilterEvent;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 11, 2007
 */
public interface FilterListener extends EventListener {

  public enum FilterListenerMethod {
    SEARCH, SEARCH_ALL, RESET_FILTER;
  };

  public void search(FilterEvent evt);

  public void searchAll(FilterEvent evt);

  public void resetFilter(FilterEvent evt);
}