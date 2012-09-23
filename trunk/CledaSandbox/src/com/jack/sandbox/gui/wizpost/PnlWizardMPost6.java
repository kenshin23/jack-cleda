/*
 * Created on 16/09/2011
 */
package com.jack.sandbox.gui.wizpost;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.mcrudpost._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.minnerac.FrmMInnerAList;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMInnerA;
import com.minotauro.sandbox.model._PropMInnerPost;

/**
 * @author e.g.:Jack
 */
public class PnlWizardMPost6 extends PnlWizardBase {

  protected FieldModel fmInnerPostA;

  // --------------------------------------------------------------------------------

  public PnlWizardMPost6(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmInnerPostA/* */= initInnerPostA());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initInnerPostA() {

    EInnerButton innInner = new EInnerButton(
        FrmMInnerAList.class,
        (MBase) data,
        _PropMCrudPost.INNER_POST_CLIST,
        _PropMInnerPost.CRUD_POST_REF,
        parent);

    innInner.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmInner = new FieldModel();
    fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.innerPostC()));
    fmInner.setFieldCmp(innInner);
    fmInner.setKey(_PropMCrudPost.INNER_POST_CLIST);

    return fmInner;
  }
}
