/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dsingjointac;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.DSingJointAC;
import com.minotauro.sandbox.model._PropDSingJointAC;

/**
 * @author Demián Gutierrez
 */
public class FrmDSingJointACEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmDSingJointACEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    DSingJointAC jointAC = (DSingJointAC) data;

    String name1 = jointAC.getCrudCRef().getName();
    String name2 = jointAC.getName();

    setTitle(_I18NFrmDSingJointACEdit.title( //
        (name1 == null ? "-" : name1) + " / " + (name2 == null ? "-" : name2)));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDSingJointACEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDSingJointAC.NAME);
    fmName.setProperty(_PropDSingJointAC.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDSingJointACEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDSingJointACEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDSingJointAC.DESC);
    fmDesc.setProperty(_PropDSingJointAC.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDSingJointACEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}
