/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;

/**
 * @author Demián Gutierrez
 */
public class HandlerEdtDta implements CommandListener {

  protected FrmListDtaBase frmBaseDtaList;

  // --------------------------------------------------------------------------------

  public HandlerEdtDta(FrmListDtaBase frmBaseDtaList) {
    this.frmBaseDtaList = frmBaseDtaList;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(final int row) {
    try {
      failsafeBtnEdtClicked(row);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeBtnEdtClicked(int row) throws Exception {
    MBase data = (MBase) frmBaseDtaList.getTable().getTableDtaModel().getElementAt(row);

    FrmEditBase frmEditBase = frmBaseDtaList.getFrmEditDtaClass().newInstance();

    frmEditBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelAdapter() {
          @Override
          public void btnAcceptClicked(ActionEvent evt) {
            frmBaseDtaList.loadTable();
          }
        });

    BaseAppInstance.getDesktop().addForm(
        frmBaseDtaList, frmEditBase, ProcessContextMode.CREATE);

    frmEditBase.init(data, EnumEditMode.UPDATE, frmBaseDtaList.getDataMode());
  }
}
