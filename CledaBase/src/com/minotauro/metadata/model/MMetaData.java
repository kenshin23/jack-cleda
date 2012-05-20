/*
 * Created on 30/12/2005
 */
package com.minotauro.metadata.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@MappedSuperclass
public abstract class MMetaData extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String key;

  private String val;

  // --------------------------------------------------------------------------------

  public MMetaData() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  @Column(name = "okey")
  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  // --------------------------------------------------------------------------------

  @Column(name = "oval")
  public String getVal() {
    return val;
  }

  public void setVal(String val) {
    this.val = val;
  }

  // --------------------------------------------------------------------------------

  @Transient
  public abstract MMetaDataParent getParent();

  public abstract void/*        */setParent(MMetaDataParent MetaDataParent);

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  public static String getMetaData(Map<String, ?> metaDataMap, String key) {
    MMetaData metaData = (MMetaData) metaDataMap.get(key);

    if (metaData == null) {
      return null;
    }

    return metaData.getVal();
  }

  // --------------------------------------------------------------------------------

  public static void setMetaData(Map<String, ?> metaDataMap, String key, //
      String val, MMetaDataParent metaDataParent, Class<?> metaDataClass) {

    try {
      failsafeSetMetaData( //
          metaDataMap, key, val, metaDataParent, metaDataClass);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  protected static void failsafeSetMetaData(Map<String, ?> metaDataMap, String key, //
      String val, MMetaDataParent metaDataParent, Class<?> metaDataClass) //
      throws Exception {

    MMetaData metaData = (MMetaData) metaDataMap.get(key);

    if (metaData == null) {
      metaData = (MMetaData) metaDataClass.newInstance();
      metaData.setKey(key);

      ((Map<String, MMetaData>) metaDataMap).put(key, metaData);
      metaData.setParent(metaDataParent);
    }

    metaData.setVal(val);
  }
}
