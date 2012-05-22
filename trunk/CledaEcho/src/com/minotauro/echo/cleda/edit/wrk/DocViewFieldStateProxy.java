/*
 * Created on 03/07/2008
 */
package com.minotauro.echo.cleda.edit.wrk;

import com.minotauro.echo.grid.FieldStateProxy;
import com.minotauro.echo.grid.FieldStateProxyException;
import com.minotauro.workflow.api.WorkflowFacade;

/**
 * @author Demián Gutierrez
 */
public class DocViewFieldStateProxy implements FieldStateProxy {

  protected WorkflowFacade workflowFacade;

  // --------------------------------------------------------------------------------

  public DocViewFieldStateProxy(WorkflowFacade workflowFacade) {
    this.workflowFacade = workflowFacade;
  }

  // --------------------------------------------------------------------------------

  public boolean isFieldEnabled(String key)
      throws FieldStateProxyException {

    return false;
  }

  // --------------------------------------------------------------------------------

  public boolean isFieldVisible(String key)
      throws FieldStateProxyException {

    return workflowFacade.isViewFieldVisible(key);
  }
}
