/*
 * Created on 27/08/2008
 */
package com.minotauro.echo.base;

import java.util.EventListener;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.EventListenerList;

/**
 * @author Demián Gutierrez
 */
public class AcceptCancelProxy {

  protected EventListenerList eventListenerList;

  // --------------------------------------------------------------------------------

  public AcceptCancelProxy(EventListenerList eventListenerList) {
    this.eventListenerList = eventListenerList;
  }

  // --------------------------------------------------------------------------------

  public void addAcceptCancelListener(AcceptCancelListener listener) {
    eventListenerList.addListener(AcceptCancelListener.class, listener);
  }

  public void delAcceptCancelListener(AcceptCancelListener listener) {
    eventListenerList.removeListener(AcceptCancelListener.class, listener);
  }

  public EventListener[] getAcceptCancelListener() {
    return eventListenerList.getListeners(AcceptCancelListener.class);
  }

  public void fireAcceptCancelEvent(ActionEvent evt, AcceptCancelListenerMethod method) {
    EventListener[] eventListeners = getAcceptCancelListener();

    for (int i = 0; i < eventListeners.length; i++) {
      AcceptCancelListener listener = (AcceptCancelListener) eventListeners[i];
      switch (method) {
        case ACCEPT :
          listener.btnAcceptClicked(evt);
          break;
        case CANCEL :
          listener.btnCancelClicked(evt);
          break;
      }
    }
  }
}
