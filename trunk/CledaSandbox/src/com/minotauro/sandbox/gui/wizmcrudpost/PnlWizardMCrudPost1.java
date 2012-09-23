/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcrudpost;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.mcrudb._I18NFrmMCrudBEdit;
import com.minotauro.sandbox.model._PropMCrudB;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudPost1 extends PnlWizardBase {

  private FieldModel fmName;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost1(FrmWizardBase parent) {
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
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudBEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudB.NAME);
    fmName.setProperty(_PropMCrudB.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudBEdit.name(), txtName));

    sectionModel.addChild(fmName);
  }
}
