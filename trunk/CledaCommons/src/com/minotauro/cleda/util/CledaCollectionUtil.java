/*
 * Created on 06/09/2011
 */
package com.minotauro.cleda.util;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * @author Demián Gutierrez
 */
public class CledaCollectionUtil {

  private CledaCollectionUtil() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public static Collection<Object> createCollection(
      Class<?> clazz, Object data) {

    try {
      return failsafeCreateCollection(clazz, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static Collection<Object> failsafeCreateCollection(
      Class<?> clazz, Object data)
      throws Exception {

    Collection<Object> ret = (Collection<Object>) clazz.newInstance();

    /*   */if (data instanceof Collection) {
      ret.addAll((Collection<? extends Object>) data);
    } else if (data instanceof Object[]) {
      CollectionUtils.addAll(ret, (Object[]) data);
    } else {
      ret.add(data);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static List<?> createList(
      Class<?> clazz, Object data) {

    try {
      return failsafeCreateList(clazz, data);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public static List<?> failsafeCreateList(
      Class<?> clazz, Object data)
      throws Exception {

    List<Object> ret = (List<Object>) clazz.newInstance();

    /*   */if (data instanceof Collection) {
      ret.addAll((Collection<? extends Object>) data);
    } else if (data instanceof Object[]) {
      CollectionUtils.addAll(ret, (Object[]) data);
    } else {
      ret.add(data);
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public static void printCollection(PrintStream ps, Collection<Object> col) {
    Iterator<Object> itt = col.iterator();

    while (itt.hasNext()) {
      ps.print(itt.next().toString());

      if (itt.hasNext()) {
        ps.print(" / ");
      }
    }

    ps.println();
  }

  // --------------------------------------------------------------------------------

  public static void main(String[] args) {
    printCollection(System.out, createCollection(ArrayList.class, new Integer(1)));
    System.out.println("****************************************");

    printCollection(System.out, createCollection(ArrayList.class, new Object[]{111, 222, 333}));
    System.out.println("****************************************");

    printCollection(System.out, createCollection(ArrayList.class, new String[]{"1", "2", "3"}));
    System.out.println("****************************************");

    Collection<String> items = new ArrayList<String>();

    items.add("1");
    items.add("2");
    items.add("3");

    printCollection(System.out, createCollection(ArrayList.class, items));
  }
}
