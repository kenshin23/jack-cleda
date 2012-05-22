/*
 * Created on 13/04/2007
 */
package com.minotauro.echo.app;

import java.util.Locale;

import nextapp.echo.app.ApplicationInstance;
import nextapp.echo.app.Component;
import nextapp.echo.app.StyleSheet;
import nextapp.echo.app.Window;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.serial.SerialException;
import nextapp.echo.app.serial.StyleSheetLoader;

import com.minotauro.echo.desktop.DesktopContentPane;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public abstract class BaseAppInstance extends ApplicationInstance {

  protected Window window;

  protected MUser user;

  // --------------------------------------------------------------------------------

  public BaseAppInstance() {
    setLocale(Locale.ENGLISH);
  }

  // --------------------------------------------------------------------------------

  public static void notImplementedYet() {
    MessageBox.msgBox(_I18NMessageBox.information(), //
        _I18NBaseAppInstance.notImplementedYet(), null, 250, 115, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  public static void defaultAddForm(Component parent, WindowPane form) {
    parent.add(form);
  }

  // --------------------------------------------------------------------------------

  public static MUser getUser() {
    BaseAppInstance baseAppInstance = (BaseAppInstance) BaseAppInstance.getActive();
    return baseAppInstance.user;
  }

  // --------------------------------------------------------------------------------

  public static void setUser(MUser user) {
    BaseAppInstance baseAppInstance = (BaseAppInstance) BaseAppInstance.getActive();
    baseAppInstance.user = user;

    if (user != null) {
      baseAppInstance.setLocale(user.getLocale());
    }
  }

  // --------------------------------------------------------------------------------

  public static DesktopContentPane getDesktop() {
    BaseAppInstance applicationInstance = (BaseAppInstance) ApplicationInstance.getActive();
    Window window = applicationInstance.window;
    return (DesktopContentPane) window.getContent();
  }

  // --------------------------------------------------------------------------------

  public void loadStyle(String name) {
    try {
      StyleSheet styleSheet =
          StyleSheetLoader.load(getClass().getResourceAsStream(name), //
              Thread.currentThread().getContextClassLoader());

      setStyleSheet(styleSheet);
    } catch (SerialException e) {
      e.printStackTrace();
    }
  }
}
