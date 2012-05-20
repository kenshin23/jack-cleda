/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestLoadHeavy extends TestLoadBase {

  public TestLoadHeavy() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestLoad(-1, 6000, 300, 500);
  }
}
