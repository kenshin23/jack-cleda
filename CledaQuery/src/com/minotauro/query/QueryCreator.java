package com.minotauro.query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

import com.minotauro.base.model.MBase;
import com.minotauro.query.bean.FilterBean;
import com.minotauro.query.bean.base.CodeFilterBeanImpl;
import com.minotauro.query.i18n.RelationTypeI18N;
import com.minotauro.user.model.MUser;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 5, 2007
 */
public class QueryCreator {

  public enum FilterType {
    RELATION_RANGE1, RELATION_RANGE2, STRING_SEARCH;
  }

  // XXX: Beware, ordinals are being used. Do not change order!
  public enum RelationType {
    NO_FILTER, CUSTOM, EQUAL(" = :"), NOT_EQUAL(" != :"), GREATER(" > :"), GREATER_EQUAL(" >= :"), LESS(" < :"), LESS_EQUAL(
        " <= :"), LIKE(" LIKE :"), IS_NULL(" IS NULL"), NOT_NULL(" IS NOT NULL");

    private String operator;

    private RelationType() {
      // Empty
    }

    private RelationType(String operator) {
      this.operator = operator;
    }

    public String getOperator() {
      return operator;
    }

    public static List<RelationType> getRelationList(FilterType filterType) {
      List<RelationType> ret = new ArrayList<RelationType>();
      switch (filterType) {
        case RELATION_RANGE1 :
          ret.add(RelationType.EQUAL);
          fillStandardRelations(ret);
          break;
        case RELATION_RANGE2 :
          ret.add(RelationType.NO_FILTER);
          fillStandardRelations(ret);
          break;
        case STRING_SEARCH :
          ret.add(RelationType.EQUAL);
          ret.add(RelationType.LIKE);
          break;
      }
      return ret;
    }

    private static void fillStandardRelations(List<RelationType> list) {
      list.add(RelationType.GREATER);
      list.add(RelationType.GREATER_EQUAL);
      list.add(RelationType.LESS);
      list.add(RelationType.LESS_EQUAL);
    }

    @Override
    public String toString() {
      switch (this) {
        case NO_FILTER :
          return RelationTypeI18N.noFilter();
        case EQUAL :
          return RelationTypeI18N.equal();
        case GREATER :
          return RelationTypeI18N.greater();
        case GREATER_EQUAL :
          return RelationTypeI18N.greaterEqual();
        case LESS :
          return RelationTypeI18N.less();
        case LESS_EQUAL :
          return RelationTypeI18N.lessEqual();
        case LIKE :
          return RelationTypeI18N.like();
        case IS_NULL :
          return RelationTypeI18N.isNull();
        case NOT_NULL :
          return RelationTypeI18N.notNull();
        case NOT_EQUAL :
          return RelationTypeI18N.notEqual();
        default :
          return null;
      }
    }
  }

  public static final String ITEM = "item";

  // ----------------------------------------
  // FROM x
  // SELECT COUNT(*) FROM x
  // ----------------------------------------

  private String countQuery;
  private String baseQuery;
  private String posQuery;
  private String orderBy;

  // ----------------------------------------
  // Custom code (non GUI) filter
  // ----------------------------------------

  private boolean codeFiltered;

  // ----------------------------------------
  // Disable all filters
  // ----------------------------------------

  private boolean findAll;

  private List<? extends FilterBean> filterBeanGUIList; // User (GUI) filters
  private List<FilterBean> filterBeanCodeList = new ArrayList<FilterBean>(); // User (Code) filters

  private Map<String, Object> paramMap = new HashMap<String, Object>(); // Hibernate parameters to filters

  private StringBuilder queryStrBld;

  // ----------------------------------------

  public QueryCreator() {
    // Empty
  }

  public QueryCreator(Class<? extends MBase> baseModel) {
    this(baseModel, false);
  }

  public QueryCreator(Class<? extends MBase> baseModel, boolean codeFiltered) {
    this.codeFiltered = codeFiltered;
    baseQuery = "FROM " + baseModel.getName();
  }

  // ----------------------------------------
  // Getters / Setters
  // ----------------------------------------

  public String getBaseQuery() {
    return baseQuery;
  }

  public void setBaseQuery(String baseQuery) {
    this.baseQuery = baseQuery;
  }

  // ----------------------------------------

  public String getCountQuery() {
    return countQuery;
  }

  public void setCountQuery(String countQuery) {
    this.countQuery = countQuery;
  }

  // ----------------------------------------

  public String getPosQuery() {
    return posQuery;
  }

