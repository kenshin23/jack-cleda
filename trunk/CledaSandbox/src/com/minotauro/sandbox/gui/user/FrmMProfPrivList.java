/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.user;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;

import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.list.var.FrmListJointMultBase;
import com.minotauro.echo.filter.base.FrmFilterEditor;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.query.QueryCreator;
import com.minotauro.query.bean.gui.TextFilterBean;
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MProf;
import com.minotauro.user.model._PropMPriv;

/**
 * @author Demián Gutierrez
 */
public class FrmMProfPrivList extends FrmListJointMultBase {

  public FrmMProfPrivList() {
    // frmEditDtaClass = N/A;
    setTitle(_I18NFrmMProfPrivList.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Component initTopComponent() {
    splTop.setSeparatorPosition(new Extent(0));
    return null;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected TableColModel initTableColModel() {
    TableColModel tableColModel = new TableColModel();

    TableColumn tableColumn;

    // ----------------------------------------
    // CheckBox
    // ----------------------------------------

    tableColumn = initTableSelColumn(editMode != EnumEditMode.SELECT, //
        false, new Extent(5, Extent.PERCENT));
    tableColumn.setEventOnSetValue(true);
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Prof
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MPriv) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(50, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMProfPrivList.name());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMPriv.NAME, //
        _I18NFrmMProfPrivList.name(), //
        TextFilterBean.class);

    frmFilterEditor.addFilter(_PropMPriv.DESCRIPTION, //
        _I18NFrmMProfPrivList.description(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MPriv.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMPriv.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MProf initMBase() {
    throw new UnsupportedOperationException();
  }
}
