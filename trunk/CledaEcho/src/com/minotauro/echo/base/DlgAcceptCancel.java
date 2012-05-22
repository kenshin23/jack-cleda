/*
 * Created on 15/02/2007
 */
package com.minotauro.echo.base;

import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Session;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.gui.DlgValidation;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.echo.validator.base.ValidatorList;

/** 
 * @author Alejandro Salas 
 */
public class DlgAcceptCancel extends DlgBorderBase {

  // --------------------------------------------------------------------------------

  protected AcceptCancelProxy acceptCancelProxy = //
  new AcceptCancelProxy(getEventListenerList());

  // --------------------------------------------------------------------------------

  protected ValidatorList validatorList = new ValidatorList();

  // --------------------------------------------------------------------------------

  protected Button btnAccept;

  protected Button btnCancel;

  // --------------------------------------------------------------------------------

  public DlgAcceptCancel() {
    // empty
  }

  // --------------------------------------------------------------------------------

  public DlgAcceptCancel(String title, int w, int h) {
    init(title, w, h);
  }

  // --------------------------------------------------------------------------------

  public void init(String title, int w, int h) {
    setTitle(title);
    setModal(true);

    setW(new Extent(w));
    setH(new Extent(h));

    initGUI();
  }

  // --------------------------------------------------------------------------------

  private void initGUI() {
    colBot.setInsets(new Insets(6));
    colBot.setCellSpacing(new Extent(5));

    Row row = new Row();
    row.setCellSpacing(new Extent(5));
    row.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    colBot.add(row);

    // ----------------------------------------
    //  Ok button
    // ----------------------------------------

    btnAccept = new Button(_I18NMessageBox.ok());
    btnAccept.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnAccept.setWidth(new Extent(80));

    btnAccept.setStyleName(GUIStyles.DEFAULT);

    btnAccept.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAcceptClicked(evt);
      }
    });
    row.add(btnAccept);

    // ----------------------------------------
    // Cancel button
    // ----------------------------------------

    btnCancel = new Button(_I18NMessageBox.ca());
    btnCancel.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnCancel.setWidth(new Extent(80));

    btnCancel.setStyleName(GUIStyles.DEFAULT);

    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(evt);
      }
    });
    row.add(btnCancel);
  };

  // --------------------------------------------------------------------------------
  // TODO: Check if validation is ok and according to new way
  // --------------------------------------------------------------------------------

  protected boolean validateInt(Session session) {
    List<String> msgList = validatorList.validateAll(null, null);

    if (!CollectionUtils.isEmpty(msgList)) {
      DlgValidation dlgValidation = new DlgValidation(msgList);
      BaseAppInstance.getDesktop().addForm(
          null, dlgValidation, ProcessContextMode.IGNORE);

      return false;
    }

    msgList = validateGUI(session);

    if (!CollectionUtils.isEmpty(msgList)) {
      DlgValidation dlgValidation = new DlgValidation(msgList);
      BaseAppInstance.getDesktop().addForm(
          null, dlgValidation, ProcessContextMode.IGNORE);

      return false;
    }

    return true;
  }

  // --------------------------------------------------------------------------------

  protected <T extends Object> List<T> validateGUI(Session session) {
    return null;
  }

  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked(ActionEvent evt) {
    if (!validateInt(null)) {
      return;
    }

    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.ACCEPT);
    userClose();
  }

  // --------------------------------------------------------------------------------

  protected void btnCancelClicked(ActionEvent evt) {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.CANCEL);
    userClose();
  }

  // --------------------------------------------------------------------------------

  public AcceptCancelProxy getAcceptCancelProxy() {
    return acceptCancelProxy;
  }

  public void setAcceptCancelProxy(AcceptCancelProxy acceptCancelProxy) {
    this.acceptCancelProxy = acceptCancelProxy;
  }
}
