/*
 * Created on 26/06/2008
 */
package com.minotauro.echo.grid;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Demián Gutierrez
 */
public class TestFieldStateProxy implements FieldStateProxy {

  protected Properties properties = new Properties();

  public TestFieldStateProxy(InputStream is) {
    try {
      properties.load(is);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public boolean isFieldEnabled(String key) {
    String val = properties.getProperty(key + ".enabled");
    return val == null || val.equals("true");
  }

  public boolean isFieldVisible(String key) {
    String val = properties.getProperty(key + ".visible");
    return val == null || val.equals("true");
  }
}
