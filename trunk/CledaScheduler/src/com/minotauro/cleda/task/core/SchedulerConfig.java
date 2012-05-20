/*
 * Created on 06/02/2008
 */
package com.minotauro.cleda.task.core;

import com.minotauro.cleda.config.ConfigFactory;

/**
 * @author Demián Gutierrez
 */
public class SchedulerConfig {

  private static final String QUEUE_MAX_LENGTH = "queueMaxLength";

  private static final String THREAD_POOL_SIZE = "threadPoolSize";

  // --------------------------------------------------------------------------------

  private SchedulerConfig() {
    // Empty
  }

  public static int getQueueMaxLength() {
    return Integer.parseInt( //
        ConfigFactory.getInstance().getProperties( //
            SchedulerConfig.class.getSimpleName()).getProperty(QUEUE_MAX_LENGTH));
  }

  public static int getThreadPoolSize() {
    return Integer.parseInt( //
        ConfigFactory.getInstance().getProperties( //
            SchedulerConfig.class.getSimpleName()).getProperty(THREAD_POOL_SIZE));
  }
}
