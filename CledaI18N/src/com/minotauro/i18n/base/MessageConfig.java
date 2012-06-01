/*
 * Created on 30/01/2008
 */
package com.minotauro.i18n.base;

import com.minotauro.cleda.config.ConfigFactory;

/**
 * @author Demián Gutierrez
 */
public class MessageConfig {

  private static final String MESSAGE_FACTORY = "message.factory";

  // --------------------------------------------------------------------------------

  private MessageConfig() {
    // Empty
  }

  public static String getMessageFactory() {
    return ConfigFactory.getInstance().getProperties( //
        MessageConfig.class.getSimpleName()).getProperty(MESSAGE_FACTORY);
  }
}
