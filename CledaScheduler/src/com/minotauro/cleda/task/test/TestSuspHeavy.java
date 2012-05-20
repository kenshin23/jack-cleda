/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestSuspHeavy extends TestSuspBase {

  public TestSuspHeavy() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestSusp(-1, 3000, 300, 500);
  }
}
