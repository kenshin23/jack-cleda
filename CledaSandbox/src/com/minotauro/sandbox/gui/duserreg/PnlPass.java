/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.duserreg;

import nextapp.echo.app.Extent;
import nextapp.echo.app.PasswordField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model._PropDUserReg;

/**
 * @author Demián Gutierrez
 */
public class PnlPass extends PnlWizardBase {

  private FieldModel fmPass;

  // --------------------------------------------------------------------------------

  public PnlPass(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    PasswordField txtPass = new PasswordField();
    txtPass.setWidth(new Extent(204));

    fmPass = new FieldModel();
    fmPass.setLabelCmp(new EFieldLabel(_I18NFrmWizardDUserReg.pass()));
    fmPass.setFieldCmp(txtPass);
    fmPass.setKey(_PropDUserReg.PASS);
    fmPass.setProperty(_PropDUserReg.PASS);

    fmPass.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmWizardDUserReg.pass(), txtPass));

    sectionModel.addChild(fmPass);
  }
}
