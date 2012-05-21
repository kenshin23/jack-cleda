/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcruda;

import com.minotauro.echo.cleda.wizard.FrmWizardDta;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.sandbox.model.MCrudA;

/**
 * @author Demián Gutierrez
 */
public class FrmWizardMCrudA extends FrmWizardDta {

  public FrmWizardMCrudA() {
    super(_I18NFrmWizardMCrudA.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    pnlWizardBaseList.add(new PnlWizardMCrudA1(this));
    pnlWizardBaseList.add(new PnlWizardMCrudA2(this));
    pnlWizardBaseList.add(new PnlWizardMCrudA3(this));
    pnlWizardBaseList.add(new PnlWizardMCrudA4(this));
    pnlWizardBaseList.add(new PnlWizardMCrudA5(this));
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    super.doFnshClicked(pnlWizardBase);

    MessageBox.msgBox(_I18NMessageBox.information(),
        _I18NFrmWizardMCrudA.finalMessage(), null,
        250, 100, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Object initData() {
    return new MCrudA();
  }
}
