/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.msingjointac;

import nextapp.echo.app.Extent;

import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.cleda.list.var.FrmListJointSingBase;
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
import com.minotauro.sandbox.model.MSingJointAC;
import com.minotauro.sandbox.model._PropMCrudC;

/**
 * @author Demián Gutierrez
 */
public class FrmMSingJointACList extends FrmListJointSingBase {

  public FrmMSingJointACList() {
    frmEditDtaClass = FrmMCrudCEdit.class;
    frmEditRelClass = FrmMSingJointACEdit.class;
    setTitle(_I18NFrmMSingJointACList.title());
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
        MSingJointAC singJointAC = (MSingJointAC) jointModel.get((MCrudC) element);

        if (singJointAC != null) {
          return singJointAC.getCrudARef().getName();
        }

        return _I18NFrmMSingJointACList.na();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMSingJointACList.aName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Name Rel
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        MSingJointAC singJointAC = (MSingJointAC) jointModel.get((MCrudC) element);

        if (singJointAC != null) {
          return singJointAC.getName();
        }

        return _I18NFrmMSingJointACList.na();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMSingJointACList.rName());
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
    tableColumn.setHeadValue(_I18NFrmMSingJointACList.bName());
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
