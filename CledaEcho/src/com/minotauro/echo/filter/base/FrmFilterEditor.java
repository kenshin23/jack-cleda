/*
 * Created on 09/05/2007
 */
package com.minotauro.echo.filter.base;

import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.Row;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.event.EventListenerList;

import org.apache.commons.beanutils.BeanComparator;

import com.minotauro.echo.base.DlgBorderBase;
import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.beans.FilterEvent;
import com.minotauro.echo.cleda.list.BaseTableDtaModel;
import com.minotauro.echo.filter.base.FilterListener.FilterListenerMethod;
import com.minotauro.echo.table.base.CellRenderer;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.base.TableColModel;
import com.minotauro.echo.table.base.TableColumn;
import com.minotauro.echo.table.base.TableSelModel;
import com.minotauro.echo.table.renderer.BaseCellRenderer;
import com.minotauro.echo.table.renderer.LabelCellRenderer;
import com.minotauro.echo.util.FilterEditorRegistry;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;
import com.minotauro.query.bean.GUIFilterBean;
import com.minotauro.query.gui.FilterEntryEditor;
import com.minotauro.query.util.QueryUtil;

/** 
 * @author Alejandro Salas
 */
public class FrmFilterEditor extends DlgBorderBase {

  protected EventListenerList eventListenerList = new EventListenerList();

  protected FilterTableModel allFiltersTableModel;
  protected FilterTableModel curFiltersTableModel;

  protected Map<GUIFilterBean, FilterEntryEditor> propertyFilterMap = //
  new HashMap<GUIFilterBean, FilterEntryEditor>();

  // ----------------------------------------

  public FrmFilterEditor() {
    setTitle/* */(_I18NFrmFilterEditor.windowTitle());
    setWidth/* */(new Extent(739, Extent.PX));
    setHeight/**/(new Extent(350, Extent.PX));

    setModal(true);
    initGUI();
  }

  // ----------------------------------------

  private void initGUI() {
    splTop.setSeparatorPosition(new Extent(50));
    splMid.setSeparatorHeight(new Extent(3));

    setColMidOverflow(true);
    setColBotOverflow(true);

    Row cmdRow = new Row();
    cmdRow.setInsets(new Insets(6, 6, 6, 6));
    cmdRow.setCellSpacing(new Extent(6, Extent.PX));

    cmdRow.add(getBtnSearchFlt());
    cmdRow.add(getBtnSearchAll());
    cmdRow.add(getBtnResetFlt());

    colTop.add(cmdRow);

    splMid.setSeparatorPosition(new Extent(125));
    splMid.setResizable(true);

    allFiltersTableModel = new FilterTableModel();
    ETable table;

    table = new ETable();
    table.setTableColModel(initTableColumnModel(true));
    table.setTableDtaModel(allFiltersTableModel);
    table.setTableSelModel(new TableSelModel());
    table.setHeadVisible(false);
    //    table.setWidth(new Extent(100, Extent.PERCENT));
    // XXX:    table.setBorder(new Border(0, null, Border.STYLE_NONE));
    // XXX:   table.setInsets(new Insets(5, 2, 5, 2));
    colMid.add(table);

    curFiltersTableModel = new FilterTableModel();
    table = new ETable();
    table.setTableColModel(initTableColumnModel(false));
    table.setTableDtaModel(curFiltersTableModel);
    table.setTableSelModel(new TableSelModel());
    table.setHeadVisible(false);
    //    table.setWidth(new Extent(100, Extent.PERCENT));
    // XXX:   table.setBorder(new Border(0, null, Border.STYLE_NONE));
    // XXX:   table.setInsets(new Insets(5, 2, 5, 2));
    colBot.add(table);
  }

