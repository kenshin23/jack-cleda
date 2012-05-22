/*
 * Created on 01/09/2008
 */
package com.minotauro.echo.grid;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author Demián Gutierrez
 */
public class FormPopulator {

  protected FormModel formModel;

  // --------------------------------------------------------------------------------

  public FormPopulator() {
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

  public void updateGUIFromData(Object data) throws Exception {
    List<FieldModel> fieldModelList = new ArrayList<FieldModel>();
    initFieldModelList(fieldModelList, formModel);

    for (FieldModel fieldModel : fieldModelList) {
      if (fieldModel.getProperty() == null) {
        continue;
      }

      fieldModel.setFieldVal(
          PropertyUtils.getProperty(data, fieldModel.getProperty()));
    }
  }

  // --------------------------------------------------------------------------------

  public void updateDataFromGUI(Object data) throws Exception {
    List<FieldModel> fieldModelList = new ArrayList<FieldModel>();
    initFieldModelList(fieldModelList, formModel);

    Map<String, Object> propertyValueMap = //
    new HashMap<String, Object>();

    for (FieldModel fieldModel : fieldModelList) {
      if (fieldModel.getProperty() == null) {
        continue;
      }

      if (!fieldModel.isEnabled()) {
        continue;
      }

      propertyValueMap.put(fieldModel.getProperty(),
          fieldModel.getFieldVal());
    }

    BeanUtils.populate(data, propertyValueMap);
  }

  // --------------------------------------------------------------------------------

  protected void initFieldModelList( //
      List<FieldModel> fieldModelList, BaseModel rootbaseModel) //
      throws Exception {

    if (rootbaseModel instanceof FieldModel) {
      fieldModelList.add((FieldModel) rootbaseModel);
    } else {
      for (BaseModel currBaseModel : rootbaseModel.getVisibleChildrenList()) {
        initFieldModelList(fieldModelList, currBaseModel);
      }
    }
  }
}
