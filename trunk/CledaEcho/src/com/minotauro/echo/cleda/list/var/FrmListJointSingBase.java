/*
 * Created on 10/09/2011
 */
package com.minotauro.echo.cleda.list.var;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.WindowPaneEvent;
import nextapp.echo.app.event.WindowPaneListener;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelListener;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.event.TableSelModelEvent;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListJointSingBase extends FrmListJointBase {

  protected MBase prevRgh;
  protected MBase prevMid;

  // --------------------------------------------------------------------------------

  public FrmListJointSingBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected TableSelModel initTableSelModel() {
    TableSelModel ret = super.initTableSelModel();
    ret.setMultiple(false);
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected void tableSelChanged(TableSelModelEvent evt) {

    // ----------------------------------------
    // Checks sel element
    // ----------------------------------------

    if (evt.getElement() instanceof Collection<?>) {
      Collection<?> sel = (Collection<?>) evt.getElement();

      if (evt.isSelected()) {
        throw new IllegalStateException(
            "evt.getElement() instanceof Collection<?> && " +
                "evt.isSelected()");
      }

      if (sel.size() > 1) {
        throw new IllegalStateException(
            "evt.getElement() instanceof Collection<?> && " +
                "sel.size() > 1");
      }

      Iterator<?> itt = sel.iterator();

      if (itt.hasNext()) {
        selectSing(evt.isSelected(),
            (MBase) sel.iterator().next());
      }
    } else {
      if (!(evt.getElement() instanceof MBase)) {
        throw new IllegalStateException(
            "!(evt.getElement() instanceof MBase)");
      }

      selectSing(evt.isSelected(),
          (MBase) evt.getElement());
    }
  }

  // --------------------------------------------------------------------------------

  protected void selectSing(boolean selected, MBase currRgh) {
    if (selected) {
      handleQueue(currRgh);
    } else {
      prevMid = jointModel.del(currRgh);
      prevRgh = /*           */currRgh;
    }
  }

  // --------------------------------------------------------------------------------

  protected void handleQueue(MBase currRgh) {
    try {
      if (frmEditRelClass == null) {
        failsafeHandleQueueNoEdit(currRgh);
      } else {
        failsafeHandleQueueDoEdit(currRgh);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeHandleQueueNoEdit(final MBase currRgh)
      throws Exception {

    jointModel.add(currRgh);
    jointModel.del(prevRgh);

    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected void failsafeHandleQueueDoEdit(final MBase currRgh)
      throws Exception {

    MBase currMid = (MBase) jointModel.add(currRgh);

    FrmEditBase frmEditBase = frmEditRelClass.newInstance();

    frmEditBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelListener() {

          public void btnAcceptClicked(ActionEvent evt) {
            jointModel.del(prevRgh);

            prevRgh = null;
            prevMid = null;
          }

          public void btnCancelClicked(ActionEvent evt) {
            Set<Object> set = table.getTableSelModel().getSelectedSet();

            set.add/*   */(prevRgh);
            set.remove/**/(currRgh);

            jointModel.add(prevRgh, prevMid);
            jointModel.del(currRgh);

            loadTable();
          };
        });

    BaseAppInstance.getDesktop().addForm(
        this, frmEditBase, ProcessContextMode.PARENT);

    frmEditBase.init(currMid, EnumEditMode.UPDATE, EnumDataMode.INMEMORY);

    // -----------------------------------------------------
    // This needs to go here to add the listener after init
    // -----------------------------------------------------

    frmEditBase.addWindowPaneListener(new WindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent e) {
        loadTable();
      }
    });
  }
}
