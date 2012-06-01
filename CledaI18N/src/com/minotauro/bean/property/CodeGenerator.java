/*
 * Created on 04/11/2006
 */
package com.minotauro.bean.property;

import java.beans.PropertyDescriptor;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.SystemUtils;

import com.minotauro.cleda.util.FreeMarkerUtil;

/**
 * @author Demián Gutierrez
 */
public class CodeGenerator {

  private Class<?> clazz;

  private String pck;
  private String out;

  // --------------------------------------------------------------------------------

  public CodeGenerator(Class<?> clazz, String pck, String out) {
    this.clazz = clazz;

    this.pck = pck;
    this.out = out;
  }

  // --------------------------------------------------------------------------------

  protected String propertyNameToUpperCase(String propertyName) {
    StringBuffer ret = new StringBuffer();

    char prevChar = 'a';

    for (int i = 0; i < propertyName.length(); i++) {
      char currChar = propertyName.charAt(i);

      if (!Character.isUpperCase(prevChar) &&
          Character.isUpperCase(currChar) && i > 0) {
        ret.append("_");
      }

      ret.append(Character.toUpperCase(currChar));

      prevChar = currChar;
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public void run() throws Exception {
    List<PropertyBean> propertyBeanList = new ArrayList<PropertyBean>();

    PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(clazz);

    for (PropertyDescriptor descriptor : propertyDescriptors) {
      Method rd = descriptor.getReadMethod();
      Method wr = descriptor.getWriteMethod();

      if (rd == null || wr == null) {
        continue;
      }

      PropertyBean propertyBean = new PropertyBean();
      propertyBean.propertyName = propertyNameToUpperCase(descriptor.getName());
      propertyBean.propertyValue = descriptor.getName();

      propertyBeanList.add(propertyBean);
    }

    ObjectBean objectBean = new ObjectBean();
    objectBean.setClsName("_Prop" + clazz.getSimpleName());
    objectBean.setPckName(pck);
    objectBean.setPropertyBeanList(propertyBeanList);

    FileWriter fileWriter = new FileWriter( //
        out + SystemUtils.FILE_SEPARATOR + objectBean.getClsName() + ".java");

    FreeMarkerUtil.getInstance().process("/templates/class-prop.ftl", objectBean, fileWriter);
    fileWriter.close();
  }
}
