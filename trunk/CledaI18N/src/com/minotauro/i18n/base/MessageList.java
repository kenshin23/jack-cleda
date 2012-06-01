/*
 * Created on 17/10/2006
 */
package com.minotauro.i18n.base;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

/**
 * @author Demián Gutierrez
 */
public class MessageList {

  protected List<Properties> propertiesList = new ArrayList<Properties>();

  // --------------------------------------------------------------------------------

  public MessageList() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public List<Properties> getPropertiesList() {
    return propertiesList;
  }

  // --------------------------------------------------------------------------------

  public void load(InputStream is) throws IOException {
    if (is == null) {
      throw new NullPointerException();
    }

    Properties properties = new Properties();
    properties.load(is);

    propertiesList.add(properties);
  }

  // --------------------------------------------------------------------------------

  public boolean test() {
    Properties test = new Properties();

    int size = -1;

    for (Properties curr : propertiesList) {
      if (size == -1) {
        size = curr.size();
      }

      if (size != curr.size()) {
        return false;
      }

      test.putAll(curr);

      if (size != test.size()) {
        return false;
      }
    }

    return true;
  }

  // --------------------------------------------------------------------------------

  public String keyToJava(String key) {
    StringBuffer strbuf = new StringBuffer();

    boolean toUpperCase = false;

    for (int i = 0; i < key.length(); i++) {
      char currChar = key.charAt(i);

      if (Character.isLetterOrDigit(currChar)) {
        if (toUpperCase) {
          strbuf.append(Character.toUpperCase(currChar));
          toUpperCase = false;
        } else {
          strbuf.append(currChar);
        }
      } else {
        toUpperCase = true;
      }
    }

    String ret = strbuf.toString();

    if (ret.equals("")) {
      throw new IllegalArgumentException("key.equals(\"\")");
    }

    if (Character.isDigit(ret.charAt(0))) {
      throw new IllegalArgumentException("Character.isDigit(ret.charAt(0))");
    }

    return strbuf.toString();
  }

  // --------------------------------------------------------------------------------

  public int countArgs(String key) {
    int ret = 0;

    for (Properties properties : propertiesList) {
      MessageFormat messageFormat = new MessageFormat(properties.getProperty(key));

      int cur = messageFormat.getFormatsByArgumentIndex().length;

      if (cur > ret) {
        ret = cur;
      }
    }

    return ret - 1;
  }

  // --------------------------------------------------------------------------------

  public List<MethodBean> getKeyList() {
    List<MethodBean> ret = new ArrayList<MethodBean>();

    Properties properties = propertiesList.get(0);

    for (Object obj : properties.keySet()) {
      String key = obj.toString();

      MethodBean methodBean = new MethodBean();
      methodBean.setName(keyToJava(key));
      methodBean.setKey(key);
      methodBean.setArgs(countArgs(key));

      ret.add(methodBean);
    }

    Collections.sort(ret, new Comparator<MethodBean>() {
      public int compare(MethodBean obj1, MethodBean obj2) {
        return obj1.getName().compareTo(obj2.getName());
      }
    });

    return ret;
  }
}
