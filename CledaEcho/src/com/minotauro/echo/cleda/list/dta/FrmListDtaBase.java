/*
 * Created on 13/05/2007
 */
package com.minotauro.echo.cleda.list.dta;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.cleda.list.FrmListBase;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.desktop.ProcessContext;
import com.minotauro.echo.filter.base.FilterListener;

/**
 * @author Demián Gutierrez
 */
public abstract class FrmListDtaBase extends FrmListBase implements FilterListener {

  // --------------------------------------------------------------------------------

  protected Class<? extends FrmEditBase> frmEditDtaClass;

  protected String basePriv;

  // --------------------------------------------------------------------------------

  protected CommandListener handlerAddDta = new HandlerAddDta(this);
  protected CommandListener handlerDelAll = new HandlerDelAll(this);

  protected CommandListener handlerVieDta = new HandlerVieDta(this);
  protected CommandListener handlerEdtDta = new HandlerEdtDta(this);
  protected CommandListener handlerDelOne = new HandlerDelOne(this);

  // --------------------------------------------------------------------------------

  protected CommandFactory cfcBtnAddDta;
  protected CommandFactory cfcBtnDelDta;

  protected CommandFactory cfcRndEdtDta;
  protected CommandFactory cfcRndVieDta;
  protected CommandFactory cfcRndDelDta;

  // --------------------------------------------------------------------------------

  public FrmListDtaBase() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initAll() {
    ListDtaButtonFactory listDtaButtonFactory = //
    new ListDtaButtonFactory(editMode, basePriv);

    cfcBtnAddDta = listDtaButtonFactory.initBtnAddDta(handlerAddDta);
    cfcBtnDelDta = listDtaButtonFactory.initBtnDelDta(handlerDelAll);

    cfcRndVieDta = listDtaButtonFactory.initRndVieDta(handlerVieDta);
    cfcRndEdtDta = listDtaButtonFactory.initRndEdtDta(handlerEdtDta);
    cfcRndDelDta = listDtaButtonFactory.initRndDelDta(handlerDelOne);

    super.initAll();
  }

  // --------------------------------------------------------------------------------

  protected boolean deleteAllowed(MBase base) {
    return base.getDeleteAllowed();
  }

  // --------------------------------------------------------------------------------
  // pre / pos Delete | TODO: implement as delete handler's hooks
  // --------------------------------------------------------------------------------

  protected void preDelete(ProcessContext processContext, MBase base) {
    // Empty
  }

  protected void posDelete(ProcessContext processContext, MBase base) {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // Misc Methods
  // --------------------------------------------------------------------------------

  protected abstract MBase initMBase();

  // --------------------------------------------------------------------------------

  public Class<? extends FrmEditBase> getFrmEditDtaClass() {
    return frmEditDtaClass;
  }
}
