/*
 * Created on 17/09/2008
 */
package com.minotauro.echo.cleda.list.var;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
public class EJointModel {

  // RGH, MID
  protected Map/* */<MBase, MBase> midMap = //
  new LinkedHashMap<MBase, MBase>();

  protected Class<? extends MBase> midCls;

  protected MBase/*              */lftObj;

  // --------------------------------------------------------------------------------

  protected String lftLstProperty;

  protected String lftRefProperty;
  protected String rghRefProperty;

  // --------------------------------------------------------------------------------

  public EJointModel() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(MBase lftObj, Class<? extends MBase> midCls,
      String lftLstProperty, String lftRefProperty, String rghRefProperty) {

    this.lftObj/*     */= lftObj;
    this.midCls/*     */= midCls;

    this.lftLstProperty = lftLstProperty;
    this.lftRefProperty = lftRefProperty;
    this.rghRefProperty = rghRefProperty;

    cancel();
  }

  // --------------------------------------------------------------------------------

  public MBase createNewMid() {
    try {
      return midCls.newInstance();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public MBase get(MBase rghObj) {
    return midMap.get(rghObj);
  }

  // --------------------------------------------------------------------------------

  public MBase add(MBase rghObj) {
    MBase ret = createNewMid();

    failsafeSetProperty(ret, lftRefProperty, lftObj);
    failsafeSetProperty(ret, rghRefProperty, rghObj);

    midMap.put(rghObj, ret);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public void add(MBase rghObj, MBase midObj) {
    failsafeSetProperty(midObj, lftRefProperty, lftObj);
    failsafeSetProperty(midObj, rghRefProperty, rghObj);

    midMap.put(rghObj, midObj);
  }

  // --------------------------------------------------------------------------------

  public MBase del(MBase rghObj) {
    MBase ret = get(rghObj);

    if (ret == null) {
      return null;
    }

    midMap.remove(rghObj);

    return ret;
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public void accept() {
    Collection<MBase> collection =
        (Collection<MBase>) failsafeGetProperty(
            lftObj, lftLstProperty);

    collection.clear();
    collection.addAll(midMap.values());
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public void cancel() {
    midMap.clear();

    Collection<MBase> collection =
        (Collection<MBase>) failsafeGetProperty(
            lftObj, lftLstProperty);

    for (MBase mid : collection) {
      midMap.put((MBase) failsafeGetProperty(
          mid, rghRefProperty), mid);
    }
  }

  // --------------------------------------------------------------------------------

  protected Object/**/failsafeGetProperty(Object bean, String name) {
    try {
      return PropertyUtils.getProperty(bean, name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void/*  */failsafeSetProperty(Object bean, String name,
      Object value) {

    try {
      PropertyUtils.setProperty(bean, name, value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public Map<MBase, MBase> getMidMap() {
    return midMap;
  }

  // --------------------------------------------------------------------------------

  public MBase getLftObj() {
    return lftObj;
  }

  // --------------------------------------------------------------------------------

  public String getLftLstProperty() {
    return lftLstProperty;
  }

  public String getLftRefProperty() {
    return lftRefProperty;
  }

  public String getRghRefProperty() {
    return rghRefProperty;
  }
}
