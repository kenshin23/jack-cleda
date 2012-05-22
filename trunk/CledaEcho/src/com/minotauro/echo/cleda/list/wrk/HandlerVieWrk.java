/*
 * Created on 15/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.FrmListBase;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class HandlerVieWrk implements CommandListener {

  protected FrmListBase frmBaseList;

  // --------------------------------------------------------------------------------

  public HandlerVieWrk(FrmListBase frmBaseList) {
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
  // TODO: Duplicated (HandlerVieWrk / HandlerEdtWrk)
  // --------------------------------------------------------------------------------

  protected void failsafeBtnVieClicked(int row) throws Exception {

    MWrkTransSet wrkTransSet =
        (MWrkTransSet) frmBaseList.getTable().getTableDtaModel().getElementAt(row);

    FrmEditWrk frmEditWrk = getFrmEditWrk(wrkTransSet.getNetTransSetRef());

    frmEditWrk.getAcceptCancelProxy().addAcceptCancelListener(new AcceptCancelAdapter() {
      public void btnAcceptClicked(ActionEvent evt) {
        frmBaseList.loadTable();
      }
    });

    BaseAppInstance.getDesktop().addForm(
        frmBaseList, frmEditWrk, ProcessContextMode.CREATE);

    frmEditWrk.init(wrkTransSet, wrkTransSet.getWorkflowRef().getDocumentRef(),
        EnumEditMode.SELECT, EnumDataMode.DATABASE);
  }

  // --------------------------------------------------------------------------------
  // TODO: Duplicated (HandlerVieWrk / HandlerEdtWrk)
  // --------------------------------------------------------------------------------

  protected FrmEditWrk getFrmEditWrk(MNetTransSet netTransSet)
      throws Exception {

    String clazzName = netTransSet.getMetaData(
        /*  */"java.echo.doc.editor");

    if (clazzName == null) {
      clazzName = netTransSet.getNetPetriRef().getMetaData(
          /**/"java.echo.doc.editor");
    }

    Class<?> clazzInst = Class.forName(clazzName);

    return (FrmEditWrk) clazzInst.newInstance();
  }
}
