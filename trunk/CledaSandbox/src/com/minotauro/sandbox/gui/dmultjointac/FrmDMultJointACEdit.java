/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dmultjointac;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.DMultJointAC;
import com.minotauro.sandbox.model._PropDMultJointAC;

/**
 * @author Demián Gutierrez
 */
public class FrmDMultJointACEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmDMultJointACEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    DMultJointAC jointAC = (DMultJointAC) data;

    String name1 = jointAC.getCrudCRef().getName();
    String name2 = jointAC.getName();

    setTitle(_I18NFrmDMultJointACEdit.title( //
        (name1 == null ? "-" : name1) + " / " + (name2 == null ? "-" : name2)));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDMultJointACEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDMultJointAC.NAME);
    fmName.setProperty(_PropDMultJointAC.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDMultJointACEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDMultJointACEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDMultJointAC.DESC);
    fmDesc.setProperty(_PropDMultJointAC.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDMultJointACEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}
