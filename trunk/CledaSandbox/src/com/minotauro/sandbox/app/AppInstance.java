/*
 * Created on 13/04/2007
 */
package com.minotauro.sandbox.app;

import nextapp.echo.app.Window;

import com.minotauro.cleda.task.core.SchedulerFactory;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.workflow.api.WorkflowFactory;

/**
 * @author Demián Gutierrez
 */
public class AppInstance extends BaseAppInstance {

  public AppInstance() {
    try {
      SchedulerFactory.getInstance().initFacade("ID");
      WorkflowFactory.getInstance().initFacade("ID", "ID");
    } catch (Exception e) {
      e.printStackTrace();
    }

    loadStyle("style.xml");
  }

  // --------------------------------------------------------------------------------

  @Override
  public Window init() {
    window = new Window();
    window.setTitle(_I18NAppInstance.windowTitle());
    window.setContent(new AppDesktop());

    return window;
  }
}
