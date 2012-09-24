/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.dsingjointsmsb;

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
import com.minotauro.sandbox.gui.mcrudb.FrmMCrudBEdit;
import com.minotauro.sandbox.gui.mcrudb._I18NFrmMCrudBList;
import com.minotauro.sandbox.model.DSingJointSmsB;
import com.minotauro.sandbox.model.MCrudB;
import com.minotauro.sandbox.model._PropMCrudB;

/**
 * @author Demián Gutierrez
 */
public class FrmDSingJointSmsBList extends FrmListJointSingBase {

  public FrmDSingJointSmsBList() {
    frmEditDtaClass = FrmMCrudBEdit.class;
    setTitle(_I18NFrmDSingJointSmsBList.title());
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
        DSingJointSmsB singJointSmsB = (DSingJointSmsB) jointModel.get((MCrudB) element);

        if (singJointSmsB != null) {
          return singJointSmsB.getMainSmsRef().getName();
        }

        return _I18NFrmDSingJointSmsBList.na();
      }
    };
    tableColumn.setWidth(new Extent(45, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDSingJointSmsBList.smsName());
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
    tableColumn.setHeadValue(_I18NFrmDSingJointSmsBList.bName());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);
    
    //TODO: REVISAR LA INTERNACIONALIZACION: _I18NFrmMCrudBList
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
