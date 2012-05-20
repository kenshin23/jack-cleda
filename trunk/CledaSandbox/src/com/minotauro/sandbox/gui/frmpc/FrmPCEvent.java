/*
 * Created on 03/09/2011
 */
package com.minotauro.sandbox.gui.frmpc;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class FrmPCEvent implements AppDesktopEvent {

  // --------------------------------------------------------------------------------

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    FrmPCTest frmCreate = new FrmPCTest();

    BaseAppInstance.getDesktop().addForm(
        null, frmCreate, ProcessContextMode.CREATE);

    frmCreate.initForm();
  }
}
