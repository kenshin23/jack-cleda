/*
 * Created on 30/01/2008
 */
package com.minotauro.workflow.config;

import com.minotauro.cleda.config.ConfigFactory;

/**
 * @author Demián Gutierrez
 */
public class FlowConfig {

  private static final String DEFAULT = "default";

  // --------------------------------------------------------------------------------

  private static final String WORKFLOW_SCHEDULER_ENGINE = "class.WorkflowSchedulerEngine";
  private static final String WORKFLOW_SCHEDULER_FACADE = "class.WorkflowSchedulerFacade";

  private static final String WORKFLOW_FACADE = "class.WorkflowFacade";

  private static final String RETRY_TRIES = "int.RetryTries";
  private static final String RETRY_MILIS = "int.RetryMilis";

  private static final String DEFAULT_VALIDATE_STATE = "key.DefaultValidateState";

  private static final String DEFAULT_EDIT_STATE = "key.DefaultEditState";
  private static final String DEFAULT_VIEW_STATE = "key.DefaultViewState";

  // --------------------------------------------------------------------------------

  private FlowConfig() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static String getWorkflowSchedulerEngine(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + WORKFLOW_SCHEDULER_ENGINE);

    if (ret == null) {
      ret = getWorkflowSchedulerEngine(DEFAULT);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static String getWorkflowSchedulerFacade(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + WORKFLOW_SCHEDULER_FACADE);

    if (ret == null) {
      ret = getWorkflowSchedulerFacade(DEFAULT);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static String getWorkflowFacade(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + WORKFLOW_FACADE);

    if (ret == null) {
      ret = getWorkflowFacade(DEFAULT);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static int getRetryTries(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + RETRY_TRIES);

    if (ret != null) {
      return Integer.parseInt(ret);
    }

    return getRetryTries(DEFAULT);
  }

  // --------------------------------------------------------------------------------

  public static int getRetryMilis(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + RETRY_MILIS);

    if (ret != null) {
      return Integer.parseInt(ret);
    }

    return getRetryMilis(DEFAULT);
  }

  // --------------------------------------------------------------------------------

  public static String getDefaultValidateState(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + DEFAULT_VALIDATE_STATE);

    return ret != null ? ret : getDefaultValidateState(DEFAULT);
  }

  // --------------------------------------------------------------------------------

  public static String getDefaultEditState(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + DEFAULT_EDIT_STATE);

    return ret != null ? ret : getDefaultEditState(DEFAULT);
  }

  // --------------------------------------------------------------------------------

  public static String getDefaultViewState(String wflowId) {
    String ret = ConfigFactory.getInstance().getProperties( //
        FlowConfig.class.getSimpleName()).getProperty( //
        wflowId + "." + DEFAULT_VIEW_STATE);

    return ret != null ? ret : getDefaultViewState(DEFAULT);
  }
}
