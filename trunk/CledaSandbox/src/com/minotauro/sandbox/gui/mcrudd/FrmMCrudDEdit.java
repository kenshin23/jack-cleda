/*
 * Created on 27/08/2011
 */
package com.minotauro.sandbox.gui.mcrudd;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.grid.TabModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.model.MCrudD;
import com.minotauro.sandbox.model._PropMCrudD;

/**
 * @author Demián Gutierrez
 */
public class FrmMCrudDEdit extends FrmEditBase {

  private FieldModel fmName;
  private FieldModel fmDesc;

  // --------------------------------------------------------------------------------

  public FrmMCrudDEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrudD) data).getName();
    setTitle(_I18NFrmMCrudDEdit.title(name == null ? "-" : name));

    // --------------------------------------------------------------------------------

    formModel.setShowTabs(true);

    initTabA();

    initTab("B", 5);
    initTab("C", 5);
    initTab("D", 5);
    initTab("E", 5);
  }

  // --------------------------------------------------------------------------------

  protected void initTabA() {
    TabModel tabModel = new TabModel();
    tabModel.setLabelCmp(new EFieldLabel(_I18NFrmMCrudDEdit.tab("A"), null));
    formModel.addChild(tabModel);

    SectionModel sectionModel = new SectionModel();
    tabModel.addChild(sectionModel);

    // --------------------------------------------------------------------------------

    TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudDEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudD.NAME);
    fmName.setProperty(_PropMCrudD.NAME);

    fmName.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudDEdit.name(), txtName));

    sectionModel.addChild(fmName);

    // --------------------------------------------------------------------------------

    TextField txtDesc = new TextField();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudDEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudD.DESC);
    fmDesc.setProperty(_PropMCrudD.DESC);

    fmDesc.getValidatorList().add( //
        new NotEmptyValidator(_I18NFrmMCrudDEdit.desc(), txtDesc));

    sectionModel.addChild(fmDesc);

    // --------------------------------------------------------------------------------

    DuplicatedValidator duplicatedValidator = new DuplicatedValidator(data);
    duplicatedValidator.add(fmName);
    formModel.getValidatorList().add(duplicatedValidator);
  }

  // --------------------------------------------------------------------------------

  protected void initTab(String prefix, int count) {
    TabModel tabModel = new TabModel();
    tabModel.setLabelCmp(new EFieldLabel(_I18NFrmMCrudDEdit.tab(prefix), null));
    formModel.addChild(tabModel);

    SectionModel sectionModel = new SectionModel();
    tabModel.addChild(sectionModel);

    // --------------------------------------------------------------------------------

    for (int i = 1; i <= count; i++) {
      String key = "val" + prefix + i;
      String lbl = _I18NFrmMCrudDEdit.lbl(prefix, i);
      String val = _I18NFrmMCrudDEdit.val(prefix, i);

      TextField txtVal = new TextField();
      FieldModel fmVal = new FieldModel();
      fmVal.setLabelCmp(new EFieldLabel(lbl));
      fmVal.setFieldCmp(txtVal);
      fmVal.setKey(key);
      fmVal.setProperty(key);

      fmVal.getValidatorList().add(
          new NotEmptyValidator(val, txtVal));

      sectionModel.addChild(fmVal);
    }
  }
}
