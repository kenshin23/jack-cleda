
package com.minotauro.sandbox.gui.mcrudpost;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.*;

//TODO:plantilla agregar dinamicamente imports.

import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMMultJointMPostA;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model._PropMSingJointAC;
import com.minotauro.sandbox.model._PropMSingJointPostB;



public class FrmMCrudPostEdit extends FrmEditBase {


  protected FieldModel fmname;
  protected FieldModel fmdesc;
  protected FieldModel fmbody;
  
  protected FieldModel fmMMultJointMPostA;
  protected FieldModel fmMSingJointMPostB;
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


	TextField txtname = new TextField();
    txtname.setWidth(new Extent(204));

    fmname = new FieldModel();
    fmname.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmname.setFieldCmp(txtname);
    fmname.setKey(_PropMCrudPost.NAME);
    fmname.setProperty(_PropMCrudPost.NAME);
   	fmname.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtname));
    sectionModel.addChild(fmname);


	ETextAreaEx txtdesc = new ETextAreaEx();
    txtdesc.setWidth(new Extent(204));

    fmdesc = new FieldModel();
    fmdesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.desc()));
    fmdesc.setFieldCmp(txtdesc);
    fmdesc.setKey(_PropMCrudPost.DESC);
    fmdesc.setProperty(_PropMCrudPost.DESC);
   	fmdesc.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtdesc));
    sectionModel.addChild(fmdesc);


	ETextAreaEx txtbody = new ETextAreaEx();
    txtbody.setWidth(new Extent(204));

    fmbody = new FieldModel();
    fmbody.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.body()));
    fmbody.setFieldCmp(txtbody);
    fmbody.setKey(_PropMCrudPost.BODY);
    fmbody.setProperty(_PropMCrudPost.BODY);
   	fmbody.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.body(), txtbody));
    sectionModel.addChild(fmbody);


	sectionModel.addChild(fmMMultJointMPostA = initMMultJointMPostA());
	sectionModel.addChild(fmMSingJointMPostB = initSingJointPostB());
 }
  
  protected FieldModel initMMultJointMPostA() {

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
  protected FieldModel initSingJointPostB() {

	    EJointModel jointModel = new EJointModel();

	    jointModel.init(
	        data,
	        MSingJointPostB.class,
	        _PropMCrudPost.SING_JOINT_MPOST_BLIST,
	        _PropMSingJointPostB.CRUD_POST_REF,
	        _PropMSingJointPostB.CRUD_BREF);

	    EJointButton jntJoint = new EJointButton(
	            FrmMSingJointPostBList.class, jointModel, this) {

	          protected String getInfoText() {
	            List<MSingJointPostB> singJointPostBList =
	                ((MCrudPost) data).getSingJointPostBList();

	            return !singJointPostBList.isEmpty()
	                ? singJointPostBList.get(0).getCrudBRef().getName() +
	                    " / " + singJointPostBList.get(0).getName()
	                : null;
	          }
	        };

	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.jointPostB()));
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.SING_JOINT_MPOST_BLIST);

	    return fmJoint;
	  }
  
}
