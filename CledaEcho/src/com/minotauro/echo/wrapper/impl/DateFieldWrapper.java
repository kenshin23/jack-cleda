package com.minotauro.echo.wrapper.impl;

import java.util.Calendar;

import com.minotauro.echo.wrapper.base.ComponentWrapper;

import echopoint.jquery.DateField;

// TODO: migration to echo3 worked, test well and clean up
// TODO: also this needs to be improved
// TODO: may be the Calendar<->Date could be implemented in EDateField???
public class DateFieldWrapper implements ComponentWrapper {

  public DateFieldWrapper() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public Object getValue(Object component) {
    DateField dateField = (DateField) component;

    //return dateField.getSelectedDate();

    Calendar ret = Calendar.getInstance();
    ret.setTime(dateField.getDate());

    return ret;
  }

  public void setValue(Object component, Object value) {
    DateField dateField = (DateField) component;
    Calendar val = (Calendar) value;

    //dateField.setSelectedDate(val);
    dateField.setDate(val.getTime());
  }

  // --------------------------------------------------------------------------------

  public boolean getEnabled(Object component) {
    DateField dateField = (DateField) component;

    return dateField.isEnabled();
  }

  public void setEnabled(Object component, boolean editable) {
    DateField dateField = (DateField) component;
    dateField.setEnabled(editable);
  }

  // --------------------------------------------------------------------------------
  // Dummy
  // --------------------------------------------------------------------------------

  @Override
  public boolean getVisible(Object component) {
    return true;
  }

  @Override
  public void setVisible(Object component, boolean visible) {
    // Empty
  }
}
