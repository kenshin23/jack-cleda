/*
 * Created on 13/06/2007
 */
package com.minotauro.echo.cleda.edit;

import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.button.AbstractButton;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.WindowPaneEvent;

import org.apache.commons.collections.CollectionUtils;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelListenerMethod;
import com.minotauro.echo.base.AcceptCancelProxy;
import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.grid.FieldStateProxy;
import com.minotauro.echo.grid.FormGUILayout;
import com.minotauro.echo.grid.FormModel;
import com.minotauro.echo.grid.FormPopulator;
import com.minotauro.echo.grid.FormValidator;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.echo.util.gui.DlgValidation;
import com.minotauro.echo.util.gui.InternalWindowPaneListener;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;
import com.minotauro.echo.validator.base.FieldValidProxy;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmEditBase extends DlgBorderBase {

  protected InternalWindowPaneListener cancelOnCloseListener;

  protected AcceptCancelProxy acceptCancelProxy = //
  new AcceptCancelProxy(getEventListenerList());

  // --------------------------------------------------------------------------------

  protected FieldValidProxy fieldValidProxy;
  protected FieldStateProxy fieldStateProxy;

  protected FormModel formModel;

  protected FormGUILayout formGUILayout;
  protected FormValidator formValidator;
  protected FormPopulator formPopulator;

  // --------------------------------------------------------------------------------

  protected EnumEditMode editMode;
  protected EnumDataMode dataMode;

  // --------------------------------------------------------------------------------

  protected AbstractButton btnAccept;
  protected AbstractButton btnCancel;
  protected AbstractButton btnGoback;

  // --------------------------------------------------------------------------------

  protected MBase data;

  // --------------------------------------------------------------------------------

  public FrmEditBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(MBase data,
      EnumEditMode editMode, EnumDataMode dataMode) {

    this.editMode = editMode;
    this.dataMode = dataMode;

    initData(data);

    setW(new Extent(450)); // default
    setH(new Extent(330)); // default

    // ----------------------------------------

    initGUIProxies();
    initForm();

    initGUI();
    initGUIInt();

    addCancelOnCloseListener();

    // ----------------------------------------

    updateGUIFromDataInt();
    updateGUIFromData();

    updateStateInt();
    updateState();
  }

  // --------------------------------------------------------------------------------

  protected void initData(MBase data) {
    if (dataMode == EnumDataMode.DATABASE) {
      BaseAppInstance.getDesktop().getProcessContext(this).begTransaction();

      data = (MBase) data.reload( //
          BaseAppInstance.getDesktop().getProcessContext(this).getSession());

      BaseAppInstance.getDesktop().getProcessContext(this).comTransaction();
    }

    this.data = data;
  }

  // --------------------------------------------------------------------------------

  protected void initGUIProxies() {
    // Optional
  }

  // --------------------------------------------------------------------------------

  protected void initForm() {
    formModel = new FormModel();

    // TODO: formModel.setFieldValidProxy(fieldValidProxy);
    /*     */formModel.setFieldStateProxy(fieldStateProxy);

    formModel.setEnabled(editMode == EnumEditMode.UPDATE);

    formGUILayout = new FormGUILayout();
    formGUILayout.setFormModel(formModel);

    formPopulator = new FormPopulator();
    formPopulator.setFormModel(formModel);

    formValidator = new FormValidator();
    formValidator.setFormModel(formModel);
  }

  // --------------------------------------------------------------------------------

  protected abstract void initGUI();

  // --------------------------------------------------------------------------------

  protected void initGUIInt() {
    splTop.setSeparatorPosition(new Extent(50));
    splMid.setSeparatorPosition(new Extent(50));

    setColMidOverflow(true);

    // ----------------------------------------
    // Top Component
    // ----------------------------------------

    Component topComponent = initTopComponent();

    if (topComponent != null) {
      colTop.add(topComponent);
    } else {
      splTop.setSeparatorPosition(new Extent(0));
    }

    // ----------------------------------------
    // FormModel / manual GUI
    // ----------------------------------------

    if (formModel != null) {
      formModel.setEnabled( //
          /**/editMode == EnumEditMode.UPDATE || //
              editMode == EnumEditMode.CREATE);

      colMid.setInsets(new Insets(5, 5, 5, 5));
      colMid.add(formGUILayout.getComponent());
    }

    // ----------------------------------------
    // Bot Component
    // ----------------------------------------

    Component botComponent = initBotComponent();

    if (botComponent != null) {
      colBot.add(botComponent);
    } else {
      splMid.setSeparatorPosition(new Extent(0));
    }
  }

  // --------------------------------------------------------------------------------

  protected void addCancelOnCloseListener() {

    // ----------------------------------------
    // Close works like a cancel
    // ----------------------------------------

    cancelOnCloseListener = new InternalWindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent e) {
        btnCancelClicked(true);
      }
    };
    addInternalWindowPaneListener(cancelOnCloseListener);
  }

  // --------------------------------------------------------------------------------

  // Optional
  protected Component initTopComponent() {
    return null;
  }

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    lft.add(btnAccept = getBtnAccept());
    lft.add(btnCancel = getBtnCancel());
    lft.add(btnGoback = getBtnGoback());

    lftCntRghLayout.add(lft, PlaceHolder.LFT);

    // ----------------------------------------

    Row cnt = new Row();
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));
    cnt.setAlignment(Alignment.ALIGN_CENTER);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    // ----------------------------------------

    Row rgh = new Row();
    rgh.setInsets(new Insets(5, 5, 5, 5));
    rgh.setCellSpacing(new Extent(5));
    rgh.setAlignment(Alignment.ALIGN_RIGHT);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------
  // Button Factory Methods
  // --------------------------------------------------------------------------------

  protected Button getBtnAccept() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/accept_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/accept_d_24x24.gif"));
    ret.setVisible(editMode != EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAcceptClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnCancel() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/cancel_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/cancel_d_24x24.gif"));
    ret.setVisible(editMode != EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(false);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnGoback() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_d_24x24.gif"));
    ret.setVisible(editMode == EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(false);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------
  // Button Handler Methods
  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked() {
    if (!saveOrUpdate()) {
      return;
    }

    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.ACCEPT);

    delInternalWindowPaneListener(cancelOnCloseListener);

    userClose();
  }

  // --------------------------------------------------------------------------------

  protected void btnCancelClicked(boolean closing) {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.CANCEL);

    if (closing) {
      return;
    }

    delInternalWindowPaneListener(cancelOnCloseListener);

    userClose();
  }

  // --------------------------------------------------------------------------------
  // Save Model Methods
  // --------------------------------------------------------------------------------

  protected boolean saveOrUpdate() {
    switch (dataMode) {
      case DATABASE :
        return saveOrUpdateDatabase();
      case INMEMORY :
        return saveOrUpdateInMemory();
    }

    throw new IllegalStateException();
  }

  // --------------------------------------------------------------------------------

  protected boolean saveOrUpdateDatabase() {
    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    boolean ret =
        failsafeSaveOrUpdateDatabase(processContext);

    if (ret) {
      processContext.comTransaction();
    } else {
      processContext.rolTransaction();
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected boolean failsafeSaveOrUpdateDatabase(
      ProcessContext processContext) {

    if (!validateGUIInt(null, processContext)) {
      return false;
    }

    updateDataFromGUIInt();
    updateDataFromGUI();

    preUpdate();
    processContext.getSession().saveOrUpdate(data);
    posUpdate();

    return true;
  }

  // --------------------------------------------------------------------------------

  protected boolean saveOrUpdateInMemory() {
    if (!validateGUIInt(null, null)) {
      return false;
    }

    updateDataFromGUIInt();
    updateDataFromGUI();

    preUpdate();
    posUpdate();

    return true;
  }

  // --------------------------------------------------------------------------------

  protected void preUpdate() {
    // Empty
  }

  protected void posUpdate() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // Update AAA From BBB Methods
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
  // Validate Methods
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

  // --------------------------------------------------------------------------------
  // Misc
  // --------------------------------------------------------------------------------

  public AcceptCancelProxy getAcceptCancelProxy() {
    return acceptCancelProxy;
  }

  public void setAcceptCancelProxy(AcceptCancelProxy acceptCancelProxy) {
    this.acceptCancelProxy = acceptCancelProxy;
  }

  // --------------------------------------------------------------------------------

  public MBase getData() {
    return data;
  }
}
