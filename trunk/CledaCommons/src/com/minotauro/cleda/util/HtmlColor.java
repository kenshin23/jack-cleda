/*
 * Created on 12/01/2007
 */
package com.minotauro.cleda.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Demián Gutierrez
 */
public class HtmlColor {

  private static final Pattern pattern = Pattern.compile("#([0-9]{2})([0-9]{2})([0-9]{2})");

  // --------------------------------------------------------------------------------

  private HtmlColor() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static int[] htmlColorToIntRGBArray(String str) //
      throws ParseException {

    if (str == null) {
      throw new IllegalArgumentException();
    }

    str = str.trim();

    Matcher matcher = pattern.matcher(str);

    if (!matcher.matches()) {
      throw new IllegalArgumentException();
    }

    int[] ret = new int[3];

    ret[0] = Integer.parseInt(matcher.group(0), 16);
    ret[1] = Integer.parseInt(matcher.group(1), 16);
    ret[2] = Integer.parseInt(matcher.group(2), 16);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static String intRGBArrayToHtmlColor(int r, int g, int b) {
    String ret = "#{0}{1}{2}";

    String rStr = Integer.toHexString(r);
    String gStr = Integer.toHexString(g);
    String bStr = Integer.toHexString(b);

    rStr = rStr.length() == 1 ? "0" + rStr : rStr;
    gStr = gStr.length() == 1 ? "0" + gStr : gStr;
    bStr = bStr.length() == 1 ? "0" + bStr : bStr;

    ret = MessageFormat.format(ret, new Object[]{rStr, gStr, bStr});

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public static String intRGBArrayToHtmlColor(int[] rgb) {
    if (rgb == null || rgb.length != 3) {
      throw new IllegalArgumentException();
    }

    return intRGBArrayToHtmlColor(rgb[0], rgb[1], rgb[2]);
  }
}
