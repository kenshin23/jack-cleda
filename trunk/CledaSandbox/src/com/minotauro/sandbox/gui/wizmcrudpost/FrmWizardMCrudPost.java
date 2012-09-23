/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcrudpost;

import com.minotauro.echo.cleda.wizard.FrmWizardDta;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.sandbox.model.MCrudPost;

/**
 * @author Demián Gutierrez
 */
public class FrmWizardMCrudPost extends FrmWizardDta {

  public FrmWizardMCrudPost() {
    super(_I18NFrmWizardMCrudPost.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    pnlWizardBaseList.add(new PnlWizardMCrudPost1(this));
    pnlWizardBaseList.add(new PnlWizardMCrudPost2(this));
    pnlWizardBaseList.add(new PnlWizardMCrudPost3(this));
    pnlWizardBaseList.add(new PnlWizardMCrudPost4(this));
    pnlWizardBaseList.add(new PnlWizardMCrudPost5(this));
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    super.doFnshClicked(pnlWizardBase);

    MessageBox.msgBox(_I18NMessageBox.information(),
        _I18NFrmWizardMCrudPost.finalMessage(), null,
        250, 100, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Object initData() {
    return new MCrudPost();
  }
}