  protected Button getBtnSearchFlt() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/search_24x24.gif"));
    ret.setText(_I18NFrmFilterEditor.search());

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        fireFilterEvent(new FilterEvent(FrmFilterEditor.this), //
            FilterListenerMethod.SEARCH);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnSearchAll() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/search_all_24x24.gif"));
    ret.setText(_I18NFrmFilterEditor.searchAll());

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        fireFilterEvent(new FilterEvent(FrmFilterEditor.this), //
            FilterListenerMethod.SEARCH_ALL);
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnResetFlt() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/reset_24x24.gif"));
    ret.setText(_I18NFrmFilterEditor.resetFilter());

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        btnResetFilterClicked();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected CellRenderer getTableCellRendererAdd() {
    return new BaseCellRenderer() {
      @Override
      public Component getCellRenderer( //
          final ETable table, final Object value, final int col, final int row) {

        Button ret = new Button(ImageReferenceCache.getInstance(). //
            getImageReference("images/icons/add_16x16.gif"));

        ret.setStyleName(GUIStyles.DEFAULT);

        ret.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            btnAddClicked(row);
          }
        });
        return ret;
      }
    };
  }

  // --------------------------------------------------------------------------------

  protected CellRenderer getTableCellRendererRem() {
    return new BaseCellRenderer() {
      @Override
      public Component getCellRenderer( //
          final ETable table, final Object value, final int col, final int row) {

        Button ret = new Button(ImageReferenceCache.getInstance(). //
            getImageReference("images/icons/rem_16x16.gif"));

        ret.setStyleName(GUIStyles.DEFAULT);

        ret.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            btnRemClicked(row);
          }
        });
        return ret;
      }
    };
  }

  // --------------------------------------------------------------------------------

  private void btnResetFilterClicked() {
    for (int i = allFiltersTableModel.getRowCount() - 1; i >= 0; i--) {
      btnRemClicked(i);
    }

    fireFilterEvent(new FilterEvent(FrmFilterEditor.this), //
        FilterListenerMethod.RESET_FILTER);
  }

  public FilterEntryEditor addFilter(String propertyName, String label, Class<? extends GUIFilterBean> clazz) {
    GUIFilterBean bean = QueryUtil.beanFromClass(clazz, propertyName, label, curFiltersTableModel.getRowCount());
    FilterEntryEditor propertyFilter = FilterEditorRegistry.getEditor(bean);
    curFiltersTableModel.add(bean);
    propertyFilterMap.put(bean, propertyFilter);

    return propertyFilter;
  }

  public List<Object> getFilteringBeanList() {
    for (Object filterBean : allFiltersTableModel.getDataList()) {
      propertyFilterMap.get(filterBean).sync();
    }
    return allFiltersTableModel.getDataList();
  }

  public void addFilterListener(FilterListener listener) {
    eventListenerList.addListener(FilterListener.class, listener);
  }

  public void delFilterListener(FilterListener listener) {
    eventListenerList.removeListener(FilterListener.class, listener);
  }

  public EventListener[] getFilterListeners() {
    return eventListenerList.getListeners(FilterListener.class);
  }

  protected void fireFilterEvent(FilterEvent evt, FilterListenerMethod listenerMethod) {
    EventListener[] eventListeners = getFilterListeners();

    for (EventListener l : eventListeners) {
      FilterListener listener = (FilterListener) l;

      switch (listenerMethod) {
        case SEARCH :
          listener.search(evt);
          break;
        case SEARCH_ALL :
          listener.searchAll(evt);
          break;
        case RESET_FILTER :
          listener.resetFilter(evt);
          break;
      }
    }
  }

  protected void btnAddClicked(int row) {
    changeFiltered(row, curFiltersTableModel, allFiltersTableModel);
  }

  protected void btnRemClicked(int row) {
    GUIFilterBean filterBean = (GUIFilterBean) allFiltersTableModel.getElementAt(row);

    propertyFilterMap.get(filterBean).reset();

    changeFiltered(row, allFiltersTableModel, curFiltersTableModel);
  }

  // --------------------------------------------------------------------------------

  protected void changeFiltered(int row, //
      FilterTableModel delModel, FilterTableModel addModel) {

    addModel.add(delModel.getDataList().remove(row));

    Collections.<Object> sort(addModel.getDataList(), //
        new BeanComparator(GUIFilterBean.ORDER));

    allFiltersTableModel.currPageChanged();
    curFiltersTableModel.currPageChanged();
  }

  // ----------------------------------------
  // FilterTableModel
  // ----------------------------------------

  protected TableColModel initTableColumnModel(boolean editable) { // TODO:
    TableColModel ret = new TableColModel();

    Extent extent = new Extent(editable ? 100 : 730);

    TableColumn tableColumn;

    tableColumn = new TableColumn();
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());
    tableColumn.setDataCellRenderer(new LabelCellRenderer());
    tableColumn.setWidth(extent);
    ret.getTableColumnList().add(tableColumn); // TODO:

    if (editable) {
      tableColumn = new TableColumn();
      tableColumn.setHeadCellRenderer(new LabelCellRenderer());
      tableColumn.setDataCellRenderer(getTableCellRendererFilter());
      tableColumn.setWidth(new Extent(630));
      ret.getTableColumnList().add(tableColumn); // TODO:
    }

    tableColumn = new TableColumn();
    tableColumn.setHeadCellRenderer(new LabelCellRenderer());

    if (editable) {
      tableColumn.setDataCellRenderer(getTableCellRendererRem());
    } else {
      tableColumn.setDataCellRenderer(getTableCellRendererAdd());
    }
    tableColumn.setWidth(new Extent(9));
    ret.getTableColumnList().add(tableColumn); // TODO:

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected CellRenderer getTableCellRendererFilter() {
    return new BaseCellRenderer() {
      @Override
      public Component getCellRenderer( //
          ETable table, Object value, int col, int row) {
        return (Component) propertyFilterMap.get(value);
      }
    };
  }

  class FilterTableModel extends BaseTableDtaModel {

    public FilterTableModel() {
      setDataMode(EnumDataMode.INMEMORY);
      setPageSize(Integer.MAX_VALUE);
    }

    // TODO: Ugly
    @Override
    public Object getValue(int col, int row) {
      return super.getElementAt(row);
    }
  }
}
