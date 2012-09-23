/*
 * Created on 16/09/2011
 */
package com.jack.sandbox.gui.wizpost;

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
import com.minotauro.sandbox.gui.mcrudpost._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.msingjointab.FrmMSingJointABList;
import com.minotauro.sandbox.gui.msingjointac.FrmMSingJointACList;
import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model.MSingJointAB;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMSingJointAB;
import com.minotauro.sandbox.model._PropMSingJointAC;
import com.minotauro.sandbox.model._PropMSingJointPostB;

/**
 * @author e.g.:Jack
 */
public class PnlWizardMPost5 extends PnlWizardBase {

  protected FieldModel fmSingJointPostB;

  // --------------------------------------------------------------------------------

  public PnlWizardMPost5(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmSingJointPostB = initSingJointPostB());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initSingJointPostB() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MSingJointPostB.class,
        _PropMCrudPost.SING_JOINT_POST_BLIST,
        _PropMSingJointPostB.CRUD_POST_REF,
        _PropMSingJointPostB.CRUD_BREF);

    EJointButton jntJoint = new EJointButton(
        FrmMSingJointABList.class, jointModel, parent) {

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
    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

  
}
