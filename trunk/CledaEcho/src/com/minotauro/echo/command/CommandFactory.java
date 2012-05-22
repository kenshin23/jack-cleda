/*
 * Created on 21/10/2008
 */
package com.minotauro.echo.command;

import nextapp.echo.app.Button;
import nextapp.echo.app.Component;
import nextapp.echo.app.button.AbstractButton;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import com.minotauro.echo.table.base.CellRenderer;
import com.minotauro.echo.table.base.ETable;
import com.minotauro.echo.table.renderer.BaseCellRenderer;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;

/**
 * @author Demián Gutierrez
 */
public class CommandFactory {

  protected CommandStateProxy enabledStateProxy;
  protected CommandStateProxy visibleStateProxy;

  protected CommandListener commandListener;

  protected String enaIcon;
  protected String disIcon;
  protected String lblText;

  // --------------------------------------------------------------------------------

  public CommandFactory(
      String enaIcon, String disIcon, String lblText) {

    this.enaIcon = enaIcon;
    this.disIcon = disIcon;
    this.lblText = lblText;
  }

  // --------------------------------------------------------------------------------

  public CellRenderer getTableCellRenderer() {
    return new BaseCellRenderer() {
      @Override
      public Component getCellRenderer( //
          ETable table, Object value, int col, int row) {
        return getButton(table, value, col, row);
      }
    };
  }

  // --------------------------------------------------------------------------------

  public AbstractButton getButton() {
    return getButton(null, null, -1, -1);
  }

  // --------------------------------------------------------------------------------

  protected AbstractButton getButton( //
      final ETable table, final Object value, final int col, final int row) {

    if (commandListener == null) {
      throw new IllegalStateException( //
          "commandListener == null");
    }

    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference(enaIcon));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference(disIcon));

    ret.setStyleName(GUIStyles.DEFAULT);

    if (lblText != null) {
      ret.setText(lblText);
    }

    ret.setEnabled(enabledStateProxy != null //
        ? enabledStateProxy.getCommandState(table, value, col, row) && //
            enabledStateProxy.getCommandState()
        : true);

    ret.setVisible(visibleStateProxy != null //
        ? visibleStateProxy.getCommandState(table, value, col, row) && //
            visibleStateProxy.getCommandState()
        : true);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        commandListener.commandClicked(row);
      }
    });

    return ret;
  }

  // --------------------------------------------------------------------------------

  public CommandStateProxy getEnabledStateProxy() {
    return enabledStateProxy;
  }

  public void setEnabledStateProxy(CommandStateProxy enabledStateProxy) {
    this.enabledStateProxy = enabledStateProxy;
  }

  // --------------------------------------------------------------------------------

  public CommandStateProxy getVisibleStateProxy() {
    return visibleStateProxy;
  }

  public void setVisibleStateProxy(CommandStateProxy visibleStateProxy) {
    this.visibleStateProxy = visibleStateProxy;
  }

  // --------------------------------------------------------------------------------

  public CommandListener getCommandListener() {
    return commandListener;
  }

  public void setCommandListener(CommandListener commandListener) {
    this.commandListener = commandListener;
  }
}
