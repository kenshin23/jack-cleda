// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------


package com.minotauro.sandbox.gui.mcrudpost;

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
import com.minotauro.sandbox.model.MCrudPost;
import com.minotauro.sandbox.model._PropMCrudPost;
// parametrizar paquetes con xml y xsd

/**
 * @author E.G Jack™
 */
public class FrmMCrudPostList extends FrmListCrudBase {

  public FrmMCrudPostList() {
    frmEditDtaClass = FrmMCrudPostEdit.class;
    setTitle(_I18NFrmMCrudPostList.title());
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
        return ((MCrudPost) element).getName();
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

    frmFilterEditor.addFilter(_PropMCrudPost.NAME, //
        _I18NFrmMCrudPostList.name(), //
        TextFilterBean.class);

    // ------------------------------------
    // TODO: It's using sql column name :(
    // ------------------------------------

    frmFilterEditor.addFilter("odesc", //
        _I18NFrmMCrudPostList.desc(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MCrudPost.class, true);

    // ----------------------------------------
    // TODO: improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_PropMCrudPost.NAME);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected MCrudPost initMBase() {
    return new MCrudPost();
  }
}

