/*
 * Created on 14/04/2007
 */
package com.minotauro.echo.base;

import nextapp.echo.app.Column;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Panel;
import nextapp.echo.app.SplitPane;
import nextapp.echo.app.layout.SplitPaneLayoutData;

import com.minotauro.echo.util.gui.EWindowPane;

/**
 * @author Demián Gutierrez
 */
public class DlgBorderBase extends EWindowPane {

  protected boolean colTopOverflow;
  protected boolean colMidOverflow;
  protected boolean colBotOverflow;

  protected SplitPane splTop;
  protected SplitPane splMid;

  protected Column colTop;
  protected Panel colMid;
  protected Column colBot;

  // --------------------------------------------------------------------------------

  public DlgBorderBase() {
    initGUI();
  }

  // --------------------------------------------------------------------------------

  private void initGUI() {
    splTop = new SplitPane(SplitPane.ORIENTATION_VERTICAL_TOP_BOTTOM);
    splTop.setSeparatorHeight(new Extent(1));
    splTop.setSeparatorPosition(new Extent(35));
    // topSplitPane.setResizable(true); // DEBUG

    add(splTop);

    splMid = new SplitPane(SplitPane.ORIENTATION_VERTICAL_BOTTOM_TOP);
    splMid.setSeparatorPosition(new Extent(80));
    splMid.setSeparatorHeight(new Extent(1));
    // midSplitPane.setResizable(true); // DEBUG

    colTop = new Column();
    colTop.setCellSpacing(new Extent(5));

    colBot = new Column();
    colBot.setCellSpacing(new Extent(5));

    colMid = new Panel();
    //colMid.setCellSpacing(new Extent(5));

    rebuild();
  }

  // --------------------------------------------------------------------------------

  protected void rebuild() {
    SplitPaneLayoutData sld;

    splTop.removeAll();
    splMid.removeAll();

    sld = new SplitPaneLayoutData();
    sld.setOverflow(colTopOverflow ? //
        SplitPaneLayoutData.OVERFLOW_SCROLL
        : SplitPaneLayoutData.OVERFLOW_HIDDEN);
    colTop.setLayoutData(sld);
    splTop.add(colTop);
    splTop.add(splMid);

    sld = new SplitPaneLayoutData();
    sld.setOverflow(colBotOverflow ? //
        SplitPaneLayoutData.OVERFLOW_SCROLL
        : SplitPaneLayoutData.OVERFLOW_HIDDEN);
    colBot.setLayoutData(sld);
    splMid.add(colBot);

    sld = new SplitPaneLayoutData();
    sld.setOverflow(colMidOverflow ? //
        SplitPaneLayoutData.OVERFLOW_SCROLL
        : SplitPaneLayoutData.OVERFLOW_HIDDEN);
    colMid.setLayoutData(sld);
    splMid.add(colMid);
  }

  // --------------------------------------------------------------------------------

  public void setColTopOverflow(boolean colTopOverflow) {
    this.colTopOverflow = colTopOverflow;
    rebuild();
  }

  public void setColMidOverflow(boolean colMidOverflow) {
    this.colMidOverflow = colMidOverflow;
    rebuild();
  }

  public void setColBotOverflow(boolean colBotOverflow) {
    this.colBotOverflow = colBotOverflow;
    rebuild();
  }
}
