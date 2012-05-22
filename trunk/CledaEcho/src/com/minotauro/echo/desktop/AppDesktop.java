/*
 * Created on 15/04/2007
 */
package com.minotauro.echo.desktop;

import java.util.HashMap;
import java.util.Map;

import nextapp.echo.app.Component;
import nextapp.echo.app.ContentPane;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.SplitPane;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.WindowPaneEvent;
import nextapp.echo.app.event.WindowPaneListener;
import nextapp.echo.extras.app.MenuBarPane;
import nextapp.echo.extras.app.menu.MenuModel;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.login.DlgLogin;
import com.minotauro.echo.login.LoginEvent;
import com.minotauro.echo.login.UserProxyImpl;
import com.minotauro.echo.menu.CledaEntryModel;
import com.minotauro.echo.menu.CledaEventModel;
import com.minotauro.echo.menu.MenuBuilder;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.menu.model.MMenu.Scope;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class AppDesktop extends ContentPane implements DesktopContentPane {

  private SplitPane splMain;

  private DlgLogin dlgLogin;

  // --------------------------------------------------------------------------------

  public AppDesktop() {
    initGUI();
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {

    // ----------------------------------------
    // The left background
    // ----------------------------------------

    FillImage fillImage = new FillImage(
        ImageReferenceCache.getInstance().getImageReference("images/fondo.jpg"),
        new Extent(0), new Extent(0), FillImage.REPEAT_VERTICAL);

    setBackgroundImage(fillImage);

    // ----------------------------------------
    // The main SplitPane
    // ----------------------------------------

    splMain = new SplitPane();
    splMain.setStyleName("AppDesktop.SplMain");
    splMain.setOrientation(SplitPane.ORIENTATION_VERTICAL_TOP_BOTTOM);

    // ----------------------------------------
    // To debug only
    // ----------------------------------------
    // splMain.setResizable(true);
    // splMain.setSeparatorColor(Color.BLACK);
    // splMain.setSeparatorHeight(new Extent(5, Extent.PX));
    // ----------------------------------------

    add(splMain);

    // ----------------------------------------
    // The login dialog
    // ----------------------------------------

    initWelcomeScreen();
  }

  // --------------------------------------------------------------------------------

  protected void initWelcomeScreen() {
    removeAllForms();

    // -----------------------------------------------------------
    // TODO: User proxy is hard-coded, but should be configurable
    // -----------------------------------------------------------

    dlgLogin = new DlgLogin(new UserProxyImpl());

    dlgLogin.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dlgLoginClicked(evt);
      }
    });

    add(dlgLogin);

    // ----------------------------------------

    initMenu(Scope.EXTERNAL);
  }

  // --------------------------------------------------------------------------------

  protected void initDesktopScreen() {
    removeAllForms();

    initMenu(Scope.INTERNAL);
  }

  // --------------------------------------------------------------------------------

  protected void initMenu(Scope scope) {
    MenuBuilder menuBuilder = new MenuBuilder();
    MenuModel menuModel = menuBuilder.build(
        BaseAppInstance.getUser(), scope);

    MenuBarPane menuBarPane = new MenuBarPane(menuModel);
    menuBarPane.setStyleName("Default");

    menuBarPane.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        MenuBarPane menuBarPane = (MenuBarPane) evt.getSource();

        CledaEntryModel cledaEntryModel =
            (CledaEntryModel) menuBarPane.getModel();

        CledaEventModel cledaEventModel =
            cledaEntryModel.findCledaEventModelById(evt.getActionCommand());

        try {
          AppDesktopEvent appDesktopEvent =
              (AppDesktopEvent) Class.forName(
                  cledaEventModel.getMenu().getHandler()).newInstance();

          appDesktopEvent.run(AppDesktop.this, cledaEventModel.getMenu());
        } catch (Exception e) {
          e.printStackTrace(); // TODO!!!
        }
      }
    });

    splMain.removeAll();
    splMain.add(menuBarPane);
  }

  // --------------------------------------------------------------------------------

  protected void removeAllForms() {
    Component[] componentArray = getComponents();

    for (Component component : componentArray) {
      if (component instanceof WindowPane) {
        WindowPane windowPane = (WindowPane) component;
        windowPane.userClose();
      }
    }
  }

  // --------------------------------------------------------------------------------

  protected void dlgLoginClicked(ActionEvent evt) {
    LoginEvent loginEvent = (LoginEvent) evt;

    MUser user = loginEvent.getUser();

    BaseAppInstance.setUser(user);
    initDesktopScreen();
  }

  // --------------------------------------------------------------------------------

  public void exitEvent() {
    BaseAppInstance.setUser(null);
    initWelcomeScreen();
  }

  // --------------------------------------------------------------------------------
  // DesktopContentPane
  // --------------------------------------------------------------------------------

  protected Map<WindowPane, ProcessContext> windowContextMap =
      new HashMap<WindowPane, ProcessContext>();

  // --------------------------------------------------------------------------------

  public void setProcessContext(WindowPane form, ProcessContext processContext) {
    if (windowContextMap.get(form) != null && processContext != null) {
      throw new IllegalArgumentException( //
          "windowContextMap.get(form) != null && processContext != null");
    }

    if (processContext != null) {
      windowContextMap.put(form, processContext);
    } else {
      windowContextMap.remove(form);
    }
  }

  // --------------------------------------------------------------------------------

  public ProcessContext getProcessContext(WindowPane form) {
    return windowContextMap.get(form);
  }

  // --------------------------------------------------------------------------------
  // DEBUG only
  // --------------------------------------------------------------------------------

  @Deprecated
  public void dumpProcessContext() {
    System.err.println(
        "********************************************************************************");

    for (Map.Entry<WindowPane, ProcessContext> entry : windowContextMap.entrySet()) {
      System.err.println(entry.getKey() + ";" + entry.getValue());
    }
  }

  // --------------------------------------------------------------------------------
  // addForm / delForm
  // --------------------------------------------------------------------------------

  public void addForm(
      final WindowPane parent, final WindowPane newfrm,
      final ProcessContextMode pcmode) {

    // ----------------------------------------

    ProcessContext processContext = null;

    switch (pcmode) {
      case IGNORE :
        // EMPTY
        break;
      case CREATE :
        processContext = new ProcessContext();
        processContext.begSession();
        break;
      case PARENT :
        processContext = BaseAppInstance.getDesktop().
            getProcessContext(parent);
        break;
      default :
        throw new IllegalArgumentException(pcmode.toString());
    }

    // ----------------------------------------

    if (processContext != null) {
      BaseAppInstance.getDesktop().
          setProcessContext(newfrm, processContext);
    }

    // ----------------------------------------

    newfrm.addWindowPaneListener(new WindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent evt) {
        if (pcmode == ProcessContextMode.CREATE) {
          System.err.println("Closing: " +
              BaseAppInstance.getDesktop().
                  getProcessContext(newfrm));

          BaseAppInstance.getDesktop().
              getProcessContext(newfrm).
              endSession();
        }

        BaseAppInstance.getDesktop().
            setProcessContext(newfrm, null);

        if (parent != null) {
          parent.setVisible(true);
        }
      }
    });

    // ----------------------------------------

    if (parent != null) {
      parent.setVisible(false);
    }

    BaseAppInstance.defaultAddForm(this, newfrm);
  }

  // --------------------------------------------------------------------------------

  public void delForm(WindowPane form) {
    remove(form);
  }
}
