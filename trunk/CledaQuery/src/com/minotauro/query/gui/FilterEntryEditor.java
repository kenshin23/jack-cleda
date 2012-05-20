package com.minotauro.query.gui;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 11, 2008
 */
public interface FilterEntryEditor {

  /**
   * Leaves the underlying filterBean ready for use. Usually by synchronizing the filter's GUI with the bean
   */
  public void sync();

  /**
   * Resets both GUI and FilterBean, leaving it just as if the filter was just created.
   */
  public void reset();
}