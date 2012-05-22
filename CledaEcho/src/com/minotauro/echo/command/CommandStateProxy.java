/*
 * Created on 21/10/2008
 */
package com.minotauro.echo.command;

import com.minotauro.echo.table.base.ETable;

/**
 * @author Demián Gutierrez
 */
public class CommandStateProxy {

  public boolean getCommandState( //
      ETable table, Object value, int col, int row) {

    return true;
  }

  public boolean getCommandState() {
    return true;
  }
}
