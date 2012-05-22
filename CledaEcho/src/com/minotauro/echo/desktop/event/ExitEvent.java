/*
 * Created on 20/08/2011
 */
package com.minotauro.echo.desktop.event;

import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class ExitEvent implements AppDesktopEvent {

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    desktop.exitEvent();
  }
}
