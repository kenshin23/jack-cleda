package com.minotauro.cleda.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** 
 * @author Alejandro Salas
 * <br> Created on 12/03/2008 
 */
public class LabelValueBeanUtils {

  private LabelValueBeanUtils() {
    // Empty
  }

  public static <E extends Enum<E>> List<LabelValueBean<Integer>> getLblValueBeanFromEnum(Class<E> enumClass) {
    return getLblValueBeanFromEnumList(Arrays.asList(enumClass.getEnumConstants()));
  }

  public static <E extends Enum<E>> List<LabelValueBean<Integer>> getLblValueBeanFromEnumList(List<E> enumList) {
    List<LabelValueBean<Integer>> labelValueList = new ArrayList<LabelValueBean<Integer>>();

    for (E enumElement : enumList) {
      labelValueList.add(new LabelValueBean<Integer>(enumElement.toString(), enumElement.ordinal()));
    }

    return labelValueList;
  }
}