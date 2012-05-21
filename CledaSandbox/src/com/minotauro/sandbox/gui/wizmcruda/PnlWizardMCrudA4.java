/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcruda;

import java.util.List;

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
import com.minotauro.sandbox.gui.msingjointab.FrmMSingJointABList;
import com.minotauro.sandbox.gui.msingjointac.FrmMSingJointACList;
import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MSingJointAB;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMSingJointAB;
import com.minotauro.sandbox.model._PropMSingJointAC;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudA4 extends PnlWizardBase {

  protected FieldModel fmSingJointAB;
  protected FieldModel fmSingJointAC;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudA4(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmSingJointAB = initSingJointAB());
    sectionModel.addChild(fmSingJointAC = initSingJointAC());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointAB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MSingJointAB.class,
        _PropMCrudA.SING_JOINT_ABLIST,
        _PropMSingJointAB.CRUD_AREF,
        _PropMSingJointAB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointABList.class, jointModel, parent) {

      protected String getInfoText() {
        List<MSingJointAB> singJointABList =
            ((MCrudA) data).getSingJointABList();

        return !singJointABList.isEmpty()
            ? singJointABList.get(0).getCrudBRef().getName()
            : null;
      }
    };

    jntJoint.setEditMode(EnumEditMode.UPDATE);

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
        (MBase) data,
        MSingJointAC.class,
        _PropMCrudA.SING_JOINT_ACLIST,
        _PropMSingJointAC.CRUD_AREF,
        _PropMSingJointAC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointACList.class, jointModel, parent) {

      protected String getInfoText() {
        List<MSingJointAC> singJointACList =
            ((MCrudA) data).getSingJointACList();

        return !singJointACList.isEmpty()
            ? singJointACList.get(0).getCrudCRef().getName() +
                " / " + singJointACList.get(0).getName()
            : null;
      }
    };

    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.singJointAC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudA.MULT_JOINT_ACLIST);

    return fmJoint;
  }
}
