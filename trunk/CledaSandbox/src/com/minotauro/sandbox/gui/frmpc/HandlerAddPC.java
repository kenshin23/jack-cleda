/*
 * Created on 03/09/2011
 */
package com.minotauro.sandbox.gui.frmpc;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;

/**
 * @author Demián Gutierrez
 */
public class HandlerAddPC implements CommandListener {

  // --------------------------------------------------------------------------------

  protected ProcessContextMode pCtxtMode;

  protected FrmPCTest frmParent;

  // --------------------------------------------------------------------------------

  public HandlerAddPC(FrmPCTest frmParent, ProcessContextMode pCtxtMode) {
    this.frmParent = frmParent;
    this.pCtxtMode = pCtxtMode;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(int row) {
    FrmPCTest frmCreate = new FrmPCTest();

    BaseAppInstance.getDesktop().addForm(
        frmParent, frmCreate, pCtxtMode);

    frmCreate.initForm();
  }
}
