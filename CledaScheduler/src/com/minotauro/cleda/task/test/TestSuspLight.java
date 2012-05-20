/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestSuspLight extends TestSuspBase {

  public TestSuspLight() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestSusp(-1, 3000, 1000, 300000);
  }
}
