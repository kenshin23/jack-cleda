package com.minotauro.echo.util;

import java.lang.reflect.Method;

import nextapp.echo.app.Extent;

public class Hwh {

  // --------------------------------------------------------------------------------

  public static int getW(Object obj) {
    Class<?> clazz = obj.getClass();

    try {
      Method method = clazz.getMethod("getWidth", new Class<?>[0]);
      return ((Number) method.invoke(obj, new Object[0])).intValue();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void setW(Object obj, Extent ext) {
    Class<?> clazz = obj.getClass();

    try {
      Method method = clazz.getMethod("setWidth", new Class<?>[]{Extent.class});
      method.invoke(obj, new Object[]{ext});
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static int getH(Object obj) {
    Class<?> clazz = obj.getClass();

    try {
      Method method = clazz.getMethod("getHeight", new Class<?>[0]);
      return ((Number) method.invoke(obj, new Object[0])).intValue();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public static void setH(Object obj, Extent ext) {
    Class<?> clazz = obj.getClass();

    try {
      Method method = clazz.getMethod("setHeight", new Class<?>[]{Extent.class});
      method.invoke(obj, new Object[]{ext});
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
