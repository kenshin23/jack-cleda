/*
 * Created on 10/06/2012
 */
package com.jack.sandbox.gui.wizpost;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.mcrudb._I18NFrmMCrudBEdit;
import com.minotauro.sandbox.gui.mcrudpost._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.model._PropMCrudPost;

/**
 * @author e.g.:Jack
 */
public class PnlWizardMPost1 extends PnlWizardBase {

  private FieldModel fmName;

  // --------------------------------------------------------------------------------

  public PnlWizardMPost1(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudPost.NAME);
    fmName.setProperty(_PropMCrudPost.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtName));

    sectionModel.addChild(fmName);
  }
}
