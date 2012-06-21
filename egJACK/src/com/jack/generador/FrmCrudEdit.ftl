package com.minotauro.sandbox.gui.mcrudpost;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.model.${name};

import com.minotauro.sandbox.model._Prop${name};


public class Frm${name}Edit extends FrmEditBase {


[#list attributes.att as currentAtt]

  private FieldModel fmN${currentAtt.name};

[/#list]

  // --------------------------------------------------------------------------------

  public Frm${name}Edit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((${name}) data).getName();
    setTitle(_I18NFrm${name}Edit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------



[#list attributes.att as currentAtt]

	TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudPost.NAME);
    fmName.setProperty(_PropMCrudPost.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtName));

    sectionModel.addChild(fmName);

[/#list]

    

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

    // --------------------------------------------------------------------------------

    ETextAreaEx txtBody = new ETextAreaEx();
    txtBody.setWidth(new Extent(204));

    fmBody = new FieldModel();
    fmBody.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.body()));
    fmBody.setFieldCmp(txtBody);
    fmBody.setKey(_PropMCrudPost.BODY);
    fmBody.setProperty(_PropMCrudPost.BODY);

    fmBody.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc));

    sectionModel.addChild(fmBody);
    
    // --------------------------------------------------------------------------------

    
    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmName);
    formModel.getValidatorList().add(duplicatedValidator);
  }
}
