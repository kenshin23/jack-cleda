/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dinnerac;

import nextapp.echo.app.Extent;

import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.list.var.FrmListInnerBase;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.query.QueryCreator;
import com.minotauro.sandbox.model.DInnerA;

/**
 * @author Demián Gutierrez
 */
public class FrmDInnerAList extends FrmListInnerBase {

  public FrmDInnerAList() {
    frmEditDtaClass = FrmDInnerAEdit.class;
    setTitle(_I18NFrmDInnerAList.title());
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
        return ((DInnerA) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(85, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDInnerAList.name());
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
  protected void initQueryCreator() {

    // --------------------------------------------------------------------------------
    // TODO: This has no sense in inner, because it's only a memory list
    // --------------------------------------------------------------------------------

    queryCreator = new QueryCreator(DInnerA.class, true);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected DInnerA initMBase() {
    return new DInnerA();
  }
}
