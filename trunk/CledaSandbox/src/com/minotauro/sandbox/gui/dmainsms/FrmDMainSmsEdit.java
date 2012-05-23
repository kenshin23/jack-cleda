
package com.minotauro.sandbox.gui.dmainsms;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.dinnerac.FrmDInnerAList;
import com.minotauro.sandbox.gui.dmultjointab.FrmDMultJointABList;
import com.minotauro.sandbox.gui.dmultjointac.FrmDMultJointACList;
import com.minotauro.sandbox.gui.dsingjointab.FrmDSingJointABList;
import com.minotauro.sandbox.gui.dsingjointac.FrmDSingJointACList;
import com.minotauro.sandbox.model.DMainSms;
import com.minotauro.sandbox.model.DMultJointAB;
import com.minotauro.sandbox.model.DMultJointAC;
import com.minotauro.sandbox.model.DSingJointAB;
import com.minotauro.sandbox.model.DSingJointAC;
import com.minotauro.sandbox.model._PropDInnerA;
import com.minotauro.sandbox.model._PropDMainSms;
import com.minotauro.sandbox.model._PropDMultJointAB;
import com.minotauro.sandbox.model._PropDMultJointAC;
import com.minotauro.sandbox.model._PropDSingJointAB;
import com.minotauro.sandbox.model._PropDSingJointAC;

/**
 * @author Karla Moreno
 * 
 */
public class FrmDMainSmsEdit extends FrmEditWrk {

  protected FieldModel fmName;
  protected FieldModel fmDesc;
  protected FieldModel fmTipo;

 
  // --------------------------------------------------------------------------------

  public FrmDMainSmsEdit() {
    super("ID", "sms-net", "sms-doc");
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    String name = ((DMainSms) data).getName();
    setTitle(_I18NFrmDMainSmsEdit.title(name == null ? "-" : name));

    setW(new Extent(450));
    setH(new Extent(500));

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDMainSms.NAME);
    fmName.setProperty(_PropDMainSms.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDMainSmsEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDMainSms.DESC);
    fmDesc.setProperty(_PropDMainSms.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDMainSmsEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);

    // --------------------------------------------------------------------------------
    
    
    ETextAreaEx txtTipo = new ETextAreaEx();
    txtTipo.setWidth(new Extent(204));

    fmTipo = new FieldModel();
    fmTipo.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.tipo()));
    fmTipo.setFieldCmp(txtTipo);
    fmTipo.setKey(_PropDMainSms.TIPO);
    fmTipo.setProperty(_PropDMainSms.TIPO);

    fmTipo.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmDMainSmsEdit.tipo(), txtTipo));

    sectionModel.addChild(fmTipo);

    // --------------------------------------------------------------------------------
    
    }

  // --------------------------------------------------------------------------------
/*
  protected FieldModel initMultJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        DMultJointAB.class,
        _PropDMainSms.MULT_JOINT_ABLIST,
        _PropDMultJointAB.MAIN_AREF,
        _PropDMultJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmDMultJointABList.class, jointModel, this);

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainAEdit.multJointAB()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropDMainA.MULT_JOINT_ABLIST);

    return fmJoint;
  }*/

  // --------------------------------------------------------------------------------
/*
  protected FieldModel initMultJointAC() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        DMultJointAC.class,
        _PropDMainA.MULT_JOINT_ACLIST,
        _PropDMultJointAC.MAIN_AREF,
        _PropDMultJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmDMultJointACList.class, jointModel, this);

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainAEdit.multJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropDMainA.MULT_JOINT_ACLIST);

    return fmJoint;
  }
*/
  // --------------------------------------------------------------------------------
/*
  protected FieldModel initSingJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        DSingJointAB.class,
        _PropDMainA.SING_JOINT_ABLIST,
        _PropDSingJointAB.MAIN_AREF,
        _PropDSingJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmDSingJointABList.class, jointModel, this) {

      protected String getInfoText() {
        List<DSingJointAB> singJointABList =
            ((DMainA) data).getSingJointABList();

        return !singJointABList.isEmpty()
            ? singJointABList.get(0).getCrudBRef().getName()
            : null;
      }
    };

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainAEdit.singJointAB()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropDMainA.MULT_JOINT_ABLIST);

    return fmJoint;
  }
  */

  // --------------------------------------------------------------------------------
/*
  protected FieldModel initSingJointAC() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        data,
        DSingJointAC.class,
        _PropDMainA.SING_JOINT_ACLIST,
        _PropDSingJointAC.MAIN_AREF,
        _PropDSingJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmDSingJointACList.class, jointModel, this) {

      protected String getInfoText() {
        List<DSingJointAC> singJointACList =
            ((DMainA) data).getSingJointACList();

        return !singJointACList.isEmpty()
            ? singJointACList.get(0).getCrudCRef().getName() +
                " / " + singJointACList.get(0).getName()
            : null;
      }
    };

    jntJoint.setEditMode(editMode);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainAEdit.singJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropDMainA.MULT_JOINT_ACLIST);

    return fmJoint;
  }
*/
  // --------------------------------------------------------------------------------
/*
  protected FieldModel initInnerAC() {

    EInnerButton innInner = new EInnerButton(
        FrmDInnerAList.class,
        data,
        _PropDMainA.INNER_ACLIST,
        _PropDInnerA.MAIN_AREF,
        this);

    innInner.setEditMode(editMode);

    FieldModel fmInner = new FieldModel();
    fmInner.setLabelCmp(new EFieldLabel(_I18NFrmDMainAEdit.innerAC()));
    fmInner.setFieldCmp(innInner);
    fmInner.setKey(_PropDMainA.INNER_ACLIST);

    return fmInner;
  }
*/
  // --------------------------------------------------------------------------------

  @Override
  protected DMainSms initMDocument() {
    return new DMainSms();
  }
}
