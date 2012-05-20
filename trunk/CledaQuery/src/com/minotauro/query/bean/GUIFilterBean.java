package com.minotauro.query.bean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 11, 2008
 */
public interface GUIFilterBean extends FilterBean {

  public static final String ORDER = "order";

  /**
   * Gets the label to be displayed in the GUI next to the filter itself
   * @return the label displayed in the GUI
   */
  public String getLabel();

  /**
   * Gets the position in which this filter will be displayed in relation to the overall filter list
   * @return the order of this filter
   */
  public int getOrder();

  public void reset();

  public GUIFilterBean clone();
}