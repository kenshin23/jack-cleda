/*
 * Created on 27/03/2008
 */
package com.minotauro.workflow.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.WorkflowLockI18N;

/**
 * @author Demián Gutierrez
 */
public class WorkflowLock {

  protected static final Logger log = LoggerFactory.getLogger( //
      WorkflowLock.class.getName());

  private static WorkflowLock instance = new WorkflowLock();

  // --------------------------------------------------------------------------------

  private Map<Integer, LockBean> lockBeanByIdMap = //
  new HashMap<Integer, LockBean>();

  // --------------------------------------------------------------------------------

  public WorkflowLock() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static WorkflowLock getInstance() {
    return instance;
  }

  // --------------------------------------------------------------------------------
  // DoLock Methods
  // --------------------------------------------------------------------------------

  public void doLockWorkflow(int workflowId) //
      throws WorkflowException {

    log.debug(WorkflowLockI18N.preDoLock( //
        workflowId, ThreadUtils.threadToString()));

    LockBean lockBean = syncDoLockWorkflow(workflowId);
    lockBean.lock();

    log.debug(WorkflowLockI18N.posDoLock( //
        workflowId, ThreadUtils.threadToString()));
  }

  // --------------------------------------------------------------------------------

  protected synchronized LockBean syncDoLockWorkflow(int workflowId) //
      throws WorkflowException {
    LockBean lockBean = lockBeanByIdMap.get(workflowId);

    if (lockBean == null) {
      lockBeanByIdMap.put( //
          workflowId, lockBean = new LockBean());
    }

    lockBean.lockCount++;

    return lockBean;
  }

  // --------------------------------------------------------------------------------
  // UnLock Methods
  // --------------------------------------------------------------------------------

  public void unLockWorkflow(int workflowId) //
      throws WorkflowException {

    log.debug(WorkflowLockI18N.preUnLock( //
        workflowId, ThreadUtils.threadToString()));

    LockBean lockBean = syncUnLockWorkflow(workflowId);
    lockBean.unlock();

    log.debug(WorkflowLockI18N.posUnLock( //
        workflowId, ThreadUtils.threadToString()));
  }

  // --------------------------------------------------------------------------------

  protected synchronized LockBean syncUnLockWorkflow(int workflowId) //
      throws WorkflowException {

    LockBean lockBean = lockBeanByIdMap.get(workflowId);

    if (lockBean == null) {
      throw new WorkflowException( //
          WorkflowLockI18N.lockBeanNullForId(workflowId));
    }

    if (lockBean.lockCount == 1) {
      lockBeanByIdMap.remove(workflowId);
    }

    lockBean.lockCount--;

    return lockBean;
  }

  // --------------------------------------------------------------------------------
  // LockBean
  // --------------------------------------------------------------------------------

  private static class LockBean extends ReentrantLock {
    public int lockCount;
  }
}
