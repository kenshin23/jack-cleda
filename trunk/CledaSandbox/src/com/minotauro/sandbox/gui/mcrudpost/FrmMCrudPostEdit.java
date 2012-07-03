
package com.minotauro.sandbox.gui.mcrudpost;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.mmultjointab.FrmMMultJointABList;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model.MMultJointAB;
import com.minotauro.sandbox.model._PropMCrudA;
import com.minotauro.sandbox.model._PropMMultJointAB;
import com.minotauro.sandbox.model._PropMMultJointMPostA;

import com.minotauro.sandbox.model._PropMCrudPost;


public class FrmMCrudPostEdit extends FrmEditBase {



  private FieldModel fmNname;


  private FieldModel fmNdesc;


  private FieldModel fmNbody;
  
  protected FieldModel fmMultJointMPostA; //1er cambio

  // --------------------------------------------------------------------------------

  public FrmMCrudPostEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrudPost) data).getName(); //*** pregunta getName
    setTitle(_I18NFrmMCrudPostEdit.title(name == null ? "-" : name)); // preguntar "name"

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------




	TextField txtname = new TextField();
    txtname.setWidth(new Extent(204));

    fmNname = new FieldModel();
    fmNname.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmNname.setFieldCmp(txtname);
    fmNname.setKey(_PropMCrudPost.NAME);
    fmNname.setProperty(_PropMCrudPost.NAME);

	
    fmNname.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtname)); //investigar especificaciones de validadores

	
    sectionModel.addChild(fmNname);
	


	ETextAreaEx txtdesc = new ETextAreaEx();
    txtdesc.setWidth(new Extent(204));

    fmNdesc = new FieldModel();
    fmNdesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmNdesc.setFieldCmp(txtdesc);
    fmNdesc.setKey(_PropMCrudPost.NAME);
    fmNdesc.setProperty(_PropMCrudPost.NAME);

	
    fmNdesc.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtdesc)); //investigar especificaciones de validadores

	
    sectionModel.addChild(fmNdesc);
	


	ETextAreaEx txtbody = new ETextAreaEx();
    txtbody.setWidth(new Extent(204));

    fmNbody = new FieldModel();
    fmNbody.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmNbody.setFieldCmp(txtbody);
    fmNbody.setKey(_PropMCrudPost.NAME);
    fmNbody.setProperty(_PropMCrudPost.NAME);

	
    fmNbody.getValidatorList().add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.body(), txtbody)); //investigar especificaciones de validadores

	
    sectionModel.addChild(fmNbody);
	
    //++++++++++++++++++++++

    sectionModel.addChild(fmMultJointMPostA = initMultJointMPostA());
    
    
    
    
  }
  
  protected FieldModel initMultJointMPostA() {

	    EJointModel jointModel = new EJointModel();

	    jointModel.init(
	        data,
	        MMultJointAB.class,
	        _PropMCrudPost.MULT_JOINT_MPOST_ALIST,
	        _PropMMultJointMPostA.CRUD_AREF,
	        _PropMMultJointMPostA.CRUD_POST_REF);

	    EJointButton jntJoint = new EJointButton(
	        FrmMMultJointABList.class, jointModel, this);

	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel("hola mundo"));
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

	    return fmJoint;
	  }
  
}
