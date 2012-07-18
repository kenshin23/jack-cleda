/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.mmultjointmposta;

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

import com.minotauro.sandbox.gui.mcruda.*;


import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MMultJointMPostA;
import com.minotauro.sandbox.model._PropMCrudA;


/**
 * @author e.g.:Jack
 */
public class FrmMMultJointMPostAList extends FrmListJointMultBase {

  public FrmMMultJointMPostAList() {
    frmEditDtaClass = FrmMCrudAEdit.class;
    setTitle(_I18NFrmMMultJointMPostAList.title());
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
    // Post name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        MMultJointMPostA multJointMPostA = (MMultJointMPostA) jointModel.get((MCrudA) element);

        if (multJointMPostA != null) {
          return multJointMPostA.getcrudPostRef().getName();
        }

        return _I18NFrmMMultJointMPostAList.na();
      }
    };
    tableColumn.setWidth(new Extent(45, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMMultJointMPostAList.aName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // A name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MCrudA) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(50, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMMultJointMPostAList.bName());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMCrudA.NAME, //
        _I18NFrmMCrudAList.name(), //
        TextFilterBean.class);

    frmFilterEditor.addFilter(_PropMCrudA.DESC, //
        _I18NFrmMCrudAList.desc(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MCrudA.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMCrudA.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MCrudA initMBase() {
    return new MCrudA();
  }
}
