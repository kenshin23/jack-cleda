package com.minotauro.echo.filter.entry;

import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.echo.beans.ENumberEditor;
import com.minotauro.echo.filter.base.EFilterEntryBase;
import com.minotauro.i18n.bundled._NumbFormatNumb;
import com.minotauro.query.QueryCreator.FilterType;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 9, 2007
 */
public class NumberFilterEntry extends EFilterEntryBase {

  public enum NumberFilterDataType {
    INTEGER, DECIMAL;
  }

  private ENumberEditor numberFilter1;
  private ENumberEditor numberFilter2;

  private NumberFilterDataType numberFilterDataType;

  public NumberFilterEntry(GUIFilterBean filterBean, NumberFilterDataType numberFilterDataType) {
    this.numberFilterDataType = numberFilterDataType;
    init(filterBean);
    load();
  }

  @Override
  protected void load() {
    fillRelationCbo(cboRelation1, FilterType.RELATION_RANGE1);
    fillRelationCbo(cboRelation2, FilterType.RELATION_RANGE2);
    cboRelation1Changed();
  }

  @Override
  protected void initGUI() {
    super.initGUI();

    switch (numberFilterDataType) {
      case INTEGER :
        component1 = numberFilter1 = new ENumberEditor(_NumbFormatNumb.getNumberFormatter());
        component2 = numberFilter2 = new ENumberEditor(_NumbFormatNumb.getNumberFormatter());
        break;
      case DECIMAL :
        component1 = numberFilter1 = new ENumberEditor();
        component2 = numberFilter2 = new ENumberEditor();
        break;
    }
    add(numberFilter1);

    cboRelation2 = new SelectField();
    cboRelation2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cboRelation2Changed();
      }
    });
    add(cboRelation2);
    add(numberFilter2);
  }

  @Override
  public void sync() {
    super.sync();

    filterBean.setValue1(numberFilter1.getNumber());

    RelationType relationType = (RelationType) cboRelation2.getSelectedItem();

    if (relationType != RelationType.NO_FILTER) {
      filterBean.setRelationType2(relationType);
      filterBean.setValue2(numberFilter2.getNumber());
    }
  }

  @Override
  public void reset() {
    super.reset();
    numberFilter1.setNumber(0);
    numberFilter2.setNumber(0);
  }
}
