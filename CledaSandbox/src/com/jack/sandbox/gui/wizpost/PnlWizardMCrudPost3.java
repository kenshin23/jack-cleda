

package com.jack.sandbox.gui.wizpost;

import java.util.List;
import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;

import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.minnerpostc.*;
import com.minotauro.sandbox.gui.mcrudpost.*;
import com.minotauro.sandbox.gui.mmultjointmposta.*;
import com.minotauro.sandbox.gui.msingjointpostb.*;
import com.minotauro.sandbox.model.*;

//TODO:plantilla agregar dinamicamente imports.





public class PnlWizardMCrudPost3 extends PnlWizardBase {

  
  protected FieldModel fmMMultJointMPostA;
  protected FieldModel fmMSingJointPostB;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost3(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------


	sectionModel.addChild(fmMMultJointMPostA = initmultJointPostA());
	sectionModel.addChild(fmMSingJointPostB = initsingJointPostB());
	
	}
  protected FieldModel initmultJointPostA() {

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
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.multJointPostA())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

	    return fmJoint;
	  }
  
  
  
  
  protected FieldModel initsingJointPostB() {

	    EJointModel jointModel = new EJointModel();
		
		
	   	jointModel.init(
	        (MBase) data,
	        MSingJointPostB.class,
	        _PropMCrudPost.SING_JOINT_POST_BLIST,
	        _PropMSingJointPostB.CRUD_POST_REF,
	        _PropMSingJointPostB.CRUD_BREF);
	        
	        
	    EJointButton jntJoint = new EJointButton(
        		FrmMSingJointPostBList.class, jointModel, parent) {

      	protected String getInfoText() {
        List<MSingJointPostB> aux =
            ((MCrudPost) data).getSingJointPostBList();

        return !aux.isEmpty()
            ? aux.get(0).getCrudBRef().getName() +
                " / " + aux.get(0).getName() //getname por defecto por ahora
            : null;
      		}
    	};

	    
	    jntJoint.setEditMode(EnumEditMode.UPDATE);

	    FieldModel fmJoint = new FieldModel();		
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.singJointPostB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.SING_JOINT_POST_BLIST);

	    return fmJoint;
	  }
  
  
}
