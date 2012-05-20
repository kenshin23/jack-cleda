/*
 * Created on 04/02/2008
 */
package com.minotauro.cleda.task.prop;

import com.minotauro.cleda.task.model.MTask;
import com.minotauro.i18n.base.BaseI18NMain;

/**
 * @author Demián Gutierrez
 */
public class Main extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    Main m = new Main();

    m.prop(MTask.class);
  }
}
