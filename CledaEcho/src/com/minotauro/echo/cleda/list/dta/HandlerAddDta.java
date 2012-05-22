/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;

/**
 * @author Demián Gutierrez
 */
public class HandlerAddDta implements CommandListener {

  protected FrmListDtaBase frmBaseDtaList;

  // --------------------------------------------------------------------------------

  public HandlerAddDta(FrmListDtaBase frmBaseDtaList) {
    this.frmBaseDtaList = frmBaseDtaList;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(int row) {
    try {
      failsafeBtnAddClicked();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeBtnAddClicked() throws Exception {
    MBase data = frmBaseDtaList.initMBase();

    FrmEditBase frmEditBase =
        frmBaseDtaList.getFrmEditDtaClass().newInstance();

    frmEditBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelAdapter() {
          @Override
          public void btnAcceptClicked(ActionEvent evt) {
            if (frmBaseDtaList.getDataMode() != EnumDataMode.DATABASE) {
              FrmEditBase frmEditBase = (FrmEditBase) evt.getSource();

              frmBaseDtaList.getDataList().add(frmEditBase.getData());
            }

            frmBaseDtaList.loadTable();
          }
        });

    BaseAppInstance.getDesktop().addForm( //
        frmBaseDtaList, frmEditBase, ProcessContextMode.CREATE);

    frmEditBase.init(data, EnumEditMode.CREATE, //
        frmBaseDtaList.getDataMode());
  }
}
