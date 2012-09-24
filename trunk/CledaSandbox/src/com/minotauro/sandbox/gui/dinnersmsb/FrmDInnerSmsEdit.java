/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dinnersmsb;

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
import com.minotauro.sandbox.model.DInnerSms;
import com.minotauro.sandbox.model._PropDInnerSms;

/**
 * @author Demián Gutierrez
 */
public class FrmDInnerSmsEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmDInnerSmsEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((DInnerSms) data).getName();
    setTitle(_I18NFrmDInnerSmsEdit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDInnerSmsEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDInnerSms.NAME);
    fmName.setProperty(_PropDInnerSms.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDInnerSmsEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDInnerSmsEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDInnerSms.DESC);
    fmDesc.setProperty(_PropDInnerSms.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDInnerSmsEdit.desc(), txtDesc));

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
