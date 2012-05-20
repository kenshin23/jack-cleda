/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.commons.lang.Validate;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.regexp.RegexpFilter;
import com.minotauro.cleda.regexp.RegexpFilter.RegexpFilterMode;
import com.minotauro.cleda.task.core.SchedulerException;
import com.minotauro.cleda.task.core.SchedulerFacade;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.core.SchedulerProxy;
import com.minotauro.cleda.task.i18n.FormatDate;
import com.minotauro.cleda.task.i18n.SchedulerCycleI18N;
import com.minotauro.cleda.task.i18n.SchedulerQueueI18N;
import com.minotauro.cleda.task.i18n.TestCaseBaseI18N;
import com.minotauro.cleda.task.model.MTask;
import com.minotauro.cleda.task.prop.MTaskProp;
import com.minotauro.cleda.task.test.TaskStatusBean.TaskStatus;
import com.minotauro.cleda.threads.ThreadMonitor;
import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.cleda.util.CalendarUtils;
import com.minotauro.cleda.util.OutputStreamFactory;
import com.minotauro.i18n.bundled._DateFormatDate;

/**
 * @author Demián Gutierrez
 */
public class TestCaseBase extends TestCase {

  protected static final String PROXY_ID_ARRAY[] = {"proxyId_1", "proxyId_2", "proxyId_3"};

  protected static final String PROXY_DELE_TASK = "proxy_dele_task";
  protected static final String PROXY_SUSP_TASK = "proxy_susp_task";

  protected static final String HIBERNATE_CFG = "test_hibernate.cfg.xml";

  protected static final String SCHED_ID = "sched_id";

  protected static final Logger log = LoggerFactory.getLogger(TestCaseBase.class);

  protected static ThreadMonitor threadMonitor;

  // --------------------------------------------------------------------------------

  protected Map<String, TaskStatusBean> taskStatusBeanByTaskIdMap = //
  new HashMap<String, TaskStatusBean>();

  protected Random random;

  // --------------------------------------------------------------------------------

  protected long beg;
  protected long sum;
  protected long cnt;
  protected long min;
  protected long max;

  // --------------------------------------------------------------------------------

  public TestCaseBase() {

    // --------------------------------------------------------------------------------
    // System.setProperty("log4j.configuration", "test_log4j.properties");
    // --------------------------------------------------------------------------------

    if (threadMonitor != null) {
      return;
    }

    OutputStreamFactory outputStreamFactory = new OutputStreamFactory() {
      public OutputStream getOutputStream() throws Exception {
        return new FileOutputStream("STACK.TXT");
      }
    };

    threadMonitor = new ThreadMonitor(outputStreamFactory);
    threadMonitor.addRegexpFilter(new RegexpFilter( //
        RegexpFilterMode.INCLUDE, "main"));
    threadMonitor.addRegexpFilter(new RegexpFilter( //
        RegexpFilterMode.INCLUDE, SchedulerCycleI18N.threadName()));
    threadMonitor.addRegexpFilter(new RegexpFilter( //
        RegexpFilterMode.INCLUDE, SchedulerQueueI18N.threadName("[0-9]+")));

    threadMonitor.start();
  }

  // --------------------------------------------------------------------------------
  // Set up / tear down methods
  // --------------------------------------------------------------------------------

  @Override
  protected void setUp() throws Exception {
    CledaConnector.getInstance().dropDB(HIBERNATE_CFG);

    taskStatusBeanByTaskIdMap.clear();

    initScheduler();

    beg = System.currentTimeMillis();
    sum = 0;
    cnt = 0;
    min = Long.MAX_VALUE;
    max = Long.MIN_VALUE;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void tearDown() throws Exception {
    log.info(TestCaseBaseI18N.totalTestTime(System.currentTimeMillis() - beg));

    // ----------------------------------------

    SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);

    for (SchedulerProxy schedulerProxy : schedulerFacade.getSchedulerProxy()) {
      schedulerFacade.delSchedulerProxy(schedulerProxy);
    }
  }

  // --------------------------------------------------------------------------------
  // Core task / proxy methods
  // --------------------------------------------------------------------------------

