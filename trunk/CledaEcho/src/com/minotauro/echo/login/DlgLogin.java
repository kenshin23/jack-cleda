/*
 * Created on 24/04/2007
 */
package com.minotauro.echo.login;

import java.util.EventListener;
import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.PasswordField;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.TextField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.EventListenerList;
import nextapp.echo.app.layout.ColumnLayoutData;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.cleda.config.CommonsConfig;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.gui.EWindowPane;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class DlgLogin extends EWindowPane {

  protected static final Extent TXT_FIELD_WIDTH = new Extent(150);
  protected static final Extent BTN_ENTER_WIDTH = new Extent(85);

  // --------------------------------------------------------------------------------

  protected Component cmpUser;
  protected TextField txtPass;

  protected EventListenerList eventListenerList = new EventListenerList();

  protected UserProxy userProxy;

  // --------------------------------------------------------------------------------

  public DlgLogin(UserProxy userProxy) {
    this.userProxy = userProxy;

    setMaximizeEnabled(false);
    setMinimizeEnabled(false);

    setResizable(false);
    setClosable(true);

    setW(new Extent(200));
    setH(new Extent(180));

    setInsets(new Insets(5, 5, 5, 5));

    setTitle(_I18NDlgLogin.title());

    initGUI();
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    ColumnLayoutData cld;

    Column col = new Column();
    col.setCellSpacing(new Extent(5));
    add(col);

    // ----------------------------------------
    // Enter ActionListener
    // ----------------------------------------

    ActionListener enterActionListener = new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnEnterClicked();
      }
    };

    // ----------------------------------------
    // User
    // ----------------------------------------

    EFieldLabel lblUser = new EFieldLabel(_I18NDlgLogin.user());
    lblUser.setSuffix(null);
    cld = new ColumnLayoutData();
    cld.setAlignment(Alignment.ALIGN_CENTER);
    lblUser.setLayoutData(cld);
    col.add(lblUser);

    if (CommonsConfig.isDebug()) {
      cmpUser = initUserCbo(enterActionListener);
    } else {
      cmpUser = initUserTxt(enterActionListener);
    }

    cld = new ColumnLayoutData();
    cld.setAlignment(Alignment.ALIGN_CENTER);
    cmpUser.setLayoutData(cld);
    col.add(cmpUser);

    // ----------------------------------------
    // Pass
    // ----------------------------------------

    EFieldLabel lblPass = new EFieldLabel(_I18NDlgLogin.pass());
    lblPass.setSuffix(null);
    cld = new ColumnLayoutData();
    cld.setAlignment(Alignment.ALIGN_CENTER);
    lblPass.setLayoutData(cld);
    col.add(lblPass);

    txtPass = new PasswordField();
    txtPass.setWidth(TXT_FIELD_WIDTH);
    txtPass.addActionListener(enterActionListener);
    cld = new ColumnLayoutData();
    cld.setAlignment(Alignment.ALIGN_CENTER);
    txtPass.setLayoutData(cld);
    col.add(txtPass);

    // ----------------------------------------
    // Enter Button
    // ----------------------------------------

    Button btnSend = new Button(_I18NDlgLogin.send());
    btnSend.setStyleName(GUIStyles.DEFAULT);
    btnSend.setWidth(BTN_ENTER_WIDTH);
    btnSend.addActionListener(enterActionListener);
    cld = new ColumnLayoutData();
    cld.setAlignment(Alignment.ALIGN_CENTER);
    btnSend.setLayoutData(cld);
    col.add(btnSend);
  }

  // --------------------------------------------------------------------------------

  protected Component initUserTxt(ActionListener enterActionListener) {
    TextField ret = new TextField();
    ret.setWidth(TXT_FIELD_WIDTH);
    ret.addActionListener(enterActionListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Component initUserCbo(ActionListener enterActionListener) {
    SelectField ret = new SelectField();
    ret.setWidth(TXT_FIELD_WIDTH);
    ret.addActionListener(enterActionListener);

    DefaultListModel dlm = (DefaultListModel) ret.getModel();

    dlm.add(_I18NDlgLogin.select());

    List<MUser> userList = userProxy.getUserList();

    for (MUser user : userList) {
      dlm.add(user.getUser());
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected String getUser() {
    if (!CommonsConfig.isDebug()) {
      TextField cmp = (TextField) cmpUser;

      return cmp.getText();
    }

    // ----------------------------------------

    SelectField cmp = (SelectField) cmpUser;

    if (cmp.getSelectedIndex() == 0) {
      return "";
    }

    return cmp.getSelectedItem().toString();
  }

  // --------------------------------------------------------------------------------

  protected void btnEnterClicked() {
    MUser user = userProxy.loadUser(getUser(), txtPass.getText());

    if (user != null) {
      LoginEvent evt = new LoginEvent(this, user);
      fireActionEvent(evt);
      return;
    }

    MessageBox.msgBox(
        _I18NMessageBox.error(), _I18NDlgLogin.error(),
        null, 300, 110, MessageBox.OK_OPT);

    txtPass.setText("");
  }

  // --------------------------------------------------------------------------------

  public void addActionListener(ActionListener listener) {
    eventListenerList.addListener(ActionListener.class, listener);
  }

  // --------------------------------------------------------------------------------

  protected void fireActionEvent(ActionEvent evt) {
    EventListener[] eventListeners =
        eventListenerList.getListeners(ActionListener.class);

    for (int i = 0; i < eventListeners.length; i++) {
      ActionListener listener = (ActionListener) eventListeners[i];
      listener.actionPerformed(evt);
    }
  }
}
