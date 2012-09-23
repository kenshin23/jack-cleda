/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.wizmcrudpost;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.mcrudpost._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.model.*;

import java.util.List;
import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.minnerpostc.FrmMInnerPostList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.FrmMSingJointPostBList;
import com.minotauro.sandbox.model.*;
/**
 * @author Demián Gutierrez
 */
public class PnlWizardMCrudPost3 extends PnlWizardBase {

	  protected FieldModel fmMMultJointMPostA;


  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost3(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
	    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

		sectionModel.addChild(fmMMultJointMPostA = initjointAB());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initjointAB() {

	    EJointModel jointModel = new EJointModel();

		
	    jointModel.init(
	        data,
	        MMultJointMPostA.class,
	        _PropMCrudPost.MULT_JOINT_MPOST_ALIST,
	        _PropMMultJointMPostA.CRUD_POST_REF,
	        _PropMMultJointMPostA.CRUD_AREF);

	    EJointButton jntJoint = new EJointButton(
	    		FrmMMultJointMPostAList.class, jointModel, this);

	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.jointAB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

	    return fmJoint;
	  }

  // --------------------------------------------------------------------------------

  
}
