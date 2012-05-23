/*
 * Created on 10/01/2008
 */
package com.minotauro.workflow.test.base;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

import junit.framework.TestCase;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.regexp.RegexpFilter;
import com.minotauro.cleda.regexp.RegexpFilter.RegexpFilterMode;
import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.cleda.task.i18n.SchedulerCycleI18N;
import com.minotauro.cleda.task.i18n.SchedulerQueueI18N;
import com.minotauro.cleda.threads.ThreadMonitor;
import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.cleda.util.OutputStreamFactory;
import com.minotauro.user.model.MUser;
import com.minotauro.user.model._PropMUser;
import com.minotauro.workflow.api.WorkflowFactory;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.impl.WorkflowFacadeImpl;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MActorCurr;
import com.minotauro.workflow.model.MActorHist;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetStateGrp;
import com.minotauro.workflow.model.MNetStateSet;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.prop.MWorkflowProp;

/**
 * @author Demián Gutierrez
 */
public class BaseTest extends TestCase {

  protected static final String WFLOW_ID = "WFLOW_ID";

  protected static final Logger log = LoggerFactory.getLogger( //
      BaseTest.class.getName());

  // --------------------------------------------------------------------------------

  protected Random random = new Random();

  protected ThreadMonitor threadMonitor;

  protected double time;
  protected int firedCount;

  // --------------------------------------------------------------------------------

