/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestDeleDoLockLight extends TestDeleBase {

  public TestDeleDoLockLight() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestDele(-1, 3000, 1000, 300000, true);
  }
}
