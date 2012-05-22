package com.minotauro.echo.util;

import nextapp.echo.app.Color;

public class ELookAndFeel implements EColorScheme {

  // XXX: MEMOX
  public static final Color HIGHLIGHT_COLOR = new Color(255, 243, 194);
  public static final Color BACKGROUND_COLOR = new Color(114, 136, 173);
  public static final Color LIGHT_BACKGROUND_COLOR = new Color(244, 250, 250);
  public static final Color ROLLOVER_COLOR = new Color(94, 116, 153);
  public static final Color DISABLED_FOREGROUND = new Color(60, 60, 60);
  public static final Color DISABLED_BACKGROUND = new Color(235, 235, 235);

  public static final Color EXP_SECTION_SELECT = new Color(180, 210, 225);
  public static final Color EXP_SECTION_CREATE = new Color(66, 212, 66);
  public static final Color EXP_SECTION_UPDATE = new Color(255, 186, 131);

  public static final Color DOC_SECTION = new Color(255, 186, 131);

  public Color getBackgroundClear() {
    return LIGHT_BACKGROUND_COLOR;
  }

  public Color getBackgroundDark() {
    return BACKGROUND_COLOR;
  }

  public Color getBackgroundDark2() {
    return ROLLOVER_COLOR;
  }

  public Color getHighlight() {
    return HIGHLIGHT_COLOR;
  }

  public Color getForeground() {
    return Color.WHITE;
  }

  //  public String getFooterFillImage() {
  //    return "img/footerBackground.png";
  //  }

  public String getFooterLogo() {
    return "img/wemakeitsimple.png";
  }

  // XXX: INPRADEM

  //  public static final Color HIGHLIGHT_COLOR = new Color(255, 243, 194);
  //  public static final Color BACKGROUND_COLOR = new Color(42, 36, 163);
  //  public static final Color LIGHT_BACKGROUND_COLOR = new Color(244, 250, 250);
  //  public static final Color ROLLOVER_COLOR = new Color(254, 50, 9);
  //  public static final Color DISABLED_FOREGROUND = new Color(60, 60, 60);
  //  public static final Color DISABLED_BACKGROUND = new Color(235, 235, 235);
  //
  //  public static final Color NOT_IMPLEMENTED_FOREGROUND = Color.LIGHTGRAY;
  //
  //  public static final Color EXP_SECTION_SELECT = new Color(180, 210, 225);
  //  public static final Color EXP_SECTION_CREATE = new Color(66, 212, 66);
  //  public static final Color EXP_SECTION_UPDATE = new Color(255, 186, 131);
  //
  //  public Color getBackgroundClear() {
  //    return LIGHT_BACKGROUND_COLOR;
  //  }
  //
  //  public Color getNotImplementedForeground() {
  //    return NOT_IMPLEMENTED_FOREGROUND;
  //  }
  //
  //  public Color getBackgroundDark() {
  //    return BACKGROUND_COLOR;
  //  }
  //
  //  public Color getBackgroundDark2() {
  //    return ROLLOVER_COLOR;
  //  }
  //
  //  public Color getHighlight() {
  //    return HIGHLIGHT_COLOR;
  //  }
  //
  //  public Color getForeground() {
  ////    return new Color(254, 50, 9);
  //    return Color.WHITE;
  //  }
  //
  //  public String getFooterFillImage() {
  //    return "img/footerBackground.png";
  //  }
  //
  //  public String getFooterLogo() {
  //    return "img/wemakeitsimple.png";
  //  }

}