  public void setPosQuery(String posQuery) {
    this.posQuery = posQuery;
  }

  // ----------------------------------------

  public String getOrderBy() {
    return orderBy;
  }

  public void setOrderBy(String orderBy) {
    this.orderBy = orderBy;
  }

  // ----------------------------------------

  public boolean isCodeFiltered() {
    return codeFiltered;
  }

  public void setCodeFiltered(boolean codeFiltered) {
    this.codeFiltered = codeFiltered;
  }

  // ----------------------------------------

  public List<? extends FilterBean> getFilterBeanGUIList() {
    return filterBeanGUIList;
  }

  public void setFilterBeanGUIList(List<? extends FilterBean> filterBeanGUIList) {
    this.filterBeanGUIList = filterBeanGUIList;
  }

  // ----------------------------------------

  public List<FilterBean> getFilterBeanCodeList() {
    return filterBeanCodeList;
  }

  public void setFilterBeanCodeList(List<FilterBean> filterBeanCodeList) {
    this.filterBeanCodeList = filterBeanCodeList;
  }

  // ----------------------------------------

  public boolean isFindAll() {
    return findAll;
  }

  public void setFindAll(boolean findAll) {
    this.findAll = findAll;
  }

  // ----------------------------------------

  public Map<String, Object> getParamMap() {
    return paramMap;
  }

  // ----------------------------------------
  // Utility methods
  // ----------------------------------------

  public void addCodeFilter(FilterBean filterBean) {
    filterBeanCodeList.add(filterBean);
  }

  public void addCodeFilter(String property, RelationType relationType, Object value) {
    filterBeanCodeList.add(new CodeFilterBeanImpl(property, relationType, value));
  }

  // ----------------------------------------
  // Actual work
  // ----------------------------------------

  public String createCurrQuery() {
    queryStrBld = new StringBuilder(baseQuery);

    queryStrBld.append(" AS ");
    queryStrBld.append(ITEM);
    queryStrBld.append(posQuery == null ? "" : posQuery);
    appendWhere();
    queryStrBld.append(orderBy == null ? "" : " ORDER BY " + orderBy);

    return queryStrBld.toString();
  }

  public String createRowsQuery() {
    if (countQuery != null) {
      queryStrBld = new StringBuilder(countQuery);
    } else {
      queryStrBld = new StringBuilder("SELECT COUNT(*) ");
      queryStrBld.append(baseQuery);
    }

    queryStrBld.append(" AS ");
    queryStrBld.append(ITEM);
    queryStrBld.append(posQuery == null ? "" : posQuery);
    appendWhere();

    return queryStrBld.toString();
  }

  protected void appendWhere() {
    paramMap.clear();

    boolean whereExists = false;
    if (codeFiltered) {
      whereExists = appendQuery(whereExists, filterBeanCodeList);
    }

    if (findAll || CollectionUtils.isEmpty(filterBeanGUIList)) {
      return;
    }

    appendQuery(whereExists, filterBeanGUIList);
  }

  protected boolean appendQuery(boolean whereExists, List<? extends FilterBean> filterBeanList) {
    if (filterBeanList.isEmpty()) {
      return whereExists;
    }

    // Blocks will always be concatenated with AND. The first time around we must add a WHERE
    queryStrBld.append(whereExists ? " AND " : " WHERE ");

    // FilterBeans will be appended in blocks, so that we can have AND operated blocks and OR operated blocks
    queryStrBld.append("(");

    Iterator<? extends FilterBean> itt = filterBeanList.iterator();
    int index = 0;
    while (itt.hasNext()) {
      FilterBean filterBean = itt.next();

      queryStrBld.append(filterBean.createQuery(index));
      index = filterBean.getIndex();

      Map<String, Object> parameterMap = filterBean.getParameterMap();
      if (parameterMap != null) {
        paramMap.putAll(parameterMap);
      }

      if (itt.hasNext()) {
        queryStrBld.append(" AND ");
      }
    }

    queryStrBld.append(")");

    return true;
  }

  public static void main(String[] args) {
    QueryCreator queryCreator = new QueryCreator(MUser.class, true);
    queryCreator.addCodeFilter("b", RelationType.EQUAL, "B");
    queryCreator.addCodeFilter("c", RelationType.EQUAL, "C");

    System.out.println(queryCreator.createRowsQuery());
    System.out.println(queryCreator.createCurrQuery());

    for (Entry<String, Object> entry : queryCreator.getParamMap().entrySet()) {
      System.out.println(entry.getKey() + " : " + entry.getValue());
    }
  }
}