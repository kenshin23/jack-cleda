/*
 * Created on 13/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;

import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.FrmListBase;
import com.minotauro.echo.cleda.list._I18NFrmListBase;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.table.renderer.NestedCellRenderer;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListDoc extends FrmListBase {

  protected Class<? extends FrmEditWrk> frmEditDtaClass;

  protected CommandListener handlerVieDoc = new HandlerVieDoc(this);
  protected CommandListener handlerEdtDoc = new HandlerEdtDoc(this);

  protected CommandFactory cfcRndEdtDoc;
  protected CommandFactory cfcRndVieDoc;

  // --------------------------------------------------------------------------------

  public FrmListDoc() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void init(EnumEditMode editMode) {
    this.dataMode = EnumDataMode.DATABASE;
    this.editMode = editMode;

    initAll();
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initAll() {
    ListDocButtonFactory listDocButtonFactory =
        new ListDocButtonFactory(editMode, null);

    cfcRndVieDoc = listDocButtonFactory.initRndVieDoc(handlerVieDoc);
    cfcRndEdtDoc = listDocButtonFactory.initRndEdtDoc(handlerEdtDoc);

    super.initAll();

    btnAccept.setVisible(false);
    btnCancel.setVisible(false);
    btnLstmod.setVisible(false);
  }

  // --------------------------------------------------------------------------------

  @Override
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

    // ----------------------------------------
    // TODO: Add a new document from here too?
    // ----------------------------------------
    //    rgh.add(cfcBtnAddDta.getButton());
    // ----------------------------------------

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------

  protected TableColumn initTableCommandColumn(Extent width) {
    TableColumn tableColumn = new TableColumn();

    NestedCellRenderer nestedCellRenderer = new NestedCellRenderer();
    nestedCellRenderer.getCellRendererList().add(cfcRndEdtDoc.getTableCellRenderer());
    nestedCellRenderer.getCellRendererList().add(cfcRndVieDoc.getTableCellRenderer());
    tableColumn.setDataCellRenderer(nestedCellRenderer);
    tableColumn.setWidth(width);

    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListBase.command());

    return tableColumn;
  }

  // --------------------------------------------------------------------------------

  public Class<? extends FrmEditWrk> getFrmEditDtaClass() {
    return frmEditDtaClass;
  }
}
