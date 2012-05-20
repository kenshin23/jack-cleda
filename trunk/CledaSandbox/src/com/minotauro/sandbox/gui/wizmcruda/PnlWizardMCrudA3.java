/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcruda;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.mmultjointab.FrmMMultJointABList;
import com.minotauro.sandbox.gui.mmultjointac.FrmMMultJointACList;
import com.minotauro.sandbox.model.MMultJointAB;
import com.minotauro.sandbox.model.MMultJointAC;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMMultJointAB;
import com.minotauro.sandbox.model._PropMMultJointAC;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudA3 extends PnlWizardBase {

  protected FieldModel fmMultJointAB;
  protected FieldModel fmMultJointAC;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudA3(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmMultJointAB = initMultJointAB());
    sectionModel.addChild(fmMultJointAC = initMultJointAC());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initMultJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MMultJointAB.class,
        _PropMCrudA.MULT_JOINT_ABLIST,
        _PropMMultJointAB.CRUD_AREF,
        _PropMMultJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMMultJointABList.class, jointModel, parent);

    jntJoint.setEditMode(EnumEditMode.UPDATE);

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
        (MBase) data,
        MMultJointAC.class,
        _PropMCrudA.MULT_JOINT_ACLIST,
        _PropMMultJointAC.CRUD_AREF,
        _PropMMultJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmMMultJointACList.class, jointModel, parent);

    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.multJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ACLIST);

    return fmJoint;
  }
}
