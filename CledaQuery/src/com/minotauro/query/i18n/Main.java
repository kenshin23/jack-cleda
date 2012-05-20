package com.minotauro.query.i18n;

import com.minotauro.i18n.base.BaseI18NMain;
import com.minotauro.query.QueryCreator.RelationType;

/** 
 * @author Alejandro Salas 
 * <br> Created on Dec 20, 2007
 */
public class Main extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    Main m = new Main();
    m.i18n(RelationType.class.getSimpleName());
  }
}