/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.mmultjointac;

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
import com.minotauro.sandbox.gui.mcrudc.FrmMCrudCEdit;
import com.minotauro.sandbox.gui.mcrudc._I18NFrmMCrudCList;
import com.minotauro.sandbox.model.MCrudC;
import com.minotauro.sandbox.model.MMultJointAC;
import com.minotauro.sandbox.model._PropMCrudC;

/**
 * @author Demián Gutierrez
 */
public class FrmMMultJointACList extends FrmListJointMultBase {

  public FrmMMultJointACList() {
    frmEditDtaClass = FrmMCrudCEdit.class;
    frmEditRelClass = FrmMMultJointACEdit.class;
    setTitle(_I18NFrmMMultJointACList.title());
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
    // Name A
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        MMultJointAC multJointAC = (MMultJointAC) jointModel.get((MCrudC) element);

        if (multJointAC != null) {
          return multJointAC.getCrudARef().getName();
        }

        return _I18NFrmMMultJointACList.na();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMMultJointACList.aName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Name Rel
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        MMultJointAC multJointAC = (MMultJointAC) jointModel.get((MCrudC) element);

        if (multJointAC != null) {
          return multJointAC.getName();
        }

        return _I18NFrmMMultJointACList.na();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMMultJointACList.rName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Name B
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MCrudC) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMMultJointACList.bName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Action
    // ----------------------------------------

    tableColModel.getTableColumnList().add( //
        initTableCommandColumn(new Extent(5, Extent.PERCENT)));

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

    frmFilterEditor.addFilter(_PropMCrudC.DESC, //
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
