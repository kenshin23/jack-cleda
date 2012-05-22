package com.minotauro.echo.util;

import java.lang.reflect.Constructor;
import java.util.Properties;

import com.minotauro.cleda.config.ConfigFactory;
import com.minotauro.query.bean.GUIFilterBean;
import com.minotauro.query.gui.FilterEntryEditor;

/** 
 * @author Alejandro Salas 
 * <br> Created on Mar 17, 2008
 */
public class FilterEditorRegistry {

  public static final String QUERY_CONFIG = "DefaultQueryFilterConfig";

  private FilterEditorRegistry() {
    // Empty
  }

  public static FilterEntryEditor getEditor(GUIFilterBean filterBean) {
    FilterEntryEditor editor = null;
    try {
      editor = failsafeGetEditor(filterBean);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return editor;
  }

  @SuppressWarnings("unchecked")
  private static FilterEntryEditor failsafeGetEditor(GUIFilterBean filterBean) throws Exception {
    Properties properties = ConfigFactory.getInstance().getProperties(QUERY_CONFIG);
    String className = properties.getProperty(filterBean.getClass().getName());

    Class<? extends FilterEntryEditor> editorClass = (Class<? extends FilterEntryEditor>) Class.forName(className);
    Constructor<? extends FilterEntryEditor> constructor = editorClass.getConstructor(GUIFilterBean.class);
    return constructor.newInstance(filterBean);
  }

}