  protected void initScheduler() throws SchedulerException {
    SchedulerFactory.getInstance().shutdownAllFacades();
    SchedulerFactory.getInstance().initFacade(SCHED_ID, HIBERNATE_CFG);

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade(SCHED_ID);

    for (int i = 0; i < PROXY_ID_ARRAY.length; i++) {
      addProxy(PROXY_ID_ARRAY[i]);
    }

    SchedulerProxy schedulerProxy;

    schedulerProxy = new SchedulerProxy() {
      @Override
      public void executeTask(MTask task) throws SchedulerException {
        TestCaseBase.this.deleTaskProxy(task);
      }
    };
    schedulerProxy.setProxyId(PROXY_DELE_TASK);
    schedulerFacade.addSchedulerProxy(schedulerProxy);

    schedulerProxy = new SchedulerProxy() {
      @Override
      public void executeTask(MTask task) throws SchedulerException {
        TestCaseBase.this.suspTaskProxy(task);
      }
    };
    schedulerProxy.setProxyId(PROXY_SUSP_TASK);
    schedulerFacade.addSchedulerProxy(schedulerProxy);
  }

  // --------------------------------------------------------------------------------

  protected void addProxy(String proxyId) throws SchedulerException {
    SchedulerProxy schedulerProxy = new SchedulerProxy() {
      @Override
      public void executeTask(MTask task) throws SchedulerException {
        TestCaseBase.this.execTaskProxy(task);
      }
    };
    schedulerProxy.setProxyId(proxyId);

    SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);
    schedulerFacade.addSchedulerProxy(schedulerProxy);
  }

  // --------------------------------------------------------------------------------

  protected TaskStatusBean getTaskStatusBean(int id) {
    TaskStatusBean taskStatusBean = taskStatusBeanByTaskIdMap.get( //
        Integer.toString(id));

    // ----------------------------------------
    // We may have to wait a little
    // ----------------------------------------

    while (taskStatusBean == null) {
      ThreadUtils.sleep(500);

      taskStatusBean = taskStatusBeanByTaskIdMap.get( //
          Integer.toString(id));
    }

    return taskStatusBean;
  }

  // --------------------------------------------------------------------------------
  // Task proxy methods
  // --------------------------------------------------------------------------------

  protected void execTaskProxy(MTask task) //
      throws SchedulerException {

    TaskStatusBean taskStatusBean = getTaskStatusBean(task.getId());

    Calendar cal = Calendar.getInstance();

    long cur = Math.abs(cal.getTimeInMillis() - //
        task.getNextDate().getTimeInMillis());

    sum += cur;

    min = cur < min ? cur : min;
    max = cur > max ? cur : max;

    String lastDate = task.getLastDate() != null ? //
        FormatDate.formatTaskDate(task.getLastDate())
        : null;

    log.debug(task.getTaskIdAsString() + //
        " / curr: " + FormatDate.formatTaskDate(cal) + //
        " / next: " + FormatDate.formatTaskDate(task.getNextDate()) + //
        " / prog: " + FormatDate.formatTaskDate(task.getProgDate()) + //
        " / last: " + lastDate);

    cnt++;

    double err = ((double) sum) / cnt;

    NumberFormat nf = new DecimalFormat("#.##");

    log.debug(task.getTaskIdAsString() + //
        " / cnt: " + cnt + //
        " / cur: " + nf.format(cur) + //
        " / err: " + nf.format(err) + //
        " / min: " + nf.format(min) + //
        " / max: " + nf.format(max));

    // ----------------------------------------

    log.debug(TestCaseBaseI18N.execTask(task.getTaskIdAsString(), //
        task.getRetryTimes(), task.getRetryTries(), //
        taskStatusBean.getTaskStatus(), //
        taskStatusBean.getExecCount(), taskStatusBean.getCurrCount()));

    // ----------------------------------------
    // Oops, frozen failed
    // ----------------------------------------

    if (taskStatusBean.isFrozen()) {
      if (!taskStatusBean.isDolock()) {
        taskStatusBean.setFailed( //
            taskStatusBean.getCurrCount() != //
            taskStatusBean.getFrozCount());
      } else {
        taskStatusBean.setFailed(true);
        return;
      }
    }

    // ----------------------------------------
    // The task will never succeed
    // ----------------------------------------

    if (taskStatusBean.isNeverDone()) {
      taskStatusBean.setTaskStatus(TaskStatus.FAIL);
      taskStatusBean.setCurrCount(taskStatusBean.getCurrCount() + 1);

      throw new SchedulerException(TestCaseBaseI18N.simulatedFail());
    }

    // ----------------------------------------
    // Do we succeed this time
    // ----------------------------------------

    if (taskStatusBean.getCurrCount() < taskStatusBean.getExecCount()) {
      taskStatusBean.setTaskStatus(TaskStatus.FAIL);
      taskStatusBean.setCurrCount(taskStatusBean.getCurrCount() + 1);

      throw new SchedulerException(TestCaseBaseI18N.simulatedFail());
    }

    taskStatusBean.setTaskStatus(TaskStatus.DONE);
  }

  // --------------------------------------------------------------------------------

  protected void deleTaskProxy(MTask task) //
      throws SchedulerException {

    TaskStatusBean taskStatusBeanCurr = getTaskStatusBean(task.getId());
    TaskStatusBean taskStatusBeanDele = getTaskStatusBean(taskStatusBeanCurr.getRelatedId());

    log.debug("Task dele: " + task.getTaskIdAsString() + //
        "; To dele: ID=" + taskStatusBeanCurr.getRelatedId());

    // ----------------------------------------

    try {
      SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);
      schedulerFacade.delTask(taskStatusBeanCurr.getRelatedId(), true);
      taskStatusBeanDele.setFrozen(true);
    } catch (SchedulerException e) {
      System.out.println(ExceptionUtils.getStackTrace(e));
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void suspTaskProxy(MTask task) //
      throws SchedulerException {

    TaskStatusBean taskStatusBeanCurr = getTaskStatusBean(task.getId());
    TaskStatusBean taskStatusBeanSusp = getTaskStatusBean(taskStatusBeanCurr.getRelatedId());

    log.debug("Task susp: " + task.getTaskIdAsString() + //
        "; To susp: ID=" + taskStatusBeanCurr.getRelatedId());

    // ----------------------------------------

    try {
      taskStatusBeanSusp.setFrozen(false);
      SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);
      schedulerFacade.addWaitTask(taskStatusBeanSusp.getTask());
    } catch (SchedulerException e) {
      System.out.println(ExceptionUtils.getStackTrace(e));
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------
  // Misc utils
  // --------------------------------------------------------------------------------

  protected void initRandom(long seed) {
    if (seed != -1) {
      random = new Random(seed);
    } else {
      random = new Random();
    }
  }

  // --------------------------------------------------------------------------------

  protected int getRandomMilis(int safe, int delta) {
    return safe + random.nextInt(delta);
  }

  // --------------------------------------------------------------------------------

  protected String getRandomProxyId() {
    return PROXY_ID_ARRAY[random.nextInt(PROXY_ID_ARRAY.length)];
  }

  // --------------------------------------------------------------------------------

  protected void logTimeError() {
    double err = ((double) sum) / cnt;
    log.info(TestCaseBaseI18N.errorReport(cnt, err, min, max));
  }

  // --------------------------------------------------------------------------------

  protected void writeTestLogBeg() throws Exception {
    PrintWriter pw = new PrintWriter(new FileOutputStream("test.log", true));
    pw.println("TEST (BEG): " + getClass().getName() + ";" + //
        _DateFormatDate.formatDateTime(Calendar.getInstance()));
    pw.close();
  }

  // --------------------------------------------------------------------------------

  protected void writeTestLogEnd() throws Exception {
    PrintWriter pw = new PrintWriter(new FileOutputStream("test.log", true));
    pw.println("TEST (END): " + getClass().getName() + ";" + //
        _DateFormatDate.formatDateTime(Calendar.getInstance()));
    pw.close();
  }

  // --------------------------------------------------------------------------------

  protected void waitUntilEmpty(int safe, int delta, int sleep, boolean shutdown) //
      throws SchedulerException {

    Calendar endCal = Calendar.getInstance();
    endCal.add(Calendar.MILLISECOND, safe);
    endCal.add(Calendar.MILLISECOND, 3000 * delta);

    // TODO: Don't like the code
    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade(SCHED_ID);

    while (!schedulerFacade.isTaskListEmpty()) {
      if (shutdown) {
        initScheduler();

        schedulerFacade = //
        SchedulerFactory.getInstance().getFacade(SCHED_ID);
      }

      ThreadUtils.sleep(sleep);

      if (endCal.before(Calendar.getInstance())) {
        assertTrue(TestCaseBaseI18N.timeOut(), false);
      }
    }
  }

  // --------------------------------------------------------------------------------
  // Check methods
  // --------------------------------------------------------------------------------

  protected void checkTaskListEmpty() {
    Session session = CledaConnector.getInstance().getSession(HIBERNATE_CFG);
    session.beginTransaction();

    List<MTask> list = MBase.listByField( //
        session, MTask.class, MTaskProp.STATUS, //
        com.minotauro.cleda.task.model.MTask.Status.WAIT, null);

    boolean ret = list.isEmpty();

    session.getTransaction().commit();
    session.close();

    assertTrue(ret);
  }

  // --------------------------------------------------------------------------------

  protected void checkTaskStatusBeanMap() {
    for (TaskStatusBean taskStatusBean : taskStatusBeanByTaskIdMap.values()) {

      log.debug(TestCaseBaseI18N.checkTaskStatus(taskStatusBean.getTask().getTaskIdAsString(), //
          taskStatusBean.getTask().getStatus(), //
          taskStatusBean.isNeverDone(), //
          taskStatusBean.getTaskStatus(), //
          taskStatusBean.getExecCount(), //
          taskStatusBean.getCurrCount(), //
          taskStatusBean.getTask().getRetryTimes(), //
          taskStatusBean.isFrozen(), //
          taskStatusBean.isFailed(), //
          taskStatusBean.getRelatedId()));

      if (taskStatusBean.getRelatedId() != 0) {
        continue;
      }

      if (taskStatusBean.isFrozen()) {
        assertFalse(taskStatusBean.isFailed());
      } else {
        if (taskStatusBean.isNeverDone()) {
          assertEquals(TaskStatus.FAIL, taskStatusBean.getTaskStatus());
          assertEquals(taskStatusBean.getExecCount(), taskStatusBean.getCurrCount() - 1);
        } else {
          assertEquals(TaskStatus.DONE, taskStatusBean.getTaskStatus());
          assertEquals(taskStatusBean.getExecCount(), taskStatusBean.getCurrCount());
        }

        assertEquals(taskStatusBean.getExecCount(), taskStatusBean.getTask().getRetryTimes());
      }
    }
  }

  // --------------------------------------------------------------------------------
  // Task add / del methods
  // --------------------------------------------------------------------------------

  protected int addBaseTask(TaskStatusBean taskStatusBean, MTask task) //
      throws SchedulerException {

    if (taskStatusBean == null) {
      taskStatusBean = new TaskStatusBean();
      taskStatusBean.setTask(task);
    }

    SchedulerFacade schedulerFacade = SchedulerFactory.getInstance().getFacade(SCHED_ID);

    int id = schedulerFacade.addWaitTask(task);

    taskStatusBeanByTaskIdMap.put(Integer.toString(id), taskStatusBean);
    log.debug("Add Task: " + task.getTaskIdAsString() + ", At: " + FormatDate.formatTaskDate(Calendar.getInstance()));

    return id;
  }

  // --------------------------------------------------------------------------------

  protected int addDeleTask( //
      TaskStatusBean taskStatusBean1, MTask task1, //
      TaskStatusBean taskStatusBean2, MTask task2) //
      throws SchedulerException {

    Validate.notNull(task1);
    Validate.notNull(task2);

    if (taskStatusBean1 == null) {
      taskStatusBean1 = new TaskStatusBean();
      taskStatusBean1.setTask(task1);
    }

    if (taskStatusBean2 == null) {
      taskStatusBean2 = new TaskStatusBean();
      taskStatusBean2.setTask(task2);
    }

    int idt;
    int idd;

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade(SCHED_ID);

    idt = schedulerFacade.addWaitTask(task1);
    taskStatusBeanByTaskIdMap.put(Integer.toString(idt), taskStatusBean1);

    taskStatusBean2.setRelatedId(idt);

    idd = schedulerFacade.addWaitTask(task2);
    taskStatusBeanByTaskIdMap.put(Integer.toString(idd), taskStatusBean2);

    log.debug("Add Task: " + task1.getTaskIdAsString() + ", At: " + //
        FormatDate.formatTaskDate(Calendar.getInstance()) + //
        "; Del Task: " + task2.getTaskIdAsString());

    return idd;
  }

  // --------------------------------------------------------------------------------

  protected int addSuspTask( //
      TaskStatusBean taskStatusBean1, MTask task1, //
      TaskStatusBean taskStatusBean2, MTask task2) //
      throws SchedulerException {

    Validate.notNull(task1);
    Validate.notNull(task2);

    if (taskStatusBean1 == null) {
      taskStatusBean1 = new TaskStatusBean();
      taskStatusBean1.setTask(task1);
    }

    if (taskStatusBean2 == null) {
      taskStatusBean2 = new TaskStatusBean();
      taskStatusBean2.setTask(task2);
    }

    int idt;
    int ids;

    SchedulerFacade schedulerFacade = //
    SchedulerFactory.getInstance().getFacade(SCHED_ID);

    idt = schedulerFacade.addSuspTask(task1);
    taskStatusBeanByTaskIdMap.put(Integer.toString(idt), taskStatusBean1);

    taskStatusBean2.setRelatedId(idt);

    ids = schedulerFacade.addWaitTask(task2);
    taskStatusBeanByTaskIdMap.put(Integer.toString(ids), taskStatusBean2);

    log.debug("Add Task: " + task1.getTaskIdAsString() + ", At: " + //
        FormatDate.formatTaskDate(Calendar.getInstance()) + //
        "; Sus Task: " + task2.getTaskIdAsString());

    return ids;
  }

  // --------------------------------------------------------------------------------

  protected void addRandomLoadTask(Calendar baseCal, int safe, int delta) //
      throws SchedulerException {

    TaskStatusBean taskStatusBean = new TaskStatusBean();

    taskStatusBean.setExecCount(random.nextInt(5) + 1);
    taskStatusBean.setNeverDone(random.nextBoolean());

    MTask task = new MTask( //
        getRandomProxyId(), //
        CalendarUtils.getCalendarPlusDelta( //
            baseCal, Calendar.MILLISECOND, getRandomMilis(safe, delta)));

    task.setRetryTimes(taskStatusBean.getExecCount());
    task.setRetryMilis(getRandomMilis(safe, delta / task.getRetryTimes()));

    taskStatusBean.setTask(task);

    addBaseTask(taskStatusBean, task);
  }

  // --------------------------------------------------------------------------------

  protected void addRandomDeleTask(Calendar baseCal, int safe, int delta, boolean dolock) //
      throws SchedulerException {

    // ----------------------------------------
    // The target task
    // ----------------------------------------

    TaskStatusBean taskStatusBean = new TaskStatusBean();

    taskStatusBean.setExecCount(random.nextInt(5) + 1);
    taskStatusBean.setNeverDone(random.nextBoolean());
    taskStatusBean.setDolock(dolock);

    int task1RandomMilis = getRandomMilis(safe, delta);

    MTask task1 = new MTask( //
        getRandomProxyId(), //
        CalendarUtils.getCalendarPlusDelta( //
            baseCal, Calendar.MILLISECOND, task1RandomMilis));

    task1.setRetryTimes(taskStatusBean.getExecCount());
    task1.setRetryMilis(getRandomMilis(safe, delta / task1.getRetryTimes()));

    // ----------------------------------------
    // The trigger task
    // ----------------------------------------

    int task2RandomMilis = getRandomMilis(safe, //
        task1RandomMilis + task1.getRetryTimes() * task1.getRetryMilis());

    MTask task2 = new MTask( //
        PROXY_DELE_TASK, //
        CalendarUtils.getCalendarPlusDelta( //
            baseCal, Calendar.MILLISECOND, task2RandomMilis));

    taskStatusBean.setTask(task1);

    addDeleTask(taskStatusBean, task1, null, task2);
  }

  // --------------------------------------------------------------------------------

  protected void addRandomSuspTask(Calendar baseCal, int safe, int delta) //
      throws SchedulerException {

    // ----------------------------------------
    // The target task
    // ----------------------------------------

    TaskStatusBean taskStatusBean = new TaskStatusBean();

    taskStatusBean.setExecCount(random.nextInt(5) + 1);
    taskStatusBean.setNeverDone(random.nextBoolean());

    int task1RandomMilis = getRandomMilis(safe, delta);

    MTask task1 = new MTask( //
        getRandomProxyId(), //
        CalendarUtils.getCalendarPlusDelta( //
            baseCal, Calendar.MILLISECOND, task1RandomMilis));

    task1.setRetryTimes(taskStatusBean.getExecCount());
    task1.setRetryMilis(getRandomMilis(safe, delta / task1.getRetryTimes()));

    // ----------------------------------------
    // The trigger task
    // ----------------------------------------

    int task2RandomMilis = getRandomMilis(safe, //
        task1RandomMilis + task1.getRetryTimes() * task1.getRetryMilis());

    MTask task2 = new MTask( //
        PROXY_SUSP_TASK, //
        CalendarUtils.getCalendarPlusDelta( //
            baseCal, Calendar.MILLISECOND, task2RandomMilis));

    taskStatusBean.setTask(task1);

    addSuspTask(taskStatusBean, task1, null, task2);
  }
}
