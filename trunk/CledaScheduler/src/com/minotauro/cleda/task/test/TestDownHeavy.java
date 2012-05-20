/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestDownHeavy extends TestDownBase {

  public TestDownHeavy() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestDown(-1, 6000, 300, 500, 0);
  }
}
