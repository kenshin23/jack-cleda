/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestLoadLight extends TestLoadBase {

  public TestLoadLight() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestLoad(-1, 6000, 300, 300000);
  }
}
