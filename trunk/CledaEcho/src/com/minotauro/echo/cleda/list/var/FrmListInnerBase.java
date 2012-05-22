/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.cleda.list.var;

import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.list._I18NFrmListBase;
import com.minotauro.echo.cleda.list.dta.FrmListDtaBase;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.table.renderer.NestedCellRenderer;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListInnerBase extends FrmListDtaBase {

  protected MBase parModel;

  // --------------------------------------------------------------------------------

  public FrmListInnerBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(EnumEditMode editMode, //
      List<MBase> dataList, MBase parModel) {

    this.dataMode = EnumDataMode.INMEMORY;
    this.editMode = editMode;

    this.dataList = dataList;
    this.parModel = parModel;

    initAll();
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
    rgh.add(cfcBtnDelDta.getButton());

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------

  protected TableColumn initTableCommandColumn(Extent width) {
    TableColumn tableColumn = new TableColumn();

    NestedCellRenderer nestedCellRenderer = new NestedCellRenderer();

    nestedCellRenderer.getCellRendererList().add(cfcRndVieDta.getTableCellRenderer());
    nestedCellRenderer.getCellRendererList().add(cfcRndEdtDta.getTableCellRenderer());
    nestedCellRenderer.getCellRendererList().add(cfcRndDelDta.getTableCellRenderer());

    tableColumn.setDataCellRenderer(nestedCellRenderer);
    tableColumn.setWidth(width);

    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListBase.command());

    return tableColumn;
  }
}
