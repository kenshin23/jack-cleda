/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.msingjointac;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model._PropMMultJointAC;

/**
 * @author Demián Gutierrez
 */
public class FrmMSingJointACEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmMSingJointACEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    MSingJointAC jointAC = (MSingJointAC) data;

    String name1 = jointAC.getCrudCRef().getName();
    String name2 = jointAC.getName();

    setTitle(_I18NFrmMSingJointACEdit.title( //
        (name1 == null ? "-" : name1) + " / " + (name2 == null ? "-" : name2)));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMSingJointACEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMMultJointAC.NAME);
    fmName.setProperty(_PropMMultJointAC.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMSingJointACEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMSingJointACEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMMultJointAC.DESC);
    fmDesc.setProperty(_PropMMultJointAC.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMSingJointACEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}
