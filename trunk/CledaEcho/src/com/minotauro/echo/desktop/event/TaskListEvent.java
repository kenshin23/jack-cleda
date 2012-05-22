/*
 * Created on 01/09/2011
 */
package com.minotauro.echo.desktop.event;

import com.minotauro.echo.cleda.list.wrk.FrmListWrk;
import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class TaskListEvent implements AppDesktopEvent {

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    String worklist = menu.getMetaData("worklist");

    if (worklist == null) {
      throw new IllegalArgumentException("worklist == null");
    }

    FrmListWrk frmListWrk = new FrmListWrk(worklist);

    desktop.addForm(
        null, frmListWrk, ProcessContextMode.CREATE);

    frmListWrk.initAll();
  }
}
