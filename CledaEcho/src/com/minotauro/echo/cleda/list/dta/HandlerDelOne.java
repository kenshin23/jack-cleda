/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.cleda.list.BaseTableDtaModel;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;

/**
 * @author Demián Gutierrez
 */
public class HandlerDelOne implements CommandListener {

  protected FrmListDtaBase frmBaseDtaList;

  // --------------------------------------------------------------------------------

  public HandlerDelOne(FrmListDtaBase frmBaseDtaList) {
    this.frmBaseDtaList = frmBaseDtaList;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(final int row) {
    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent aevt) {
        if (MessageBox.isOption(aevt, MessageBox.NO_OPT)) {
          return;
        }

        deleteOne(row);
      }
    };

    MessageBox.msgBox(
        _I18NMessageBox.information(),
        _I18NHandlerDelOne.confirmDeleteOne(),
        actionListener, 300, 130, MessageBox.YE_OPT | MessageBox.NO_OPT);
  }

  protected void deleteOne(int row) {
    switch (frmBaseDtaList.getDataMode()) {
      case DATABASE :
        deleteOneDatabase(row);
        break;
      case INMEMORY :
        deleteOneInMemory(row);
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected void deleteOneDatabase(int row) {
    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(frmBaseDtaList);

    try {
      processContext.begTransaction();

      boolean deleteAllowed = //
      failsafeDeleteOneDatabase(processContext, row);

      if (!deleteAllowed) {
        MessageBox.msgBox(
            _I18NMessageBox.information(),
            _I18NHandlerDelOne.delNotAllowedOne(),
            null, 300, 130, MessageBox.OK_OPT);
      }
    } finally {
      processContext.comTransaction();
      frmBaseDtaList.loadTable();
    }
  }

  // --------------------------------------------------------------------------------

  protected boolean failsafeDeleteOneDatabase(ProcessContext processContext, int row) {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) frmBaseDtaList.getTable().getTableDtaModel();

    MBase base = (MBase) tableDtaModel.getElementAt(row);

    base = (MBase) base.reload(processContext.getSession());

    if (base == null) {
      return true;
    }

    frmBaseDtaList.preDelete(processContext, base);

    if (!frmBaseDtaList.deleteAllowed(base)) {
      return false;
    }

    frmBaseDtaList.getTable().getTableSelModel().getSelectedSet().remove(base);
    processContext.getSession().delete(base);
    frmBaseDtaList.posDelete(processContext, base);

    return true;
  }

  // --------------------------------------------------------------------------------

  protected void deleteOneInMemory(int row) {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) frmBaseDtaList.getTable().getTableDtaModel();

    MBase base = (MBase) tableDtaModel.getElementAt(row);

    if (!frmBaseDtaList.deleteAllowed(base)) {
      MessageBox.msgBox(_I18NMessageBox.information(),
          _I18NHandlerDelOne.delNotAllowedOne(),
          null, 300, 130, MessageBox.OK_OPT);
      return;
    }

    frmBaseDtaList.getTable().getTableSelModel().getSelectedSet().remove(base);
    frmBaseDtaList.getDataList().remove(base);
    frmBaseDtaList.loadTable();
  }
}
