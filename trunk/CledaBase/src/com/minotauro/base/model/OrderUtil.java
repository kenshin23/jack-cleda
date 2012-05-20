/*
 * Created on 03/05/2006
 */
package com.minotauro.base.model;

import java.util.Iterator;
import java.util.List;

/**
 * @author Alejandro Salas
 */
public class OrderUtil {

  private OrderUtil() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // TODO: move to MOrder
  // --------------------------------------------------------------------------------

  @Deprecated
  public static void orderList(List<MOrder> col) {
    Iterator<MOrder> itt = col.iterator();

    for (int i = 0; itt.hasNext(); i++) {
      MOrder order = itt.next();
      order.setOrder(i);
    }
  }
}
