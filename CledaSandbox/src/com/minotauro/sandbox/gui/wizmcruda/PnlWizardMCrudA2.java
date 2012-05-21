/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcruda;

import nextapp.echo.app.Extent;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
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
public class PnlWizardMCrudA2 extends PnlWizardBase {

  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudA2(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudBEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudB.DESC);
    fmDesc.setProperty(_PropMCrudB.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudBEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}
