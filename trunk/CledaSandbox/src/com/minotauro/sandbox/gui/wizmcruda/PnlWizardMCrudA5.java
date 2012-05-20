/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcruda;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.minnerac.FrmMInnerAList;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMInnerA;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudA5 extends PnlWizardBase {

  protected FieldModel fmInnerAC;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudA5(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmInnerAC/* */= initInnerAC());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initInnerAC() {

    EInnerButton innInner = new EInnerButton(
        FrmMInnerAList.class,
        (MBase) data,
        _PropMCrudA.INNER_ACLIST,
        _PropMInnerA.CRUD_AREF,
        parent);

    innInner.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmInner = new FieldModel();
    fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.innerAC()));
    fmInner.setFieldCmp(innInner);
    fmInner.setKey(_PropMCrudA.INNER_ACLIST);

    return fmInner;
  }
}
