/*
 * Created on 26/06/2008
 */
package com.minotauro.echo.wrapper.base;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Demián Gutierrez
 */
public class ComponentWrapperMap implements ComponentWrapper {

  protected static final Logger log = LoggerFactory.getLogger( //
      ComponentWrapperMap.class.getName());

  // --------------------------------------------------------------------------------

  protected static ComponentWrapperMap instance =
      new ComponentWrapperMap();

  // --------------------------------------------------------------------------------

  protected Map<Object, ComponentWrapper> componentWrapperMap =
      new HashMap<Object, ComponentWrapper>();

  // --------------------------------------------------------------------------------

  private ComponentWrapperMap() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static ComponentWrapperMap getInstance() {
    return instance;
  }

  // --------------------------------------------------------------------------------

  protected ComponentWrapper getComponentWrapper(Object component) {

    if (component == null) {
      throw new IllegalArgumentException(
          "component == null");
    }

    ComponentWrapper componentWrapper =
        componentWrapperMap.get(component);

    //    if (componentWrapper == null) {
    //      throw new IllegalArgumentException(
    //          component.getClass().getName());
    //    }

    return componentWrapper;
  }

  // --------------------------------------------------------------------------------

  public void addComponentWrapper(
      Object component, ComponentWrapper componentWrapper) {

    log.debug("add component wrapper for " + component +
        " / " + componentWrapper.getClass());

    componentWrapperMap.put(component, componentWrapper);
  }

  // --------------------------------------------------------------------------------
  // ComponentWrapper
  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    log.debug("pre getting value " + component);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return null;
    }

    Object ret = componentWrapper.getValue(component);
    log.debug("pos getting value " + component + " / " + ret);
    return ret;
  }

  // --------------------------------------------------------------------------------

  public void setValue(Object component, Object value) {
    log.debug("setting value " + component + " / " + value);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return;
    }

    componentWrapper.setValue(component, value);
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    log.debug("pre getting enabled " + component);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return true;
    }

    boolean ret = componentWrapper.getEnabled(component);
    log.debug("pos getting enabled " + component + " / " + ret);
    return ret;
  }

  public void setEnabled(Object component, boolean enabled) {
    log.debug("setting enabled " + component + " / " + enabled);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return;
    }

    componentWrapper.setEnabled(component, enabled);
  }

  // --------------------------------------------------------------------------------

  public boolean getVisible(Object component) {
    log.debug("pre getting visible " + component);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return true;
    }

    boolean ret = componentWrapper.getVisible(component);
    log.debug("pos getting visible " + component + " / " + ret);
    return ret;
  }

  public void setVisible(Object component, boolean visible) {
    log.debug("setting visible " + component + " / " + visible);

    ComponentWrapper componentWrapper =
        ComponentWrapperMap.getInstance().getComponentWrapper(
            component.getClass());

    // TODO: Beware
    if (componentWrapper == null) {
      return;
    }

    componentWrapper.setVisible(component, visible);
  }
}
