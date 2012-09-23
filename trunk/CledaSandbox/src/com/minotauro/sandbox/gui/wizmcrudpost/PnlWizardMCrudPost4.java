/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcrudpost;

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
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.msingjointab.FrmMSingJointPostBList;
import com.minotauro.sandbox.gui.msingjointac.FrmMSingJointPostCList;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model.MSingJointPostC;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMSingJointPostB;
import com.minotauro.sandbox.model._PropMSingJointPostC;

/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudPost4 extends PnlWizardBase {

  protected FieldModel fmSingJointPostB;
  protected FieldModel fmSingJointPostC;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost4(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmSingJointPostB = initSingJointPostB());
    sectionModel.addChild(fmSingJointPostC = initSingJointPostC());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointPostB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MSingJointPostB.class,
        _PropMCrudPost.SING_JOINT_PostBLIST,
        _PropMSingJointPostB.CRUD_PostREF,
        _PropMSingJointPostB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointPostBList.class, jointModel, parent) {

      protected String getInfoText() {
        List<MSingJointPostB> singJointPostBList =
            ((MCrudPost) data).getSingJointPostBList();

        return !singJointPostBList.isEmpty()
            ? singJointPostBList.get(0).getCrudBRef().getName()
            : null;
      }
    };

    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.singJointPostB()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_PostBLIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointPostC() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MSingJointPostC.class,
        _PropMCrudPost.SING_JOINT_PostCLIST,
        _PropMSingJointPostC.CRUD_PostREF,
        _PropMSingJointPostC.CRUD_CREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointPostCList.class, jointModel, parent) {

      protected String getInfoText() {
        List<MSingJointPostC> singJointPostCList =
            ((MCrudPost) data).getSingJointPostCList();

        return !singJointPostCList.isEmpty()
            ? singJointPostCList.get(0).getCrudCRef().getName() +
                " / " + singJointPostCList.get(0).getName()
            : null;
      }
    };

    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.singJointPostC()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_PostCLIST);

    return fmJoint;
  }
}
