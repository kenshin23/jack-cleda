/*
 * Created on 13/04/2007
 */
package com.minotauro.echo.desktop;

import nextapp.echo.app.WindowPane;

/**
 * @author Demián Gutierrez
 */
public interface DesktopContentPane {

  public void setProcessContext(WindowPane form, ProcessContext processContext);

  public ProcessContext getProcessContext(WindowPane form);

  // --------------------------------------------------------------------------------

  //  public void addForm(WindowPane form);
  //
  //  public void addForm(WindowPane form, //
  //      WindowPane parent);

  public void addForm(
      final WindowPane parent, final WindowPane newfrm,
      final ProcessContextMode pcmode);

  public void delForm(WindowPane form);
}
