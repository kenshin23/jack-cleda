/*
 * Created on 16/08/2011
 */
package com.minotauro.echo.menu;

import java.util.List;
import java.util.regex.Pattern;

import nextapp.echo.extras.app.menu.ItemModel;
import nextapp.echo.extras.app.menu.MenuModel;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.menu.model.MMenu;
import com.minotauro.menu.model.MMenu.Scope;
import com.minotauro.menu.model._PropMMenu;
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
public class MenuBuilder {

  public MenuBuilder() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public CledaEntryModel getItemById(CledaEntryModel parent, String path) {
    for (int i = 0; i < parent.getItemCount(); i++) {
      ItemModel itemModel = parent.getItem(i);

      if (itemModel instanceof CledaEntryModel) {
        CledaEntryModel cledaMenuModel = (CledaEntryModel) itemModel;

        if (path.equals(cledaMenuModel.getMenu().getRoute())) {
          return (CledaEntryModel) parent.getItem(i);
        }
      }
    }

    return null;
  }

  // --------------------------------------------------------------------------------

  private String getRouteFragment(String[] pathArray, int index) {
    StringBuffer ret = new StringBuffer();

    ret.append(pathArray[0]);

    for (int i = 1; i <= index && i < pathArray.length; i++) {
      ret.append("/");
      ret.append(pathArray[i]);
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  private List<MMenu> listMenu(Scope scope) {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    Criteria criteria = session.createCriteria(MMenu.class).
        add(Restrictions.disjunction().
            add(Restrictions.isNull(_PropMMenu.SCOPE)).
            add(Restrictions.eq(/**/_PropMMenu.SCOPE, Scope.ALL)).
            add(Restrictions.eq(/**/_PropMMenu.SCOPE, scope)));

    criteria.addOrder(Order.asc(_PropMMenu.ROUTE));

    List<MMenu> ret = criteria.list();

    session.getTransaction().commit();
    session.close();

    return ret;
  }

  // --------------------------------------------------------------------------------

  public MenuModel build(MUser user, Scope scope) {
    CledaEntryModel root = new CledaEntryModel(null);

    Pattern p = Pattern.compile("\\/");

    List<MMenu> menuList = listMenu(scope);

    for (MMenu menu : menuList) {
      String[] routeArray = p.split(menu.getRoute());

      CledaEntryModel prev = root;

      for (int i = 0; i < routeArray.length; i++) {
        if (i + 1 == routeArray.length) {
          ItemModel itemModel;

          switch (menu.getType()) {
            case ENTRY :
            case GROUP :
              itemModel = new CledaEntryModel(menu);
              break;
            case EVENT :
              itemModel = new CledaEventModel(menu);
              break;
            default :
              throw new RuntimeException(
                  "impossible: " + menu.getType());
          }

          prev.addItem(itemModel);
        } else {
          prev = getItemById(prev, getRouteFragment(routeArray, i));

          if (prev == null) {
            throw new IllegalStateException( //
                "route: " + menu.getRoute());
          }
        }
      } // routeArray
    } // menuList

    // ----------------------------------------

    root.clean(user);

    return root;
  }
}
