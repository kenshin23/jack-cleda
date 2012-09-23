/*
 * Created on 10/06/2012
 */
package com.jack.sandbox.gui.wizpost;

import com.minotauro.echo.cleda.wizard.FrmWizardDta;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.sandbox.model.MCrudPost;

/**
 * @author e.g.:Jack
 */
public class FrmWizardMPost extends FrmWizardDta {

  public FrmWizardMPost() {
    super(_I18NFrmWizardMPost.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    pnlWizardBaseList.add(new PnlWizardMPost1(this));
    pnlWizardBaseList.add(new PnlWizardMPost2(this));
    pnlWizardBaseList.add(new PnlWizardMPost3(this));
    pnlWizardBaseList.add(new PnlWizardMPost4(this));
    pnlWizardBaseList.add(new PnlWizardMPost5(this));
    pnlWizardBaseList.add(new PnlWizardMPost6(this));
    
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    super.doFnshClicked(pnlWizardBase);

    MessageBox.msgBox(_I18NMessageBox.information(),
        _I18NFrmWizardMPost.finalMessage(), null,
        250, 100, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Object initData() {
    return new MCrudPost();
  }
}
