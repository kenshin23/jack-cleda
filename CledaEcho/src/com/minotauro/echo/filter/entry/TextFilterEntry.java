package com.minotauro.echo.filter.entry;

import nextapp.echo.app.TextField;

import com.minotauro.echo.filter.base.EFilterEntryBase;
import com.minotauro.query.QueryCreator.FilterType;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 9, 2007
 */
public class TextFilterEntry extends EFilterEntryBase {

  private TextField txtFilter;

  public TextFilterEntry(GUIFilterBean filterBean) {
    super(filterBean);
    load();
  }

  @Override
  protected void load() {
    fillRelationCbo(cboRelation1, FilterType.STRING_SEARCH);
  }

  @Override
  protected void initGUI() {
    super.initGUI();

    txtFilter = new TextField();
    add(txtFilter);
  }

  @Override
  public void sync() {
    super.sync();
    filterBean.setValue1(txtFilter.getText());
  }

  @Override
  public void reset() {
    super.reset();
    txtFilter.setText("");
  }
}
