/*
 * Created on 25/08/2011
 */
package com.minotauro.echo.util.gui;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Label;
import nextapp.echo.app.layout.GridLayoutData;

/**
 * @author Demián Gutierrez
 */
public class LftCntRghLayout extends Grid {

  public enum PlaceHolder {
    LFT, CNT, RGH;
  }

  // --------------------------------------------------------------------------------

  private Component lft = new Label("");
  private Component cnt = new Label("");
  private Component rgh = new Label("");

  // --------------------------------------------------------------------------------

  public LftCntRghLayout() {
    super(3);

    setWidth(new Extent(100, Extent.PERCENT));

    setColumnWidth(0, new Extent(33, Extent.PERCENT));
    setColumnWidth(1, new Extent(34, Extent.PERCENT));
    setColumnWidth(2, new Extent(33, Extent.PERCENT));
  }

  // --------------------------------------------------------------------------------

  public void add(Component c, PlaceHolder p) {
    switch (p) {
      case LFT :
        lft = c;
        break;
      case CNT :
        cnt = c;
        break;
      case RGH :
        rgh = c;
        break;
    }

    rebuild();
  }

  // --------------------------------------------------------------------------------

  private void rebuild() {
    removeAll();

    GridLayoutData gld;

    gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_LEFT);
    lft.setLayoutData(gld);
    add(lft);

    gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_CENTER);
    cnt.setLayoutData(gld);
    add(cnt);

    gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_RIGHT);
    rgh.setLayoutData(gld);
    add(rgh);
  }
}
