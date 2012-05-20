/*
 * Created on 04/02/2008
 */
package com.minotauro.cleda.task.i18n;

import com.minotauro.cleda.task.core.SchedulerCycle;
import com.minotauro.cleda.task.core.SchedulerFacade;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.core.SchedulerQueue;
import com.minotauro.cleda.task.core.SchedulerRunner;
import com.minotauro.cleda.task.test.TestCaseBase;
import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class Main extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    Main m = new Main();

    m.i18n(SchedulerFactory.class.getSimpleName());
    m.i18n(SchedulerRunner.class.getSimpleName());
    m.i18n(SchedulerFacade.class.getSimpleName());
    m.i18n(SchedulerCycle.class.getSimpleName());
    m.i18n(SchedulerQueue.class.getSimpleName());
    m.i18n(TestCaseBase.class.getSimpleName());

    m.date("FormatDate");
  }
}
