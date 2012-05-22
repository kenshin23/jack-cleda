/*
 * Created on 11/12/2007
 */
package com.minotauro.echo.cleda.wizard;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.ProcessContext;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmWizardDta extends FrmWizardBase {

  public FrmWizardDta(String title) {
    super(title);
  }

  // --------------------------------------------------------------------------------

  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    saveOrUpdate();

    userClose();
  }

  // --------------------------------------------------------------------------------
  // Save Model Methods
  // --------------------------------------------------------------------------------

  protected boolean saveOrUpdate() {
    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    boolean ret =
        failsafeSaveOrUpdate(processContext);

    if (ret) {
      processContext.comTransaction();
    } else {
      processContext.rolTransaction();
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected boolean failsafeSaveOrUpdate(
      ProcessContext processContext) {

    for (PnlWizardBase pnlWizardBase : pnlWizardBaseList) {
      pnlWizardBase.updateDataFromGUIInt();
      pnlWizardBase.updateDataFromGUI();
    }

    preUpdate();
    processContext.getSession().saveOrUpdate(data);
    posUpdate();

    return true;
  }

  // --------------------------------------------------------------------------------

  protected void preUpdate() {
    // Empty
  }

  protected void posUpdate() {
    // Empty
  }
}
