/*
 * Created on 10/01/2008
 */
package com.minotauro.workflow.test.net2;

import java.util.List;

import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.test.base.BaseTest;

/**
 * @author Demián Gutierrez
 */
public class Test1 extends BaseTest {

  public Test1() {
    // Empty
  }

  protected void runTest(int count) throws Exception {
    createNetPetri("net2", null, null, null, null, null, null);

    List<MActor> list = createWorkflow("test-net", "ss1", new MDocument());

    assertTrue(list.isEmpty());

    beg();
    checkActorCurr(1);
    checkActorHist(0);

    stateGrp("sg1");

    trans("worklist", "dummy0", "ts1", "t1");
    stateGrp("sg2");

    for (int i = 0; i < count; i++) {
      trans("worklist", "dummy0", "ts2", "t2");
      stateGrp("sg3");
      trans("worklist", "dummy0", "ts3", "t4");
      stateGrp("sg5");
      trans("worklist", "dummy0", "ts5", "t6");
      stateGrp("sg2");

      trans("worklist", "dummy0", "ts2", "t2");
      stateGrp("sg3");
      trans("worklist", "dummy0", "ts4", "t5");
      stateGrp("sg6");
      trans("worklist", "dummy0", "ts6", "t7");
      stateGrp("sg2");
    }

    trans("worklist", "dummy0", "ts2", "t3");
    stateGrp("sg4");

    end();
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    runTest(100);
  }
}
