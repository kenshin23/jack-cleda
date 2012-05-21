/*
 * Created on 13/04/2007
 */
package com.minotauro.sandbox.app;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextapp.echo.app.ApplicationInstance;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.app.BaseEchoServlet;

/**
 * @author Demián Gutierrez
 */
public class AppServlet extends BaseEchoServlet {

  public AppServlet() {
    Locale.setDefault(Locale.ENGLISH);
  }

  @Override
  protected void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    super.process(req, res);
    //super.processApp(req, res);
  }

  @Override
  public ApplicationInstance newApplicationInstance() {
    return new AppInstance();
  }

  @Override
  protected BaseAppInstance getBaseAppInstance() {
    return (BaseAppInstance) AppInstance.getActive();
  }
}
