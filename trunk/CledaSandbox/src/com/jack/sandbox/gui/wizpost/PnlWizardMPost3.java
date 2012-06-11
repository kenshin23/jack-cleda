/* Created on 10/06/2012
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
public class PnlWizardMPost3 extends PnlWizardBase {

  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public PnlWizardMPost3(FrmWizardBase parent) {
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
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudPost.DESC);
    fmDesc.setProperty(_PropMCrudPost.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}