package com.minotauro.echo.filter.entry;

import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.cleda.util.LabelValueBean;
import com.minotauro.echo.filter.base.EFilterEntryBase;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 22, 2007
 */
public class BooleanFilterEntry extends EFilterEntryBase {

  public BooleanFilterEntry(GUIFilterBean filterBean) {
    super(filterBean);
    load();
  }

  @Override
  protected void load() {
    cboRelation1.setSelectedIndex(0);
    DefaultListModel model = (DefaultListModel) cboRelation1.getModel();

    model.add(new LabelValueBean<Boolean>(Boolean.TRUE.toString(), Boolean.TRUE));
    model.add(new LabelValueBean<Boolean>(Boolean.FALSE.toString(), Boolean.FALSE));
  }

  @Override
  @SuppressWarnings("unchecked")
  public void sync() {
    super.reset();

    filterBean.setRelationType1(RelationType.EQUAL);
    LabelValueBean<Boolean> valueBean = (LabelValueBean<Boolean>) cboRelation1.getSelectedItem();
    filterBean.setValue1(valueBean.getValue());
  }
}