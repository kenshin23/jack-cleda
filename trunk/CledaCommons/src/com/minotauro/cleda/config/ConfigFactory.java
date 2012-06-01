/*
 * Created on 30/01/2008
 */
package com.minotauro.cleda.config;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Demián Gutierrez
 */
public class ConfigFactory {

  private static final String DEF_PROPERTIES = /**/"Default{0}.properties";
  private static final String CUR_PROPERTIES = /*       */"{0}.properties";

  // --------------------------------------------------------------------------------

  private static ConfigFactory instance;

  // --------------------------------------------------------------------------------

  private Map<String, Properties> propertiesMap = //
  new HashMap<String, Properties>();

  // --------------------------------------------------------------------------------

  public static ConfigFactory getInstance() {
    if (instance == null) {
      instance = new ConfigFactory();
    }

    return instance;
  }

  // --------------------------------------------------------------------------------

  private ConfigFactory() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  private Properties loadProperties(String name) //
      throws Exception {

    String defPropertiesFile = MessageFormat.format(DEF_PROPERTIES, new Object[]{name});
    String curPropertiesFile = MessageFormat.format(CUR_PROPERTIES, new Object[]{name});

    Properties defProperties = loadProperties(defPropertiesFile, null);
    Properties curProperties = loadProperties(curPropertiesFile, defProperties);

    return curProperties;
  }

  private Properties loadProperties(String file, Properties defaults) //
      throws Exception {

    Class<?> clazz = Class.forName(ConfigFactory.class.getName());
    ClassLoader classLoader = clazz.getClassLoader();

    InputStream is = classLoader.getResourceAsStream(file);

    Properties ret = new Properties(defaults);

    if (is != null) {
      ret.load(is);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public synchronized Properties getProperties(String name) {
    try {
      return failsafeGetProperties(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Properties failsafeGetProperties(String name) //
      throws Exception {

    Properties ret = propertiesMap.get(name);

    if (ret == null) {
      propertiesMap.put(name, ret = loadProperties(name));
    }

    return ret;
  }
}
