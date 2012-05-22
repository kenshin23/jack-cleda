/*
 * Created on 15/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.workflow.model.MDocument;

/**
 * @author Demián Gutierrez
 */
public class HandlerVieDoc implements CommandListener {

  protected FrmListDoc frmBaseList;

  // --------------------------------------------------------------------------------

  public HandlerVieDoc(FrmListDoc frmBaseList) {
    this.frmBaseList = frmBaseList;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(final int row) {
    try {
      failsafeBtnVieClicked(row);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeBtnVieClicked(int row) throws Exception {

    MBase data = (MBase) frmBaseList.getTable().getTableDtaModel().getElementAt(row);

    FrmEditWrk frmEditWrk =
        (FrmEditWrk) frmBaseList.getFrmEditDtaClass().newInstance();

    frmEditWrk.getAcceptCancelProxy().addAcceptCancelListener(new AcceptCancelAdapter() {
      public void btnAcceptClicked(ActionEvent evt) {
        frmBaseList.loadTable();
      }
    });

    BaseAppInstance.getDesktop().addForm(
        frmBaseList, frmEditWrk, ProcessContextMode.CREATE);

    frmEditWrk.init(null, (MDocument) data,
        EnumEditMode.SELECT, frmBaseList.getDataMode());
  }
}
