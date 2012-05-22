/*
 * Created on 01/09/2011
 */
package com.minotauro.echo.desktop.event;

import com.minotauro.echo.base.EnumDataMode;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class DocEditEvent implements AppDesktopEvent {

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    try {
      failsafeRun(desktop, menu);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  public void failsafeRun(AppDesktop desktop, MMenu menu) throws Exception {
    String frm = menu.getMetaData("frm");

    FrmEditWrk frmEditWrk =
        (FrmEditWrk) Class.forName(frm).newInstance();

    desktop.addForm(
        null, frmEditWrk, ProcessContextMode.CREATE);

    frmEditWrk.init(EnumDataMode.DATABASE);
  }
}
