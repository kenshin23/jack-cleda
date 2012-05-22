/*
 * Created on 15/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import java.util.List;

import nextapp.echo.app.Extent;
import nextapp.echo.app.Grid;
import nextapp.echo.app.Insets;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.list.DefaultListModel;

import com.minotauro.cleda.util.LabelValueBean;
import com.minotauro.echo.base.DlgAcceptCancel;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.validator.impl.NotBlankValidator;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class FrmSelectTask extends DlgAcceptCancel {

  protected List<MWrkTransSet> wrkTransSetList;

  protected SelectField cboTask;

  // --------------------------------------------------------------------------------

  public FrmSelectTask(List<MWrkTransSet> wrkTransSetList) {
    init(_I18NFrmSelectTask.title(), 300, 150);
    this.wrkTransSetList = wrkTransSetList;
    initGUI();

    splTop.setSeparatorPosition(new Extent(0));
    splMid.setSeparatorPosition(new Extent(40));

    setColMidOverflow(false);
  }

  // --------------------------------------------------------------------------------

  protected void initGUI() {
    Grid grid = new Grid(2);
    grid.setWidth(new Extent(100, Extent.PERCENT));
    grid.setColumnWidth(0, new Extent(50, Extent.PERCENT));
    grid.setColumnWidth(1, new Extent(50, Extent.PERCENT));
    grid.setInsets(new Insets(6, 6, 6, 6));
    colMid.add(grid);

    // ----------------------------------------

    EFieldLabel lblTask = new EFieldLabel(_I18NFrmSelectTask.task());
    grid.add(lblTask);

    cboTask = new SelectField();
    grid.add(cboTask);

    DefaultListModel dlm = (DefaultListModel) cboTask.getModel();
    dlm.add(_I18NFrmSelectTask.select());

    validatorList.add(new NotBlankValidator(
        _I18NFrmSelectTask.task(), cboTask, _I18NFrmSelectTask.select()));

    // ----------------------------------------

    for (MWrkTransSet wrkTransSet : wrkTransSetList) {
      dlm.add(new LabelValueBean<MWrkTransSet>( //
          wrkTransSet.getNetTransSetRef().getName(), wrkTransSet));
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  public MWrkTransSet getSelectedWrkTransSet() {
    LabelValueBean<MWrkTransSet> labelValueBean = //
    (LabelValueBean<MWrkTransSet>) cboTask.getSelectedItem();

    return labelValueBean.getValue();
  }
}
