/*
 * Created on 13/06/2007
 */
package com.minotauro.echo.cleda.edit.wrk;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.util.LabelValueBean;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.echo.util.gui.DlgValidation;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;
import com.minotauro.i18n.util.ReflectionI18N;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.api.WorkflowFactory;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmEditWrk extends FrmEditBase {

  protected static final String JAVA_ECHO_DOC_I18N_CLS = "java.echo.doc.i18n.cls";
  protected static final String JAVA_ECHO_DOC_I18N_KEY = "java.echo.doc.i18n.key";

  // --------------------------------------------------------------------------------

  protected MWrkTransSet wrkTransSet;

  protected String netPetriName; // TODO: Check why is this needed?
  protected String netStateName; // TODO: Check why is this needed?
  protected String workflowIdnt;

  protected Button btnWrkLog;

  protected SelectField cboAction;

  protected WorkflowFacade workflowFacade;

  // --------------------------------------------------------------------------------

  public FrmEditWrk(String workflowIdnt, String netPetriName, String netStateName) {
    this.netPetriName = netPetriName;
    this.netStateName = netStateName;
    this.workflowIdnt = workflowIdnt;
  }

  // --------------------------------------------------------------------------------

  public void init(MBase data,
      EnumEditMode editMode, EnumDataMode dataMode) {

    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public void init(
      MWrkTransSet wrkTransSet, MDocument data,
      EnumEditMode editMode, EnumDataMode dataMode)
      throws Exception {

    this.wrkTransSet = wrkTransSet;
    this.data/*    */= data;

    this.editMode = editMode;
    this.dataMode = dataMode;

    // ----------------------------------------

    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    this.data = (MDocument) data.reload(processContext.getSession());

    if (editMode == EnumEditMode.UPDATE) {
      this.wrkTransSet = (MWrkTransSet) wrkTransSet.reload(
          processContext.getSession());
    }

    // ----------------------------------------
    // Init workflow engine
    // ----------------------------------------

    workflowFacade =
        WorkflowFactory.getInstance().getFacade(workflowIdnt);

    workflowFacade.init(
        processContext.getSession(), BaseAppInstance.getUser(), null);

    workflowFacade.selectWorkflow((MDocument) this.data);

    // ----------------------------------------
    // Misc init stuff
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

    // ----------------------------------------

    processContext.comTransaction();
  }

  // --------------------------------------------------------------------------------

  public void init(EnumDataMode dataMode) //
      throws Exception {

    this.editMode = EnumEditMode.UPDATE;
    this.dataMode = dataMode;

    // ----------------------------------------

    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    // ----------------------------------------

    this.data = initMDocument();

    // ----------------------------------------
    // Init workflow engine
    // ----------------------------------------

    workflowFacade =
        WorkflowFactory.getInstance().getFacade(workflowIdnt);

    workflowFacade.init(
        processContext.getSession(), BaseAppInstance.getUser(), null);

    workflowFacade.createWorkflow(
        (MDocument) this.data,
        netPetriName, netStateName);

    workflowFacade.delegateActors();

    wrkTransSet =
        workflowFacade.getEnabledWrkTransSetForRole(
            workflowFacade.getInitRole());

    // ----------------------------------------
    // Misc init stuff
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

    // ----------------------------------------

    processContext.comTransaction();
  }

  // --------------------------------------------------------------------------------

  protected void initGUIProxies() {
    fieldValidProxy = new DocFieldValidProxy(
        workflowFacade, wrkTransSet);

    switch (editMode) {
      case SELECT :
        fieldStateProxy = new DocViewFieldStateProxy(
            workflowFacade);
        break;
      case UPDATE :
        fieldStateProxy = new DocEditFieldStateProxy(
            workflowFacade, wrkTransSet);
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected Component initTopComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    EFieldLabel lblAction = new EFieldLabel(
        _I18NFrmEditWrk.action());
    lft.add(lblAction);

    cboAction = getCboAction();
    cboAction.setEnabled(editMode != EnumEditMode.SELECT);
    lft.add(cboAction);

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

    rgh.add(getBtnWrkLog());

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------
  // Button Factory Methods
  // --------------------------------------------------------------------------------

  protected SelectField getCboAction() {
    SelectField ret = new SelectField();

    DefaultListModel defaultListModel =
        (DefaultListModel) ret.getModel();

    if (wrkTransSet != null) {
      defaultListModel.add(_I18NFrmEditWrk.select());

      for (final MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
        LabelValueBean<MWrkTrans> lvb = new LabelValueBean<MWrkTrans>(
            getTransLabel(wrkTrans), wrkTrans);
        defaultListModel.add(lvb);
      }
    } else {
      defaultListModel.add("...");
    }

    ret.setSelectedIndex(0);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnWrkLog() {
    Button ret = new Button(ImageReferenceCache.getInstance().
        getImageReference("images/icons/history_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance().
        getImageReference("images/icons/history_d_24x24.gif"));
    ret.setText(_I18NFrmEditWrk.history());

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnWrkLogClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected String getTransLabel(MWrkTrans wrkTrans) {
    try {
      return failsafeGetTransLabel(wrkTrans);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected String failsafeGetTransLabel(MWrkTrans wrkTrans)
      throws Exception {

    MNetTrans netTrans/*   */= wrkTrans.getNetTransRef();
    MNetTransSet netTransSet = netTrans.getNetTransSetRef();
    MNetPetri netPetri/*   */= netTransSet.getNetPetriRef();

    // ----------------------------------------
    // Get the i18n cls
    // ----------------------------------------

    String i18nCls = netTrans.getMetaData(
        /*  */JAVA_ECHO_DOC_I18N_CLS);

    if (i18nCls == null) {
      i18nCls = netTransSet.getMetaData(
          /**/JAVA_ECHO_DOC_I18N_CLS);
    }

    if (i18nCls == null) {
      i18nCls = netPetri.getMetaData(
          /**/JAVA_ECHO_DOC_I18N_CLS);
    }

    if (i18nCls == null) {
      throw new WorkflowException("i18nCls == null");
    }

    // ----------------------------------------
    // Get the i18n key
    // ----------------------------------------

    String i18nKey = netTrans.getMetaData(
        JAVA_ECHO_DOC_I18N_KEY);

    if (i18nKey == null) {
      throw new WorkflowException("i18nKey == null");
    }

    // ----------------------------------------
    // Call the method in i18n
    // ----------------------------------------

    return ReflectionI18N.i18n(i18nCls, i18nKey);
  }

  // --------------------------------------------------------------------------------
  // Button Handler Methods
  // --------------------------------------------------------------------------------

  protected boolean saveOrUpdate() {
    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    try {
      processContext.begTransaction();

      boolean ret =
          failsafeSaveOrUpdate(processContext);

      if (ret) {
        processContext.comTransaction();
      } else {
        processContext.rolTransaction();
      }

      return ret;
    } catch (Exception e) {
      processContext.rolTransaction();

      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  protected boolean failsafeSaveOrUpdate(
      ProcessContext processContext)
      throws Exception {

    // ----------------------------------------
    // Check if action is selected
    // ----------------------------------------

    if (cboAction.getSelectedIndex() == 0) {
      List<String> msgList = new ArrayList<String>();
      msgList.add(_I18NFrmEditWrk.actionNotEmptyValidator());

      DlgValidation dlgValidation = new DlgValidation(msgList);
      BaseAppInstance.getDesktop().addForm(
          null, dlgValidation, ProcessContextMode.IGNORE);

      return false;
    }

    // ----------------------------------------

    LabelValueBean<MWrkTrans> labelValueBean =
        (LabelValueBean<MWrkTrans>) cboAction.getSelectedItem();

    MWrkTrans wrkTrans = labelValueBean.getValue();

    // ----------------------------------------
    // Validate and update data
    // ----------------------------------------

    String command = wrkTrans.getNetTransRef().getName();

    if (!validateGUIInt(command, processContext)) {
      return false;
    }

    updateDataFromGUIInt();
    updateDataFromGUI();

    // ----------------------------------------
    // Save
    // ----------------------------------------

    preUpdate();

    MWorkflow workflow =
        wrkTrans.getWrkTransSetRef().getWorkflowRef();

    processContext.getSession().saveOrUpdate(
        workflow);
    processContext.getSession().saveOrUpdate(
        workflow.getDocumentRef());

    posUpdate();

    // ----------------------------------------
    // TODO: Dialog / abort if failed to fire
    // I Had an epiphany (spelling?), no auto
    // trans shoud be fired in the context of
    // an user firing a manual trans. This
    // should be fired from the scheduler,
    // with a 0 delay (inmediatly). This way
    // we remove trans chains and we can trace
    // auto trans failures the same way of
    // timed trans.
    // ----------------------------------------

    workflowFacade.fireUserTrans(wrkTrans);

    // ----------------------------------------
    // TODO: What is the flush scheduler for?
    // ----------------------------------------

    workflowFacade.flushScheduler();

    return true;
  }

  // --------------------------------------------------------------------------------

  protected void btnWrkLogClicked() {
    try {
      failsafeBtnWrkLogClicked();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeBtnWrkLogClicked() throws Exception {

    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    List<MWrkTransLog> list = workflowFacade.getWrkTransLogList();

    processContext.comTransaction();

    BaseAppInstance.getDesktop().addForm(
        this, new FrmDocHistory(list), ProcessContextMode.PARENT);
  }

  // --------------------------------------------------------------------------------
  // Misc
  // --------------------------------------------------------------------------------

  protected abstract MDocument initMDocument();
}
