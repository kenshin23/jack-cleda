/*
 * Created on 15/04/2007
 */
package com.minotauro.cleda.task.core;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.task.i18n.SchedulerFactoryI18N;

/**
 * @author Demián Gutierrez
 */
public class SchedulerFactory {

  private static final Logger log = LoggerFactory.getLogger( //
      SchedulerFactory.class.getName());

  // --------------------------------------------------------------------------------

  private static Boolean token = new Boolean(true);

  private static SchedulerFactory instance;

  // --------------------------------------------------------------------------------

  private Map<String, SchedulerFacade> schedulerFacadeById = //
  new HashMap<String, SchedulerFacade>();

  // --------------------------------------------------------------------------------

  private SchedulerFactory() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static SchedulerFactory getInstance() {
    synchronized (token) {
      if (instance == null) {
        instance = new SchedulerFactory();
      }
    }

    return instance;
  }

  // --------------------------------------------------------------------------------

  public synchronized void initFacade(String schedId, String hibeCfg) //
      throws SchedulerException {

    log.info(SchedulerFactoryI18N.initFacade(schedId, hibeCfg));

    if (hibeCfg == null) {
      hibeCfg = CledaConnector.getInstance().getDefaultHibernateCfg();
    }

    SchedulerFacade schedulerFacade = schedulerFacadeById.get(schedId);

    if (schedulerFacade != null) {
      log.warn(SchedulerFactoryI18N.initRepeat(schedId, hibeCfg));
      return;
    }

    schedulerFacade = new SchedulerFacade(schedId, hibeCfg);
    schedulerFacadeById.put(schedId, schedulerFacade);
    schedulerFacade.start();
  }

  // --------------------------------------------------------------------------------

  public synchronized void initFacade(String schedId) //
      throws SchedulerException {
    initFacade(schedId, null);
  }

  // --------------------------------------------------------------------------------

  public SchedulerFacade getFacade(String schedId) //
      throws SchedulerException {
    return schedulerFacadeById.get(schedId);
  }

  // --------------------------------------------------------------------------------
  // Shutdown (Only for testing)
  // --------------------------------------------------------------------------------

  public synchronized void shutdownAllFacades() //
      throws SchedulerException {

    log.debug(SchedulerFactoryI18N.shutdownAll());

    for (SchedulerFacade schedulerFacade : schedulerFacadeById.values()) {
      schedulerFacade.stop();
    }

    schedulerFacadeById.clear();
  }
}
