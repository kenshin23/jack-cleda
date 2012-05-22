package com.minotauro.echo.filter.entry;

import java.util.Calendar;

import nextapp.echo.app.Extent;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.cleda.util.CalendarUtils;
import com.minotauro.echo.filter.base.EFilterEntryBase;
import com.minotauro.query.QueryCreator.FilterType;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;

import echopoint.jquery.DateField;

/** 
 * @author Alejandro Salas
 * <br> Created on May 9, 2007
 */
public class DateFilterEntry extends EFilterEntryBase {

  // TODO: migration to echo3 worked, test well and clean up
  // TODO: also this needs to be improved
  private DateField dateFilter1;
  private DateField dateFilter2;

  public DateFilterEntry(GUIFilterBean filterBean) {
    super(filterBean);
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

    component1 = dateFilter1 = new DateField();
    //dateFilter1.getTextField().setWidth(new Extent(110));
    //dateFilter1.setPopUpAlwaysOnTop(true);
    dateFilter1.setWidth(new Extent(110));
    add(dateFilter1);

    cboRelation2 = new SelectField();
    cboRelation2.setWidth(new Extent(140));
    cboRelation2.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cboRelation2Changed();
      }
    });
    add(cboRelation2);

    component2 = dateFilter2 = new DateField();
    //dateFilter2.getTextField().setWidth(new Extent(110));
    //dateFilter2.setPopUpAlwaysOnTop(true);
    dateFilter2.setWidth(new Extent(110));
    add(dateFilter2);
  }

  @Override
  public void sync() {
    super.sync();

    //filterBean.setValue1(dateFilter1.getSelectedDate());
    filterBean.setValue1(dateFilter1.getDate());

    RelationType relationType = (RelationType) cboRelation2.getSelectedItem();
    if (relationType != RelationType.NO_FILTER) {
      filterBean.setRelationType2(relationType);
      //filterBean.setValue2(dateFilter2.getSelectedDate());
      filterBean.setValue2(dateFilter2.getDate());
    }
  }

  @Override
  public void reset() {
    super.reset();

    //Calendar cal = CalendarUtils.getDateOnlyCalendar();
    //dateFilter1.setSelectedDate(cal);
    //dateFilter2.setSelectedDate((Calendar) cal.clone());

    Calendar cal1 = CalendarUtils.getDateOnlyCalendar();
    Calendar cal2 = (Calendar) cal1.clone();

    dateFilter1.setDate(cal1.getTime());
    dateFilter2.setDate(cal2.getTime());
  }
}