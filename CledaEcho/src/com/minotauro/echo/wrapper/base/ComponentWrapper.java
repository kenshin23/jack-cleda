/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.wrapper.base;

/**
 * @author Demián Gutierrez
 */
public interface ComponentWrapper {

  public Object/**/getValue(Object component);

  public void/*  */setValue(Object component,
      Object value);

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component);

  public void/**/setEnabled(Object component,
      boolean enabled);

  // --------------------------------------------------------------------------------

  public boolean getVisible(Object component);

  public void/**/setVisible(Object component,
      boolean visible);
}
