/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.Border;
import nextapp.echo.app.Color;
import nextapp.echo.app.Column;
import nextapp.echo.app.Component;
import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.layout.GridLayoutData;
import nextapp.echo.extras.app.TabPane;
import nextapp.echo.extras.app.layout.TabPaneLayoutData;

/**
 * @author Demián Gutierrez
 */
public class FormGUILayout {

  protected Map<BaseModel, Component> componentByBaseModelMap = //
  new LinkedHashMap<BaseModel, Component>();

  // TODO: migration to TabPane worked, test well and clean up
  //protected DefaultTabModel defaultTabModel;
  protected TabPane tabPane;

  protected FormModel formModel;

  protected Component component;

  // --------------------------------------------------------------------------------

  public FormGUILayout() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public FormModel getFormModel() {
    return formModel;
  }

  public void setFormModel(FormModel formModel) {
    this.formModel = formModel;
  }

  // --------------------------------------------------------------------------------
  // GUI Creation Methods
  // --------------------------------------------------------------------------------

  public Component getComponent() {
    if (component == null) {
      component = initComponent();
    }

    return component;
  }

  // --------------------------------------------------------------------------------

  protected Component initComponent() {

    // ----------------------------------------
    // Check showTabs / model inconsistency
    // ----------------------------------------

    if (formModel.getChildrenList().size() > 1 && //
        !formModel.isShowTabs()) {
      throw new RuntimeException( //
          "formModel.getChildrenList().size() > 1 && " + //
              "!formModel.isShowTabs()");
    }

    // ----------------------------------------
    // Init with no tabs
    // ----------------------------------------

    if (!formModel.isShowTabs()) {
      return initTabComponent( //
      (TabModel) formModel.getChildrenList().get(0));
    }

    // ----------------------------------------
    // Init with tabs
    // ----------------------------------------

    tabPane = new TabPane();

    for (BaseModel baseModel : formModel.getChildrenList()) {
      Component component = initTabComponent( //
      (TabModel) baseModel);

      componentByBaseModelMap.put(baseModel, component);
    }

    return tabPane;
  }

  // --------------------------------------------------------------------------------

  protected Component initTabComponent(TabModel tabModel) {
    //    if (!formModel.isShowTabs()) {
    //      ret.setBorder( //
    //      new Border(1, Color.BLACK, Border.STYLE_SOLID));
    //      ret.setBackground(Color.WHITE);
    //    }

    Grid grid = new Grid(2);
    grid.setWidth(new Extent(100, Extent.PERCENT));
    grid.setColumnWidth(0, new Extent(30, Extent.PERCENT));
    grid.setColumnWidth(1, new Extent(70, Extent.PERCENT));
    grid.setInsets(new Insets(6, 6, 6, 6));
    //    ret.add(grid);
    grid.setBorder( //
    new Border(1, Color.BLACK, Border.STYLE_SOLID));
    grid.setBackground(Color.WHITE);

    for (BaseModel baseModel : tabModel.getChildrenList()) {
      initSectionComponent((SectionModel) baseModel, grid);
    }

    //    return ret;
    return grid;
  }

  // --------------------------------------------------------------------------------

  protected void initSectionComponent(SectionModel sectionModel, Grid grid) {
    initSectionLabelComponent(sectionModel, grid);
    initSectionFieldComponent(sectionModel, grid);
  }

  // --------------------------------------------------------------------------------

  protected void initSectionLabelComponent(SectionModel sectionModel, Grid grid) {
    if (sectionModel.getLabelCmp() == null) {
      return;
    }

    GridLayoutData gld = new GridLayoutData();
    gld.setColumnSpan(2);

    Component labelCmp = (Component) sectionModel.getLabelCmp();
    labelCmp.setLayoutData(gld);
    grid.add(labelCmp);
  }

  // --------------------------------------------------------------------------------

  protected void initSectionFieldComponent(SectionModel sectionModel, Grid grid) {
    for (BaseModel baseModel : sectionModel.getChildrenList()) {
      initFieldComponent((FieldModel) baseModel, grid);
    }
  }

