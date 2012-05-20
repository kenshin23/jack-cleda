/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.user;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.grid.TabModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.user.model.MUser;
import com.minotauro.user.model.MUserProf;
import com.minotauro.user.model._PropMUser;
import com.minotauro.user.model._PropMUserProf;

/**
 * @author Demián Gutierrez
 */
public class FrmMUserEdit extends FrmEditBase {

  protected FieldModel fmUser;
  protected FieldModel fmUserProf;

  // --------------------------------------------------------------------------------

  public FrmMUserEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel;

    // --------------------------------------------------------------------------------

    formModel.setShowTabs(false);

    TabModel tabModel = new TabModel();
    formModel.addChild(tabModel);

    // --------------------------------------------------------------------------------

    sectionModel = new SectionModel();
    tabModel.addChild(sectionModel);

    // --------------------------------------------------------------------------------

    TextField txtUser = new TextField();
    txtUser.setWidth(new Extent(204));

    fmUser = new FieldModel();
    fmUser.setLabelCmp(new EFieldLabel(_I18NFrmMUserEdit.user()));
    fmUser.setFieldCmp(txtUser);
    fmUser.setKey(_PropMUser.USER);
    fmUser.setProperty(_PropMUser.USER);

    fmUser.getValidatorList().add(
        new NotEmptyValidator(_I18NFrmMUserEdit.user(), txtUser));

    sectionModel.addChild(fmUser);

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmUserProf = initUserProf());

    // --------------------------------------------------------------------------------

    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmUser);
    formModel.getValidatorList().add(duplicatedValidator);
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initUserProf() {
    EJointModel modelJointFactory = new EJointModel();

    modelJointFactory.init(data, MUserProf.class, //
        _PropMUser.USER_PROF_LIST, //
        _PropMUserProf.USER_REF, _PropMUserProf.PROF_REF);

    EJointButton jntJoint = new EJointButton( //
        FrmMUserProfList.class, modelJointFactory, this) {
      protected String getInfoText() {
        List<MUserProf> userProfList = //
        ((MUser) data).getUserProfList();

        return !userProfList.isEmpty() ? //
            userProfList.size() + " elements selected..."
            : null;
      }
    };
    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMUserEdit.prof()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMUser.USER_PROF_LIST);

    return fmJoint;
  }
}
