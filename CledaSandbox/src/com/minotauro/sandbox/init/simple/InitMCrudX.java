package com.minotauro.sandbox.init.simple;

import java.text.NumberFormat;

import org.hibernate.Session;

import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.sandbox.model.MCrudA;
import com.minotauro.sandbox.model.MCrudB;
import com.minotauro.sandbox.model.MCrudC;

public class InitMCrudX {

  public static void createMCrudA(String prefix, int amount) {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    for (int i = 0; i < amount; i++) {
      MCrudA crudA = new MCrudA();

      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(0);
      nf.setMinimumIntegerDigits(2);

      crudA.setName(prefix + " " + nf.format(i));
      crudA.setDesc(prefix + " " + nf.format(i));

      session.saveOrUpdate(crudA);
    }

    session.getTransaction().commit();
    session.close();
  }

  public static void createMCrudB(String prefix, int amount) {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    for (int i = 0; i < amount; i++) {
      MCrudB crudB = new MCrudB();

      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(0);
      nf.setMinimumIntegerDigits(2);

      crudB.setName(prefix + " " + nf.format(i));
      crudB.setDesc(prefix + " " + nf.format(i));

      session.saveOrUpdate(crudB);
    }

    session.getTransaction().commit();
    session.close();
  }

  public static void createMCrudC(String prefix, int amount) {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    for (int i = 0; i < amount; i++) {
      MCrudC crudC = new MCrudC();

      NumberFormat nf = NumberFormat.getInstance();
      nf.setMaximumFractionDigits(0);
      nf.setMinimumIntegerDigits(2);

      crudC.setName(prefix + " " + nf.format(i));
      crudC.setDesc(prefix + " " + nf.format(i));

      session.saveOrUpdate(crudC);
    }

    session.getTransaction().commit();
    session.close();
  }
}
