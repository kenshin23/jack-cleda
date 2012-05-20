/*
 * Created on 28/08/2008
 */
package com.minotauro.sandbox.gui.user;

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
import com.minotauro.user.model.MUser;
import com.minotauro.user.model._PropMUser;

/**
 * @author Demián Gutierrez
 */
public class FrmMUserList extends FrmListCrudBase {

  public FrmMUserList() {
    frmEditDtaClass = FrmMUserEdit.class;
    setTitle(_I18NFrmMUserList.title());
    // basePriv = FrmMUserList.class.getSimpleName();
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
    // Login
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MUser) element).getUser();
      }
    };
    tableColumn.setWidth(new Extent(85, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmMUserList.user());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Action
    // ----------------------------------------

    tableColModel.getTableColumnList().add( //
        initTableCommandColumn(new Extent(10, Extent.PERCENT)));

    // ----------------------------------------

    return tableColModel;
  }

  @Override
  protected void initFrmFilterEditor() {
    frmFilterEditor = new FrmFilterEditor();
    frmFilterEditor.addFilterListener(this);

    frmFilterEditor.addFilter(_PropMUser.USER, //
        _I18NFrmMUserList.user(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MUser.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMUser.USER);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MUser initMBase() {
    return new MUser();
  }
}
