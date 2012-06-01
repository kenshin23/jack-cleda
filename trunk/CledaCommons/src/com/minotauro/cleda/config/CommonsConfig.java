/*
 * Created on 30/01/2008
 */
package com.minotauro.cleda.config;

/**
 * @author Demián Gutierrez
 */
public class CommonsConfig {

  private static final String DEBUG = "debug";

  // --------------------------------------------------------------------------------

  private CommonsConfig() {
    // Empty
  }

  public static boolean isDebug() {
    return Boolean.parseBoolean( //
        ConfigFactory.getInstance().getProperties( //
            CommonsConfig.class.getSimpleName()).getProperty(DEBUG));
  }
}
