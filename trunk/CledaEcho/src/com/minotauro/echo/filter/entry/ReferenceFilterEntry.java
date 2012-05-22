package com.minotauro.echo.filter.entry;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.Insets;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.layout.RowLayoutData;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.cleda.util.LabelValueBean;
import com.minotauro.cleda.util.LabelValueBeanUtils;
import com.minotauro.echo.filter.base.EFilterEntryBase;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 9, 2007
 */
public class ReferenceFilterEntry extends EFilterEntryBase {

  protected SelectField cboReference;
  private List<? extends LabelValueBean<? extends Object>> labelValueList;

  public ReferenceFilterEntry(GUIFilterBean filterBean) {
    super(filterBean);
  }

  @Override
  protected void initGUI() {
    setInsets(new Insets(6, 6, 6, 6));
    setCellSpacing(new Extent(6, Extent.PX));

    RowLayoutData rld = new RowLayoutData();
    rld.setWidth(new Extent(300, Extent.PX));

    cboReference = new SelectField();
    cboReference.setLayoutData(rld);
    cboReference.setWidth(new Extent(300, Extent.PX));
    add(cboReference);
  }

  @Override
  public void load() {
    DefaultListModel model = (DefaultListModel) cboReference.getModel();
    for (LabelValueBean labelValueBean : labelValueList) {
      model.add(labelValueBean);
    }
  }

  @Override
  public void sync() {
    filterBean.reset();
    filterBean.setRelationType1(RelationType.EQUAL);
    LabelValueBean<? extends Object> lvb = (LabelValueBean<? extends Object>) cboReference.getSelectedItem();
    filterBean.setValue1(lvb.getValue());
  }

  @Override
  public void reset() {
    cboReference.setSelectedIndex(0);
  }

  public void setLabelValueList(List<? extends LabelValueBean<? extends Object>> labelValueList) {
    this.labelValueList = labelValueList;
    load();
  }

  public <E extends Enum<E>> void setValuesFromEnum(Class<E> enumClass) {
    this.labelValueList = LabelValueBeanUtils.getLblValueBeanFromEnum(enumClass);
    load();
  }
}