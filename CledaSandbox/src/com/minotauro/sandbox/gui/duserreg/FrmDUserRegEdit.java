/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.duserreg;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.DUserReg;
import com.minotauro.sandbox.model._PropDUserReg;

/**
 * @author Demián Gutierrez
 */
public class FrmDUserRegEdit extends FrmEditWrk {

  protected FieldModel fmUser;

  // --------------------------------------------------------------------------------

  public FrmDUserRegEdit() {
    super("ID", "test-net", "enterdoc");
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    String user = ((DUserReg) data).getUser();
    setTitle(_I18NFrmDUserRegEdit.title(user == null ? "-" : user));

    setW(new Extent(450));
    setH(new Extent(500));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmUser = new FieldModel();
    fmUser.setLabelCmp(new EFieldLabel(_I18NFrmDUserRegEdit.user()));
    fmUser.setFieldCmp(txtName);
    fmUser.setKey(_PropDUserReg.USER);
    fmUser.setProperty(_PropDUserReg.USER);

    fmUser.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDUserRegEdit.user(), txtName));

    sectionModel.addChild(fmUser);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected DUserReg initMDocument() {
    return new DUserReg();
  }
}
