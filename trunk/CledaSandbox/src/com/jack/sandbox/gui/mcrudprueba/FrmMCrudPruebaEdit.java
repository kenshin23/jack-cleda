package com.jack.sandbox.gui.mcrudprueba;

import java.util.Date;
import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;
import nextapp.echo.app.CheckBox;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.PasswordField;
import nextapp.echo.app.TextArea;
import echopoint.jquery.DateField;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.login._I18NDlgLogin;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.EmailValidator;
import com.minotauro.echo.validator.impl.IdNumberValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;
import com.minotauro.echo.validator.impl.LowerThanValidator;
import com.minotauro.echo.validator.impl.GreaterThanValidator;
import com.minotauro.echo.validator.impl.NotBlankValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.echo.validator.impl.RegexValidator;
import com.minotauro.echo.validator.impl.TrueValidator;
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.sandbox.model.MCrudPrueba;
import com.minotauro.sandbox.model._PropMCrudPrueba;
import com.minotauro.user.model.MUser;

import com.minotauro.echo.wrapper.impl.CheckBoxWrapper;
import com.minotauro.echo.wrapper.impl.SelectFieldWrapper;

/**
 * @author E.G JACK™
 */
public class FrmMCrudPruebaEdit extends FrmEditBase {

	private FieldModel fmNumero;
	private FieldModel fmEmail;
	private FieldModel fmNotblankfield;
	private FieldModel fmDuplicatedfield;
	private FieldModel fmIdnumber;
	private FieldModel fmConditional;
	private FieldModel fmRegex;
	private FieldModel fmTruefield;
	private FieldModel fmNotempty;
	private FieldModel fmRango;
	private FieldModel fmSelect;
	private FieldModel fmPass;
	private FieldModel fmDate;

	// --------------------------------------------------------------------------------

	public FrmMCrudPruebaEdit() {
		// Empty
	}

	// --------------------------------------------------------------------------------

