/*
 * Created on 16/08/2011
 */
package com.minotauro.echo.menu;

import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class CledaEventModel extends CledaBaseMenuModel {

  public CledaEventModel(MMenu menu) {
    super(menu);

    if (menu == null) {
      throw new IllegalArgumentException(
          "menu == null");
    }
  }
}
