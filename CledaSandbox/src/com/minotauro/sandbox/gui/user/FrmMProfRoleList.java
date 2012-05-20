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
import com.minotauro.user.model.MProf;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model._PropMRole;

/**
 * @author Demián Gutierrez
 */
public class FrmMProfRoleList extends FrmListJointMultBase {

  public FrmMProfRoleList() {
    // frmEditDtaClass = N/A;
    setTitle(_I18NFrmMProfRoleList.title());
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
        return ((MRole) element).getName();
      }
    };
    tableColumn.setWidth(new Extent(50, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMProfRoleList.name());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMRole.NAME, //
        _I18NFrmMProfRoleList.name(), //
        TextFilterBean.class);

    frmFilterEditor.addFilter(_PropMRole.DESCRIPTION, //
        _I18NFrmMProfRoleList.description(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MRole.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMRole.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MProf initMBase() {
    throw new UnsupportedOperationException();
  }
}
