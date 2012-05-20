/*
 * Created on 05/02/2008
 */
package com.minotauro.cleda.task.test;

/**
 * @author Demián Gutierrez
 */
public class TestDeleDoLockHeavy extends TestDeleBase {

  public TestDeleDoLockHeavy() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void testA() throws Exception {
    doRunTestDele(-1, 3000, 300, 500, true);
  }
}
