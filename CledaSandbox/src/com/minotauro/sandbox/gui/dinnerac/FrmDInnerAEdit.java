/*
 * Created on 26/06/2008
 */
package com.minotauro.sandbox.gui.dinnerac;

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
import com.minotauro.sandbox.model.DInnerA;
import com.minotauro.sandbox.model._PropDInnerA;

/**
 * @author Demián Gutierrez
 */
public class FrmDInnerAEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmDInnerAEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((DInnerA) data).getName();
    setTitle(_I18NFrmDInnerAEdit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDInnerAEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDInnerA.NAME);
    fmName.setProperty(_PropDInnerA.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDInnerAEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDInnerAEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDInnerA.DESC);
    fmDesc.setProperty(_PropDInnerA.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDInnerAEdit.desc(), txtDesc));

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
