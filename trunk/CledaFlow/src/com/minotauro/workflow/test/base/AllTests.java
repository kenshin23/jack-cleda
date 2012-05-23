/*
 * Created on 06/02/2008
 */
package com.minotauro.workflow.test.base;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.apache.commons.lang.ClassUtils;

/**
 * @author Demián Gutierrez
 */
public class AllTests {

  public static Test suite() {
    TestSuite suite = new TestSuite(ClassUtils.getPackageName(AllTests.class));

    //$JUnit-BEGIN$
    suite.addTestSuite(com.minotauro.workflow.test.net1.Test1.class);
    suite.addTestSuite(com.minotauro.workflow.test.net2.Test1.class);
    suite.addTestSuite(com.minotauro.workflow.test.net2.Test2.class);
    suite.addTestSuite(com.minotauro.workflow.test.net3.Test1.class);
    //$JUnit-END$

    return suite;
  }
}
