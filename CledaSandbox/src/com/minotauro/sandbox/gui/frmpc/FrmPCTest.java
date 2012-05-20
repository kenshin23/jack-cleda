/*
 * Created on 03/09/2011
 */
package com.minotauro.sandbox.gui.frmpc;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextArea;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.WindowPaneEvent;
import nextapp.echo.app.layout.GridLayoutData;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelListenerMethod;
import com.minotauro.echo.base.AcceptCancelProxy;
import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.Hwh;
import com.minotauro.echo.util.gui.InternalWindowPaneListener;
import com.minotauro.echo.util.gui._I18NMessageBox;

/**
 * @author Demián Gutierrez
 */
public class FrmPCTest extends DlgBorderBase {

  // --------------------------------------------------------------------------------

  protected InternalWindowPaneListener cancelOnCloseListener;

  protected AcceptCancelProxy acceptCancelProxy = //
  new AcceptCancelProxy(getEventListenerList());

  // --------------------------------------------------------------------------------

  protected Button btnAccept;

  protected Button btnCancel;

  // --------------------------------------------------------------------------------

  public FrmPCTest() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void initForm() {
    setTitle(this.toString());
    setModal(false);

    setW(new Extent(500));
    setH(new Extent(400));

    initGUI();
  }

  // --------------------------------------------------------------------------------

  private void initGUI() {
    colBot.setInsets(new Insets(6));
    colBot.setCellSpacing(new Extent(5));

    colMid.add(initMidComponent());
    colBot.add(initBotComponent());

    addCancelOnCloseListener();
  }

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    Row ret = new Row();
    ret.setCellSpacing(new Extent(5));
    ret.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));

    // ----------------------------------------
    //  Accept button
    // ----------------------------------------

    btnAccept = new Button(_I18NMessageBox.ok());
    btnAccept.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnAccept.setWidth(new Extent(80));

    btnAccept.setStyleName(GUIStyles.DEFAULT);

    btnAccept.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAcceptClicked();
      }
    });
    ret.add(btnAccept);

    // ----------------------------------------
    // Cancel button
    // ----------------------------------------

    btnCancel = new Button(_I18NMessageBox.ca());
    btnCancel.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnCancel.setWidth(new Extent(80));

    btnCancel.setStyleName(GUIStyles.DEFAULT);

    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(false);
      }
    });
    ret.add(btnCancel);

    // ----------------------------------------

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Component initMidComponent() {
    GridLayoutData gld;

    Grid ret = new Grid(2);
    ret.setWidth(new Extent(100, Extent.PERCENT));

    // ----------------------------------------

    TextArea txtPC = new TextArea();

    Hwh.setW(txtPC, new Extent(100, Extent.PERCENT));
    Hwh.setH(txtPC, new Extent(200));

    ProcessContext pc =
        BaseAppInstance.getDesktop().getProcessContext(this);

    txtPC.setText(pc.toString());

    gld = new GridLayoutData();
    gld.setColumnSpan(2);
    gld.setAlignment(Alignment.ALIGN_CENTER);
    txtPC.setLayoutData(gld);
    ret.add(txtPC);

    // ----------------------------------------

    Button btnNewPC = new Button("BtnNewPC");
    btnNewPC.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnNewPC.setWidth(new Extent(80));

    btnNewPC.setStyleName(GUIStyles.DEFAULT);

    btnNewPC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnNewPCClicked();
      }
    });
    gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_CENTER);
    btnNewPC.setLayoutData(gld);
    ret.add(btnNewPC);

    // ----------------------------------------

    Button btnProPC = new Button("BtnProPC");
    btnProPC.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));
    btnProPC.setWidth(new Extent(80));

    btnProPC.setStyleName(GUIStyles.DEFAULT);

    btnProPC.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnProPCClicked();
      }
    });
    gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_CENTER);
    btnProPC.setLayoutData(gld);
    ret.add(btnProPC);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void btnNewPCClicked() {

    // ---------------------------------------------------------
    // this is all the handling the form is doing related to PC
    // ---------------------------------------------------------

    HandlerAddPC handlerAddPC = new HandlerAddPC(this, ProcessContextMode.CREATE);
    handlerAddPC.commandClicked(-1);
  }

  // --------------------------------------------------------------------------------

  protected void btnProPCClicked() {

    // ---------------------------------------------------------
    // this is all the handling the form is doing related to PC
    // ---------------------------------------------------------

    HandlerAddPC handlerAddPC = new HandlerAddPC(this, ProcessContextMode.PARENT);
    handlerAddPC.commandClicked(-1);
  }

  // --------------------------------------------------------------------------------

  protected void addCancelOnCloseListener() {

    // ----------------------------------------
    // Close works like a cancel
    // ----------------------------------------

    cancelOnCloseListener = new InternalWindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent e) {
        btnCancelClicked(true);
      }
    };
    addInternalWindowPaneListener(cancelOnCloseListener);
  }

  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked() {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.ACCEPT);
    userClose();
  }

  // --------------------------------------------------------------------------------

  protected void btnCancelClicked(boolean closing) {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.CANCEL);

    if (closing) {
      return;
    }

    delInternalWindowPaneListener(cancelOnCloseListener);

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
