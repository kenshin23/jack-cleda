/*
 * Created on 10/09/2011
 */
package com.minotauro.echo.cleda.list.var;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.WindowPaneEvent;
import nextapp.echo.app.event.WindowPaneListener;

import com.minotauro.base.model.MBase;
import com.minotauro.cleda.util.CledaCollectionUtil;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.event.TableSelModelEvent;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListJointMultBase extends FrmListJointBase {

  public FrmListJointMultBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected TableSelModel initTableSelModel() {
    TableSelModel ret = super.initTableSelModel();
    ret.setMultiple(true);
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void tableSelChanged(TableSelModelEvent evt) {

    // ----------------------------------------
    // Checks if sel comes mult or sing
    // ----------------------------------------

    if (evt.getElement() instanceof Collection<?>) {
      selectMult(evt.isSelected(),
          (Collection<?>) evt.getElement());
    } else {
      selectSing(evt.isSelected(),
          /*            */evt.getElement());
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectMult(boolean selected,
      Collection<?> items) {

    if (selected) {
      handleQueue( //
      (List<Object>) CledaCollectionUtil.createCollection(
          ArrayList.class, items));
    } else {
      for (Object object : items) {
        jointModel.del((MBase) object);
      }

      loadTable();
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectSing(boolean selected,
      Object item) {

    selectMult(selected,
        CledaCollectionUtil.createCollection(
            ArrayList.class, item));
  }

  // --------------------------------------------------------------------------------

  protected void handleQueue(final List<Object> queue) {
    try {
      if (frmEditRelClass == null) {
        failsafeHandleQueueNoEdit(queue);
      } else {
        failsafeHandleQueueDoEdit(queue);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeHandleQueueNoEdit(final List<Object> queue)
      throws Exception {

    for (Object object : queue) {
      jointModel.add((MBase) object);
    }

    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected void failsafeHandleQueueDoEdit(final List<Object> queue)
      throws Exception {

    final MBase rgh = (MBase) queue.remove(0);
    final MBase mid = (MBase) jointModel.add(rgh);

    FrmEditBase frmEditBase = frmEditRelClass.newInstance();

    // ----------------------------------------
    // Recursively handle queue
    // ----------------------------------------

    frmEditBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelAdapter() {

          public void btnCancelClicked(ActionEvent evt) {
            table.getTableSelModel().getSelectedSet().remove(rgh);

            jointModel.del(rgh);

            loadTable();
          };
        });

    BaseAppInstance.getDesktop().addForm(
        this, frmEditBase, ProcessContextMode.PARENT);

    frmEditBase.init(mid, EnumEditMode.UPDATE, EnumDataMode.INMEMORY);

    // -----------------------------------------------------
    // This needs to go here to add the listener after init
    // -----------------------------------------------------

    frmEditBase.addWindowPaneListener(new WindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent e) {
        if (!queue.isEmpty()) {
          handleQueue(queue);
        } else {
          loadTable();
        }
      }
    });
  }
}
