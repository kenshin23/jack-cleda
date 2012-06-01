/*
 * Created on 18/01/2007
 */
package com.minotauro.cleda.model;

import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.ImprovedNamingStrategy;

import com.minotauro.cleda.util.CledaStringUtils;

/**
 * @author Demián Gutierrez
 */
public class CledaNamingStrategy extends ImprovedNamingStrategy {

  protected Configuration configuration;

  public CledaNamingStrategy(Configuration configuration) {
    this.configuration = configuration;
  }

  public String tableName(String tableName) {
    String url = (String) configuration.getProperties().get( //
        "hibernate.connection.url");

    // ----------------------------------------
    // Use "username.tablename" schema in
    // oracle, to avoid problems with different
    // unrelated databases with common tables
    // ----------------------------------------

    if (url.contains("oracle")) {
      return CledaStringUtils.dotIt( //
          configuration.getProperty("hibernate.connection.username"), //
          super.tableName(tableName));
    }

    return super.tableName(tableName);
  }
}
