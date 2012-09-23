/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcrudpost;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.minnerac.FrmMInnerPostList;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMInnerPost;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudPost5 extends PnlWizardBase {

  protected FieldModel fmInnerPostC;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost5(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmInnerPostC/* */= initInnerPostC());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initInnerPostC() {

    EInnerButton innInner = new EInnerButton(
        FrmMInnerPostList.class,
        (MBase) data,
        _PropMCrudPost.INNER_PostCLIST,
        _PropMInnerPost.CRUD_PostREF,
        parent);

    innInner.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmInner = new FieldModel();
    fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.innerPostC()));
    fmInner.setFieldCmp(innInner);
    fmInner.setKey(_PropMCrudPost.INNER_PostCLIST);

    return fmInner;
  }
}