	/* (non-Javadoc)
	 * @see com.minotauro.echo.cleda.edit.FrmEditBase#initGUI()
	 */
	@Override
	protected void initGUI() {
		String numero = ((MCrudPrueba) data).getNumero();
		setTitle(_I18NFrmMCrudPruebaEdit.title(numero == null ? "-" : numero));

		// --------------------------------------------------------------------------------

		SectionModel sectionModel = formModel.initSingleSectionModel();

		// --------------------------------------------------------------------------------

		TextField txtNumero = new TextField();
		txtNumero.setWidth(new Extent(204));

		fmNumero = new FieldModel();
		fmNumero.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.numero()));
		fmNumero.setFieldCmp(txtNumero);
		fmNumero.setKey(_PropMCrudPrueba.NUMERO);
		fmNumero.setProperty(_PropMCrudPrueba.NUMERO);

		// ConditionalValidator VN1 = ConditionalValidator
		// .IF(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.numero(),
		// txtNumero));
		//
		// VN1.add(new IntegerValidator(_I18NFrmMCrudPruebaEdit.numero(),
		// txtNumero));

		// ConditionalValidator VN1 = ConditionalValidator.CHAIN(true);
		//
		// VN1.add(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.numero(),
		// txtNumero));
		// VN1.add(new IntegerValidator(_I18NFrmMCrudPruebaEdit.numero(),
		// txtNumero));
		
		fmNumero.getValidatorList().add(
				ConditionalValidator
						.CHAIN(true)
						.add(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit
								.numero(), txtNumero))
						.add(new IntegerValidator(_I18NFrmMCrudPruebaEdit
								.numero(), txtNumero)));

		// fmNumero.getValidatorList().add(VN1);

		// fmNumero.getValidatorList().add(
		// new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.numero(),
		// txtNumero));

		//fmNumero.getValidatorList().add(VN1);

		sectionModel.addChild(fmNumero);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtEmail = new ETextAreaEx();
		txtEmail.setWidth(new Extent(204));

		fmEmail = new FieldModel();
		fmEmail.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.email()));
		fmEmail.setFieldCmp(txtEmail);
		fmEmail.setKey(_PropMCrudPrueba.EMAIL);
		fmEmail.setProperty(_PropMCrudPrueba.EMAIL);

		ConditionalValidator EV1 = ConditionalValidator
				.IF(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.email(),
						txtEmail));

		EV1.add(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.email(), txtEmail));

		EV1.add(new EmailValidator(_I18NFrmMCrudPruebaEdit.email(), txtEmail));

		// fmNumero.getValidatorList()
		// .add(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.email(),
		// txtEmail));

		fmEmail.getValidatorList().add(EV1);

		sectionModel.addChild(fmEmail);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtNotblankfield = new ETextAreaEx();
		txtNotblankfield.setWidth(new Extent(204));

		fmNotblankfield = new FieldModel();
		fmNotblankfield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.notblankfield()));
		fmNotblankfield.setFieldCmp(txtNotblankfield);
		fmNotblankfield.setKey(_PropMCrudPrueba.NOTBLANKFIELD);
		fmNotblankfield.setProperty(_PropMCrudPrueba.NOTBLANKFIELD);

		fmNotblankfield.getValidatorList().add( //
				new NotBlankValidator(_I18NFrmMCrudPruebaEdit.notblankfield(),
						txtNotblankfield, "prueba"));

		sectionModel.addChild(fmNotblankfield);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtDuplicatedfield = new ETextAreaEx();
		txtDuplicatedfield.setWidth(new Extent(204));

		fmDuplicatedfield = new FieldModel();
		fmDuplicatedfield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.duplicatedfield()));
		fmDuplicatedfield.setFieldCmp(txtDuplicatedfield);
		fmDuplicatedfield.setKey(_PropMCrudPrueba.DUPLICATEDFIELD);
		fmDuplicatedfield.setProperty(_PropMCrudPrueba.DUPLICATEDFIELD);

		sectionModel.addChild(fmDuplicatedfield);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtIdnumber = new ETextAreaEx();
		txtIdnumber.setWidth(new Extent(204));

		fmIdnumber = new FieldModel();
		fmIdnumber.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.idnumber()));
		fmIdnumber.setFieldCmp(txtIdnumber);
		fmIdnumber.setKey(_PropMCrudPrueba.IDNUMBER);
		fmIdnumber.setProperty(_PropMCrudPrueba.IDNUMBER);

		ConditionalValidator ViN1 = ConditionalValidator
				.IF(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.idnumber(),
						txtIdnumber));

		ViN1.add(new IntegerValidator(_I18NFrmMCrudPruebaEdit.idnumber(),
				txtIdnumber));

		fmIdnumber.getValidatorList().add(
				new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.idnumber(),
						txtIdnumber));

		fmIdnumber.getValidatorList().add(ViN1);

		sectionModel.addChild(fmIdnumber);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtConditional = new ETextAreaEx();
		txtConditional.setWidth(new Extent(204));

		fmConditional = new FieldModel();
		fmConditional.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.conditional()));
		fmConditional.setFieldCmp(txtConditional);
		fmConditional.setKey(_PropMCrudPrueba.CONDITIONAL);
		fmConditional.setProperty(_PropMCrudPrueba.CONDITIONAL);

		sectionModel.addChild(fmConditional);
		
		// ------------------DATE--------------------------------------------------------------

		// DateField date = new DateField();
		// date.setWidth(new Extent(204));
		
		// fmDate = new FieldModel();
		// fmDate.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
		// .conditional()));
		// fmDate.setFieldCmp(date);
		// fmDate.setKey(_PropMCrudPrueba.CONDITIONAL);
		// fmDate.setProperty(_PropMCrudPrueba.CONDITIONAL);
		//
		// sectionModel.addChild(fmDate);

		// ------------------TEXTAREA--------------------------------------------------------------
 
		TextArea txtRegex = new TextArea();
		txtRegex.setWidth(new Extent(204));
		fmRegex = new FieldModel();
		fmRegex.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.regex()));
		fmRegex.setFieldCmp(txtRegex);
		fmRegex.setKey(_PropMCrudPrueba.REGEX);
		fmRegex.setProperty(_PropMCrudPrueba.REGEX);

		fmRegex.getValidatorList().add( //
				new RegexValidator(_I18NFrmMCrudPruebaEdit.regex(), txtRegex,
						"^[A-Z]*(\\s[A-Z]+)*$"));

		sectionModel.addChild(fmRegex);

		// ---------------------SELECT-----------------------------------------------------------

		SelectField selectField = new SelectField();
		selectField.setHeight(new Extent(25));
		selectField.setWidth(new Extent(100));
				
		DefaultListModel dlm = (DefaultListModel) selectField.getModel();

	    dlm.add("hola");
	    dlm.add("karla");
	    dlm.add("eduardo");
	    dlm.add("gerardo");
	    dlm.add("jesus");
	    dlm.add("armando");

	   

		fmSelect = new FieldModel();
		fmSelect.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.numero()));
		fmSelect.setFieldCmp(selectField);
		fmSelect.setKey(_PropMCrudPrueba.NUMERO);
		fmSelect.setProperty(_PropMCrudPrueba.NUMERO);

		fmSelect.getValidatorList().add( //
				new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.numero(),
						selectField));

		sectionModel.addChild(fmSelect);
		
		// ---------------------------PASSWORDFIELD-----------------------------------------------------

		PasswordField passField = new PasswordField();
		passField.setWidth(new Extent(100));

		fmPass = new FieldModel();
		fmPass.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.numero()));
		fmPass.setFieldCmp(passField);
		fmPass.setKey(_PropMCrudPrueba.NUMERO);
		fmPass.setProperty(_PropMCrudPrueba.NUMERO);

		sectionModel.addChild(fmPass);
		
		// ----------------------------CHECKBOX----------------------------------------------------

		CheckBox checkBoxtrue = new CheckBox();

		checkBoxtrue.setHeight(new Extent(50));

		fmTruefield = new FieldModel();
		fmTruefield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.truefield()));
		fmTruefield.setFieldCmp(checkBoxtrue);
		fmTruefield.setKey(_PropMCrudPrueba.TRUEFIELD);
		fmTruefield.setProperty(_PropMCrudPrueba.TRUEFIELD);

		fmIdnumber.getValidatorList().add( //
				new TrueValidator(_I18NFrmMCrudPruebaEdit.truefield(),
						checkBoxtrue));

		sectionModel.addChild(fmTruefield);

		// --------------------------------------------------------------------------------

		ETextAreaEx txtNotempty = new ETextAreaEx();
		txtNotempty.setWidth(new Extent(204));

		fmNotempty = new FieldModel();
		fmNotempty.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit
				.notempty()));
		fmNotempty.setFieldCmp(txtNotempty);
		fmNotempty.setKey(_PropMCrudPrueba.NOTEMPTY);
		fmNotempty.setProperty(_PropMCrudPrueba.NOTEMPTY);

		fmNotempty.getValidatorList().add( //
				new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.notempty(),
						txtNotempty));

		sectionModel.addChild(fmNotempty);

		// --------------------------------------------------------------------------------
		ETextAreaEx txtRango = new ETextAreaEx();
		txtRango.setWidth(new Extent(204));

		fmRango = new FieldModel();
		fmRango.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.rango()));
		fmRango.setFieldCmp(txtRango);
		fmRango.setKey(_PropMCrudPrueba.RANGO);
		fmRango.setProperty(_PropMCrudPrueba.RANGO);

		ConditionalValidator VR1 = ConditionalValidator
				.AND(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.rango(),
						txtRango),
						new IntegerValidator(_I18NFrmMCrudPruebaEdit.rango(),
								txtRango), false);

		VR1.add(new IntegerValidator(_I18NFrmMCrudPruebaEdit.rango(), txtRango));

		VR1.add(new GreaterThanValidator(_I18NFrmMCrudPruebaEdit.rango(),
				txtRango, 5));
		VR1.add(new GreaterThanValidator(_I18NFrmMCrudPruebaEdit.rango(),
				txtRango, 11));
		VR1.add(new LowerThanValidator(_I18NFrmMCrudPruebaEdit.rango(),
				txtRango, 20));

		// fmRango.getValidatorList()
		// .add(new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.rango(),
		// txtRango));
		//
		// fmRango.getValidatorList()
		// .add(new IntegerValidator(_I18NFrmMCrudPruebaEdit.rango(),
		// txtRango));

		fmRango.getValidatorList().add(VR1);

		sectionModel.addChild(fmRango);

		// --------------------------------------------------------------------------------
		DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
		duplicatedValidator.add(fmDuplicatedfield);
		formModel.getValidatorList().add(duplicatedValidator);
	}
}