  public BaseTest() {
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

  protected void init() throws Exception {
    SchedulerFactory.getInstance().shutdownAllFacades();
    SchedulerFactory.getInstance().initFacade(WFLOW_ID);
    WorkflowFactory.getInstance().initFacade(WFLOW_ID, WFLOW_ID);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void setUp() throws Exception {
    CledaConnector.getInstance().dropDB();
    init();
  }

  // --------------------------------------------------------------------------------

  protected void initRandom(long seed) {
    if (seed != -1) {
      random = new Random(seed);
    } else {
      random = new Random();
    }
  }

  // --------------------------------------------------------------------------------

  private MWrkTrans getWrkTransByName(MWrkTransSet wrkTransSet, String name) {
    for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
      if (wrkTrans.getNetTransRef().getName().equals(name)) {
        return wrkTrans;
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  protected void createNetPetri(String base, //
      String role, String prof, String user, //
      String doctype, String worklist, String netpetri) //
      throws Exception {
    Init.main(new String[]{base, role, prof, user, doctype, worklist, netpetri});
  }

  protected List<MActor> createWorkflow( //
      String netpetri, String netStateName, MDocument document) //
      throws Exception {

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    WorkflowFacadeImpl workflowFacade = //
    (WorkflowFacadeImpl) WorkflowFactory.getInstance().getFacade(WFLOW_ID);

    workflowFacade.init(session, null, null);
    workflowFacade.createWorkflow(document, "test-net", netStateName);
    List<MActor> ret = workflowFacade.delegateActors();

    MWorkflow workflow = workflowFacade.getEngPetri().getWorkflow();

    session.saveOrUpdate(document);
    session.saveOrUpdate(workflow);

    session.getTransaction().commit();
    session.close();

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected MWrkTrans failsafeFindWrkTrans( //
      String worklist, String userName, String tsetName, String trnsName) //
      throws WorkflowException {

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    try {
      MUser user = MBase.loadByField( //
          session, MUser.class, _PropMUser.USER, userName);

      WorkflowFacadeImpl workflowFacade = //
      (WorkflowFacadeImpl) WorkflowFactory.getInstance().getFacade(WFLOW_ID);

      workflowFacade.init(session, user, worklist);

      List<MWrkTransSet> wrkTransSetList = workflowFacade.listTasks(0, 0);

      MWrkTrans wrkTrans = null;

      for (MWrkTransSet wrkTransSet : wrkTransSetList) {
        MNetTransSet netTransSet = wrkTransSet.getNetTransSetRef();

        if (StringUtils.equals(tsetName, netTransSet.getName())) {
          wrkTrans = getWrkTransByName(wrkTransSet, trnsName);
        }
      }

      return wrkTrans;
    } finally {
      session.getTransaction().commit();
      session.close();
    }
  }

  protected boolean trans(String worklist, String userName, String tsetName, String trnsName) //
      throws Exception {

    log.debug("trans: " + worklist + "; " + userName + "; " + trnsName);

    // ----------------------------------------

    MWrkTrans wrkTrans = failsafeFindWrkTrans(worklist, userName, tsetName, trnsName);

    if (wrkTrans == null) {
      return false;
    }

    WorkflowFacadeImpl workflowFacade = //
    (WorkflowFacadeImpl) WorkflowFactory.getInstance().getFacade(WFLOW_ID);

    MWorkflow workflow = wrkTrans.getWrkTransSetRef().getWorkflowRef();

    //workflowFacade.dolockWorkflow(workflow);

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    MUser user = MBase.loadByField( //
        session, MUser.class, _PropMUser.USER, userName);

    workflowFacade.init(session, user, worklist);

    workflow = (MWorkflow) workflow.reload(session);
    wrkTrans = (MWrkTrans) wrkTrans.reload(session);

    workflowFacade.selectWorkflow(
        workflow.getDocumentRef());

    boolean ret = workflowFacade.fireUserTrans(wrkTrans);

    session.getTransaction().commit();
    session.close();

    workflowFacade.flushScheduler();
    //workflowFacade.unlockWorkflow(workflow);

    // ----------------------------------------

    firedCount++;

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void stateSet(String name) //
      throws Exception {

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    MWorkflow workflow = MBase.loadByField( //
        session, MWorkflow.class, MWorkflowProp.ID, new Integer(1));

    MNetStateSet netStateSet = workflow.getNetStateSetRef();

    if (!StringUtils.equals(name, netStateSet.getName())) {
      throw new Exception("!StringUtils.equals(name, netStateSet.getName())");
    }

    session.getTransaction().commit();
    session.close();
  }

  // --------------------------------------------------------------------------------

  protected void stateGrp(String name) //
      throws Exception {

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    MWorkflow workflow = MBase.loadByField( //
        session, MWorkflow.class, MWorkflowProp.ID, new Integer(1));

    MNetStateGrp netStateGrp = workflow.getNetStateGrpRef();

    if (!StringUtils.equals(name, netStateGrp.getName())) {
      throw new Exception("!StringUtils.equals(name, netStateGrp.getName())");
    }

    session.getTransaction().commit();
    session.close();
  }

  // --------------------------------------------------------------------------------

  protected void relax(long milis) {
    log.debug("relax: " + milis / 1000);

    time += milis;

    ThreadUtils.dowait(this, milis);
  }

  // --------------------------------------------------------------------------------

  protected void checkActor(int count, Class<? extends MActor> clazz) //
      throws Exception {

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    List<? extends MActor> list = MBase.listByField( //
        session, clazz);

    if (list.size() != count) {
      session.getTransaction().commit();
      session.close();

      throw new Exception("list.size() != count : " //
          + list.size() + "!=" + count);
    }

    session.getTransaction().commit();
    session.close();
  }

  // --------------------------------------------------------------------------------

  protected void checkActorHist(int count) //
      throws Exception {
    checkActor(count, MActorHist.class);
  }

  protected void checkActorCurr(int count) //
      throws Exception {
    checkActor(count, MActorCurr.class);
  }

  // --------------------------------------------------------------------------------

  protected void beg() {
    log.debug("****************************************> beg running: " + //
        getClass().getName() + ";" + ThreadUtils.getCallerStackTrace());

    time = System.currentTimeMillis();
  }

  // --------------------------------------------------------------------------------

  protected void end() {
    time = (System.currentTimeMillis() - time) / 1000;

    log.debug("fired " + firedCount + //
        " trans, in " + time + " secs");

    log.debug("****************************************> end running: " + //
        getClass().getName() + ";" + ThreadUtils.getCallerStackTrace());
  }
}
