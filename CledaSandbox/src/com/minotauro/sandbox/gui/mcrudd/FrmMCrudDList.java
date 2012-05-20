/*
 * Created on 27/08/2011
 */
package com.minotauro.sandbox.gui.mcrudd;

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
import com.minotauro.sandbox.model.MCrudD;
import com.minotauro.sandbox.model._PropMCrudD;

/**
 * @author Demián Gutierrez
 */
public class FrmMCrudDList extends FrmListCrudBase {

  public FrmMCrudDList() {
    frmEditDtaClass = FrmMCrudDEdit.class;
    setTitle(_I18NFrmMCrudDList.title());
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
        return ((MCrudD) element).getName();
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

    frmFilterEditor.addFilter(_PropMCrudD.NAME, //
        _I18NFrmMCrudDList.name(), //
        TextFilterBean.class);

    // ------------------------------------
    // TODO: It's using sql column name :(
    // ------------------------------------

    frmFilterEditor.addFilter("odesc", //
        _I18NFrmMCrudDList.desc(), //
        TextFilterBean.class);

    initFilter("B", 5);
    initFilter("C", 5);
    initFilter("D", 5);
    initFilter("E", 5);
  }

  // --------------------------------------------------------------------------------

  protected void initFilter(String prefix, int count) {
    for (int i = 1; i <= count; i++) {
      String key = "val" + prefix + i;
      String lbl = _I18NFrmMCrudDList.lbl(prefix, i);

      frmFilterEditor.addFilter(key, lbl,
          TextFilterBean.class);
    }
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MCrudD.class, true);

    // ----------------------------------------
    // TODO: improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMCrudD.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MCrudD initMBase() {
    return new MCrudD();
  }
}
