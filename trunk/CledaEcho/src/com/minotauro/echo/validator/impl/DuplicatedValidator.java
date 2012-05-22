package com.minotauro.echo.validator.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.minotauro.base.model.MBase;
import com.minotauro.base.model._PropMBase;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.validator.base.BaseValidator;

public class DuplicatedValidator extends BaseValidator {

  protected List<FieldModel> fieldModelList =
      new ArrayList<FieldModel>();

  protected MBase data;

  // --------------------------------------------------------------------------------

  public DuplicatedValidator(MBase data) {
    super(null, null);

    this.data = data;
  }

  // --------------------------------------------------------------------------------

  public void add(FieldModel fieldModel) {
    fieldModelList.add(fieldModel);
  }

  // --------------------------------------------------------------------------------

  @Override
  public String doValidate(
      Object value, ProcessContext processContext) {

    String retmsg = null;

    Criteria criteria = processContext.getSession().createCriteria(data.getClass());
    criteria.setProjection(Projections.property(_PropMBase.ID));

    Iterator<FieldModel> itt = fieldModelList.iterator();

    while (itt.hasNext()) {
      FieldModel fieldModel = (FieldModel) itt.next();

      criteria.add(Restrictions.eq(
          fieldModel.getProperty(), fieldModel.getFieldVal()));
    }

    Integer id = (Integer) criteria.uniqueResult();

    if (id != null && !id.equals(data.getId())) {
      retmsg = _I18NBaseValidator.fieldsAlreadyExists(createNameFieldList());
    }

    return retmsg;
  }

  // --------------------------------------------------------------------------------

  protected String createNameFieldList() {
    StringBuffer ret = new StringBuffer();

    Iterator<FieldModel> itt = fieldModelList.iterator();

    while (itt.hasNext()) {
      FieldModel fieldModel = (FieldModel) itt.next();

      ret.append(fieldModel.getLabelVal());

      if (itt.hasNext()) {
        ret.append(" / ");
      }
    }

    return ret.toString();
  }
}
