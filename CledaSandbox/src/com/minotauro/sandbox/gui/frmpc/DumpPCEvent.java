/*
 * Created on 21/08/2011
 */
package com.minotauro.sandbox.gui.frmpc;

import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class DumpPCEvent implements AppDesktopEvent {

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    desktop.dumpProcessContext();
  }
}
