/*
 * Created on 09/01/2008
 */
package com.minotauro.workflow.api;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.WorkflowFactoryI18N;
import com.minotauro.workflow.impl.WorkflowFacadeImpl;
import com.minotauro.workflow.impl.WorkflowFactoryBeanImpl;

/**
 * @author Demián Gutierrez
 */
public class WorkflowFactory {

  private static final Logger log = LoggerFactory.getLogger( //
      WorkflowFactory.class.getName());

  // --------------------------------------------------------------------------------

  private static WorkflowFactory instance = new WorkflowFactory();

  // --------------------------------------------------------------------------------

  private Map<String, WorkflowFactoryBean> workflowFactoryBeanMap = //
  new HashMap<String, WorkflowFactoryBean>();

  // --------------------------------------------------------------------------------

  private WorkflowFactory() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static WorkflowFactory getInstance() {
    return instance;
  }

  // --------------------------------------------------------------------------------

  public synchronized void initFacade( //
      String wflowId, String schedId, String hibeCfg) //
      throws WorkflowException {

    log.info(WorkflowFactoryI18N.initFacade(wflowId, schedId, hibeCfg));

    WorkflowFactoryBean workflowFactoryBean = //
    workflowFactoryBeanMap.get(wflowId);

    if (workflowFactoryBean != null) {
      log.warn(WorkflowFactoryI18N.initRepeat(wflowId, schedId, hibeCfg));
      return;
    }

    hibeCfg = hibeCfg == null ? //
        CledaConnector.getInstance().getDefaultHibernateCfg() //
        : hibeCfg;

    workflowFactoryBean = new WorkflowFactoryBeanImpl();
    workflowFactoryBean.init(wflowId, schedId, hibeCfg);

    workflowFactoryBeanMap.put( //
        wflowId, workflowFactoryBean);
  }

  public synchronized void initFacade(String wflowId, String schedId) //
      throws WorkflowException {
    initFacade(wflowId, schedId, null);
  }

  // --------------------------------------------------------------------------------

  public WorkflowFacade getFacade(String wflowId) //
      throws WorkflowException {

    WorkflowFactoryBean ret = workflowFactoryBeanMap.get(wflowId);

    if (ret == null) {
      throw new WorkflowException("ret == null");
    }

    return new WorkflowFacadeImpl(ret);
  }
}
