/*
 * Created on 03/07/2008
 */
package com.minotauro.echo.cleda.edit.wrk;

import com.minotauro.echo.grid.FieldStateProxy;
import com.minotauro.echo.grid.FieldStateProxyException;
import com.minotauro.workflow.api.WorkflowFacade;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class DocEditFieldStateProxy implements FieldStateProxy {

  protected WorkflowFacade/**/workflowFacade;

  protected MWrkTransSet/*  */wrkTransSet;

  // --------------------------------------------------------------------------------

  public DocEditFieldStateProxy(
      WorkflowFacade workflowFacade, MWrkTransSet wrkTransSet) {

    this.workflowFacade/**/= workflowFacade;
    this.wrkTransSet/*   */= wrkTransSet;
  }

  // --------------------------------------------------------------------------------

  public boolean isFieldEnabled(String key)
      throws FieldStateProxyException {

    return workflowFacade.isEditFieldEnabled(key, wrkTransSet);
  }

  // --------------------------------------------------------------------------------

  public boolean isFieldVisible(String key)
      throws FieldStateProxyException {

    return workflowFacade.isEditFieldVisible(key, wrkTransSet);
  }
}
