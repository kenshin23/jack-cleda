/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.user;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MProfPriv;
import com.minotauro.user.model.MProfRole;
import com.minotauro.user.model._PropMProf;
import com.minotauro.user.model._PropMProfPriv;
import com.minotauro.user.model._PropMProfRole;

/**
 * @author Demián Gutierrez
 */
public class FrmMProfEdit extends FrmEditBase {

  protected FieldModel fmName;
  protected FieldModel fmDesc;

  protected FieldModel fmProfPriv;
  protected FieldModel fmProfRole;

  // --------------------------------------------------------------------------------

  public FrmMProfEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MProf) data).getName();
    setTitle(_I18NFrmMProfEdit.title(name == null ? "-" : name));

    setW(new Extent(450));
    setH(new Extent(280));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMProfEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMProf.NAME);
    fmName.setProperty(_PropMProf.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMProfEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMProfEdit.description()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMProf.DESCRIPTION);
    fmDesc.setProperty(_PropMProf.DESCRIPTION);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMProfEdit.description(), txtDesc));

    sectionModel.addChild(fmDesc);

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmProfPriv = initProfPriv());
    sectionModel.addChild(fmProfRole = initProfRole());

    // --------------------------------------------------------------------------------

    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmName);
    formModel.getValidatorList().add(duplicatedValidator);
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initProfPriv() {
    EJointModel modelJointFactory = new EJointModel();

    modelJointFactory.init(data, MProfPriv.class, //
        _PropMProf.PROF_PRIV_LIST, //
        _PropMProfPriv.PROF_REF, _PropMProfPriv.PRIV_REF);

    EJointButton jntJoint = new EJointButton( //
        FrmMProfPrivList.class, modelJointFactory, this) {
      protected String getInfoText() {
        List<MProfPriv> profPrivList = //
        ((MProf) data).getProfPrivList();

        return !profPrivList.isEmpty() ? //
            profPrivList.size() + " elements selected..."
            : null;
      }
    };
    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMProfEdit.priv()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMProf.PROF_PRIV_LIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initProfRole() {
    EJointModel modelJointFactory = new EJointModel();

    modelJointFactory.init(data, MProfRole.class, //
        _PropMProf.PROF_ROLE_LIST, //
        _PropMProfRole.PROF_REF, _PropMProfRole.ROLE_REF);

    EJointButton jntJoint = new EJointButton( //
        FrmMProfRoleList.class, modelJointFactory, this) {
      protected String getInfoText() {
        List<MProfRole> profRoleList = //
        ((MProf) data).getProfRoleList();

        return !profRoleList.isEmpty() ? //
            profRoleList.size() + " elements selected..."
            : null;
      }
    };
    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMProfEdit.role()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMProf.PROF_ROLE_LIST);

    return fmJoint;
  }
}
