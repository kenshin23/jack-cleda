/*
 * Created on 13/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;

import com.minotauro.cleda.util.CledaStringUtils;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.cleda.list.FrmListBase;
import com.minotauro.echo.cleda.list._I18NFrmListBase;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.table.renderer.NestedCellRenderer;
import com.minotauro.query.QueryCreator;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.base.CodeFilterBeanImpl;
import com.minotauro.workflow.model.MWrkTransSet;
import com.minotauro.workflow.prop.MActorProp;
import com.minotauro.workflow.prop.MNetTransSetProp;
import com.minotauro.workflow.prop.MWorklistProp;
import com.minotauro.workflow.prop.MWrkTransSetProp;

/**
 * @author Demián Gutierrez
 */
public class FrmListWrk extends FrmListBase {

  protected CommandListener handlerVieDta = new HandlerVieWrk(this);
  protected CommandListener handlerEdtDta = new HandlerEdtWrk(this);

  protected CommandFactory cfcRndEdtDta;
  protected CommandFactory cfcRndVieDta;

  protected String worklist;

  // --------------------------------------------------------------------------------

  public FrmListWrk(String worklist) {
    this.dataMode = EnumDataMode.DATABASE;
    this.worklist = worklist;

    setModal(false);
  }

  // --------------------------------------------------------------------------------
  // TODO: there has to exist a common initialization mechanism along all the forms
  // --------------------------------------------------------------------------------

  @Override
  public void initAll() {
    ListDocButtonFactory listDocButtonFactory =
        new ListDocButtonFactory(editMode, null);

    cfcRndVieDta = listDocButtonFactory.initRndVieWrk(handlerVieDta);
    cfcRndEdtDta = listDocButtonFactory.initRndEdtWrk(handlerEdtDta);

    super.initAll();

    btnAccept.setVisible(false);
    btnCancel.setVisible(false);
    btnLstmod.setVisible(false);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Component initTopComponent() {
    return null;
  }

  // --------------------------------------------------------------------------------

  protected TableColumn initTableCommandColumn(Extent width) {
    TableColumn tableColumn = new TableColumn();

    NestedCellRenderer nestedCellRenderer = new NestedCellRenderer();
    nestedCellRenderer.getCellRendererList().add(cfcRndEdtDta.getTableCellRenderer());
    nestedCellRenderer.getCellRendererList().add(cfcRndVieDta.getTableCellRenderer());
    tableColumn.setDataCellRenderer(nestedCellRenderer);
    tableColumn.setWidth(width);

    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListBase.command());

    return tableColumn;
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initQueryCreator() {
    queryCreator = new QueryCreator(MWrkTransSet.class);
    queryCreator.setCodeFiltered(true);

    queryCreator.addCodeFilter(
        new CodeFilterBeanImpl(
            CledaStringUtils.dotIt(
                QueryCreator.ITEM, MWrkTransSetProp.STATUS),
            QueryCreator.RelationType.EQUAL, MWrkTransSet.STATUS_ENABLED));

    queryCreator.addCodeFilter(
        new CodeFilterBeanImpl(
            CledaStringUtils.dotIt(
                QueryCreator.ITEM, MWrkTransSetProp.NET_TRANS_SET_REF,
                MNetTransSetProp.WORKLIST_REF, MWorklistProp.NAME),
            RelationType.EQUAL, worklist));

    queryCreator.addCodeFilter(
        new CodeFilterBeanImpl(
            CledaStringUtils.dotIt(
                QueryCreator.ITEM, MWrkTransSetProp.ACTOR_REF, MActorProp.USER_REF),
            RelationType.EQUAL, BaseAppInstance.getUser()));
  }

  // ----------------------------------------
  // InboxTableModel
  // ----------------------------------------

  @Override
  protected TableColModel initTableColModel() {
    TableColModel tableColModel = new TableColModel();

    TableColumn tableColumn;

    // ----------------------------------------
    // WrkId/Id
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransSet) element).getId() + " / " +
            ((MWrkTransSet) element).getWorkflowRef().getId();
      }
    };
    tableColumn.setWidth(new Extent(10, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListWrk.id());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Document
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransSet) element).getWorkflowRef().
            getNetPetriRef().getDocTypeRef().getName();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListWrk.document());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // TransSet
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransSet) element).getNetTransSetRef().
            getName();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListWrk.activity());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Enable Date
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransSet) element).getBegDate();
      }
    };
    tableColumn.setWidth(new Extent(20, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmListWrk.sinceDate());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Action
    // ----------------------------------------

    tableColModel.getTableColumnList().add(
        initTableCommandColumn(new Extent(10, Extent.PERCENT)));

    // ----------------------------------------

    return tableColModel;
  }
}
