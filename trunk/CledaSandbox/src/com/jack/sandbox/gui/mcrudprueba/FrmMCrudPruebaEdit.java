package com.jack.sandbox.gui.mcrudprueba;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.EmailValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;
import com.minotauro.echo.validator.impl.NotBlankValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.MCrudPrueba;
import com.minotauro.sandbox.model._PropMCrudPrueba;

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

  // --------------------------------------------------------------------------------

  public FrmMCrudPruebaEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

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

       
    fmNumero.getValidatorList().add( //
            new IntegerValidator(_I18NFrmMCrudPruebaEdit.numero(), txtNumero));

    sectionModel.addChild(fmNumero);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtEmail = new ETextAreaEx();
    txtEmail.setWidth(new Extent(204));

    fmEmail = new FieldModel();
    fmEmail.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.email()));
    fmEmail.setFieldCmp(txtEmail);
    fmEmail.setKey(_PropMCrudPrueba.EMAIL);
    fmEmail.setProperty(_PropMCrudPrueba.EMAIL);

    fmEmail.getValidatorList().add( //
            new EmailValidator(_I18NFrmMCrudPruebaEdit.email(), txtEmail));

    sectionModel.addChild(fmEmail);

    // --------------------------------------------------------------------------------

    ETextAreaEx txtNotblankfield = new ETextAreaEx();
    txtNotblankfield.setWidth(new Extent(204));

    fmNotblankfield = new FieldModel();
    fmNotblankfield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.notblankfield()));
    fmNotblankfield.setFieldCmp(txtNotblankfield);
    fmNotblankfield.setKey(_PropMCrudPrueba.NOTBLANKFIELD);
    fmNotblankfield.setProperty(_PropMCrudPrueba.NOTBLANKFIELD);

    fmNotblankfield.getValidatorList().add( //
            new NotBlankValidator(_I18NFrmMCrudPruebaEdit.notblankfield(), txtNotblankfield, "prueba"));
    
    sectionModel.addChild(fmNotblankfield);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtDuplicatedfield = new ETextAreaEx();
    txtDuplicatedfield.setWidth(new Extent(204));

    fmDuplicatedfield = new FieldModel();
    fmDuplicatedfield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.duplicatedfield()));
    fmDuplicatedfield.setFieldCmp(txtDuplicatedfield);
    fmDuplicatedfield.setKey(_PropMCrudPrueba.DUPLICATEDFIELD);
    fmDuplicatedfield.setProperty(_PropMCrudPrueba.DUPLICATEDFIELD);

    
     
    sectionModel.addChild(fmDuplicatedfield);
    
    // --------------------------------------------------------------------------------

    ETextAreaEx txtIdnumber = new ETextAreaEx();
    txtIdnumber.setWidth(new Extent(204));

    fmIdnumber = new FieldModel();
    fmIdnumber.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.idnumber()));
    fmIdnumber.setFieldCmp(txtIdnumber);
    fmIdnumber.setKey(_PropMCrudPrueba.IDNUMBER);
    fmIdnumber.setProperty(_PropMCrudPrueba.IDNUMBER);

    
     
    sectionModel.addChild(fmIdnumber);
    
      
    // --------------------------------------------------------------------------------

    ETextAreaEx txtConditional = new ETextAreaEx();
    txtConditional.setWidth(new Extent(204));

    fmConditional = new FieldModel();
    fmConditional.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.conditional()));
    fmConditional.setFieldCmp(txtConditional);
    fmConditional.setKey(_PropMCrudPrueba.CONDITIONAL);
    fmConditional.setProperty(_PropMCrudPrueba.CONDITIONAL);

      
    sectionModel.addChild(fmConditional);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtRegex = new ETextAreaEx();
    txtRegex.setWidth(new Extent(204));

    fmRegex = new FieldModel();
    fmRegex.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.regex()));
    fmRegex.setFieldCmp(txtRegex);
    fmRegex.setKey(_PropMCrudPrueba.REGEX);
    fmRegex.setProperty(_PropMCrudPrueba.REGEX);


     
    sectionModel.addChild(fmRegex);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtTruefield = new ETextAreaEx();
    txtTruefield.setWidth(new Extent(204));

    fmTruefield = new FieldModel();
    fmTruefield.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.truefield()));
    fmTruefield.setFieldCmp(txtTruefield);
    fmTruefield.setKey(_PropMCrudPrueba.TRUEFIELD);
    fmTruefield.setProperty(_PropMCrudPrueba.TRUEFIELD);

   
     
    sectionModel.addChild(fmTruefield);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtNotempty = new ETextAreaEx();
    txtNotempty.setWidth(new Extent(204));

    fmNotempty = new FieldModel();
    fmNotempty.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.notempty()));
    fmNotempty.setFieldCmp(txtNotempty);
    fmNotempty.setKey(_PropMCrudPrueba.NOTEMPTY);
    fmNotempty.setProperty(_PropMCrudPrueba.NOTEMPTY);

    fmNotempty.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.notempty(), txtNotempty));

     
    sectionModel.addChild(fmNotempty);
    
    // --------------------------------------------------------------------------------
    ETextAreaEx txtRango = new ETextAreaEx();
    txtRango.setWidth(new Extent(204));

    fmRango = new FieldModel();
    fmRango.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.rango()));
    fmRango.setFieldCmp(txtRango);
    fmRango.setKey(_PropMCrudPrueba.RANGO);
    fmRango.setProperty(_PropMCrudPrueba.RANGO);

   
     
    sectionModel.addChild(fmRango);
    
    // --------------------------------------------------------------------------------
    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmDuplicatedfield);
    formModel.getValidatorList().add(duplicatedValidator);
  }
}
