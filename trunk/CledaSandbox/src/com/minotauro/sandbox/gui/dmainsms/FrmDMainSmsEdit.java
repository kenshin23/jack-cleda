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
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.dinnerac.FrmDInnerAList;
import com.minotauro.sandbox.gui.dmultjointab.FrmDMultJointABList;
import com.minotauro.sandbox.gui.dmultjointac.FrmDMultJointACList;
import com.minotauro.sandbox.gui.dsingjointab.FrmDSingJointABList;
import com.minotauro.sandbox.gui.dsingjointac.FrmDSingJointACList;
import com.minotauro.sandbox.gui.mcruda._I18NFrmMCrudAEdit;
import com.minotauro.sandbox.gui.mcrudpost._I18NFrmMCrudPostEdit;
import com.minotauro.sandbox.gui.minnerpostc.FrmMInnerPostList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.FrmMSingJointPostBList;
import com.minotauro.sandbox.model.DMainSms;
import com.minotauro.sandbox.model.DMultJointAB;
import com.minotauro.sandbox.model.DMultJointAC;
import com.minotauro.sandbox.model.DMultJointSmsB;
import com.minotauro.sandbox.model.DSingJointAB;
import com.minotauro.sandbox.model.DSingJointAC;
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model.MSingJointPostB;
import com.minotauro.sandbox.model._PropDInnerA;
import com.minotauro.sandbox.model._PropDMainSms;
import com.minotauro.sandbox.model._PropDMultJointAB;
import com.minotauro.sandbox.model._PropDMultJointAC;
import com.minotauro.sandbox.model._PropDMultJointSmsB;
import com.minotauro.sandbox.model._PropDSingJointAB;
import com.minotauro.sandbox.model._PropDSingJointAC;
import com.minotauro.sandbox.model._PropDSingJointSmsB;
import com.minotauro.sandbox.model._PropMCrudPost;
import com.minotauro.sandbox.model._PropMInnerPost;
import com.minotauro.sandbox.model._PropMMultJointMPostA;
import com.minotauro.sandbox.model._PropMSingJointPostB;

/**
 * @author Karla Moreno
 * 
 */
public class FrmDMainSmsEdit extends FrmEditWrk {

	protected FieldModel fmName;
	protected FieldModel fmDesc;
	protected FieldModel fmTipo;

	protected FieldModel fmDMultJointSmsA;
	protected FieldModel fmDSingJointSmsB;
	protected FieldModel fmMInnerSmsC;

	// --------------------------------------------------------------------------------

	public FrmDMainSmsEdit() {
		super("ID", "sms-net", "enterdoc");
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

		fmName.getValidatorList().add( //
				new NotEmptyValidator(_I18NFrmDMainSmsEdit.name(), txtName));

		sectionModel.addChild(fmName);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtDesc = new ETextAreaEx();
		txtDesc.setWidth(new Extent(204));

		fmDesc = new FieldModel();
		fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.desc()));
		fmDesc.setFieldCmp(txtDesc);
		fmDesc.setKey(_PropDMainSms.DESC);
		fmDesc.setProperty(_PropDMainSms.DESC);

		fmDesc.getValidatorList().add( //
				new NotEmptyValidator(_I18NFrmDMainSmsEdit.desc(), txtDesc));

		sectionModel.addChild(fmDesc);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtTipo = new ETextAreaEx();
		txtTipo.setWidth(new Extent(204));

		fmTipo = new FieldModel();
		fmTipo.setLabelCmp(new EFieldLabel(_I18NFrmDMainSmsEdit.tipo()));
		fmTipo.setFieldCmp(txtTipo);
		fmTipo.setKey(_PropDMainSms.TIPO);
		fmTipo.setProperty(_PropDMainSms.TIPO);

		fmTipo.getValidatorList().add( //
				new NotEmptyValidator(_I18NFrmDMainSmsEdit.tipo(), txtTipo));

		sectionModel.addChild(fmTipo);

		// --------------------------------------------------------------------------------
		sectionModel.addChild(fmDMultJointSmsA = initMultjointSmsA());
		sectionModel.addChild(fmDSingJointSmsB = initSingJointSmsB());
		sectionModel.addChild(fmMInnerSmsC = initInnerPostC());

	}

	@Override
	protected DMainSms initMDocument() {
		return new DMainSms();
	}

	protected FieldModel initMultjointSmsA() {

		EJointModel jointModel = new EJointModel();

		jointModel.init(data, MMultJointMPostA.class,
				_PropMCrudPost.MULT_JOINT_MPOST_ALIST,
				_PropDMultJointSmsB.CRUD_BREF, _PropDMultJointAC.CRUD_CREF);

		EJointButton jntJoint = new EJointButton(FrmDMultJointACList.class,
				jointModel, this);

		jntJoint.setEditMode(editMode);

		FieldModel fmJoint = new FieldModel();
		fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit
				.multJointPostA())); // TODO: editar properties
		fmJoint.setFieldCmp(jntJoint);
		fmJoint.setKey(_PropMCrudPost.MULT_JOINT_MPOST_ALIST);

		return fmJoint;
	}

	protected FieldModel initSingJointSmsB() {

		EJointModel jointModel = new EJointModel();

		jointModel.init(data, MSingJointPostB.class,
				_PropMCrudPost.SING_JOINT_POST_BLIST,
				_PropDSingJointSmsB.CRUD_BREF, _PropDSingJointAC.CRUD_CREF);

		EJointButton jntJoint = new EJointButton(FrmMSingJointPostBList.class,
				jointModel, this) {

			protected String getInfoText() {
				List<MSingJointPostB> aux = ((MCrudPost) data)
						.getSingJointPostBList();

				return !aux.isEmpty() ? aux.get(0).getCrudBRef().getName()
						+ " / " + aux.get(0).getName() // getname por defecto
														// por ahora
				: null;
			}
		};

		jntJoint.setEditMode(editMode);

		FieldModel fmJoint = new FieldModel();
		fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit
				.singJointPostB())); // TODO: editar properties
		fmJoint.setFieldCmp(jntJoint);
		fmJoint.setKey(_PropMCrudPost.SING_JOINT_POST_BLIST);

		return fmJoint;
	}

	protected FieldModel initInnerPostC() {

		EInnerButton innInner = new EInnerButton(FrmMInnerPostList.class, data,
				_PropMCrudPost.INNER_POST_CLIST, _PropMInnerPost.CRUD_POST_REF,
				this);

		innInner.setEditMode(editMode);

		FieldModel fmInner = new FieldModel();
		fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudAEdit.innerAC()));
		fmInner.setFieldCmp(innInner);
		fmInner.setKey(_PropMCrudPost.INNER_POST_CLIST);

		return fmInner;
	}
}
