/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.mcruda;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.minnerac.FrmMInnerAList;
import com.minotauro.sandbox.gui.mmultjointab.FrmMMultJointABList;
import com.minotauro.sandbox.gui.mmultjointac.FrmMMultJointACList;
import com.minotauro.sandbox.gui.msingjointab.FrmMSingJointABList;
import com.minotauro.sandbox.gui.msingjointac.FrmMSingJointACList;
import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MMultJointAB;
import com.minotauro.sandbox.model.MMultJointAC;
import com.minotauro.sandbox.model.MSingJointAB;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMInnerA;
import com.minotauro.sandbox.model._PropMMultJointAB;
import com.minotauro.sandbox.model._PropMMultJointAC;
import com.minotauro.sandbox.model._PropMSingJointAB;
import com.minotauro.sandbox.model._PropMSingJointAC;

/**
 * @author Demián Gutierrez
 */
public class FrmMCrudAEdit extends FrmEditBase {

  protected FieldModel fmName;
  protected FieldModel fmDesc;

  protected FieldModel fmMultJointAB;
  protected FieldModel fmMultJointAC;
  protected FieldModel fmSingJointAB;
  protected FieldModel fmSingJointAC;

  protected FieldModel fmInnerAC;

  // --------------------------------------------------------------------------------

  public FrmMCrudAEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrudA) data).getName();
    setTitle(_I18NFrmMCrudAEdit.title(name == null ? "-" : name));

    setW(new Extent(450));
    setH(new Extent(450));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudA.NAME);
    fmName.setProperty(_PropMCrudA.NAME);

    fmName.getValidatorList().add(
        new NotEmptyValidator(_I18NFrmMCrudAEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudA.DESC);
    fmDesc.setProperty(_PropMCrudA.DESC);

    fmDesc.getValidatorList().add(
        new NotEmptyValidator(_I18NFrmMCrudAEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmMultJointAB = initMultJointAB());
    sectionModel.addChild(fmMultJointAC = initMultJointAC());
    sectionModel.addChild(fmSingJointAB = initSingJointAB());
    sectionModel.addChild(fmSingJointAC = initSingJointAC());

    sectionModel.addChild(fmInnerAC/* */= initInnerAC());

    // --------------------------------------------------------------------------------

    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmName);
    formModel.getValidatorList().add(duplicatedValidator);
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initMultJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        MMultJointAB.class,
        _PropMCrudA.MULT_JOINT_ABLIST,
        _PropMMultJointAB.CRUD_AREF,
        _PropMMultJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMMultJointABList.class, jointModel, this);

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.multJointAB()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ABLIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initMultJointAC() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        MMultJointAC.class,
        _PropMCrudA.MULT_JOINT_ACLIST,
        _PropMMultJointAC.CRUD_AREF,
        _PropMMultJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmMMultJointACList.class, jointModel, this);

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.multJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ACLIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        MSingJointAB.class,
        _PropMCrudA.SING_JOINT_ABLIST,
        _PropMSingJointAB.CRUD_AREF,
        _PropMSingJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointABList.class, jointModel, this) {

      protected String getInfoText() {
        List<MSingJointAB> singJointABList =
            ((MCrudA) data).getSingJointABList();

        return !singJointABList.isEmpty()
            ? singJointABList.get(0).getCrudBRef().getName()
            : null;
      }
    };

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.singJointAB()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ABLIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointAC() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        MSingJointAC.class,
        _PropMCrudA.SING_JOINT_ACLIST,
        _PropMSingJointAC.CRUD_AREF,
        _PropMSingJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointACList.class, jointModel, this) {

      protected String getInfoText() {
        List<MSingJointAC> singJointACList =
            ((MCrudA) data).getSingJointACList();

        return !singJointACList.isEmpty()
            ? singJointACList.get(0).getCrudCRef().getName() +
                " / " + singJointACList.get(0).getName()
            : null;
      }
    };

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.singJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ACLIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initInnerAC() {

    EInnerButton innInner = new EInnerButton(
        FrmMInnerAList.class,
        data,
        _PropMCrudA.INNER_ACLIST,
        _PropMInnerA.CRUD_AREF,
        this);

    innInner.setEditMode(editMode);

    FieldModel fmInner = new FieldModel();
    fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.innerAC()));
    fmInner.setFieldCmp(innInner);
    fmInner.setKey(_PropMCrudA.INNER_ACLIST);

    return fmInner;
  }
}
