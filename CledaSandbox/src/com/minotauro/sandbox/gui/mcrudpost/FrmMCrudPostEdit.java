

package com.minotauro.sandbox.gui.mcrudpost;

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
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;

import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.minnerpostc.FrmMInnerPostList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.FrmMSingJointPostBList;
import com.minotauro.sandbox.model.*;

//TODO:plantilla agregar dinamicamente imports.

import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMMultJointMPostA;
import com.minotauro.sandbox.model.MCrudPost;



public class FrmMCrudPostEdit extends FrmEditBase {

  protected FieldModel fmName;
  protected FieldModel fmDesc;
  
  protected FieldModel fmMMultJointMPostA;
  protected FieldModel fmMSingJointPostB;
  protected FieldModel fmMInnerPost;

  // --------------------------------------------------------------------------------

  public FrmMCrudPostEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrudPost) data).getName(); 
    setTitle(_I18NFrmMCrudPostEdit.title(name == null ? "-" : name)); 

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------


	TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudPost.NAME);
    fmName.setProperty(_PropMCrudPost.NAME);
 	fmName.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtName))
	;

	sectionModel.addChild(fmName);
	

	ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudPost.DESC);
    fmDesc.setProperty(_PropMCrudPost.DESC);
 	fmDesc.getValidatorList()
			.add(ConditionalValidator		
				.CHAIN(true)
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
			.add(ConditionalValidator		
				.CHAIN(true)
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
   		.add(new IntegerValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
				)
				)
	;

	sectionModel.addChild(fmDesc);
	

	sectionModel.addChild(fmMMultJointMPostA = initjointAB());
	sectionModel.addChild(fmMSingJointPostB = initjointPostB());
	sectionModel.addChild(fmMInnerPost = initinnerPostC());
	
	}
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
  
  
  
  
  protected FieldModel initjointPostB() {

	    EJointModel jointModel = new EJointModel();
		
		
	   	jointModel.init(
	        data,
	        MSingJointPostB.class,
	        _PropMCrudPost.SING_JOINT_POST_BLIST,
	        _PropMSingJointPostB.CRUD_POST_REF,
	        _PropMSingJointPostB.CRUD_BREF);
	        
	        
	    EJointButton jntJoint = new EJointButton(
        		FrmMSingJointPostBList.class, jointModel, this) {

      	protected String getInfoText() {
        List<MSingJointPostB> aux =
            ((MCrudPost) data).getSingJointPostBList();

        return !aux.isEmpty()
            ? aux.get(0).getCrudBRef().getName() +
                " / " + aux.get(0).getName() //getname por defecto por ahora
            : null;
      		}
    	};

	    
	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();		
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.jointPostB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.SING_JOINT_POST_BLIST);

	    return fmJoint;
	  }
  
  
  
  
  
    protected FieldModel initinnerPostC() {

	   EInnerButton innInner = new EInnerButton(
	   	  FrmMInnerPostList.class,
	      data,
	      _PropMCrudPost.INNER_POST_CLIST,
	      _PropMInnerPost.CRUD_POST_REF,
	      this);

	   innInner.setEditMode(editMode);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.innerPostC()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_PropMCrudPost.INNER_POST_CLIST);

	   return fmInner;
	 }
  
  
  
}
