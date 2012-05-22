/*
 * Created on 15/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import java.util.List;

import nextapp.echo.app.event.ActionEvent;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class HandlerEdtDoc implements CommandListener {

  protected FrmListDoc frmBaseList;

  // --------------------------------------------------------------------------------

  public HandlerEdtDoc(FrmListDoc frmBaseList) {
    this.frmBaseList = frmBaseList;
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

    MDocument data = (MDocument) frmBaseList.getTable().getTableDtaModel().getElementAt(row);

    MWorkflow workflow = data.getWorkflowRef();

    // ---------------------------------------------------------------------------
    // TODO: if there is only one trans set, the selection is not necesary
    // ---------------------------------------------------------------------------

    List<MWrkTransSet> wrkTransSetList =
        workflow.getEnabledWrkTransSetForUser(BaseAppInstance.getUser());

    FrmSelectTask frmSelectTask = new FrmSelectTask(wrkTransSetList);

    frmSelectTask.getAcceptCancelProxy().addAcceptCancelListener(new AcceptCancelAdapter() {

      public void btnAcceptClicked(ActionEvent evt) {
        FrmSelectTask frmSelectTask = (FrmSelectTask) evt.getSource();

        try {
          failsafeFrmSelectTaskClicked(frmSelectTask.getSelectedWrkTransSet());
        } catch (Exception e) {
          throw new RuntimeException(e);
        }
      }
    });

    BaseAppInstance.getDesktop().addForm(
        null, frmSelectTask, ProcessContextMode.IGNORE);
  }

  // --------------------------------------------------------------------------------

  protected void failsafeFrmSelectTaskClicked(MWrkTransSet wrkTransSet) throws Exception {

    FrmEditWrk frmEditWrk =
        (FrmEditWrk) frmBaseList.getFrmEditDtaClass().newInstance();

    frmEditWrk.getAcceptCancelProxy().addAcceptCancelListener(new AcceptCancelAdapter() {
      public void btnAcceptClicked(ActionEvent evt) {
        frmBaseList.loadTable();
      }
    });

    BaseAppInstance.getDesktop().addForm(
        frmBaseList, frmEditWrk, ProcessContextMode.CREATE);

    frmEditWrk.init(wrkTransSet, wrkTransSet.getWorkflowRef().getDocumentRef(),
        EnumEditMode.UPDATE, EnumDataMode.DATABASE);
  }
}
