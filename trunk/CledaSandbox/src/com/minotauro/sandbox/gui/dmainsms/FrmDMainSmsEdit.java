
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
import com.minotauro.sandbox.model.DMainSms;
import com.minotauro.sandbox.model.DMultJointAB;
import com.minotauro.sandbox.model.DMultJointAC;
import com.minotauro.sandbox.model.DSingJointAB;
import com.minotauro.sandbox.model.DSingJointAC;
import com.minotauro.sandbox.model._PropDInnerA;
import com.minotauro.sandbox.model._PropDMainSms;
import com.minotauro.sandbox.model._PropDMultJointAB;
import com.minotauro.sandbox.model._PropDMultJointAC;
import com.minotauro.sandbox.model._PropDSingJointAB;
import com.minotauro.sandbox.model._PropDSingJointAC;

/**
 * @author Karla Moreno
 * 
 */
public class FrmDMainSmsEdit extends FrmEditWrk {

  protected FieldModel fmName;
  protected FieldModel fmDesc;
  protected FieldModel fmTipo;

 
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
    
    }

  @Override
  protected DMainSms initMDocument() {
    return new DMainSms();
  }
}
