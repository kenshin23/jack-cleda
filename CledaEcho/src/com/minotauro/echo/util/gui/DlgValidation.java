/*
 * Created on 14/04/2007
 */
package com.minotauro.echo.util.gui;

import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.layout.ColumnLayoutData;

import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;

/**
 * @author Demián Gutierrez
 */
public class DlgValidation extends DlgBorderBase {

  protected List<String> msgList;

  // --------------------------------------------------------------------------------

  public DlgValidation(List<String> msgList) {
    this.msgList = msgList;

    setW(new Extent(450));
    setH(new Extent(250));

    setTitle(_I18NDlgValidation.title());
    setModal(true);

    initGUI();
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    setColMidOverflow(true);

    splTop.setSeparatorPosition(new Extent(0));
    splMid.setSeparatorPosition(new Extent(50));

    Component midComponent = initMidComponent();
    colMid.add(midComponent);

    Component botComponent = initBotComponent();
    colBot.add(botComponent);
  }

  // --------------------------------------------------------------------------------

  protected Component initMidComponent() {
    Column ret = new Column();

    ret.setInsets(new Insets(5, 5, 5, 5));
    ret.setCellSpacing(new Extent(3));

    for (String msg : msgList) {
      Button btnMsg = new Button(msg);

      btnMsg.setStyleName(GUIStyles.VALIDATION_LABEL);

      ColumnLayoutData cld = new ColumnLayoutData();
      cld.setAlignment(Alignment.ALIGN_CENTER);
      btnMsg.setLayoutData(cld);
      ret.add(btnMsg);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    Row cnt = new Row();

    cnt.setAlignment(Alignment.ALIGN_CENTER);
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));

    Button btnAccept = new Button(_I18NDlgValidation.accept());
    btnAccept.setAlignment(Alignment.ALIGN_CENTER);
    btnAccept.setWidth(new Extent(80));

    btnAccept.setStyleName(GUIStyles.DEFAULT);

    btnAccept.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        userClose();
      }
    });
    cnt.add(btnAccept);

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    return lftCntRghLayout;
  }
}
