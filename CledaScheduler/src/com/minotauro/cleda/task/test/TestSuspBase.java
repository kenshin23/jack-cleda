/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.task.test;

import java.util.Calendar;

import com.minotauro.cleda.task.i18n.TestCaseBaseI18N;

/**
 * @author Demián Gutierrez
 */
public class TestSuspBase extends TestCaseBase {

  public TestSuspBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected void doRunTestSusp(int seed, int tasks, int safe, int delta) //
      throws Exception {

    writeTestLogBeg();

    taskStatusBeanByTaskIdMap.clear();

    initRandom(seed);

    log.info(TestCaseBaseI18N.begAddingTasks());

    for (int i = 0; i < tasks; i++) {
      addRandomSuspTask(Calendar.getInstance(), safe, delta);
    }

    log.info(TestCaseBaseI18N.endAddingTasks());

    waitUntilEmpty(safe, delta, 2000, false);
    checkTaskStatusBeanMap();
    checkTaskListEmpty();
    logTimeError();

    writeTestLogEnd();
  }
}
