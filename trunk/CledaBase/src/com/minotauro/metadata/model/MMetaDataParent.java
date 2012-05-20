/*
 * Created on 11/01/2008
 */
package com.minotauro.metadata.model;

/**
 * @author Demián Gutierrez
 */
public interface MMetaDataParent {

  public String/**/getMetaData(String key);

  public void/*  */setMetaData(String key, String val);
}
