/*
 * Created on 03/09/2011
 */
package com.minotauro.echo.config;

import com.minotauro.cleda.config.ConfigFactory;

/**
 * @author Demián Gutierrez
 */
public class CledaEchoConfig {

  private static final String DEFAULT_PAGE_SIZE = "defaultPageSize";

  // --------------------------------------------------------------------------------

  private CledaEchoConfig() {
    // Empty
  }

  public static int defaultPageSize() {
    return Integer.parseInt( //
        ConfigFactory.getInstance().getProperties( //
            CledaEchoConfig.class.getSimpleName()).getProperty(DEFAULT_PAGE_SIZE));
  }
}
