/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

import java.util.Calendar;

import com.minotauro.cleda.task.core.SchedulerException;
import com.minotauro.cleda.task.core.SchedulerFacade;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.util.CalendarUtils;

/**
 * @author Demián Gutierrez
 */
public class SimpleTest extends TestCaseBase {

  public SimpleTest() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected void delTask(int id) //
      throws SchedulerException {

    SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);

    schedulerFacade.delTask(id, true);
    taskStatusBeanByTaskIdMap.remove(Integer.toString(id));
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    Calendar cal = Calendar.getInstance();

    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 2000)));
    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 4000)));
    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 6000)));
    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 8000)));
    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 10000)));

    waitUntilEmpty(0, 12000, 2000, false);

    checkTaskStatusBeanMap();
    checkTaskListEmpty();

    logTimeError();
  }

  // --------------------------------------------------------------------------------

  public void testB() throws Exception {
    Calendar cal = Calendar.getInstance();

    int id;

    id = addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 4000)));
    id = addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 2000)));

    delTask(id);

    waitUntilEmpty(0, 6000, 2000, false);

    checkTaskStatusBeanMap();
    checkTaskListEmpty();

    logTimeError();
  }

  // --------------------------------------------------------------------------------

  public void testC() throws Exception {
    Calendar cal = Calendar.getInstance();

    addBaseTask(null, new MTask( //
        PROXY_ID_ARRAY[0], CalendarUtils.getCalendarPlusDelta(cal, Calendar.MILLISECOND, 2000)));

    waitUntilEmpty(0, 6000, 2000, false);

    checkTaskStatusBeanMap();
    checkTaskListEmpty();

    logTimeError();
  }
}
