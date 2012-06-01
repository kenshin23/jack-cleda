/*
 * Created on 16/08/2011
 */
package com.minotauro.i18n.util;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

import com.minotauro.i18n.base.MessageException;

/**
 * @author Demián Gutierrez
 */
public class ReflectionI18N {

  private ReflectionI18N() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  private static Class<?>[] buildClassFromObjectArray(Object[] args) {
    if (args == null || args.length == 0) {
      return null;
    }

    Class<?>[] ret = new Class<?>[args.length];

    for (int i = 0; i < args.length; i++) {
      ret[i] = Object.class;
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static String i18n(String i18nCls, String i18nKey, Object[] args) {

    if (StringUtils.isBlank(i18nCls) || StringUtils.isBlank(i18nKey)) {
      throw new IllegalStateException(
          "StringUtils.isBlank(i18nCls) || StringUtils.isBlank(i18nKey) :"
              + i18nCls + "/" + i18nKey);
    }

    try {
      Method method = Class.forName(i18nCls).getMethod( //
          i18nKey, buildClassFromObjectArray(args));

      return method.invoke(null, args).toString();
    } catch (Exception e) {
      throw new MessageException(i18nCls + "/" + i18nKey, e);
    }
  }

  // --------------------------------------------------------------------------------

  public static String i18n(String i18nCls, String i18nKey) {
    return i18n(i18nCls, i18nKey, null);
  }
}
