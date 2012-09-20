/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.msingjointpostb;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model._PropMSingJointPostB;

/**
 * @author e.g.:jack
 */
public class FrmMSingJointPostBEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmMSingJointPostBEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    MSingJointPostB jointPostB = (MSingJointPostB) data;

    String name1 = jointPostB.getCrudBRef().getName();
    String name2 = jointPostB.getName();

    setTitle(_I18NFrmMSingJointPostBEdit.title( //
        (name1 == null ? "-" : name1) + " / " + (name2 == null ? "-" : name2)));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMSingJointPostBEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMSingJointPostB.NAME);
    fmName.setProperty(_PropMSingJointPostB.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMSingJointPostBEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMSingJointPostBEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMSingJointPostB.DESC);
    fmDesc.setProperty(_PropMSingJointPostB.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMSingJointPostBEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }
}
