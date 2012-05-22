package com.minotauro.echo.filter.base;

import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Row;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.query.QueryCreator.FilterType;
import com.minotauro.query.QueryCreator.RelationType;
import com.minotauro.query.bean.GUIFilterBean;
import com.minotauro.query.bean.base.GUIFilterBeanImpl;
import com.minotauro.query.gui.FilterEntryEditor;

/** 
 * @author Alejandro Salas 
 * <br> Created on May 9, 2007
 */
public abstract class EFilterEntryBase extends Row implements FilterEntryEditor {

  protected SelectField cboRelation1;
  protected SelectField cboRelation2;

  protected Component component1;
  protected Component component2;

  protected GUIFilterBeanImpl filterBean;

  protected EFilterEntryBase() {
    // Empty
  }

  public EFilterEntryBase(GUIFilterBean filterBean) {
    init(filterBean);
  }

  protected void init(GUIFilterBean filterBean) {
    this.filterBean = (GUIFilterBeanImpl) filterBean;
    initGUI();
  }

  protected abstract void load();

  protected void initGUI() {
    setCellSpacing(new Extent(6, Extent.PX));

    cboRelation1 = new SelectField();
    cboRelation1.setWidth(new Extent(140));
    cboRelation1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent arg0) {
        cboRelation1Changed();
      }
    });
    add(cboRelation1);
  }

  //----------------------------------------
  // Utility methods for enabling and disabling combos in a range type situation
  //----------------------------------------

  protected void cboRelation1Changed() {
    if (component1 == null || component2 == null) {
      return;
    }

    boolean visible = cboRelation1.getSelectedItem() != RelationType.NO_FILTER;
    component1.setVisible(visible);

    visible = visible && cboRelation1.getSelectedItem() != RelationType.EQUAL;
    cboRelation2.setVisible(visible);

    visible = visible && cboRelation2.getSelectedItem() != RelationType.NO_FILTER;
    component2.setVisible(visible);
  }

  protected void cboRelation2Changed() {
    component2.setVisible(cboRelation2.getSelectedItem() != RelationType.NO_FILTER);
  }

  //----------------------------------------
  // Utility methods for adding standard search entries in relation combos
  //---------------------------------------- 

  protected void fillRelationCbo(SelectField cbo, FilterType relation) {
    if (!(cbo.getModel() instanceof DefaultListModel)) {
      return;
    }

    DefaultListModel model = (DefaultListModel) cbo.getModel();
    for (RelationType relationType : RelationType.getRelationList(relation)) {
      model.add(relationType);
    }

    cbo.setSelectedIndex(0);
  }

  //----------------------------------------
  // Misc
  //---------------------------------------- 

  /**
   * Leaves the underlying filterBean ready for use. Usually by synchronizing the filter's GUI with the bean
   */
  public void sync() {
    filterBean.reset();
    filterBean.setRelationType1((RelationType) cboRelation1.getSelectedItem());
  }

  /**
   * Resets both GUI and FilterBean, leaving it just as if the filter was just created.
   */
  public void reset() {
    if (cboRelation1 != null) {
      cboRelation1.setSelectedIndex(0);
      cboRelation1Changed();
    }

    if (cboRelation2 != null) {
      cboRelation2.setSelectedIndex(0);
      cboRelation2Changed();
    }

    filterBean.reset();
  }
}