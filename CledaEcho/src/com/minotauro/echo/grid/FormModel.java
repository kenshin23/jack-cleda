/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

/**
 * @author Demián Gutierrez
 */
public class FormModel extends BaseModel {

  protected boolean enabled = true;

  protected boolean showTabs;

  // --------------------------------------------------------------------------------

  public FormModel() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  // --------------------------------------------------------------------------------

  public boolean isShowTabs() {
    return showTabs;
  }

  public void setShowTabs(boolean showTabs) {
    this.showTabs = showTabs;
  }

  // --------------------------------------------------------------------------------
  // BaseModel Methods
  // --------------------------------------------------------------------------------

  public BaseModel getParent() {
    throw new UnsupportedOperationException();
  }

  public void setParent(BaseModel parent) {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public String getKey() {
    throw new UnsupportedOperationException();
  }

  public void setKey(String key) {
    throw new UnsupportedOperationException();
  }

  // --------------------------------------------------------------------------------

  public boolean isProxyEnabled() {
    return enabled;
  }

  public boolean isProxyVisible() {
    return true;
  }

  // --------------------------------------------------------------------------------

  public SectionModel initSingleSectionModel() {
    if (childrenList.size() > 0) {
      throw new IllegalStateException(
          "childrenList.size() > 0");
    }

    setShowTabs(false);

    TabModel tabModel = new TabModel();
    addChild(tabModel);

    SectionModel sectionModel = new SectionModel();
    tabModel.addChild(sectionModel);

    return sectionModel;
  }
}
