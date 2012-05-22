/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.cleda.list.var;

import java.util.HashSet;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list._I18NFrmListBase;
import com.minotauro.echo.cleda.list.dta.FrmListDtaBase;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.command.CommandStateProxy;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.event.TableSelModelEvent;
import com.minotauro.echo.table.event.TableSelModelListener;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.table.renderer.NestedCellRenderer;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListJointBase extends FrmListDtaBase {

  protected Class<? extends FrmEditBase> frmEditRelClass;

  protected CommandFactory cfcRndEdtRel;
  protected CommandFactory cfcRndVieRel;

  protected EJointModel jointModel;

  // --------------------------------------------------------------------------------

  public FrmListJointBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initAll() {
    cfcRndVieRel = initRndVieRel();
    cfcRndEdtRel = initRndEdtRel();

    super.initAll();
  }

  // --------------------------------------------------------------------------------

  public void init(EnumEditMode editMode, EJointModel jointModel) {

    this.dataMode/*  */= EnumDataMode.DATABASE;;

    this.jointModel/**/= jointModel;
    this.editMode/*  */= editMode;

    initAll();

    TableSelModel tableSelModel = table.getTableSelModel();

    tableSelModel.setSelectedSet(new HashSet<Object>(
        jointModel.getMidMap().keySet()));

    tableSelModel.getTableSelModelEvtProxy().addTableSelModelListener(
        new TableSelModelListener() {
          public void tableSelChanged(TableSelModelEvent evt) {
            FrmListJointBase.this.tableSelChanged(evt);
          }

          public void tableSetChanged(TableSelModelEvent evt) {
            // Empty
          }
        });
  }

  // --------------------------------------------------------------------------------
  // Relation Buttons
  // --------------------------------------------------------------------------------

  protected CommandFactory initRndVieRel() {
    CommandFactory ret = new CommandFactory(
        "images/icons/vie_e_16x16.gif",
        "images/icons/vie_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState(
          ETable table, Object value, int col, int row) {

        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".rel.select") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");

        MBase rgh = (MBase) table.getTableDtaModel().getElementAt(row);
        MBase mid = (MBase) jointModel.get(rgh);

        return privs && mid != null;
      }
    });

    ret.setCommandListener(new CommandListener() {
      public void commandClicked(int row) {
        rndVieRelClicked(row);
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected CommandFactory initRndEdtRel() {
    CommandFactory ret = new CommandFactory(
        "images/icons/edt_e_16x16.gif",
        "images/icons/edt_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState(
          ETable table, Object value, int col, int row) {

        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".rel.update") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");

        MBase rgh = (MBase) table.getTableDtaModel().getElementAt(row);
        MBase mid = (MBase) jointModel.get(rgh);

        return editMode != EnumEditMode.SELECT && privs && mid != null;
      }
    });

    ret.setCommandListener(new CommandListener() {
      public void commandClicked(int row) {
        rndEdtRelClicked(row);
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected TableColumn initTableCommandColumn(Extent width) {
    TableColumn tableColumn = new TableColumn();

    NestedCellRenderer nestedCellRenderer = new NestedCellRenderer();

    nestedCellRenderer.getCellRendererList().add(cfcRndVieRel.getTableCellRenderer());
    nestedCellRenderer.getCellRendererList().add(cfcRndEdtRel.getTableCellRenderer());

    tableColumn.setDataCellRenderer(nestedCellRenderer);
    tableColumn.setWidth(width);

    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListBase.command());

    return tableColumn;
  }

  // --------------------------------------------------------------------------------

  protected Component initTopComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(lft, PlaceHolder.LFT);

    // ----------------------------------------

    Row cnt = new Row();
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));
    cnt.setAlignment(Alignment.ALIGN_CENTER);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    // ----------------------------------------

    Row rgh = new Row();
    rgh.setInsets(new Insets(5, 5, 5, 5));
    rgh.setCellSpacing(new Extent(5));
    rgh.setAlignment(Alignment.ALIGN_RIGHT);

    rgh.add(cfcBtnAddDta.getButton());

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------
  // Relation Handler Methods
  // --------------------------------------------------------------------------------

  protected void rndVieRelClicked(int row) {
    try {
      failsafeRndVieRelClicked(row);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeRndVieRelClicked(int row)
      throws Exception {

    MBase rgh = (MBase) table.getTableDtaModel().getElementAt(row);
    MBase mid = (MBase) jointModel.get(rgh);

    FrmEditBase frmEditBase = frmEditRelClass.newInstance();

    BaseAppInstance.getDesktop().addForm(
        this, frmEditBase, ProcessContextMode.PARENT);

    frmEditBase.init(mid, EnumEditMode.SELECT, dataMode);
  }

  // --------------------------------------------------------------------------------

  protected void rndEdtRelClicked(int row) {
    try {
      failsafeRndEdtRelClicked(row);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeRndEdtRelClicked(int row)
      throws Exception {

    MBase rgh = (MBase) table.getTableDtaModel().getElementAt(row);
    MBase mid = (MBase) jointModel.get(rgh);

    FrmEditBase frmEditBase = frmEditRelClass.newInstance();

    frmEditBase.getAcceptCancelProxy().addAcceptCancelListener(new AcceptCancelAdapter() {
      public void btnAcceptClicked(ActionEvent evt) {
        loadTable();
      }
    });

    BaseAppInstance.getDesktop().addForm(
        this, frmEditBase, ProcessContextMode.PARENT);

    frmEditBase.init(mid, EnumEditMode.UPDATE, dataMode);
  }

  // --------------------------------------------------------------------------------

  protected abstract void tableSelChanged(TableSelModelEvent evt);
}
