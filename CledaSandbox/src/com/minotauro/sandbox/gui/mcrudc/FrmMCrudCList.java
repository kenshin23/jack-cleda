/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.mcrudc;

import nextapp.echo.app.Extent;

import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.list.var.FrmListCrudBase;
import com.minotauro.echo.filter.base.FrmFilterEditor;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.query.QueryCreator;
import com.minotauro.query.bean.gui.TextFilterBean;
import com.minotauro.sandbox.model.MCrudC;
import com.minotauro.sandbox.model._PropMCrudC;

/**
 * @author Demián Gutierrez
 */
public class FrmMCrudCList extends FrmListCrudBase {

  public FrmMCrudCList() {
    frmEditDtaClass = FrmMCrudCEdit.class;
    setTitle(_I18NFrmMCrudCList.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected TableColModel initTableColModel() {
    TableColModel tableColModel = new TableColModel();

    TableColumn tableColumn;

    // ----------------------------------------
    // CheckBox
    // ----------------------------------------

    tableColModel.getTableColumnList().add( //
        initTableSelColumn(editMode != EnumEditMode.SELECT, //
            true, new Extent(5, Extent.PERCENT)));

    // ----------------------------------------
    // Name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MCrudC) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(85, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue("Name");
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Action
    // ----------------------------------------

    tableColModel.getTableColumnList().add( //
        initTableCommandColumn(new Extent(10, Extent.PERCENT)));

    // ----------------------------------------

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMCrudC.NAME, //
        _I18NFrmMCrudCList.name(), //
        TextFilterBean.class);

    // ------------------------------------
    // TODO: It's using sql column name :(
    // ------------------------------------

    frmFilterEditor.addFilter("odesc", //
        _I18NFrmMCrudCList.desc(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MCrudC.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMCrudC.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MCrudC initMBase() {
    return new MCrudC();
  }
}
