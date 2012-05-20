/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestDownLight extends TestDownBase {

  public TestDownLight() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestDown(-1, 6000, 300, 300000, 0);
  }
}
