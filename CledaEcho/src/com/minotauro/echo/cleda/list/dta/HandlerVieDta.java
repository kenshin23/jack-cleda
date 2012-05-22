/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;

/**
 * @author Demián Gutierrez
 */
public class HandlerVieDta implements CommandListener {

  protected FrmListDtaBase frmBaseDtaList;

  // --------------------------------------------------------------------------------

  public HandlerVieDta(FrmListDtaBase frmBaseDtaList) {
    this.frmBaseDtaList = frmBaseDtaList;
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
    MBase data = (MBase) frmBaseDtaList.getTable().getTableDtaModel().getElementAt(row);

    FrmEditBase frmEditBase = frmBaseDtaList.getFrmEditDtaClass().newInstance();

    BaseAppInstance.getDesktop().addForm(
        frmBaseDtaList, frmEditBase, ProcessContextMode.CREATE);

    frmEditBase.init(data, EnumEditMode.SELECT, frmBaseDtaList.getDataMode());
  }
}
