/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Demián Gutierrez
 */
public class RegexpFilter {

  public enum RegexpFilterMode {
    INCLUDE, EXCLUDE;
  }

  // --------------------------------------------------------------------------------

  protected RegexpFilterMode regexpFilterMode;

  protected Pattern pattern;

  // --------------------------------------------------------------------------------

  public RegexpFilter(RegexpFilterMode regexpFilterMode, String regexp) {
    this.regexpFilterMode = regexpFilterMode;
    pattern = Pattern.compile(regexp);
  }

  // --------------------------------------------------------------------------------

  public RegexpFilterMode keep(String value) {
    Matcher matcher = pattern.matcher(value);
    return matcher.matches() ? regexpFilterMode : null;
  }
}
