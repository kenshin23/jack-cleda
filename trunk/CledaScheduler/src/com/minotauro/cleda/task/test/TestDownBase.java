/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.task.test;

import java.util.Calendar;

import com.minotauro.cleda.task.i18n.TestCaseBaseI18N;

/**
 * @author Demián Gutierrez
 */
public class TestDownBase extends TestCaseBase {

  public TestDownBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected void doRunTestDown(int seed, int tasks, int safe, int delta, int start) //
      throws Exception {

    writeTestLogBeg();

    taskStatusBeanByTaskIdMap.clear();

    initRandom(seed);

    Calendar baseCal = Calendar.getInstance();
    baseCal.add(Calendar.MILLISECOND, start);

    log.info(TestCaseBaseI18N.begAddingTasks());

    for (int i = 0; i < tasks; i++) {
      addRandomLoadTask(baseCal, safe, delta);
    }

    log.info(TestCaseBaseI18N.endAddingTasks());

    waitUntilEmpty(safe, delta, 10000, true);
    checkTaskStatusBeanMap();
    checkTaskListEmpty();
    logTimeError();

    writeTestLogEnd();
  }
}
