/*
 * Created on 03/07/2008
 */
package com.minotauro.echo.cleda.edit.wrk;

import com.minotauro.echo.validator.base.FieldValidProxy;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class DocFieldValidProxy implements FieldValidProxy {

  protected WorkflowFacade/**/workflowFacade;

  protected MWrkTransSet/*  */wrkTransSet;

  // --------------------------------------------------------------------------------

  public DocFieldValidProxy(
      WorkflowFacade workflowFacade, MWrkTransSet wrkTransSet) {

    this.workflowFacade/**/= workflowFacade;
    this.wrkTransSet/*   */= wrkTransSet;
  }

  // --------------------------------------------------------------------------------

  @Override
  public boolean isFieldValidable(String key, String command) {

    for (MWrkTrans wrkTrans : wrkTransSet.getWrkTransList()) {
      if (command.equals(wrkTrans.getNetTransRef().getName())) {
        return workflowFacade.isEditFieldValidable(key, wrkTrans);
      }
    }

    throw new RuntimeException("netTrans == null: " + command);
  }
}
