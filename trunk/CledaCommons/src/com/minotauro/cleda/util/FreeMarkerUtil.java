/*
 * Created on 18/03/2007
 */
package com.minotauro.cleda.util;

import java.io.Writer;
import java.net.URL;

import freemarker.cache.URLTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

/**
 * @author Demián Gutierrez
 */
public class FreeMarkerUtil extends URLTemplateLoader {

  private static FreeMarkerUtil instance;

  // ----------------------------------------

  private Configuration configuration;

  // ----------------------------------------

  private FreeMarkerUtil() {
    configuration = new Configuration();
    configuration.setObjectWrapper(new BeansWrapper());
    configuration.setTemplateLoader(this);
  }

  // ----------------------------------------

  public static FreeMarkerUtil getInstance() {
    if (instance == null) {
      instance = new FreeMarkerUtil();
    }

    return instance;
  }

  // ----------------------------------------

  public void process(String name, Object root, Writer out) //
      throws Exception {

    configuration.getTemplate(name).process(root, out);
  }

  // ----------------------------------------
  // URLTemplateLoader
  // ----------------------------------------

  @Override
  protected URL getURL(String name) {
    return getClass().getClassLoader().getResource(name);
  }
}
