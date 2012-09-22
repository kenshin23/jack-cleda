/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.minnerpostc;

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
import com.minotauro.sandbox.model.MInnerPost;
import com.minotauro.sandbox.model._PropMInnerPost;

/**
 * @author Demián Gutierrez
 */
public class FrmMInnerPostEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmMInnerPostEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MInnerPost) data).getName();
    setTitle(_I18NFrmMInnerPostEdit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMInnerPostEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMInnerPost.NAME);
    fmName.setProperty(_PropMInnerPost.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMInnerPostEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMInnerPostEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMInnerPost.DESC);
    fmDesc.setProperty(_PropMInnerPost.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMInnerPostEdit.desc(), txtDesc));

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
