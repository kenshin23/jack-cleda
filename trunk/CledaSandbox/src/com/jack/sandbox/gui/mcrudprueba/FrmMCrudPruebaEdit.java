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
  private FieldModel fmCampo1;
  private FieldModel fmCampo2;
  private FieldModel fmCampo3;
  private FieldModel fmCampo4;
  private FieldModel fmCampo5;
  private FieldModel fmCampo6;
  private FieldModel fmCampo7;
  private FieldModel fmCampo8;

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

    ETextAreaEx txtCampo1 = new ETextAreaEx();
    txtCampo1.setWidth(new Extent(204));

    fmCampo1 = new FieldModel();
    fmCampo1.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo1()));
    fmCampo1.setFieldCmp(txtCampo1);
    fmCampo1.setKey(_PropMCrudPrueba.CAMPO1);
    fmCampo1.setProperty(_PropMCrudPrueba.CAMPO1);

    fmCampo1.getValidatorList().add( //
            new NotBlankValidator(_I18NFrmMCrudPruebaEdit.campo1(), txtCampo1, "prueba"));
    
    sectionModel.addChild(fmCampo1);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtCampo2 = new ETextAreaEx();
    txtCampo2.setWidth(new Extent(204));

    fmCampo2 = new FieldModel();
    fmCampo2.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo2()));
    fmCampo2.setFieldCmp(txtCampo2);
    fmCampo2.setKey(_PropMCrudPrueba.CAMPO2);
    fmCampo2.setProperty(_PropMCrudPrueba.CAMPO2);

    
     
    sectionModel.addChild(fmCampo2);
    
    // --------------------------------------------------------------------------------

    ETextAreaEx txtCampo3 = new ETextAreaEx();
    txtCampo3.setWidth(new Extent(204));

    fmCampo3 = new FieldModel();
    fmCampo3.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo3()));
    fmCampo3.setFieldCmp(txtCampo3);
    fmCampo3.setKey(_PropMCrudPrueba.CAMPO3);
    fmCampo3.setProperty(_PropMCrudPrueba.CAMPO3);

    
     
    sectionModel.addChild(fmCampo3);
    
      
    // --------------------------------------------------------------------------------

    ETextAreaEx txtCampo4 = new ETextAreaEx();
    txtCampo4.setWidth(new Extent(204));

    fmCampo4 = new FieldModel();
    fmCampo4.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo4()));
    fmCampo4.setFieldCmp(txtCampo4);
    fmCampo4.setKey(_PropMCrudPrueba.CAMPO4);
    fmCampo4.setProperty(_PropMCrudPrueba.CAMPO4);

      
    sectionModel.addChild(fmCampo4);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtCampo5 = new ETextAreaEx();
    txtCampo5.setWidth(new Extent(204));

    fmCampo5 = new FieldModel();
    fmCampo5.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo5()));
    fmCampo5.setFieldCmp(txtCampo5);
    fmCampo5.setKey(_PropMCrudPrueba.CAMPO5);
    fmCampo5.setProperty(_PropMCrudPrueba.CAMPO5);


     
    sectionModel.addChild(fmCampo5);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtCampo6 = new ETextAreaEx();
    txtCampo6.setWidth(new Extent(204));

    fmCampo6 = new FieldModel();
    fmCampo6.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo6()));
    fmCampo6.setFieldCmp(txtCampo6);
    fmCampo6.setKey(_PropMCrudPrueba.CAMPO6);
    fmCampo6.setProperty(_PropMCrudPrueba.CAMPO6);

   
     
    sectionModel.addChild(fmCampo6);
    
    // --------------------------------------------------------------------------------
    
    ETextAreaEx txtCampo7 = new ETextAreaEx();
    txtCampo7.setWidth(new Extent(204));

    fmCampo7 = new FieldModel();
    fmCampo7.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo7()));
    fmCampo7.setFieldCmp(txtCampo7);
    fmCampo7.setKey(_PropMCrudPrueba.CAMPO7);
    fmCampo7.setProperty(_PropMCrudPrueba.CAMPO7);

    fmCampo7.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudPruebaEdit.campo6(), txtCampo7));

     
    sectionModel.addChild(fmCampo7);
    
    // --------------------------------------------------------------------------------
    ETextAreaEx txtCampo8 = new ETextAreaEx();
    txtCampo8.setWidth(new Extent(204));

    fmCampo8 = new FieldModel();
    fmCampo8.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPruebaEdit.campo8()));
    fmCampo8.setFieldCmp(txtCampo8);
    fmCampo8.setKey(_PropMCrudPrueba.CAMPO8);
    fmCampo8.setProperty(_PropMCrudPrueba.CAMPO8);

   
     
    sectionModel.addChild(fmCampo8);
    
    // --------------------------------------------------------------------------------
    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmNumero);
    formModel.getValidatorList().add(duplicatedValidator);
  }
}
