/*
 * Created on 13/06/2006
 */
package com.minotauro.cleda.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * @author Demián Gutierrez
 */
public class CSSParser {

  private Map<String, String> cssMap = new HashMap<String, String>();

  // --------------------------------------------------------------------------------

  public CSSParser() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Map<String, String> getCssMap() {
    return cssMap;
  }

  public void setCssMap(Map<String, String> cssMap) {
    this.cssMap = cssMap;
  }

  // --------------------------------------------------------------------------------

  public String getCss() {
    StringBuffer strbuf = new StringBuffer();

    Iterator<Map.Entry<String, String>> itt = cssMap.entrySet().iterator();

    while (itt.hasNext()) {
      Map.Entry<String, String> entry = itt.next();

      if (entry.getValue() == null) {
        continue;
      }

      strbuf.append(entry.getKey());
      strbuf.append(": ");
      strbuf.append(entry.getValue());
      strbuf.append(";");

      if (itt.hasNext()) {
        strbuf.append(" ");
      }
    }

    return strbuf.toString();
  }

  // --------------------------------------------------------------------------------

  public void setCss(String css) {
    css = CledaStringUtils.trim(css, " \r\n\t");

    if (StringUtils.isBlank(css)) {
      return;
    }

    Pattern p1 = Pattern.compile(";");
    Pattern p2 = Pattern.compile(":");

    String[] cssKeyArray = p1.split(css);

    for (int i = 0; i < cssKeyArray.length; i++) {
      String[] cssKey = p2.split(cssKeyArray[i]);

      cssMap.put(cssKey[0], cssKey[1]);
    }
  }
}
