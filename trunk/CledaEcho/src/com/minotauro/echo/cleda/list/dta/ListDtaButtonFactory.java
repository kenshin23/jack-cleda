/*
 * Created on 04/12/2008
 */
package com.minotauro.echo.cleda.list.dta;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.command.CommandStateProxy;

/**
 * @author Demián Gutierrez
 */
public class ListDtaButtonFactory {

  protected final EnumEditMode editMode;

  protected final String basePriv;

  // --------------------------------------------------------------------------------

  public ListDtaButtonFactory(EnumEditMode editMode, String basePriv) {
    this.editMode = editMode;
    this.basePriv = basePriv;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initBtnAddDta( //
      CommandListener commandListener) {

    CommandFactory ret = new CommandFactory( //
        "images/icons/add_e_24x24.gif", //
        "images/icons/add_d_24x24.gif", //
        _I18NListDtaButtonFactory.addnew());

    ret.setEnabledStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.create") || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initBtnDelDta( //
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory( //
        "images/icons/del_e_24x24.gif", //
        "images/icons/del_d_24x24.gif", //
        _I18NListDtaButtonFactory.delete());

    ret.setEnabledStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.delete") || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndVieDta( //
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory( //
        "images/icons/vie_e_16x16.gif", //
        "images/icons/vie_d_16x16.gif", //
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.select") || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndEdtDta( //
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory( //
        "images/icons/edt_e_16x16.gif", //
        "images/icons/edt_d_16x16.gif", //
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.update") || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndDelDta( //
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory( //
        "images/icons/del_e_16x16.gif", //
        "images/icons/del_d_16x16.gif", //
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.delete") || //
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }
}
