/*
 * Created on 20/08/2008
 */
package com.minotauro.echo.cleda.list.var;

import java.util.ArrayList;
import java.util.List;

import nextapp.echo.app.Button;
import nextapp.echo.app.Column;
import nextapp.echo.app.Row;
import nextapp.echo.app.TextField;
import nextapp.echo.app.WindowPane;
import nextapp.echo.app.event.ActionEvent;
import nextapp.echo.app.event.ActionListener;

import org.apache.commons.beanutils.PropertyUtils;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.app.BaseAppInstance;
import com.minotauro.echo.base.AcceptCancelAdapter;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;

/**
 * @author Demián Gutierrez
 */
public class EInnerButton extends Column {

  protected EnumEditMode editMode = EnumEditMode.SELECT;

  // --------------------------------------------------------------------------------

  protected Class<? extends FrmListInnerBase> listform;

  protected MBase parmodel;

  protected String listprop;
  protected String crossref;

  protected WindowPane editform;

  // --------------------------------------------------------------------------------

  protected TextField txtInfoText;

  protected Button btnAction;

  // --------------------------------------------------------------------------------

  public EInnerButton(
      Class<? extends FrmListInnerBase> listform,
      MBase/*                         */parmodel,
      String/*                        */listprop,
      String/*                        */crossref,
      WindowPane/*                    */editform) {

    // ----------------------------------------

    this.listform = listform;
    this.parmodel = parmodel;
    this.listprop = listprop;
    this.crossref = crossref;
    this.editform = editform;

    // ----------------------------------------

    Row dummyRow = new Row();
    add(dummyRow);

    // ----------------------------------------

    txtInfoText = new TextField();
    txtInfoText.setEnabled(false);
    setInfoText(getInfoText());
    dummyRow.add(txtInfoText);

    // ----------------------------------------

    dummyRow.add(getBtnAction());
  }

  // --------------------------------------------------------------------------------

  protected Button getBtnAction() {
    Button ret = new Button(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/select_e_16x16.gif"));
    ret.setDisabledIcon(ImageReferenceCache.getInstance(). //
        getImageReference("images/icons/select_e_16x16.gif"));

    ret.setStyleName(GUIStyles.DEFAULT);

    ret.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        btnEditList();
      }
    });
    return ret;
  }

  // --------------------------------------------------------------------------------

  protected String getInfoText() {
    try {
      List<MBase> dataList = initDataList();

      return !dataList.isEmpty()
          ? dataList.size() + " " + _I18NEInnerButton.elementsInTheList()
          : null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void setInfoText(String infoText) {
    if (infoText == null) {
      txtInfoText.setText(_I18NEInnerButton.select());
    } else {
      txtInfoText.setText(infoText);
    }
  }

  // --------------------------------------------------------------------------------

  protected void btnEditList() {
    try {
      failsafeBtnEditList();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  protected void failsafeBtnEditList() throws Exception {
    List<MBase> dataList = initDataList();

    FrmListInnerBase frmListInnerBase =
        (FrmListInnerBase) listform.newInstance();

    frmListInnerBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelAdapter() {
          public void btnAcceptClicked(ActionEvent evt) {
            EInnerButton.this.btnAcceptClicked(evt);
          }
        });

    BaseAppInstance.getDesktop().addForm(
        editform, frmListInnerBase, ProcessContextMode.PARENT);

    frmListInnerBase.init( //
        editMode, new ArrayList<MBase>(dataList), parmodel);
  }

  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked(ActionEvent evt) {
    FrmListInnerBase frmInnerBase =
        (FrmListInnerBase) evt.getSource();

    try {
      List<MBase> dataList = initDataList();

      dataList.clear();

      for (MBase base : frmInnerBase.getDataList()) {
        PropertyUtils.setProperty(base, crossref, parmodel);

        dataList.add(base);
      }

      setInfoText(getInfoText());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  protected List<MBase> initDataList() throws Exception {
    return (List<MBase>) PropertyUtils.getProperty( //
        parmodel, listprop);
  }

  // --------------------------------------------------------------------------------

  @Override
  public void setEnabled(boolean newValue) {
    editMode = newValue ? EnumEditMode.UPDATE : EnumEditMode.SELECT;
  }

  // --------------------------------------------------------------------------------

  public EnumEditMode getEditMode() {
    return editMode;
  }

  public void setEditMode(EnumEditMode editMode) {
    this.editMode = editMode;
  }
}