  // --------------------------------------------------------------------------------

  protected void initFieldComponent(FieldModel fieldModel, Grid grid) {

    // ----------------------------------------
    // Add label/field to the GUI
    // ----------------------------------------

    GridLayoutData gld = new GridLayoutData();
    gld.setAlignment(Alignment.ALIGN_TOP);

    Component labelCmp = (Component) fieldModel.getLabelCmp();
    labelCmp.setLayoutData(gld);
    grid.add(labelCmp);

    Component fieldCmp = (Component) fieldModel.getFieldCmp();
    fieldCmp.setLayoutData(gld);
    grid.add(fieldCmp);
  }

  // --------------------------------------------------------------------------------
  // Update (Visible / Enable) Methods
  // --------------------------------------------------------------------------------

  public void updateGUI() {

    // ----------------------------------------
    // Check showTabs / model inconsistency
    // ----------------------------------------

    if (formModel.getChildrenList().size() > 1 && //
        !formModel.isShowTabs()) {
      throw new RuntimeException( //
          "formModel.getChildrenList().size() > 1 && " + //
              "!formModel.isShowTabs()");
    }

    // ----------------------------------------
    // Update with no tabs
    // ----------------------------------------

    if (!formModel.isShowTabs()) {
      updateTabComponentGUI( //
      (TabModel) formModel.getChildrenList().get(0));
      return;
    }

    // ----------------------------------------
    // Update with tabs
    // ----------------------------------------

    tabPane.removeAll();

    for (Entry<BaseModel, Component> entry : //
    componentByBaseModelMap.entrySet()) {

      if (!entry.getKey().isVisible()) {
        continue;
      }

      TabPaneLayoutData tpld = new TabPaneLayoutData();
      tpld.setCloseEnabled(false);

      tpld.setTitle(entry.getKey().getLabelVal().toString());

      Column col = new Column();
      col.setInsets(new Insets(5, 5, 5, 5));
      col.setLayoutData(tpld);
      tabPane.add(col);

      col.add(entry.getValue());

      //      entry.getValue().setLayoutData(tpld);
      //      tabPane.add(entry.getValue());
      //      tabPane.setInsets(new Insets(50, 50, 50, 50));

      updateTabComponentGUI((TabModel) entry.getKey());
    }
  }

  // --------------------------------------------------------------------------------

  protected void updateTabComponentGUI(TabModel tabModel) {
    for (BaseModel baseModel : tabModel.getChildrenList()) {
      updateSectionComponentGUI((SectionModel) baseModel);
    }
  }

  // --------------------------------------------------------------------------------

  protected void updateSectionComponentGUI(SectionModel sectionModel) {
    updateSectionLabelComponentGUI(sectionModel);
    updateSectionFieldComponentGUI(sectionModel);
  }

  // --------------------------------------------------------------------------------

  protected void updateSectionLabelComponentGUI(SectionModel sectionModel) {
    // XXX: I don't think this makes sense, label does not gets
    // visible / invisible on the fly, it just gets added or not :/
    //    if (sectionModel.getLabel() == null) {
    //      return;
    //    }
    //
    //    sectionModel.getLabel().setVisible(sectionModel.isVisible());
  }

  // --------------------------------------------------------------------------------

  protected void updateSectionFieldComponentGUI(SectionModel sectionModel) {
    for (BaseModel baseModel : sectionModel.getChildrenList()) {
      updateFieldComponentGUI((FieldModel) baseModel);
    }
  }

  // --------------------------------------------------------------------------------
  // XXX: I don't think this makes sense, label does not gets
  // visible / invisible or enable / disable on the fly,
  // it just gets added or not :/
  // --------------------------------------------------------------------------------

  protected void updateFieldComponentGUI(FieldModel fieldModel) {
    fieldModel.setLabelVisible(fieldModel.isVisible());
    fieldModel.setLabelEnabled(fieldModel.isEnabled());

    fieldModel.setFieldVisible(fieldModel.isVisible());
    fieldModel.setFieldEnabled(fieldModel.isEnabled());
  }
}
