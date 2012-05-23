/*
 * Created on 07/01/2008
 */
package com.minotauro.workflow.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.util.CledaStringUtils;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.api.WorkflowFactoryBean;
import com.minotauro.workflow.api.WorkflowSchedulerFacade;
import com.minotauro.workflow.config.FlowConfig;
import com.minotauro.workflow.engine.EEngPetri;
import com.minotauro.workflow.engine.EEngTrans;
import com.minotauro.workflow.engine.EngineActorDelegator;
import com.minotauro.workflow.engine.EngineAgentDelegator;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.WorkflowFacadeImplI18N;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocSectionEditState;
import com.minotauro.workflow.model.MDocSectionValidateState;
import com.minotauro.workflow.model.MDocSectionViewState;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.prop.MActorProp;
import com.minotauro.workflow.prop.MNetPetriProp;
import com.minotauro.workflow.prop.MNetTransSetProp;
import com.minotauro.workflow.prop.MWorklistProp;
import com.minotauro.workflow.prop.MWrkTransSetProp;

/**
 * @author Demián Gutierrez
 */
public class WorkflowFacadeImpl implements WorkflowFacade {

  protected static final Logger log = LoggerFactory.getLogger( //
      WorkflowFacadeImpl.class.getName());

  // --------------------------------------------------------------------------------

  protected WorkflowSchedulerFacade workflowScheduler;

  protected WorkflowFactoryBean workflowFactoryBean;

  // --------------------------------------------------------------------------------

  protected EEngPetri/**/engPetri;
  protected String/*   */worklist;

  // --------------------------------------------------------------------------------

  protected Session session;

  // --------------------------------------------------------------------------------

  public WorkflowFacadeImpl(WorkflowFactoryBean workflowFactoryBean)
      throws WorkflowException {

    this.workflowFactoryBean = workflowFactoryBean;

    this.workflowScheduler =
        workflowFactoryBean.getScheduler();

    engPetri = new EEngPetri();
  }

  // --------------------------------------------------------------------------------

  public EEngPetri getEngPetri() {
    return engPetri;
  }

  // --------------------------------------------------------------------------------
  // Init
  // --------------------------------------------------------------------------------

  public void init(Session session, MUser user, String worklist) //
      throws WorkflowException {

    this.session/* */= session;
    this.worklist/**/= worklist;

    // ----------------------------------------

    engPetri.setUser(user);

    // ----------------------------------------
    // Creates / Inits EngineActorDelegator
    // ----------------------------------------

    EngineActorDelegator engineActorDelegator = new EngineActorDelegatorImpl();
    engineActorDelegator.init(session);
    engPetri.setEngineActorDelegator(engineActorDelegator);

    // ----------------------------------------
    // Creates / Inits EngineAgentDelegator
    // ----------------------------------------

    EngineAgentDelegator engineAgentDelegator = new EngineAgentDelegatorImpl();
    engineAgentDelegator.init(session);
    engPetri.setEngineAgentDelegator(engineAgentDelegator);
  }

  // --------------------------------------------------------------------------------
  // Create / select methods
  // --------------------------------------------------------------------------------

  public void createWorkflow(MDocument document, //
      String netPetriName, String netStateName) //
      throws WorkflowException {

    // ----------------------------------------
    // Get the MNetPetri with the given name
    // ----------------------------------------

    MNetPetri netPetri = MBase.loadByField( //
        session, MNetPetri.class, MNetPetriProp.NAME, netPetriName);

    if (netPetri == null) {
      throw new WorkflowException( //
          WorkflowFacadeImplI18N.netPetriNullForName(netPetriName));
    }

    // ----------------------------------------
    // Creates the workflow
    // ----------------------------------------

    engPetri.getEngPetriSelect().selectNetPetri(netPetri);
    engPetri.getEngPetriCreate().createWorkflow( //
        netPetri.getNetStateSetByName(netStateName), document);
  }

  // --------------------------------------------------------------------------------

  public void selectWorkflow(MDocument document) //
      throws WorkflowException {

    if (document == null) {
      throw new IllegalArgumentException("document != null");
    }

    engPetri.getEngPetriSelect().selectWorkflow(
        document.getWorkflowRef());
  }

  // --------------------------------------------------------------------------------

  public void flushScheduler() throws WorkflowException {
    List<MWrkTransSet> list = engPetri.getAddToSchedulerList();

    for (MWrkTransSet wrkTransSet : list) {
      workflowScheduler.addWait(wrkTransSet);
    }
  }

  // --------------------------------------------------------------------------------

  //  public void dolockWorkflow(MWorkflow workflow) //
  //      throws WorkflowException {
  //    WorkflowLock.getInstance().doLockWorkflow( //
  //        workflow.getId());
  //  }
  //
  //  public void unlockWorkflow(MWorkflow workflow) //
  //      throws WorkflowException {
  //    WorkflowLock.getInstance().unLockWorkflow( //
  //        workflow.getId());
  //  }

