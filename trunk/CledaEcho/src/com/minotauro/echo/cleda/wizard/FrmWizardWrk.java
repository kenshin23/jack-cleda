/*
 * Created on 11/12/2007
 */
package com.minotauro.echo.cleda.wizard;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.api.WorkflowFactory;
import com.minotauro.workflow.model.MDocument;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmWizardWrk extends FrmWizardBase {

  protected String netPetriName; // TODO: Check why is this needed?
  protected String netStateName; // TODO: Check why is this needed?
  protected String workflowIdnt;

  protected WorkflowFacade workflowFacade;

  // --------------------------------------------------------------------------------

  public FrmWizardWrk(String title,
      String workflowIdnt, String netPetriName, String netStateName) {

    super(title);

    this.netPetriName = netPetriName;
    this.netStateName = netStateName;
    this.workflowIdnt = workflowIdnt;
  }

  // --------------------------------------------------------------------------------

  @Override
  public void initAll() throws Exception {

    ProcessContext processContext =
        BaseAppInstance.getDesktop().getProcessContext(this);

    processContext.begTransaction();

    // ----------------------------------------

    this.data = initData();

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

    // ----------------------------------------
    // Misc init stuff
    // ----------------------------------------

    initGUI();
    initGUIInt();

    // ----------------------------------------

    processContext.comTransaction();
  }

  // --------------------------------------------------------------------------------

  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    saveOrUpdate();

    userClose();
  }

  // --------------------------------------------------------------------------------
  // Save Model Methods
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

  protected boolean failsafeSaveOrUpdate(
      ProcessContext processContext)
      throws Exception {

    // ----------------------------------------
    // Save
    // ----------------------------------------

    preUpdate();

    MDocument document = (MDocument) data;

    processContext.getSession().saveOrUpdate(
        document.getWorkflowRef());
    processContext.getSession().saveOrUpdate(
        document);

    posUpdate();

    // ----------------------------------------
    // TODO: What is the flush scheduler for?
    // ----------------------------------------

    workflowFacade.flushScheduler();

    return true;
  }

  // --------------------------------------------------------------------------------

  protected void preUpdate() {
    // Empty
  }

  protected void posUpdate() {
    // Empty
  }
}
