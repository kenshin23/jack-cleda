/*
 * Created on 11/12/2007
 */
package com.minotauro.echo.cleda.wizard;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmWizardBase extends DlgBorderBase {

  protected List<PnlWizardBase> pnlWizardBaseList =
      new ArrayList<PnlWizardBase>();

  protected int currStep = 0;

  protected Button btnPrev;
  protected Button btnNext;
  protected Button btnFnsh;

  protected String title;

  // --------------------------------------------------------------------------------

  protected Object data;

  // --------------------------------------------------------------------------------

  public FrmWizardBase(String title) {
    this.title = title;

    setW(new Extent(500));
    setH(new Extent(330));
  }

  // --------------------------------------------------------------------------------

  public void initAll() throws Exception {
    data = initData();
    
    initGUI();
    initGUIInt();
  }

  // --------------------------------------------------------------------------------

  protected abstract void initGUI();

  // --------------------------------------------------------------------------------

  protected void initGUIInt() {
    splTop.setSeparatorPosition(new Extent(0));
    splMid.setSeparatorPosition(new Extent(50));

    colMid.add(pnlWizardBaseList.get(currStep));

    // ----------------------------------------
    // Bot Component
    // ----------------------------------------

    Component botComponent = initBotComponent();

    if (botComponent != null) {
      colBot.add(botComponent);
    } else {
      splMid.setSeparatorPosition(new Extent(0));
    }

    // ----------------------------------------

    enableBotComponent();

    updateTitle();
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnPrev() {
    Button ret = new Button(_I18NFrmWizardBase.prev());
    ret.setWidth(new Extent(80));
    ret.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnPrevClicked();
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnNext() {
    Button ret = new Button(_I18NFrmWizardBase.next());
    ret.setWidth(new Extent(80));
    ret.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnNextClicked();
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnFnsh() {
    Button ret = new Button(_I18NFrmWizardBase.fnsh());
    ret.setWidth(new Extent(80));
    ret.setAlignment(new Alignment(Alignment.CENTER, Alignment.DEFAULT));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnFnshClicked();
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(lft, PlaceHolder.LFT);

    // ----------------------------------------

    Row cnt = new Row();
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));
    cnt.setAlignment(Alignment.ALIGN_CENTER);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    // ----------------------------------------

    Row rgh = new Row();
    rgh.setInsets(new Insets(5, 5, 5, 5));
    rgh.setCellSpacing(new Extent(5));
    rgh.setAlignment(Alignment.ALIGN_RIGHT);

    rgh.add(btnPrev = getBtnPrev());
    rgh.add(btnNext = getBtnNext());
    rgh.add(btnFnsh = getBtnFnsh());

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------

  protected void enableBotComponent() {
    int last = pnlWizardBaseList.size() - 1;

    btnPrev.setEnabled(currStep > 0);
    btnNext.setEnabled(currStep < last);

    btnFnsh.setEnabled(currStep == last);
  }

  // --------------------------------------------------------------------------------

  protected void updateTitle() {
    StringBuffer strbuf = new StringBuffer();

    if (title != null) {
      strbuf.append(title);
      strbuf.append(" - ");
    }

    PnlWizardBase pnlWizardBase =
        pnlWizardBaseList.get(currStep);

    if (pnlWizardBase.getTitle() != null) {
      strbuf.append(pnlWizardBase.getTitle());
      strbuf.append(" ");
    }

    strbuf.append("(");
    strbuf.append(currStep + 1);
    strbuf.append(" / ");
    strbuf.append(pnlWizardBaseList.size());
    strbuf.append(")");

    setTitle(strbuf.toString());
  }

  // --------------------------------------------------------------------------------

  protected void btnPrevClicked() {
    PnlWizardBase pnlWizardBase = pnlWizardBaseList.get(currStep);

    btnPrevNextClicked(
        pnlWizardBase, pnlWizardBase.btnPrevClicked(), -1);
  }

  // --------------------------------------------------------------------------------

  protected void btnNextClicked() {
    PnlWizardBase pnlWizardBase = pnlWizardBaseList.get(currStep);

    if (!pnlWizardBase.validateGUIInt(null, null)) {
      return;
    }

    btnPrevNextClicked(
        pnlWizardBase, pnlWizardBase.btnNextClicked(), 1);
  }

  // --------------------------------------------------------------------------------

  protected void btnFnshClicked() {
    PnlWizardBase pnlWizardBase = pnlWizardBaseList.get(currStep);

    if (!pnlWizardBase.validateGUIInt(null, null)) {
      return;
    }

    for (PnlWizardBase currPnlWizardBase : pnlWizardBaseList) {
      currPnlWizardBase.updateDataFromGUIInt();
    }

    pnlWizardBase.btnFnshClicked();

    doFnshClicked(pnlWizardBase);
  }

  // --------------------------------------------------------------------------------

  protected abstract void doFnshClicked(PnlWizardBase pnlWizardBase);

  // --------------------------------------------------------------------------------

  protected String getFinalMessage() {
    return null;
  }

  // --------------------------------------------------------------------------------

  protected void btnPrevNextClicked(
      PnlWizardBase pnlWizardBase, int nextStep, int defaultStep) {

    int fromStep = currStep;

    if (nextStep == -1) {
      currStep = currStep + defaultStep;
    } else {
      currStep = nextStep;
    }

    pnlWizardBase = pnlWizardBaseList.get(currStep);
    pnlWizardBase.initPanel(fromStep);

    colMid.removeAll();
    colMid.add(pnlWizardBase);

    enableBotComponent();

    updateTitle();
  }

  // --------------------------------------------------------------------------------

  public Object getData() {
    return data;
  }

  // --------------------------------------------------------------------------------

  protected abstract Object initData();
}
