/*
 * Created on 06/02/2008
 */
package com.minotauro.cleda.task.test;

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
    suite.addTestSuite(TestDeleDoLockHeavy.class);
    suite.addTestSuite(TestLoadHeavy.class);
    suite.addTestSuite(TestSuspHeavy.class);
    suite.addTestSuite(TestDownHeavy.class);
    //$JUnit-END$

    return suite;
  }
}
