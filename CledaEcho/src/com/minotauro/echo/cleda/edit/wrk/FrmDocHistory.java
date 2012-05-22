/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.cleda.edit.wrk;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.button.AbstractButton;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.layout.ColumnLayoutData;

import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.cleda.list.BaseTableDtaModel;
import com.minotauro.echo.config.CledaEchoConfig;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.ETableNavigation;
import com.minotauro.echo.table.base.PageableModel;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.base.TableDtaModel;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.renderer.CalendarCellRenderer;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;
import com.minotauro.i18n.bundled._DateFormatDate;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.model.MWrkTransLog;

/**
 * @author Demián Gutierrez
 */
public class FrmDocHistory extends DlgBorderBase {

  protected List<MWrkTransLog> wrkTransLogList;

  protected AbstractButton btnGoback;

  protected ETable table;

  // --------------------------------------------------------------------------------

  public FrmDocHistory(List<MWrkTransLog> wrkTransLogList) {
    this.wrkTransLogList = wrkTransLogList;

    setW(new Extent(750));
    setH(new Extent(350));

    initGUI();
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    splTop.setSeparatorPosition(new Extent(0));
    splMid.setSeparatorPosition(new Extent(50));

    // ----------------------------------------
    // Mid Component
    // ----------------------------------------

    colMid.add(table = initTable());

    table.setTableDtaModel(initTableDtaModel());
    table.setTableSelModel(new TableSelModel());
    table.setTableColModel(initTableColModel());

    colMid.add(table);

    // ----------------------------------------
    // Bot Component
    // ----------------------------------------

    Component botComponent = initBotComponent();

    if (botComponent != null) {
      ColumnLayoutData cld = new ColumnLayoutData();
      cld.setInsets(new Insets(5, 2, 5, 2));

      botComponent.setLayoutData(cld);
      colBot.add(botComponent);
    } else {
      splMid.setSeparatorPosition(new Extent(0));
    }
  }

  // --------------------------------------------------------------------------------

  protected TableDtaModel initTableDtaModel() {
    BaseTableDtaModel tableDtaModel = new BaseTableDtaModel();

    tableDtaModel.setPageSize(CledaEchoConfig.defaultPageSize());
    tableDtaModel.setDataMode(EnumDataMode.INMEMORY);

    // ----------------------------------------------
    // TODO: I don't like this (recreating the list)
    // ----------------------------------------------

    tableDtaModel.setDataList(
        new ArrayList<Object>(wrkTransLogList));

    return tableDtaModel;
  }

  // --------------------------------------------------------------------------------

  protected ETable initTable() {
    ETable ret = new ETable();

    ret.setBorder(new Border(0, Color.BLACK, Border.STYLE_NONE));
    ret.setInsets(new Insets(5, 2, 5, 2));
    ret.setEasyview(true);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    lft.add(btnGoback = getBtnGoback());

    lftCntRghLayout.add(lft, PlaceHolder.LFT);

    // ----------------------------------------

    Row cnt = new Row();
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));
    cnt.setAlignment(Alignment.ALIGN_CENTER);

    ETableNavigation tableNavigation = new ETableNavigation( //
        (PageableModel) table.getTableDtaModel());
    cnt.add(tableNavigation);

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    // ----------------------------------------

    Row rgh = new Row();
    rgh.setInsets(new Insets(5, 5, 5, 5));
    rgh.setCellSpacing(new Extent(5));
    rgh.setAlignment(Alignment.ALIGN_RIGHT);

    // -----------
    // Empty >>>>>
    // -----------

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnGoback() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_d_24x24.gif"));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        userClose();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected String getAttendedByUser(MWrkTransLog wrkTransLog) {
    if (wrkTransLog.isAuto()) {
      return _I18NFrmDocHistory.auto();
    }

    if (wrkTransLog.getUserRef() == null) {
      return "-";
    }

    return wrkTransLog.getUserRef().getUser();
  }

  // --------------------------------------------------------------------------------

  protected String getAssignedToUser(MWrkTransLog wrkTransLog) {
    if (wrkTransLog.isAuto()) {
      return _I18NFrmDocHistory.auto();
    }

    if (wrkTransLog.getUserRef() != null) {
      return "-";
    }

    MUser user = wrkTransLog.getWrkTransSetRef().
        getActorRef().getUserRef();

    return user.getUser();
  }

  // --------------------------------------------------------------------------------

  protected String getRole(MWrkTransLog wrkTransLog) {
    if (wrkTransLog.isAuto()) {
      return _I18NFrmDocHistory.auto();
    }

    return wrkTransLog.getWrkTransSetRef().
        getNetTransSetRef().getNetTransSetRoleRef().
        getRoleRef().getName();
  }

  // --------------------------------------------------------------------------------

  protected TableColModel initTableColModel() {
    TableColModel tableColModel = new TableColModel();

    TableColumn tableColumn;

    // ----------------------------------------
    // User (attendedBy)
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return getAttendedByUser((MWrkTransLog) element);
      }
    };
    tableColumn.setWidth(new Extent(20, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.attendedByUser());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // User (assignedTo)
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return getAssignedToUser((MWrkTransLog) element);
      }
    };
    tableColumn.setWidth(new Extent(20, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.assignedToUser());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Role
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return getRole((MWrkTransLog) element);
      }
    };
    tableColumn.setWidth(new Extent(20, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.role());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // BegDate
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransLog) element).getBegDate();
      }
    };
    tableColumn.setWidth(new Extent(15, Extent.PERCENT));
    tableColumn.setDataCellRenderer(
        new CalendarCellRenderer(_DateFormatDate.getDateTimeFormatter()));
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.begDate());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // EndDate
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransLog) element).getEndDate();
      }
    };
    tableColumn.setWidth(new Extent(15, Extent.PERCENT));
    tableColumn.setDataCellRenderer(
        new CalendarCellRenderer(_DateFormatDate.getDateTimeFormatter()));
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.endDate());
    tableColModel.getTableColumnList().add(tableColumn);

    // ----------------------------------------
    // Activity
    // ----------------------------------------

    tableColumn = new TableColumn() {
      public Object getValue(ETable table, Object element) {
        return ((MWrkTransLog) element).getWrkTransRef(). //
            getNetTransRef().getName();
      }
    };
    tableColumn.setWidth(new Extent(30, Extent.PERCENT));
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setHeadValue(_I18NFrmDocHistory.activity());
    tableColModel.getTableColumnList().add(tableColumn);

    return tableColModel;
  }
}
