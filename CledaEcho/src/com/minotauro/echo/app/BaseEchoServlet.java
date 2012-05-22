/*
 * Created on 13/04/2007
 */
package com.minotauro.echo.app;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextapp.echo.webcontainer.WebContainerServlet;

import com.minotauro.echo.wrapper.base.InitEchoComponentWrapperMap;

/**
 * @author Demián Gutierrez
 */
public abstract class BaseEchoServlet extends WebContainerServlet {

  protected static final String LOGIN_TOKEN1 = "login.token1";
  protected static final String LOGIN_TOKEN2 = "login.token2";

  protected static final String USER_INSTANCE_SESSION_KEY_PREFIX = "Echo2UserInstance";

  protected static String SERVLET_NAME = null;

  @Override
  public void init() throws ServletException {

    // ----------------------------------------
    // TODO: Just a temporal solution
    // ----------------------------------------

    InitEchoComponentWrapperMap.init();
  }

  @Override
  protected void process(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    if (SERVLET_NAME == null) {
      SERVLET_NAME = getServletName();
    }

    super.process(req, res);
  }

  protected void processApp(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    req.getSession(true).setAttribute(LOGIN_TOKEN2, null);
  }

  protected void processLog(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    String token1 = (String) req.getSession().getAttribute(LOGIN_TOKEN1);
    String token2 = (String) req.getSession().getAttribute(LOGIN_TOKEN2);

    if (req.getMethod().equalsIgnoreCase("GET") && token1 == null && token2 == null) {
      req.getSession().invalidate();
      req.getSession(true).setAttribute(LOGIN_TOKEN1, LOGIN_TOKEN2);
    }
  }

  protected String getSessionKey() {
    return USER_INSTANCE_SESSION_KEY_PREFIX + ":" + SERVLET_NAME;
  }

  protected abstract BaseAppInstance getBaseAppInstance();
}
