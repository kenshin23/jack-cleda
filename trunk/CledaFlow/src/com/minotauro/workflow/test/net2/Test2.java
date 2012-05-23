/*
 * Created on 10/01/2008
 */
package com.minotauro.workflow.test.net2;

import java.util.List;

import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.test.base.BaseTest;

/**
 * @author Demián Gutierrez
 */
public class Test2 extends BaseTest {

  protected Exception exception;

  // --------------------------------------------------------------------------------

  public Test2() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected void runTest(int count, int delay, int seed) throws Exception {
    initRandom(seed);

    createNetPetri("net2", null, null, null, null, null, null);

    List<MActor> list = createWorkflow("test-net", "ss1", new MDocument());

    assertTrue(list.isEmpty());

    beg();

    checkActorCurr(1);
    checkActorHist(0);

    stateGrp("sg1");

    trans("worklist", "dummy0", "ts1", "t1");
    stateGrp("sg2");

    final Boolean threadToken = new Boolean(true);

    RunnableImplementationTS2 rTS2 = new RunnableImplementationTS2(threadToken, delay, count);
    Thread threadTS2 = new Thread(rTS2, "ts2");
    threadTS2.start();

    RunnableImplementationTS3 rTS3 = new RunnableImplementationTS3(threadToken, delay);
    Thread threadTS3 = new Thread(rTS3, "ts3");
    threadTS3.start();

    RunnableImplementationTS4 rTS4 = new RunnableImplementationTS4(threadToken, delay);
    Thread threadTS4 = new Thread(rTS4, "ts4");
    threadTS4.start();

    ThreadUtils.dowait(threadToken);

    if (exception != null) {
      throw exception;
    }

    synchronized (threadToken) {
      rTS3.running = false;
      ThreadUtils.dowait(threadToken);
    }

    synchronized (threadToken) {
      rTS4.running = false;
      ThreadUtils.dowait(threadToken);
    }

    end();
  }

  // --------------------------------------------------------------------------------

  private abstract class RunnableImplementationBase implements Runnable {
    protected Boolean threadToken;
    protected int delay;

    private RunnableImplementationBase(Boolean threadToken, int delay) {
      this.threadToken/**/= threadToken;
      this.delay/*      */= delay;
    }

    @Override
    public void run() {
      try {
        doRun();
      } catch (Exception e) {
        synchronized (threadToken) {
          if (exception == null) {
            exception = e;
          }
        }

        log.error(e.getMessage(), e);
      }

      ThreadUtils.notify(threadToken);
    }

    abstract void doRun() throws Exception;
  }

  // --------------------------------------------------------------------------------

  private class RunnableImplementationTS2 extends RunnableImplementationBase {
    protected int count;

    private RunnableImplementationTS2(Boolean threadToken, int delay, int count) {
      super(threadToken, delay);

      this.count = count;
    }

    @Override
    void doRun() throws Exception {
      int i = 0;

      while (i < count) {
        if (trans("worklist", "dummy0", "ts2", "t2")) {
          log.debug("fire ts2/t2, count: " + i++);
        } else {
          log.debug("failed for ts2/t2");
        }

        Thread.sleep(random.nextInt(delay));
      }
    }
  }

  // --------------------------------------------------------------------------------

  private class RunnableImplementationTS3 extends RunnableImplementationBase {
    public boolean running = true;

    private RunnableImplementationTS3(Boolean threadToken, int delay) {
      super(threadToken, delay);
    }

    @Override
    void doRun() throws Exception {
      while (running) {
        if (!trans("worklist", "dummy0", "ts3", "t4")) {
          log.debug("failed for ts3/t4");
          continue;
        }

        trans("worklist", "dummy0", "ts5", "t6");
        Thread.sleep(random.nextInt(delay));
      }
    }
  }

  // --------------------------------------------------------------------------------

  private class RunnableImplementationTS4 extends RunnableImplementationBase {
    public boolean running = true;

    private RunnableImplementationTS4(Boolean threadToken, int delay) {
      super(threadToken, delay);
    }

    @Override
    void doRun() throws Exception {
      while (running) {
        if (!trans("worklist", "dummy0", "ts4", "t5")) {
          log.debug("failed for ts4/t5");
          continue;
        }

        trans("worklist", "dummy0", "ts6", "t7");
        Thread.sleep(random.nextInt(delay));
      }
    }
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    runTest(500, 100, 1000);
  }
}
