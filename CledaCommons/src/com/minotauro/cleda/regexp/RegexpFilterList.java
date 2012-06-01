/*
 * Created on 21/03/2008
 */
package com.minotauro.cleda.regexp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.minotauro.cleda.regexp.RegexpFilter.RegexpFilterMode;
import com.minotauro.cleda.util.ToString;

/**
 * @author Demián Gutierrez
 */
public class RegexpFilterList<T> {

  protected List<RegexpFilter> regexpFilterList = //
  new ArrayList<RegexpFilter>();

  protected ToString<T> toString;

  // --------------------------------------------------------------------------------

  public RegexpFilterList(ToString<T> toString) {
    this.toString = toString;
  }

  // --------------------------------------------------------------------------------

  public void addRegexpFilter(RegexpFilter regexpFilter) {
    regexpFilterList.add(regexpFilter);
  }

  // --------------------------------------------------------------------------------

  protected boolean filter(T data) {

    // ----------------------------------------
    // Apply all filters to the given element
    // ----------------------------------------

    boolean include = false;
    boolean exclude = false;

    for (RegexpFilter filter : regexpFilterList) {
      RegexpFilterMode mode = filter.keep( //
          toString.toString(data));

      if (mode == RegexpFilterMode.INCLUDE) {
        include = true;
      }

      if (mode == RegexpFilterMode.EXCLUDE) {
        exclude = true;
      }
    }

    // ----------------------------------------
    // Check result
    // ----------------------------------------

    return !include || exclude;
  }

  // --------------------------------------------------------------------------------

  public void filter(List<T> dataList) {

    // ----------------------------------------
    // Check all elements in the list
    // ----------------------------------------

    Iterator<T> itt = dataList.iterator();

    while (itt.hasNext()) {
      if (filter(itt.next())) {
        itt.remove();
      }
    }
  }
}
