[#ftl]	
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------

package com.minotauro.sandbox.gui.dmainsms;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.sandbox.gui.dinnersmsb.FrmDInnerSmsList;
import com.minotauro.sandbox.gui.dmultjointsmsb.FrmDMultJointSmsBList;
import com.minotauro.sandbox.gui.dsingjointsmsb.FrmDSingJointSmsBList;
import com.minotauro.sandbox.model.${modelName};
import com.minotauro.sandbox.model.DMultJointSmsB;
import com.minotauro.sandbox.model.DSingJointSmsB;
import com.minotauro.sandbox.model._PropDInnerSms;
import com.minotauro.sandbox.model._Prop${modelName};
import com.minotauro.sandbox.model._PropDMultJointSmsB;
import com.minotauro.sandbox.model._PropDSingJointSmsB;
// parametrizar paquetes con xml y xsd

/**
 * @author E.G Jack™
 */

public class Frm${modelName}List extends FrmListDoc {

  public Frm${modelName}List() {
    frmEditDtaClass = Frm${modelName}Edit.class;
    setTitle(_I18NFrm${modelName}List.title());
  }

  // --------------------------------------------------------------------------------

  @Override
  protected TableColModel initTableColModel() {
    TableColModel tableColModel = new TableColModel();

    TableColumn tableColumn;

    // ----------------------------------------
    // CheckBox
    // ----------------------------------------

    //    tableColModel.getTableColumnList().add( //
    //        initTableSelColumn(editMode != EnumEditMode.SELECT, //
    //            true, new Extent(5, Extent.PERCENT)));

    // ----------------------------------------
    // Name
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((${modelName}) element).getName();
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

    frmFilterEditor.addFilter(_Prop${modelName}.NAME, //
        _I18NFrm${modelName}List.name(), //
        TextFilterBean.class);

    // ------------------------------------
    // TODO: It's using sql column name :(
    // ------------------------------------

    frmFilterEditor.addFilter("odesc", //
        _I18NFrm${modelName}List.desc(), //
        TextFilterBean.class);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(${modelName}.class, true);

    // ----------------------------------------
    // TODO: Improve ordering
    // ----------------------------------------

    queryCreator.setOrderBy(_Prop${modelName}.NAME);
  }
}
