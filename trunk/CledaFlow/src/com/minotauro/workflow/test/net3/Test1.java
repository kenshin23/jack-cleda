/*
 * Created on 10/01/2008
 */
package com.minotauro.workflow.test.net3;

import java.util.List;

import org.hibernate.Session;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.cleda.threads.ThreadUtils;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.test.base.BaseTest;

/**
 * @author Demián Gutierrez
 */
public class Test1 extends BaseTest {

  public void testA() throws Exception {
    createNetPetri("net3", null, null, null, null, null, null);

    DTest1 test1 = new DTest1();
    test1.setTotCount(200);

    List<MActor> list = createWorkflow("test-net", "ss1", test1);
    assertTrue(list.isEmpty());

    beg();
    checkActorCurr(1);
    checkActorHist(0);

    stateGrp("sg1");

    trans("worklist", "dummy0", "ts1", "t1");

    ThreadUtils.dowait(Global.syncToken);

    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    test1 = (DTest1) test1.reload(session);

    System.err.println(test1.getCurCount());
    System.err.println(test1.getLftCount());
    System.err.println(test1.getRghCount());
    System.err.println(test1.getTotCount());

    System.err.println("*****************");
    System.err.println(Global.curCount);
    System.err.println(Global.lftCount);
    System.err.println(Global.rghCount);

    session.getTransaction().commit();
    session.close();

    end();
  }
}
