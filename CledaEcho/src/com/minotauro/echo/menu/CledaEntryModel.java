/*
 * Created on 16/08/2011
 */
package com.minotauro.echo.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import nextapp.echo.extras.app.menu.ItemModel;
import nextapp.echo.extras.app.menu.MenuModel;
import nextapp.echo.extras.app.menu.SeparatorModel;

import org.apache.commons.lang.StringUtils;

import com.minotauro.cleda.util.CledaStringUtils;
import com.minotauro.menu.model.MMenu;
import com.minotauro.menu.model.MMenu.Type;
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class CledaEntryModel extends CledaBaseMenuModel implements MenuModel {

  private List<ItemModel> itemList =
      new ArrayList<ItemModel>();

  // --------------------------------------------------------------------------------

  public CledaEntryModel(MMenu menu) {
    super(menu);
  }

  // --------------------------------------------------------------------------------

  @Override
  public ItemModel getItem(int index) {
    return itemList.get(index);
  }

  // --------------------------------------------------------------------------------

  @Override
  public int getItemCount() {
    return itemList.size();
  }

  // --------------------------------------------------------------------------------

  public void addItem(ItemModel itemModel) {
    if (itemModel == null) {
      throw new IllegalArgumentException("itemModel == null");
    }

    itemList.add(itemModel);
  }

  // --------------------------------------------------------------------------------

  public CledaEventModel findCledaEventModelById(String id) {

    for (ItemModel itemModel : itemList) {

      // ----------------------------------------
      // CledaEntryModel
      // ----------------------------------------

      if (itemModel instanceof CledaEntryModel) {
        CledaEntryModel cledaEntryModel = (CledaEntryModel) itemModel;

        CledaEventModel cledaEventModel =
            cledaEntryModel.findCledaEventModelById(id);

        if (cledaEventModel != null) {
          return cledaEventModel;
        }
      }

      // ----------------------------------------
      // CledaEventModel
      // ----------------------------------------

      if (itemModel instanceof CledaEventModel) {
        CledaEventModel cledaEventModel = (CledaEventModel) itemModel;

        if (StringUtils.equals(id, cledaEventModel.getId())) {
          return cledaEventModel;
        }
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  public void clean(MUser user) {

    Comparator<ItemModel> cmp = new Comparator<ItemModel>() {
      @Override
      public int compare(ItemModel im1, ItemModel im2) {

        if (!(im1 instanceof CledaBaseMenuModel) ||
            !(im2 instanceof CledaBaseMenuModel)) {
          return 0;
        }

        CledaBaseMenuModel cbmm1 = (CledaBaseMenuModel) im1;
        CledaBaseMenuModel cbmm2 = (CledaBaseMenuModel) im2;

        if (cbmm1.getMenu().getOrder() == cbmm2.getMenu().getOrder()) {
          return CledaStringUtils.failsafeCompareTo(
              cbmm1.getText(), cbmm2.getText());
        }

        return cbmm1.getMenu().getOrder() - cbmm2.getMenu().getOrder();
      }
    };

    Collections.sort(itemList, cmp);

    // ----------------------------------------
    // clean group
    // ----------------------------------------

    List<ItemModel> newItemList = new ArrayList<ItemModel>();

    for (ItemModel itemModel : itemList) {
      cleanCledaEntryModel(newItemList, itemModel, user);
      cleanCledaEventModel(newItemList, itemModel, user);
    }

    itemList = newItemList;
  }

  // --------------------------------------------------------------------------------

  protected boolean isLastItemSeparatorModel(List<ItemModel> newItemList) {
    if (newItemList.isEmpty()) {
      return false;
    }

    ItemModel lastItem = newItemList.get(newItemList.size() - 1);

    if (lastItem instanceof SeparatorModel) {
      return true;
    }

    return false;
  }

  // --------------------------------------------------------------------------------

  protected void cleanCledaEntryModel(
      List<ItemModel> newItemList, ItemModel itemModel, MUser user) {

    // ----------------------------------------
    // CledaEntryModel
    // ----------------------------------------

    if (!(itemModel instanceof CledaEntryModel)) {
      return;
    }

    CledaEntryModel cledaEntryModel = (CledaEntryModel) itemModel;
    cledaEntryModel.clean(user);

    if (cledaEntryModel.getItemCount() == 0) {
      return;
    }

    if (cledaEntryModel.getMenu().getType() == Type.GROUP) {

      if (!isLastItemSeparatorModel(newItemList)) {
        newItemList.add(new SeparatorModel());
      }

      for (int i = 0; i < cledaEntryModel.getItemCount(); i++) {
        newItemList.add(cledaEntryModel.getItem(i));
      }

      newItemList.add(new SeparatorModel());
    } else {
      newItemList.add(cledaEntryModel);
    }
  }

  // --------------------------------------------------------------------------------

  protected void cleanCledaEventModel(
      List<ItemModel> newItemList, ItemModel itemModel, MUser user) {

    // ----------------------------------------
    // CledaEventModel
    // ----------------------------------------

    if (!(itemModel instanceof CledaEventModel)) {
      return;
    }

    CledaEventModel cledaEventModel = (CledaEventModel) itemModel;

    // ----------------------------------------

    MPriv priv = cledaEventModel.getMenu().getPrivRef();

    if (priv != null && !userHasPrivIgnoreNull(user, priv)) {
      return;
    }

    // ----------------------------------------

    MRole role = cledaEventModel.getMenu().getRoleRef();

    if (role != null && !userHasRoleIgnoreNull(user, role)) {
      return;
    }

    // ----------------------------------------

    newItemList.add(cledaEventModel);
  }

  // --------------------------------------------------------------------------------

  protected boolean userHasPrivIgnoreNull(MUser user, MPriv priv) {
    return user != null ? user.hasPriv(priv.getName()) : true;
  }

  // --------------------------------------------------------------------------------

  protected boolean userHasRoleIgnoreNull(MUser user, MRole role) {
    return user != null ? user.hasRole(role.getName()) : true;
  }
}
