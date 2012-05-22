/*
 * Created on 23/09/2008
 */
package com.minotauro.echo.cleda.list;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nextapp.echo.app.event.EventListenerList;

import org.hibernate.Query;

import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.table.base.PageableModel;
import com.minotauro.echo.table.base.TableDtaModel;
import com.minotauro.echo.table.event.PageableModelEvent;
import com.minotauro.echo.table.event.PageableModelEvtProxy;
import com.minotauro.echo.table.event.TableDtaModelEvent;

/**
 * @author Demián Gutierrez
 */
public class BaseTableDtaModel extends TableDtaModel implements PageableModel {

  protected PageableModelEvtProxy pageableModelEvtProxy = //
  new PageableModelEvtProxy(new EventListenerList());

  protected Map<String, Object> queryParameterMap;

  protected List<Object> dataList =
      new ArrayList<Object>();

  protected boolean editable = true;

  protected EnumDataMode dataMode = EnumDataMode.INMEMORY;

  protected ProcessContext processContext;

  protected int pageSize = 10;
  protected int currPage = 0;

  protected String currQuery;
  protected String rowsQuery;

  protected int totalRows;

  // --------------------------------------------------------------------------------

  public BaseTableDtaModel() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Map<String, Object> getQueryParameterMap() {
    return queryParameterMap;
  }

  public void setQueryParameterMap(Map<String, Object> queryParameterMap) {
    this.queryParameterMap = queryParameterMap;
  }

  // --------------------------------------------------------------------------------

  public List<Object> getDataList() {
    return dataList;
  }

  public void setDataList(List<Object> dataList) {
    this.dataList = dataList;
  }

  // --------------------------------------------------------------------------------

  public EnumDataMode getDataMode() {
    return dataMode;
  }

  public void setDataMode(EnumDataMode dataMode) {
    this.dataMode = dataMode;
  }

  // --------------------------------------------------------------------------------

  public ProcessContext getProcessContext() {
    return processContext;
  }

  public void setProcessContext(ProcessContext processContext) {
    this.processContext = processContext;
  }

  // --------------------------------------------------------------------------------

  public String getCurrQuery() {
    return currQuery;
  }

  public void setCurrQuery(String currQuery) {
    this.currQuery = currQuery;
  }

  // --------------------------------------------------------------------------------

  public String getRowsQuery() {
    return rowsQuery;
  }

  public void setRowsQuery(String rowsQuery) {
    this.rowsQuery = rowsQuery;
  }

  // --------------------------------------------------------------------------------

  public void add(Object t) {
    dataList.add(t);
    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
  }

  public void add(Object t, int index) {
    dataList.add(index, t);
    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
  }

  // --------------------------------------------------------------------------------

  public Object del(int index) {
    Object t = dataList.remove(index);
    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
    return t;
  }

  public Object del(Object element) {
    return del(dataList.indexOf(element));
  }

  // --------------------------------------------------------------------------------

  public void clear() {
    dataList.clear();
    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
  }

  // --------------------------------------------------------------------------------

  protected void updateCurrPage() {
    if (getCurrPage() + 1 >= getTotalPages()) {
      setCurrPage(getTotalPages() - 1);
    }

    setCurrPage( //
    getCurrPage() >= 0 ? getCurrPage() : 0);
  }

  // --------------------------------------------------------------------------------

  public void currPageChanged() {
    switch (dataMode) {
      case INMEMORY :
        currPageChangedInMemory();
        break;
      case DATABASE :
        currPageChangedDatabase();
        break;
    }
  }

  // --------------------------------------------------------------------------------

  protected void currPageChangedInMemory() {
    updateCurrPage();
    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
    pageableModelEvtProxy.fireActionEvent( //
        new PageableModelEvent(this));
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  protected void currPageChangedDatabase() {
    processContext.begTransaction();

    Query query;

    // ----------------------------------------
    // Count
    // ----------------------------------------

    query = processContext.getSession().createQuery(rowsQuery);
    fillQueryParameters(query);
    totalRows = ((Long) query.uniqueResult()).intValue();

    updateCurrPage();

    // ----------------------------------------
    // Data
    // ----------------------------------------

    clear();

    if (getRealFromPagedRow(0) >= totalRows && currPage > 0) {
      currPage = totalRows / pageSize;
    }

    query = processContext.getSession().createQuery(currQuery);
    query.setFirstResult(currPage * pageSize);
    query.setMaxResults(pageSize);
    fillQueryParameters(query);

    dataList.addAll(query.list());

    tableDtaModelEvtProxy.fireActionEvent( //
        new TableDtaModelEvent(this));
    pageableModelEvtProxy.fireActionEvent( //
        new PageableModelEvent(this));

    processContext.comTransaction();
  }

  // --------------------------------------------------------------------------------

  protected void fillQueryParameters(Query query) {
    if (queryParameterMap == null) {
      return;
    }

    for (Entry<String, Object> entry : queryParameterMap.entrySet()) {
      query.setParameter(entry.getKey(), entry.getValue());
    }
  }

  // --------------------------------------------------------------------------------
  // PageableModel
  // --------------------------------------------------------------------------------

  public PageableModelEvtProxy getPageableModelEvtProxy() {
    return pageableModelEvtProxy;
  }

  // --------------------------------------------------------------------------------

  public int getRealFromPagedRow(int row) {
    return getCurrPage() * getPageSize() + row;
  }

  public int getPagedFromRealRow(int row) {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public int/* */getPageSize() {
    return pageSize;
  }

  public void/**/setPageSize( //
      int pageSize) {
    this.pageSize = pageSize;
  }

  // --------------------------------------------------------------------------------

  public int/* */getCurrPage() {
    return currPage;
  }

  public void/**/setCurrPage( //
      int currPage) {
    this.currPage = currPage;
  }

  // --------------------------------------------------------------------------------

  public int getTotalPages() {
    if (getTotalRows() == 0) {
      return 1;
    }

    return (int) Math.ceil( //
        ((double) getTotalRows() / getPageSize()));
  }

  // --------------------------------------------------------------------------------

  public int getTotalRows() {
    if (dataMode == EnumDataMode.INMEMORY) {
      return dataList.size();
    }

    return totalRows;
  }

  // --------------------------------------------------------------------------------
  // TableDtaModel
  // --------------------------------------------------------------------------------

  public int getRowCount() {
    if (dataMode == EnumDataMode.DATABASE) {
      return dataList.size();
    }

    return Math.min(dataList.size() - getRealFromPagedRow(0), pageSize);
  }

  // --------------------------------------------------------------------------------

  public Object getElementAt(int row) {
    if (dataMode == EnumDataMode.INMEMORY) {
      row = getRealFromPagedRow(row);
    }

    return dataList.get(row);
  }

  // --------------------------------------------------------------------------------

  public boolean getEditable() {
    return editable;
  }

  public void setEditable(boolean editable) {
    this.editable = editable;
  }
}
