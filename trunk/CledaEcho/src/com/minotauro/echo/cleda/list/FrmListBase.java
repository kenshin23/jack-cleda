/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.cleda.list;

import java.util.List;
import java.util.Set;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Button;
import nextapp.echo.app.CheckBox;
import nextapp.echo.app.Color;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.button.AbstractButton;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.WindowPaneEvent;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelListenerMethod;
import com.minotauro.echo.base.AcceptCancelProxy;
import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.FilterEvent;
import com.minotauro.echo.config.CledaEchoConfig;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.filter.base.FilterListener;
import com.minotauro.echo.filter.base.FrmFilterEditor;
import com.minotauro.echo.table.base.CellRenderer;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.ETableNavigation;
import com.minotauro.echo.table.base.PageableModel;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.base.TableDtaModel;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.renderer.CheckBoxDataCellRenderer;
import com.minotauro.echo.table.renderer.CheckBoxHeadCellRenderer;
import com.minotauro.echo.table.renderer.RadioBtnDataCellRenderer;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.echo.util.gui.InternalWindowPaneListener;
import com.minotauro.echo.util.gui.LftCntRghLayout;
import com.minotauro.echo.util.gui.LftCntRghLayout.PlaceHolder;
import com.minotauro.query.QueryCreator;
import com.minotauro.query.bean.FilterBean;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListBase extends DlgBorderBase
    implements
    FilterListener {

  // --------------------------------------------------------------------------------

  protected enum EnumListMode {
    SEL, ALL
  }

  // --------------------------------------------------------------------------------

  protected EnumListMode listMode = EnumListMode.ALL;

  // --------------------------------------------------------------------------------
  // GUI related properties
  // --------------------------------------------------------------------------------

  protected InternalWindowPaneListener cancelOnCloseListener;

  protected AcceptCancelProxy acceptCancelProxy =
      new AcceptCancelProxy(getEventListenerList());

  // --------------------------------------------------------------------------------

  protected AbstractButton btnLstmod;
  protected AbstractButton btnFilter;
  protected AbstractButton btnReload;

  protected AbstractButton btnAccept;
  protected AbstractButton btnCancel;
  protected AbstractButton btnGoback;

  // --------------------------------------------------------------------------------
  // Filter related properties
  // --------------------------------------------------------------------------------

  protected FrmFilterEditor frmFilterEditor;

  // --------------------------------------------------------------------------------
  // DB list related properties
  // --------------------------------------------------------------------------------

  protected QueryCreator queryCreator;

  // --------------------------------------------------------------------------------
  // Form mode related properties
  // --------------------------------------------------------------------------------

  protected EnumDataMode dataMode;
  protected EnumEditMode editMode;

  // --------------------------------------------------------------------------------
  // In memory mode related properties
  // --------------------------------------------------------------------------------

  protected List<MBase> dataList;

  // --------------------------------------------------------------------------------
  // Table related properties
  // --------------------------------------------------------------------------------

  protected ETableNavigation tableNavigation;

  protected ETable table;

  // --------------------------------------------------------------------------------

  public FrmListBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected void initAll() {
    setW(new Extent(800));
    setH(new Extent(580));

    initFrmFilterEditor();

    initQueryCreator();

    initGUIInt();

    addCancelOnCloseListener();

    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected void initGUIInt() {
    splTop.setSeparatorPosition(new Extent(50));
    splMid.setSeparatorPosition(new Extent(50));

    setColMidOverflow(true);

    // ----------------------------------------
    // Top Component
    // ----------------------------------------

    Component topComponent = initTopComponent();

    if (topComponent != null) {
      colTop.add(topComponent);
    } else {
      splTop.setSeparatorPosition(new Extent(0));
    }

    // ----------------------------------------
    // Table and Model
    // ----------------------------------------

    colMid.add(table = initTable());

    table.setTableDtaModel(initTableDtaModel());
    table.setTableSelModel(initTableSelModel());
    table.setTableColModel(initTableColModel());

    // ----------------------------------------
    // Bot Component
    // ----------------------------------------

    Component botComponent = initBotComponent();

    if (botComponent != null) {
      colBot.add(botComponent);
    } else {
      splMid.setSeparatorPosition(new Extent(0));
    }
  }

  // --------------------------------------------------------------------------------

  protected void addCancelOnCloseListener() {

    // ----------------------------------------
    // Close works like a cancel
    // ----------------------------------------

    cancelOnCloseListener = new InternalWindowPaneListener() {
      public void windowPaneClosing(WindowPaneEvent e) {
        btnCancelClicked(true);
      }
    };
    addInternalWindowPaneListener(cancelOnCloseListener);
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

  protected TableDtaModel initTableDtaModel() {
    BaseTableDtaModel tableDtaModel = new BaseTableDtaModel();

    tableDtaModel.setProcessContext(
        BaseAppInstance.getDesktop().getProcessContext(this));

    tableDtaModel.setPageSize(CledaEchoConfig.defaultPageSize());

    return tableDtaModel;
  }

  // --------------------------------------------------------------------------------

  protected TableSelModel initTableSelModel() {
    return new TableSelModel();
  }

  // --------------------------------------------------------------------------------

  protected TableColModel initTableColModel() {
    return new TableColModel();
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getBtnLstmod() {
    CheckBox ret = new CheckBox();
    ret.setSelectedStateIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/lstsel_e_24x24.gif"));
    ret.setStateIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/lstall_e_24x24.gif"));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnLstmodClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getBtnFilter() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/filter_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/filter_d_24x24.gif"));
    ret.setVisible(dataMode == EnumDataMode.DATABASE && //
        frmFilterEditor != null);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnFilterClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getBtnReload() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/reload_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/reload_d_24x24.gif"));
    ret.setVisible(dataMode == EnumDataMode.DATABASE);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnReloadClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getBtnAccept() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/accept_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/accept_d_24x24.gif"));
    ret.setVisible(editMode != EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnAcceptClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getBtnCancel() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/cancel_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/cancel_d_24x24.gif"));
    ret.setVisible(editMode != EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(false);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnGoback() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_e_24x24.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/goback_d_24x24.gif"));
    ret.setVisible(editMode == EnumEditMode.SELECT);

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnCancelClicked(false);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected TableColumn initTableSelColumn( //
      boolean editable, boolean multiple, Extent width) {

    TableColumn tableColumn = new TableColumn() {

      public Object/**/getValue(ETable table, Object element) {
        TableSelModel selectModel = //
        (TableSelModel) table.getTableSelModel();
        return selectModel.getSelected(element);
      }

      public void/*  */setValue(ETable table, Object element, //
          Object value) {
        TableSelModel selectModel = //
        (TableSelModel) table.getTableSelModel();
        selectModel.setSelected(element, (Boolean) value);
      }
    };
    tableColumn.setWidth(width);

    CheckBoxHeadCellRenderer headCellRenderer = //
    new CheckBoxHeadCellRenderer();
    headCellRenderer.setEditable(editable);

    CellRenderer dataCellRenderer;

    if (table.getTableSelModel().isMultiple()) {
      CheckBoxDataCellRenderer checkBoxDataCellRenderer = //
      new CheckBoxDataCellRenderer();
      checkBoxDataCellRenderer.setEditable(editable);
      dataCellRenderer = checkBoxDataCellRenderer;
    } else {
      RadioBtnDataCellRenderer radioBtnDataCellRenderer = //
      new RadioBtnDataCellRenderer();
      radioBtnDataCellRenderer.setEditable(editable);
      dataCellRenderer = radioBtnDataCellRenderer;

      headCellRenderer.setEditable(false);
    }

    tableColumn.setDataCellRenderer(dataCellRenderer);
    tableColumn.setHeadCellRenderer(headCellRenderer);

    return tableColumn;
  }

  // --------------------------------------------------------------------------------

  protected void btnLstmodClicked() {
    CheckBox chkLstmod = (CheckBox) btnLstmod;

    listMode = chkLstmod.isSelected() //
        ? EnumListMode.SEL
        : EnumListMode.ALL;

    btnFilter.setEnabled( //
        listMode == EnumListMode.ALL);
    btnReload.setEnabled( //
        listMode == EnumListMode.ALL);

    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected void btnFilterClicked() {
    BaseAppInstance.getDesktop(). //
        addForm(null, frmFilterEditor, ProcessContextMode.IGNORE);
  }

  // --------------------------------------------------------------------------------

  protected void btnReloadClicked() {
    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked() {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.ACCEPT);
    userClose();
  }

  // --------------------------------------------------------------------------------

  protected void btnCancelClicked(boolean closing) {
    acceptCancelProxy.fireAcceptCancelEvent( //
        new ActionEvent(this, null), //
        AcceptCancelListenerMethod.CANCEL);

    if (closing) {
      return;
    }

    delInternalWindowPaneListener(cancelOnCloseListener);

    userClose();
  }

  // --------------------------------------------------------------------------------
  // Load Model Methods
  // --------------------------------------------------------------------------------

  public void loadTable() {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) table.getTableDtaModel();

    tableDtaModel.clear();

    switch (listMode) {
      case SEL :
        loadSel();
        break;
      case ALL :
        loadAll();
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadAll() {
    switch (dataMode) {
      case DATABASE :
        loadAllDatabase();
        break;
      case INMEMORY :
        loadAllInMemory();
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadAllDatabase() {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) table.getTableDtaModel();

    tableDtaModel.setQueryParameterMap(queryCreator.getParamMap());

    tableDtaModel.setRowsQuery(queryCreator.createRowsQuery());
    tableDtaModel.setCurrQuery(queryCreator.createCurrQuery());

    tableDtaModel.setDataMode(EnumDataMode.DATABASE);

    tableDtaModel.currPageChanged();
  }

  // --------------------------------------------------------------------------------

  protected void loadAllInMemory() {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) table.getTableDtaModel();

    tableDtaModel.clear();

    for (MBase base : dataList) {
      tableDtaModel.add(base);
    }

    tableDtaModel.currPageChanged();
  }

  // --------------------------------------------------------------------------------

  protected void loadSel() {
    BaseTableDtaModel tableDtaModel = //
    (BaseTableDtaModel) table.getTableDtaModel();

    tableDtaModel.setDataMode( //
        EnumDataMode.INMEMORY);

    tableDtaModel.setCurrPage(0);

    tableDtaModel.clear();

    Set<Object> selectedSet = //
    table.getTableSelModel().getSelectedSet();

    for (Object base : selectedSet) {
      tableDtaModel.add(base);
    }

    tableDtaModel.currPageChanged();
  }

  // --------------------------------------------------------------------------------

  public List<MBase> getDataList() {
    return dataList;
  }

  public void setDataList(List<MBase> dataList) {
    this.dataList = dataList;
  }

  // --------------------------------------------------------------------------------
  // Filter / FilterListener Methods
  // --------------------------------------------------------------------------------

  protected void initFrmFilterEditor() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  protected abstract void initQueryCreator();

  // --------------------------------------------------------------------------------

  public void searchAll(FilterEvent evt) {
    queryCreator.setFindAll(true);
    loadTable();
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public void search(FilterEvent evt) {
    queryCreator.setFindAll(false);

    queryCreator.setFilterBeanGUIList( //
        (List<? extends FilterBean>) //
        frmFilterEditor.getFilteringBeanList());

    loadTable();
  }

  // --------------------------------------------------------------------------------

  public void resetFilter(FilterEvent evt) {
    queryCreator.setFindAll(false);

    loadTable();
  }

  // --------------------------------------------------------------------------------

  protected abstract Component initTopComponent();

  // --------------------------------------------------------------------------------

  protected Component initBotComponent() {
    LftCntRghLayout lftCntRghLayout = new LftCntRghLayout();

    // ----------------------------------------

    Row lft = new Row();
    lft.setInsets(new Insets(5, 5, 5, 5));
    lft.setCellSpacing(new Extent(5));
    lft.setAlignment(Alignment.ALIGN_LEFT);

    lft.add(btnAccept = getBtnAccept());
    lft.add(btnCancel = getBtnCancel());
    lft.add(btnGoback = getBtnGoback());

    lftCntRghLayout.add(lft, PlaceHolder.LFT);

    // ----------------------------------------

    Row cnt = new Row();
    cnt.setInsets(new Insets(5, 5, 5, 5));
    cnt.setCellSpacing(new Extent(5));
    cnt.setAlignment(Alignment.ALIGN_CENTER);

    tableNavigation = new ETableNavigation( //
        (PageableModel) table.getTableDtaModel());
    cnt.add(tableNavigation);

    lftCntRghLayout.add(cnt, PlaceHolder.CNT);

    // ----------------------------------------

    Row rgh = new Row();
    rgh.setInsets(new Insets(5, 5, 5, 5));
    rgh.setCellSpacing(new Extent(5));
    rgh.setAlignment(Alignment.ALIGN_RIGHT);

    rgh.add(btnLstmod = getBtnLstmod());
    rgh.add(btnFilter = getBtnFilter());
    rgh.add(btnReload = getBtnReload());

    lftCntRghLayout.add(rgh, PlaceHolder.RGH);

    // ----------------------------------------

    return lftCntRghLayout;
  }

  // --------------------------------------------------------------------------------

  public Set<Object> getSelectedSet() {
    TableSelModel tableSelModel = //
    (TableSelModel) table.getTableSelModel();

    return tableSelModel.getSelectedSet();
  }

  public void setSelectedSet(Set<Object> selectedSet) {
    TableSelModel tableSelModel = //
    (TableSelModel) table.getTableSelModel();

    tableSelModel.setSelectedSet(selectedSet);
  }

  // --------------------------------------------------------------------------------

  public EnumDataMode getDataMode() {
    return dataMode;
  }

  public EnumEditMode getEditMode() {
    return editMode;
  }

  // --------------------------------------------------------------------------------

  public ETable getTable() {
    return table;
  }

  // --------------------------------------------------------------------------------

  public AcceptCancelProxy getAcceptCancelProxy() {
    return acceptCancelProxy;
  }
}
