
package com.minotauro.sandbox.gui.dmainsms;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.*;
import com.minotauro.sandbox.gui.dinnersmsb.FrmDInnerSmsList;
import com.minotauro.sandbox.gui.dmultjointsmsb.FrmDMultJointSmsBList;
import com.minotauro.sandbox.gui.dsingjointsmsb.FrmDSingJointSmsBList;
import com.minotauro.sandbox.model.DMainSms;
import com.minotauro.sandbox.model.DMultJointSmsB;
import com.minotauro.sandbox.model.DSingJointSmsB;
import com.minotauro.sandbox.model._PropDInnerSms;
import com.minotauro.sandbox.model._PropDMainSms;
import com.minotauro.sandbox.model._PropDMultJointSmsB;
import com.minotauro.sandbox.model._PropDSingJointSmsB;

/**
 * @author E.G. Jack
 * 
 */
public class FrmDMainSmsEdit extends FrmEditWrk {

  protected FieldModel fmName;
  protected FieldModel fmDesc;
  protected FieldModel fmTipo;
  protected FieldModel fmDMultJointSmsB;
  protected FieldModel fmDSingJointSmsB;
  protected FieldModel fmDInnerSms;

	// --------------------------------------------------------------------------------

	public FrmDMainSmsEdit() {
		super("ID", "sms-net", "enterdoc");
		
		//TODO: buscar que representan estos parametros
	}

	// --------------------------------------------------------------------------------

	protected void initGUI() {
		String name = ((DMainSms) data).getName();
		setTitle(_I18NFrmDMainSmsEdit.title(name == null ? "-" : name));

		setW(new Extent(450));
		setH(new Extent(500));

		// --------------------------------------------------------------------------------

		SectionModel sectionModel = formModel.initSingleSectionModel();

		// --------------------------------------------------------------------------------


	TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropDMainSms.NAME);
    fmName.setProperty(_PropDMainSms.NAME);
 	fmName.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmDMainSmsEdit.name(), txtName))
	;

	sectionModel.addChild(fmName);
	

	TextField txtDesc = new TextField();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropDMainSms.DESC);
    fmDesc.setProperty(_PropDMainSms.DESC);
 	fmDesc.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmDMainSmsEdit.desc(), txtDesc))
	;

	sectionModel.addChild(fmDesc);
	

	TextField txtTipo = new TextField();
    txtTipo.setWidth(new Extent(204));

    fmTipo = new FieldModel();
    fmTipo.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.tipo()));
    fmTipo.setFieldCmp(txtTipo);
    fmTipo.setKey(_PropDMainSms.TIPO);
    fmTipo.setProperty(_PropDMainSms.TIPO);
 	fmTipo.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmDMainSmsEdit.tipo(), txtTipo))
	;

	sectionModel.addChild(fmTipo);
	

	sectionModel.addChild(fmDMultJointSmsB = initmultJointSmsB());
	sectionModel.addChild(fmDSingJointSmsB = initsingJointSmsB());
	sectionModel.addChild(fmDInnerSms = initinnerSmsC());
	
	}
  protected FieldModel initmultJointSmsB() {

	    EJointModel jointModel = new EJointModel();

		
	    jointModel.init(
	        data,
	        DMultJointSmsB.class,
	        _PropDMainSms.MULT_JOINT_SMS_BLIST,
	        _PropDMultJointSmsB.MAIN_SMS_REF,
	        _PropDMultJointSmsB.CRUD_BREF);

	    EJointButton jntJoint = new EJointButton(
	    		FrmDMultJointSmsBList.class, jointModel, this);

	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.multJointSmsB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropDMainSms.MULT_JOINT_SMS_BLIST);

	    return fmJoint;
	  }
  
  
  
  
  protected FieldModel initsingJointSmsB() {

	    EJointModel jointModel = new EJointModel();
		
		
	   	jointModel.init(
	        data,
	        DSingJointSmsB.class,
	        _PropDMainSms.SING_JOINT_SMS_BLIST,
	        _PropDSingJointSmsB.MAIN_SMS_REF,
	        _PropDSingJointSmsB.CRUD_BREF);
	        
	        
	    EJointButton jntJoint = new EJointButton(
        		FrmDSingJointSmsBList.class, jointModel, this) {

      	protected String getInfoText() {
        List<DSingJointSmsB> aux =
            ((DMainSms) data).getSingJointSmsBList();

        return !aux.isEmpty()
            ? aux.get(0).getCrudBRef().getName() +
                " / " + aux.get(0).getMainSmsRef().getName() //getname por defecto por ahora
            : null;
      		}
    	};

	    
	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();		
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.singJointSmsB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_PropDMainSms.SING_JOINT_SMS_BLIST);

	    return fmJoint;
	  }
  
  
  
  
  
    protected FieldModel initinnerSmsC() {

	   EInnerButton innInner = new EInnerButton(
	   	  FrmDInnerSmsList.class,
	      data,
	      _PropDMainSms.INNER_SMS_CLIST,
	      _PropDInnerSms.MAIN_SMS_REF,
	      this);

	   innInner.setEditMode(editMode);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.innerSmsC()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_PropDMainSms.INNER_SMS_CLIST);

	   return fmInner;
	 }
  
  
  

	// --------------------------------------------------------------------------------

	@Override
	protected DMainSms initMDocument() {
		return new DMainSms();
	}

}
