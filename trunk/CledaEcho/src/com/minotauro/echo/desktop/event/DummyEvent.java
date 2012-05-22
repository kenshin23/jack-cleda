/*
 * Created on 21/08/2011
 */
package com.minotauro.echo.desktop.event;

import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.desktop.AppDesktop;
import com.minotauro.echo.desktop.AppDesktopEvent;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.i18n.util.ReflectionI18N;
import com.minotauro.menu.model.MMenu;

/**
 * @author Demián Gutierrez
 */
public class DummyEvent implements AppDesktopEvent {

  @Override
  public void run(AppDesktop desktop, MMenu menu) {
    String tit = ReflectionI18N.i18n(menu.getI18nCls(), menu.getI18nKey());

    String msg;

    String i18nMsgKey = menu.getMetaData("i18nMsgKey");

    if (i18nMsgKey != null) {
      msg = ReflectionI18N.i18n(menu.getI18nCls(), i18nMsgKey);
    } else {
      msg = menu.getRoute() + " / " + menu.getOrder();
    }

    // ----------------------------------------------------------------
    // MessageBox.msgBox(tit, msg, null, 350, 120, MessageBox.OK_OPT);
    // ----------------------------------------------------------------

    // ----------------------------------------------------------------
    // Used to duplicate a windows adding/closing crash
    // ----------------------------------------------------------------
    MessageBox messageBox =
        new MessageBox(tit, msg, null, 350, 120, MessageBox.OK_OPT);
    messageBox.setClosable(true);
    messageBox.setModal(false);

    BaseAppInstance.getDesktop().addForm(null, messageBox, ProcessContextMode.IGNORE);
    // ----------------------------------------------------------------
  }
}
