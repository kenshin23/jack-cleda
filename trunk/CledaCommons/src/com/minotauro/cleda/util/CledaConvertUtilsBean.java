/*
 * Created on 27/03/2008
 */
package com.minotauro.cleda.util;

/** 
 * @author Alejandro Salas 
 */
public class CledaConvertUtilsBean {

  // --------------------------------------------------------------------------------
  // TODO: DMI: Don't delete right now, check it first, may be I'm missing something
  // --------------------------------------------------------------------------------

  //  private static final BeanUtilsBean beanUtilsBean;

  // --------------------------------------------------------------------------------
  // Translates to an enum from the ordinal or the name
  // --------------------------------------------------------------------------------

  //  static {
  //    ConvertUtilsBean convertUtilsBean = new ConvertUtilsBean() {
  //
  //      @Override
  //      @SuppressWarnings("rawtypes")
  //      public Converter lookup(Class clazz) {
  //        Converter ret = super.lookup(clazz);
  //
  //        // ----------------------------------------
  //        // Default behavior first
  //        // ----------------------------------------
  //
  //        if (ret != null) {
  //          return ret;
  //        }
  //
  //        // ----------------------------------------
  //        // No converter, check if it's an enum
  //        // ----------------------------------------
  //
  //        if (!Enum.class.isAssignableFrom(clazz)) {
  //          return null;
  //        }
  //
  //        // ----------------------------------------
  //        // A new converter
  //        // ----------------------------------------
  //
  //        Converter converter = new Converter() {
  //          @SuppressWarnings("unchecked")
  //          public Object convert(Class clazz, Object src) {
  //            try {
  //              int ordinal = Integer.parseInt(src.toString());
  //              return clazz.getEnumConstants()[ordinal];
  //            } catch (Exception e) {
  //              return Enum.valueOf(clazz, (String) src);
  //            }
  //          }
  //        };
  //
  //        return converter;
  //      }
  //    };
  //
  //    beanUtilsBean = new BeanUtilsBean( //
  //        convertUtilsBean, new PropertyUtilsBean());
  //  }

  // --------------------------------------------------------------------------------

  //  private CledaConvertUtilsBean() {
  //    // Empty
  //  }

  // --------------------------------------------------------------------------------

  //  @SuppressWarnings("rawtypes")
  //  public static void populate(Object bean, Map properties) //
  //      throws IllegalAccessException, InvocationTargetException {
  //    beanUtilsBean.populate(bean, properties);
  //  }
}
