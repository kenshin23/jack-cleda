/*
 * Created on 18/07/2007
 */
package com.minotauro.cleda.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Demián Gutierrez
 */
public class SessionThreadLocal {

  protected Map<String, SessionWrapper> sessionWrapperMap = //
  new HashMap<String, SessionWrapper>();

  // --------------------------------------------------------------------------------

  public SessionThreadLocal() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  private static ThreadLocalSession threadLocalSession = new ThreadLocalSession();

  private static class ThreadLocalSession extends ThreadLocal<SessionThreadLocal> {
    public SessionThreadLocal initialValue() {
      SessionThreadLocal ret = new SessionThreadLocal();
      return ret;
    }
  }

  // --------------------------------------------------------------------------------

  public static SessionThreadLocal getInstance() {
    return threadLocalSession.get();
  }

  // --------------------------------------------------------------------------------

  public SessionWrapper getSessionWrapper() {
    return getSessionWrapper(null);
  }

  public SessionWrapper getSessionWrapper(String hibernateCfg) {
    if (hibernateCfg == null) {
      hibernateCfg = CledaConnector.getInstance().getDefaultHibernateCfg();
    }

    SessionWrapper ret = sessionWrapperMap.get(hibernateCfg);

    if (ret == null || !ret.getSession().isOpen()) {
      ret = new SessionWrapper( //
          hibernateCfg, CledaConnector.getInstance().getSession(hibernateCfg));
      sessionWrapperMap.put(hibernateCfg, ret);
    }

    return ret;
  }
}
