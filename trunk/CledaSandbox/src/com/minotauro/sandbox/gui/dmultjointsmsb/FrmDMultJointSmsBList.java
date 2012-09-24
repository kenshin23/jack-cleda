/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dmultjointsmsb;

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
import com.minotauro.sandbox.gui.mcrudb.FrmMCrudBEdit;
import com.minotauro.sandbox.gui.mcrudb._I18NFrmMCrudBList;
import com.minotauro.sandbox.model.DMultJointSmsB;
import com.minotauro.sandbox.model.MCrudB;
import com.minotauro.sandbox.model._PropMCrudB;

/**
 * @author Demián Gutierrez
 */
public class FrmDMultJointSmsBList extends FrmListJointMultBase {

  public FrmDMultJointSmsBList() {
    frmEditDtaClass = FrmMCrudBEdit.class;
    setTitle(_I18NFrmDMultJointSmsBList.title());
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
    // Sms name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        DMultJointSmsB multJointSmsB = (DMultJointSmsB) jointModel.get((MCrudB) element);

        if (multJointSmsB != null) {
          return multJointSmsB.getMainSmsRef().getName();
        }

        return _I18NFrmDMultJointSmsBList.na();
      }
    };
    tableColumn.setWidth(new Extent(45, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDMultJointSmsBList.smsName());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // B name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MCrudB) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(50, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDMultJointSmsBList.bName());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMCrudB.NAME, //
        _I18NFrmMCrudBList.name(), //
        TextFilterBean.class);

    frmFilterEditor.addFilter(_PropMCrudB.DESC, //
        _I18NFrmMCrudBList.desc(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MCrudB.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMCrudB.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MCrudB initMBase() {
    return new MCrudB();
  }
}
