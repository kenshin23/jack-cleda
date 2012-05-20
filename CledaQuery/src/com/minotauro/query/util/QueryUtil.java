package com.minotauro.query.util;

import java.lang.reflect.Constructor;
import java.util.Map.Entry;

import org.hibernate.Query;
import org.hibernate.Session;

import com.minotauro.query.QueryCreator;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on Feb 13, 2008
 */
public class QueryUtil {

  public static Query getQuery(QueryCreator queryCreator, Session session) {
    String createQuery = queryCreator.createCurrQuery();
    Query query = session.createQuery(createQuery);
    fillQueryParams(query, queryCreator);

    return query;
  }

  public static void fillQueryParams(Query query, QueryCreator queryCreator) {
    for (Entry<String, Object> entry : queryCreator.getParamMap().entrySet()) {
      query.setParameter(entry.getKey(), entry.getValue());
    }
  }

  public static GUIFilterBean beanFromClass(Class<? extends GUIFilterBean> clazz, String propertyName, String label,
      int order) {
    GUIFilterBean bean = null;

    try {
      Constructor<? extends GUIFilterBean> constructor = clazz.getConstructor(String.class, String.class, int.class);
      bean = constructor.newInstance(propertyName, label, order);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return bean;
  }
}