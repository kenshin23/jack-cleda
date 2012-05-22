/*
 * Created on 15/06/2007
 */
package com.minotauro.echo.cleda.list.wrk;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.command.CommandFactory;
import com.minotauro.echo.command.CommandListener;
import com.minotauro.echo.command.CommandStateProxy;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class ListDocButtonFactory {

  protected final EnumEditMode editMode;

  protected final String basePriv;

  // --------------------------------------------------------------------------------

  public ListDocButtonFactory(EnumEditMode editMode, String basePriv) {
    this.editMode = editMode;
    this.basePriv = basePriv;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndVieWrk(
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory(
        "images/icons/vie_e_16x16.gif",
        "images/icons/vie_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".wrk.select") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndEdtWrk(
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory(
        "images/icons/edt_e_16x16.gif",
        "images/icons/edt_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".wrk.update") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndVieDoc(
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory(
        "images/icons/vie_e_16x16.gif",
        "images/icons/vie_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".dta.select") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return privs;
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandFactory initRndEdtDoc(
      CommandListener commandListener) {
    CommandFactory ret = new CommandFactory(
        "images/icons/edt_e_16x16.gif",
        "images/icons/edt_d_16x16.gif",
        null);

    ret.setVisibleStateProxy(new CommandStateProxy() {
      public boolean getCommandState() {
        boolean privs = basePriv == null ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".doc.update") ||
            BaseAppInstance.getUser().hasPriv(basePriv + ".all");
        return editMode != EnumEditMode.SELECT && privs;
      }
    });

    ret.setEnabledStateProxy(new CommandStateProxy() {
      public boolean getCommandState(ETable table, Object value, int col, int row) {
        MDocument document =
            (MDocument) table.getTableDtaModel().getElementAt(row);
        return isEnabledEdt(document);
      }
    });

    ret.setCommandListener(commandListener);

    return ret;
  }

  // --------------------------------------------------------------------------------

  protected boolean isEnabledEdt(MDocument document) {
    MWorkflow workflow = document.getWorkflowRef();

    for (MActor actor : workflow.getActorList()) {
      MUser user = actor.getUserRef();

      if (!user.equals(BaseAppInstance.getUser())) {
        continue;
      }

      for (MWrkTransSet wrkTransSet : actor.getWrkTransSetList()) {
        if (wrkTransSet.getStatus() == MWrkTransSet.STATUS_ENABLED) {
          return true;
        }
      }
    }

    return false;
  }
}
