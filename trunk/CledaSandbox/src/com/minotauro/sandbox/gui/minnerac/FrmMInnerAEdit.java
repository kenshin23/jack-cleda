/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.minnerac;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.MInnerA;
import com.minotauro.sandbox.model._PropMInnerA;

/**
 * @author Demián Gutierrez
 */
public class FrmMInnerAEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmMInnerAEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MInnerA) data).getName();
    setTitle(_I18NFrmMInnerAEdit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMInnerAEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMInnerA.NAME);
    fmName.setProperty(_PropMInnerA.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMInnerAEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMInnerAEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMInnerA.DESC);
    fmDesc.setProperty(_PropMInnerA.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMInnerAEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected List<String> validateGUI(
      String command, ProcessContext processContext) {

    List<String> msgList = new ArrayList<String>();

    // --------------------------------------------------------------------------------
    // TODO: We need to have the parent list to check in memory for duplicates
    // --------------------------------------------------------------------------------

    return msgList;
  }
}
