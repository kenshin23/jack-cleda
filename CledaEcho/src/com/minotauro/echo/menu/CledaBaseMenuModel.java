/*
 * Created on 16/08/2011
 */
package com.minotauro.echo.menu;

import nextapp.echo.app.ImageReference;
import nextapp.echo.extras.app.menu.OptionModel;

import org.apache.commons.lang.StringUtils;

import com.minotauro.i18n.util.ReflectionI18N;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class CledaBaseMenuModel implements OptionModel {

  protected MMenu menu;

  // --------------------------------------------------------------------------------

  public CledaBaseMenuModel(MMenu menu) {
    this.menu = menu;
  }

  // --------------------------------------------------------------------------------

  public MMenu getMenu() {
    return menu;
  }

  // --------------------------------------------------------------------------------

  @Override
  public String getId() {
    return menu != null ? menu.getRoute() : "";
  }

  // --------------------------------------------------------------------------------

  @Override
  public ImageReference getIcon() {
    return null;
  }

  // --------------------------------------------------------------------------------

  @Override
  public String getText() {
    if (menu == null) {
      return StringUtils.EMPTY;
    }

    if (StringUtils.isBlank(menu.getI18nCls()) &&
        StringUtils.isBlank(menu.getI18nKey())) {
      return menu.getRoute();
    }

    return ReflectionI18N.i18n( //
        menu.getI18nCls(), menu.getI18nKey());
  }
}
