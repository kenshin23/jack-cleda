/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.duserreg;

import com.minotauro.echo.cleda.wizard.FrmWizardWrk;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.sandbox.model.DUserReg;

/**
 * @author Demián Gutierrez
 */
public class FrmWizardDUserReg extends FrmWizardWrk {

  public FrmWizardDUserReg() {
    super(_I18NFrmWizardDUserReg.title(),
        "ID", "user_reg-net", "begin");
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    pnlWizardBaseList.add(new PnlUser(this));
    pnlWizardBaseList.add(new PnlPass(this));
    pnlWizardBaseList.add(new PnlProf(this));
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    super.doFnshClicked(pnlWizardBase);

    MessageBox.msgBox(_I18NMessageBox.information(),
        _I18NFrmWizardDUserReg.finalMessage(), null,
        250, 100, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  protected Object initData() {
    return new DUserReg();
  };
}
