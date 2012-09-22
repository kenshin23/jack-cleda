
package com.minotauro.sandbox.gui.mcrudpost;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.minnerpostc.FrmMInnerPostList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.FrmMSingJointPostBList;

//TODO:plantilla agregar dinamicamente imports.

import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMInnerPost;
import com.minotauro.sandbox.model._PropMMultJointMPostA;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model._PropMSingJointPostB;

//TODO:plantilla agregar dinamicamente imports.

//import com.minotauro.sandbox.model.MMultJointMPostA;
//import com.minotauro.sandbox.model._PropMCrudPost;
//import com.minotauro.sandbox.model._PropMMultJointMPostA;
//import com.minotauro.sandbox.model.MCrudPost;



public class FrmMCrudPostEdit extends FrmEditBase {


  protected FieldModel fmname;
  protected FieldModel fmdesc;
  protected FieldModel fmbody;
  
  protected FieldModel fmMMultJointMPostA;
  protected FieldModel fmMSingJointPostB;
  protected FieldModel fmMInnerPostC;
  // --------------------------------------------------------------------------------

  public FrmMCrudPostEdit() {
    // Empty
  }

  // ---------------------------------------------------------------------------------

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


	sectionModel.addChild(fmMMultJointMPostA = initjointAB());
	sectionModel.addChild(fmMSingJointPostB = initjointPostB());
	sectionModel.addChild(fmMInnerPostC/* */= initInnerPostC());
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
  
  protected FieldModel initInnerPostC() {

	   EInnerButton innInner = new EInnerButton(
	       FrmMInnerPostList.class,
	       data,
	       _PropMCrudPost.INNER_POST_CLIST,
	       _PropMInnerPost.CRUD_POST_REF,
	       this);

	   innInner.setEditMode(editMode);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.innerAC()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_PropMCrudPost.INNER_POST_CLIST);

	   return fmInner;
	 }
  
}
