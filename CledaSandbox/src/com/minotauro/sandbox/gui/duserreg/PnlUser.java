/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.duserreg;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

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
public class PnlUser extends PnlWizardBase {

  private FieldModel fmUser;

  // --------------------------------------------------------------------------------

  public PnlUser(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtUser = new TextField();
    txtUser.setWidth(new Extent(204));

    fmUser = new FieldModel();
    fmUser.setLabelCmp(new EFieldLabel(_I18NFrmWizardDUserReg.user()));
    fmUser.setFieldCmp(txtUser);
    fmUser.setKey(_PropDUserReg.USER);
    fmUser.setProperty(_PropDUserReg.USER);

    fmUser.getValidatorList().add(
        new NotEmptyValidator(_I18NFrmWizardDUserReg.user(), txtUser));

    sectionModel.addChild(fmUser);
  }
}
