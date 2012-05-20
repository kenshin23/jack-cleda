package com.minotauro.query.bean;

import java.util.Map;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 11, 2008
 */
public interface FilterBean extends Cloneable {

  public boolean isActive();

  public String createQuery(int index);

  public Map<String, Object> getParameterMap();

  public FilterBean clone();

  /**
   * Returns the updated index after createQuery it's called.
   * Because we can have a lot of different queries on the same property, therefore a lot of parameters with the same name
   * We must differentiate between those variables. That's why an index is needed. Check default implementations.  
   * @return index
   */
  public int getIndex();
}