/*
 * Created on 16/09/2011
 */
package com.jack.sandbox.gui.wizpost;

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
import com.minotauro.sandbox.gui.mmultjointab.FrmMMultJointABList;
import com.minotauro.sandbox.gui.mmultjointac.FrmMMultJointACList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.model.MMultJointAB;
import com.minotauro.sandbox.model.MMultJointAC;
import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMMultJointMPostA;
import com.minotauro.sandbox.model._PropMSingJointPostB;
import com.minotauro.sandbox.model._PropMSingJointPostB;


/**
 * @author e.g.:Jack
 */
public class PnlWizardMPost4 extends PnlWizardBase {

  protected FieldModel fmMultJointPostA;
 

  // --------------------------------------------------------------------------------

  public PnlWizardMPost4(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmMultJointPostA = initMultJointPostA());
 
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initMultJointPostA() {

    EJointModel jointModel = new EJointModel();

    jointModel.init(
        (MBase) data,
        MMultJointMPostA.class,
        _PropMCrudPost.MULT_JOINT_MPOST_ALIST,
        _PropMMultJointMPostA.CRUD_POST_REF,
        _PropMMultJointMPostA.CRUD_AREF);
    
    EJointButton jntJoint = new EJointButton(
    		FrmMMultJointMPostAList.class, jointModel, parent);

    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.multJointPostA()));//aqui
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

    return fmJoint;
  }

  // --------------------------------------------------------------------------------

 
}
