/*
 * Created on 10/01/2008
 */
package com.minotauro.workflow.test.net1;

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

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    createNetPetri("net1", null, null, "user0.xml", null, null, null);

    List<MActor> list = createWorkflow("test-net", "wait-for-1st", new MDocument());

    assertTrue(list.isEmpty());

    beg();
    checkActorCurr(2);
    checkActorHist(0);
    trans("worklist", "dummy1", "check-1st", "accept-1st");
    trans("worklist", "dummy2", "check-2nd", "accept-2nd");
    stateGrp("accept");
    checkActorCurr(0);
    checkActorHist(2);
    end();
  }

  // --------------------------------------------------------------------------------

  public void testB() throws Exception {
    createNetPetri("net1", null, null, "user0.xml", null, null, null);

    List<MActor> list = createWorkflow("test-net", "wait-for-1st", new MDocument());

    assertTrue(list.isEmpty());

    beg();
    checkActorCurr(2);
    checkActorHist(0);
    trans("worklist", "dummy1", "check-1st", "deny-1st");
    relax(100);
    stateGrp("deny");
    checkActorCurr(0);
    checkActorHist(2);
    end();
  }

  // --------------------------------------------------------------------------------

  public void testC() throws Exception {
    createNetPetri("net1", null, null, "user0.xml", null, null, null);

    List<MActor> list = createWorkflow("test-net", "wait-for-1st", new MDocument());
    assertTrue(list.isEmpty());

    beg();
    checkActorCurr(2);
    checkActorHist(0);
    trans("worklist", "dummy1", "check-1st", "accept-1st");
    trans("worklist", "dummy2", "check-2nd", "deny-2nd");
    relax(100);
    stateGrp("deny");
    checkActorCurr(0);
    checkActorHist(2);
    end();
  }
}
