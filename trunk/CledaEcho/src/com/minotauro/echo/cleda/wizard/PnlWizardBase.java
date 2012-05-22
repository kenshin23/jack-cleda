/*
 * Created on 11/12/2007
 */
package com.minotauro.echo.cleda.wizard;

import java.util.List;

import nextapp.echo.app.Column;
import nextapp.echo.app.Insets;

import org.apache.commons.collections.CollectionUtils;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.grid.FormGUILayout;
import com.minotauro.echo.grid.FormModel;
import com.minotauro.echo.grid.FormPopulator;
import com.minotauro.echo.grid.FormValidator;
import com.minotauro.echo.util.gui.DlgValidation;

/**
 * @author Demián Gutierrez
 */
public abstract class PnlWizardBase extends Column {

  protected FormModel formModel;

  protected FormGUILayout formGUILayout;
  protected FormValidator formValidator;
  protected FormPopulator formPopulator;

  // --------------------------------------------------------------------------------

  protected FrmWizardBase parent;

  protected Object data;

  // --------------------------------------------------------------------------------

  public PnlWizardBase(FrmWizardBase parent) {
    this.parent/**/= parent;
    this.data/*  */= parent.getData();

    initForm();
    initGUI();
    initGUIInt();
  }

  // --------------------------------------------------------------------------------

  protected void initGUIInt() {
    if (formModel != null) {
      formModel.setEnabled(true);

      setInsets(new Insets(5, 5, 5, 5));
      add(formGUILayout.getComponent());
    }
  }

  // --------------------------------------------------------------------------------

  protected void initForm() {
    formModel = new FormModel();

    formModel.setEnabled(true);

    formGUILayout = new FormGUILayout();
    formGUILayout.setFormModel(formModel);

    formPopulator = new FormPopulator();
    formPopulator.setFormModel(formModel);

    formValidator = new FormValidator();
    formValidator.setFormModel(formModel);
  }

  // --------------------------------------------------------------------------------

  protected void initPanel(int fromStep) {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected int btnPrevClicked() {
    return -1; // To curr - 1
  }

  // --------------------------------------------------------------------------------

  protected int btnNextClicked() {
    return -1; // To curr + 1
  }

  // --------------------------------------------------------------------------------

  protected void btnFnshClicked() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected String getTitle() {
    return "";
  }

  // --------------------------------------------------------------------------------

  protected abstract void initGUI();

  // --------------------------------------------------------------------------------
  // Update AAA From BBB Methods
  // --------------------------------------------------------------------------------

  // --------------------------------------------------------------------------------
  // TODO: This is duplicated in FrmEditBase. We should generalize to a PnlEdit
  // --------------------------------------------------------------------------------

  protected void updateGUIFromDataInt() {
    try {
      formPopulator.updateGUIFromData(data);
      updateGUIFromData();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void updateGUIFromData() {
    // Optional
  }

  // --------------------------------------------------------------------------------

  protected void updateDataFromGUIInt() {
    try {
      formPopulator.updateDataFromGUI(data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void updateDataFromGUI() {
    // Optional
  }

  // --------------------------------------------------------------------------------
  // Update State Methods
  // --------------------------------------------------------------------------------

  protected void updateStateInt() {
    formGUILayout.updateGUI();
  }

  // --------------------------------------------------------------------------------

  protected void updateState() {
    // Optional
  }

  // --------------------------------------------------------------------------------

  protected boolean validateGUIInt(
      String command, ProcessContext processContext) {

    List<String> msgList = formValidator.validateAll(
        command, processContext);

    if (!CollectionUtils.isEmpty(msgList)) {
      DlgValidation dlgValidation = new DlgValidation(msgList);
      BaseAppInstance.getDesktop().addForm(
          null, dlgValidation, ProcessContextMode.IGNORE);

      return false;
    }

    msgList = validateGUI(command, processContext);

    if (!CollectionUtils.isEmpty(msgList)) {
      DlgValidation dlgValidation = new DlgValidation(msgList);
      BaseAppInstance.getDesktop().addForm(
          null, dlgValidation, ProcessContextMode.IGNORE);

      return false;
    }

    return true;
  }

  // --------------------------------------------------------------------------------

  protected List<String> validateGUI(
      String command, ProcessContext processContext) {

    return null;
  }
}
