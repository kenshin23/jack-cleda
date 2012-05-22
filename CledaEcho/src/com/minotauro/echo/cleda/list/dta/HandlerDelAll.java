/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import java.util.Iterator;
import java.util.Set;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;

/**
 * @author Demián Gutierrez
 */
public class HandlerDelAll implements CommandListener {

  protected FrmListDtaBase frmBaseDtaList;

  // --------------------------------------------------------------------------------

  public HandlerDelAll(FrmListDtaBase frmBaseDtaList) {
    this.frmBaseDtaList = frmBaseDtaList;
  }

  // --------------------------------------------------------------------------------

  public void commandClicked(int row) {
    ActionListener actionListener = new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if (MessageBox.isOption(evt, MessageBox.NO_OPT)) {
          return;
        }

        deleteAll();
      }
    };

    if (frmBaseDtaList.getTable().getTableSelModel().getSelectedSet().isEmpty()) {
      MessageBox.msgBox(_I18NMessageBox.information(),
          _I18NHandlerDelAll.selectAtLeastOne(),
          null, 300, 130, MessageBox.OK_OPT);
    } else {
      MessageBox.msgBox(_I18NMessageBox.information(),
          _I18NHandlerDelAll.confirmDeleteAll(),
          actionListener, 300, 130, MessageBox.YE_OPT | MessageBox.NO_OPT);
    }
  }

  // --------------------------------------------------------------------------------

  protected void deleteAll() {
    switch (frmBaseDtaList.getDataMode()) {
      case DATABASE :
        deleteAllDatabase();
        break;
      case INMEMORY :
        deleteAllInMemory();
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected void deleteAllDatabase() {
    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(frmBaseDtaList);

    try {
      processContext.begTransaction();

      boolean deleteAllowed = //
      failsafeDeleteAllDatabase(processContext);

      if (!deleteAllowed) {
        MessageBox.msgBox(_I18NMessageBox.information(),
            _I18NHandlerDelAll.delNotAllowedAll(),
            null, 300, 180, MessageBox.OK_OPT);
      }
    } finally {
      processContext.comTransaction();

      frmBaseDtaList.loadTable();
    }
  }

  // --------------------------------------------------------------------------------

  protected boolean failsafeDeleteAllDatabase(ProcessContext processContext) {
    boolean ret = true;

    Set<Object> selectedSet = //
    frmBaseDtaList.getTable().getTableSelModel().getSelectedSet();

    Iterator<Object> itt = //
    selectedSet.iterator();

    while (itt.hasNext()) {
      MBase base = (MBase) itt.next();
      base = (MBase) base.reload(processContext.getSession());

      if (base == null) {
        itt.remove();
        continue;
      }

      frmBaseDtaList.preDelete(processContext, base);

      if (!frmBaseDtaList.deleteAllowed(base)) {
        ret = false;
        continue;
      }

      processContext.getSession().delete(base);
      itt.remove();

      frmBaseDtaList.posDelete(processContext, base);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void deleteAllInMemory() {
    boolean ret = false;

    Set<Object> selectedSet = //
    frmBaseDtaList.getTable().getTableSelModel().getSelectedSet();

    Iterator<Object> itt = ///
    selectedSet.iterator();

    while (itt.hasNext()) {
      MBase base = (MBase) itt.next();

      if (!frmBaseDtaList.deleteAllowed(base)) {
        ret = true;
        continue;
      }

      frmBaseDtaList.getDataList().remove(base);
      itt.remove();
    }

    if (ret) {
      MessageBox.msgBox(_I18NMessageBox.information(),
          _I18NHandlerDelAll.delNotAllowedAll(),
          null, 300, 180, MessageBox.OK_OPT);
    }

    frmBaseDtaList.loadTable();
  }
}