  // --------------------------------------------------------------------------------

  public List<MActor> delegateActors() //
      throws WorkflowException {
    return engPetri.getEngPetriActor().delegateActors();
  }

  // --------------------------------------------------------------------------------
  // Fire trans methods
  // --------------------------------------------------------------------------------

  public boolean fireAutoTrans(MWrkTransSet wrkTransSet) //
      throws WorkflowException {

    try {
      return failsafeFireAutoTrans(wrkTransSet);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public boolean fireUserTrans(MWrkTrans wrkTrans) //
      throws WorkflowException {

    try {
      return failsafeFireUserTrans(wrkTrans);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      throw new WorkflowException(e);
    }
  }

  // --------------------------------------------------------------------------------
  // Fire utility methods
  // --------------------------------------------------------------------------------

  protected boolean failsafeFireAutoTrans(MWrkTransSet wrkTransSet) //
      throws Exception {

    // XXX: The need of this can be chequed with: session.contains(arg0)
    //    wrkTransSet = (MWrkTransSet) wrkTransSet.reload(session);

    MNetTransSet netTransSet = wrkTransSet.getNetTransSetRef();

    // ----------------------------------------
    // Calls the agent
    // ----------------------------------------

    MWrkTrans wrkTrans = engPetri.getEngineAgentDelegator().execute(wrkTransSet);

    //    MWrkTrans wrkTrans = AgentUtil.callExecute( //
    //        netTransSet, wrkTransSet);

    if (wrkTrans == null) {
      throw new WorkflowException( //
          WorkflowFacadeImplI18N.wrkTransNullForTrans( //
              netTransSet.getName(), wrkTransSet.getId()));
    }

    // ----------------------------------------
    // Fires the wrkTrans
    // ----------------------------------------

    log.debug(WorkflowFacadeImplI18N.firingAuto( //
        wrkTrans.getNetTransRef().getName()));

    boolean fired = engPetri.getEngPetriTrans().fireTrans( //
        wrkTrans.getNetTransRef(), EEngTrans.DIRECTION_FWD);

    // ----------------------------------------
    // Failed to fire
    // ----------------------------------------

    if (!fired) {
      return fired;
    }

    // ----------------------------------------
    // Calls the executePos of the Agent
    // ----------------------------------------

    //    AgentUtil.callExecutePos( //
    //        wrkTrans.getWrkTransSetRef().getNetTransSetRef(), //
    //        wrkTrans.getWrkTransSetRef());

    // ----------------------------------------

    addToScheduler();

    return fired;
  }

  // --------------------------------------------------------------------------------

  protected boolean failsafeFireUserTrans(MWrkTrans wrkTrans) //
      throws Exception {

    // ----------------------------------------
    // Fires the wrkTrans
    // ----------------------------------------

    log.debug(WorkflowFacadeImplI18N.firingUser( //
        wrkTrans.getNetTransRef().getName()));

    boolean fired = engPetri.getEngPetriTrans().fireTrans( //
        wrkTrans.getNetTransRef(), EEngTrans.DIRECTION_FWD);

    // ----------------------------------------
    // Failed to fire
    // ----------------------------------------

    if (!fired) {
      return fired;
    }

    // ----------------------------------------

    addToScheduler();
    return fired;
  }

  // --------------------------------------------------------------------------------

  protected void addToScheduler() throws WorkflowException {
    List<MWrkTransSet> list;

    // ----------------------------------------
    // Add new
    // ----------------------------------------

    list = engPetri.getAddToSchedulerList();

    for (MWrkTransSet wrkTransSet : list) {
      workflowScheduler.addSusp(wrkTransSet);
    }

    // ----------------------------------------
    // Del old
    // ----------------------------------------

    list = engPetri.getDelToSchedulerList();

    for (MWrkTransSet wrkTransSet : list) {
      workflowScheduler.delTask(wrkTransSet);
    }
  }

  // --------------------------------------------------------------------------------
  // Field enabled / visible methods
  // --------------------------------------------------------------------------------

  protected int getEditState(String key, MWrkTransSet wrkTransSet) {
    int state = engPetri.getEngPetriViews().getEditState(wrkTransSet, key);

    if (state == MDocSectionEditState.STATE_NOTDEF_INT) {
      try {
        state = MDocSectionEditState.intStateFromStr( //
            FlowConfig.getDefaultEditState(workflowFactoryBean.getWflowId()));
      } catch (WorkflowException e) {
        throw new RuntimeException(e);
      }
    }

    return state;
  }

  // --------------------------------------------------------------------------------

  public boolean isEditFieldEnabled(String key, MWrkTransSet wrkTransSet) {
    return getEditState(key, wrkTransSet) == MDocSectionEditState.STATE_EDITABLE_INT;
  }

  // --------------------------------------------------------------------------------

  public boolean isEditFieldVisible(String key, MWrkTransSet wrkTransSet) {
    int state = getEditState(key, wrkTransSet);

    return state == MDocSectionEditState.STATE_EDITABLE_INT || //
        state == MDocSectionEditState.STATE_READONLY_INT;
  }

  // --------------------------------------------------------------------------------

  public boolean isViewFieldVisible(String key) {
    int state = engPetri.getEngPetriViews().getViewState(key);

    if (state == MDocSectionViewState.STATE_NOTDEF_INT) {
      try {
        state = MDocSectionViewState.intStateFromStr( //
            FlowConfig.getDefaultViewState(workflowFactoryBean.getWflowId()));
      } catch (WorkflowException e) {
        throw new RuntimeException(e);
      }
    }

    return state == MDocSectionViewState.STATE_VISIBLE_INT;
  }

  // --------------------------------------------------------------------------------

  public boolean isEditFieldValidable(String key, MWrkTrans wrkTrans) {
    int state = engPetri.getEngPetriViews().getValidateState(wrkTrans, key);

    if (state == MDocSectionValidateState.STATE_NOTDEF_INT) {
      try {
        state = MDocSectionValidateState.intStateFromStr( //
            FlowConfig.getDefaultValidateState(workflowFactoryBean.getWflowId()));
      } catch (WorkflowException e) {
        throw new RuntimeException(e);
      }
    }

    return state == MDocSectionValidateState.STATE_DOVALIDATE_INT;
  }

  // --------------------------------------------------------------------------------
  // Worklist handling
  // --------------------------------------------------------------------------------

  protected String initQuery(boolean count) {
    StringBuffer strbuf = new StringBuffer();

    if (count) {
      strbuf.append("SELECT COUNT(item) ");
    }

    strbuf.append("FROM ");
    strbuf.append(MWrkTransSet.class.getName());
    strbuf.append(" AS item WHERE ");
    strbuf.append(" item.");
    strbuf.append(MWrkTransSetProp.STATUS);
    strbuf.append(" = :");
    strbuf.append(MWrkTransSetProp.STATUS);
    strbuf.append(" AND ");
    strbuf.append(" item.");
    strbuf.append(CledaStringUtils.dotIt( //
        MWrkTransSetProp.NET_TRANS_SET_REF, //
        MNetTransSetProp.WORKLIST_REF, //
        MWorklistProp.NAME));
    strbuf.append(" = :");
    strbuf.append(MWorklistProp.NAME);
    strbuf.append(" AND ");
    strbuf.append(" item.");
    strbuf.append(CledaStringUtils.dotIt( //
        MWrkTransSetProp.ACTOR_REF, //
        MActorProp.USER_REF));
    strbuf.append(" = :");
    strbuf.append(MActorProp.USER_REF);

    return strbuf.toString();
  }

  // --------------------------------------------------------------------------------

  public int countTasks() //
      throws WorkflowException {

    // ----------------------------------------
    // Count
    // ----------------------------------------

    String hql = initQuery(true);

    Query query = session.createQuery(hql);
    query.setParameter( //
        MWrkTransSetProp.STATUS,/**/MWrkTransSet.STATUS_ENABLED);
    query.setParameter( //
        MWorklistProp.NAME,/*     */worklist);
    query.setParameter( //
        MActorProp.USER_REF,/*    */engPetri.getUser());

    return ((Long) query.uniqueResult()).intValue();
  }

  // --------------------------------------------------------------------------------

  public List<MWrkTransSet> listTasks(int offset, int limit) //
      throws WorkflowException {

    // ----------------------------------------
    // List
    // ----------------------------------------

    String hql = initQuery(false);

    Query query = session.createQuery(hql);
    query.setParameter( //
        MWrkTransSetProp.STATUS,/**/MWrkTransSet.STATUS_ENABLED);
    query.setParameter( //
        MWorklistProp.NAME,/*     */worklist);
    query.setParameter( //
        MActorProp.USER_REF,/*    */engPetri.getUser());

    query.setFirstResult(offset);

    if (limit > 0) {
      query.setMaxResults(limit);
    }

    return query.list();
  }

  // --------------------------------------------------------------------------------
  // Misc Methods
  // --------------------------------------------------------------------------------

  public MRole getInitRole() {
    return engPetri.getEngPetriActor().getInitRole();
  }

  // --------------------------------------------------------------------------------

  public MWrkTransSet getEnabledWrkTransSetForRole(MRole reqRole) //
      throws WorkflowException {
    return engPetri.getEngPetriActor().getEnabledWrkTransSetForRole(reqRole);
  }

  // --------------------------------------------------------------------------------

  public List<MWrkTransLog> getWrkTransLogList() //
      throws WorkflowException {
    return engPetri.getEngPetriActor().getWrkTransLogList();
  }
}
