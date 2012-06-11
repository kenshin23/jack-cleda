/*
 * Created on 10/06/2012
 */
package com.jack.sandbox.gui.wizpost;

import nextapp.echo.app.Extent;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
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
public class PnlWizardMPost2 extends PnlWizardBase {

  private FieldModel fmBody;

  // --------------------------------------------------------------------------------

  public PnlWizardMPost2(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    ETextAreaEx txtBody = new ETextAreaEx();
    txtBody.setWidth(new Extent(204));

    fmBody = new FieldModel();
    fmBody.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.body()));
    fmBody.setFieldCmp(txtBody);
    fmBody.setKey(_PropMCrudPost.BODY);
    fmBody.setProperty(_PropMCrudPost.BODY);

    fmBody.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtBody));

    sectionModel.addChild(fmBody);
  }
}
