package com.minotauro.echo.util;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.minotauro.cleda.model.CledaConnector;

public class CodeGenerator {

  private static CodeGenerator instance;

  private Map<String, Long> codeByName = new HashMap<String, Long>();

  public CodeGenerator() {
    // Empty
  }

  public static CodeGenerator getInstance() {
    if (instance == null) {
      instance = new CodeGenerator();
    }

    return instance;
  }

  private long initCode(String hibernate, Class<?> clazz, String field) {
    Session session = CledaConnector.getInstance().getSession(hibernate);
    session.beginTransaction();

    String hql = "SELECT MAX(a.{0}) FROM {1} AS a";
    hql = MessageFormat.format(hql, new Object[]{field, clazz.getName()});

    Query query = session.createQuery(hql);
    Long ret = (Long) query.uniqueResult();

    if (ret == null) {
      ret = 0l;
    }

    session.getTransaction().commit();
    session.close();

    return ret;
  }

  private long getYear(long initialCode) {
    return initialCode / 10000000l;
  }

  private long getCode(long initialCode) {
    long year = getYear(initialCode);
    year *= 10000000l;
    return initialCode - year;
  }

  public synchronized long generateCode(String hibernate, Class<?> clazz, String field) {
    String key = hibernate + ":" + clazz + ":" + field;

    Long codeAsObject = codeByName.get(key);

    if (codeAsObject == null) {
      codeAsObject = initCode(hibernate, clazz, field);
    }

    long codeAsPrimitive = codeAsObject;

    long year = getYear(codeAsPrimitive);
    long code = getCode(codeAsPrimitive);

    // ----------------------------------------
    // TODO: do something (No more codes left)
    // ----------------------------------------

    if (code + 1 >= 10000000l) {
      code = 0;
    }

    Calendar cal = Calendar.getInstance();

    if (cal.get(Calendar.YEAR) != year) {
      codeAsPrimitive = cal.get(Calendar.YEAR) * 10000000l;
    }

    codeByName.put(key, ++codeAsPrimitive);

    return codeAsPrimitive;
  }
}
