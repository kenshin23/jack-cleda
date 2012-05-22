package com.minotauro.echo.util.gui;

import java.util.EventListener;

import nextapp.echo.app.Color;
import nextapp.echo.app.Extent;
import nextapp.echo.app.FillImage;
import nextapp.echo.app.FillImageBorder;
import nextapp.echo.app.Font;
import nextapp.echo.app.Insets;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.WindowPaneEvent;

import com.minotauro.echo.util.ELookAndFeel;
import com.minotauro.echo.util.ImageReferenceCache;

public abstract class EWindowPane extends WindowPane {

  public EWindowPane() {
    initGUI();
  }

  // --------------------------------------------------------------------------------

  public EWindowPane(String title, Extent w, Extent h) {
    initGUI();

    setStyleName("EWindowPane");

    setTitle(title);

    setW(w);
    setH(h);
  }

  // --------------------------------------------------------------------------------

  protected void addInternalWindowPaneListener(InternalWindowPaneListener listener) {
    getEventListenerList().addListener(
        InternalWindowPaneListener.class, listener);
  }

  protected void delInternalWindowPaneListener(InternalWindowPaneListener listener) {
    getEventListenerList().removeListener(
        InternalWindowPaneListener.class, listener);
  }

  protected EventListener[] getInternalWindowPaneListener() {
    return getEventListenerList().getListeners(
        InternalWindowPaneListener.class);
  }

  protected void fireInternalWindowPaneEvent(WindowPaneEvent evt) {
    EventListener[] eventListeners = getInternalWindowPaneListener();

    for (int i = 0; i < eventListeners.length; i++) {
      InternalWindowPaneListener listener =
          (InternalWindowPaneListener) eventListeners[i];
      listener.windowPaneClosing(evt);
    }
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void fireWindowClosing() {
    WindowPaneEvent evt = new WindowPaneEvent(this);

    fireInternalWindowPaneEvent(evt);

    super.fireWindowClosing();
  }

  // --------------------------------------------------------------------------------

  private void initGUI() {
    setBackground/* */(ELookAndFeel.LIGHT_BACKGROUND_COLOR);
    setIconInsets/* */(new Insets(7, 3, 0, 0));
    setInsets/*     */(new Insets(0, 0, 0, 0));
    setWidth/*      */(new Extent(500));
    setHeight/*     */(new Extent(450));
    setClosable/*   */(true);

    setTitleForeground(Color.WHITE);
    setTitleFont/*  */(new Font(Font.VERDANA, Font.BOLD, new Extent(16)));
    setTitleHeight/**/(new Extent(28));
    setTitleInsets/**/(new Insets(25, 5, 0, 0));

    setTitleBackgroundImage(new FillImage(ImageReferenceCache.getInstance().getImageReference(
        "images/border/TitleBackground.png"), null, null, FillImage.REPEAT_HORIZONTAL));

    FillImageBorder border = new FillImageBorder(null, new Insets(10), new Insets(6));

    // ----------------------------------------
    // OK, I'm crazy... so what???
    // ----------------------------------------

    border.setFillImage(FillImageBorder.TOP_LEFT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderTopLeft.png"),/*    */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.TOP, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderTop.png"),/*        */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.TOP_RIGHT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderTopRight.png"),/*   */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.LEFT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderLeft.png"),/*       */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.RIGHT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderRight.png"),/*      */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.BOTTOM_LEFT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderBottomLeft.png"),/* */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.BOTTOM, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderBottom.png"),/*     */null, null, FillImage.REPEAT));
    border.setFillImage(FillImageBorder.BOTTOM_RIGHT, //
        new FillImage(ImageReferenceCache.getInstance().getImageReference( //
            "images/border/BorderBottomRight.png"),/**/null, null, FillImage.REPEAT));

    setBorder(border);
  }

  // --------------------------------------------------------------------------------

  protected void setW(Extent extent) {
    setWidth/* */(extent);
  }

  protected void setH(Extent extent) {
    setHeight/**/(extent);
  }
}
