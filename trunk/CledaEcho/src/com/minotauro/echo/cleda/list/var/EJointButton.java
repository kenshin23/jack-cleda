/*
 * Created on 20/08/2008
 */
package com.minotauro.echo.cleda.list.var;

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
import com.minotauro.echo.base.AcceptCancelListener;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.desktop.ProcessContextMode;
import com.minotauro.echo.util.GUIStyles;
import com.minotauro.echo.util.ImageReferenceCache;

/**
 * @author Demián Gutierrez
 */
public class EJointButton extends Column {

  protected EnumEditMode editMode = EnumEditMode.SELECT;

  // --------------------------------------------------------------------------------

  protected Class<? extends FrmListJointBase> listform;

  protected EJointModel jointmod;

  protected WindowPane editform;

  // --------------------------------------------------------------------------------

  protected TextField txtInfoText;

  protected Button btnAction;

  // --------------------------------------------------------------------------------

  public EJointButton(
      Class<? extends FrmListJointBase> listform,
      EJointModel/*                   */jointmod,
      WindowPane/*                    */editform) {

    // ----------------------------------------

    this.listform = listform;
    this.jointmod = jointmod;
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
          ? dataList.size() + " " + _I18NEJointButton.elementsInTheList()
          : null;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected void setInfoText(String infoText) {
    if (infoText == null) {
      txtInfoText.setText(_I18NEJointButton.select());
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
    jointmod.cancel();

    FrmListJointBase frmListJointBase =
        (FrmListJointBase) listform.newInstance();

    frmListJointBase.getAcceptCancelProxy().addAcceptCancelListener(
        new AcceptCancelListener() {
          public void btnAcceptClicked(ActionEvent evt) {
            EJointButton.this.btnAcceptClicked(evt);
          }

          public void btnCancelClicked(ActionEvent evt) {
            EJointButton.this.btnCancelClicked(evt);
          }
        });

    BaseAppInstance.getDesktop().addForm(
        editform, frmListJointBase, ProcessContextMode.PARENT);

    frmListJointBase.init( //
        editMode, jointmod);
  }

  // --------------------------------------------------------------------------------

  protected void btnAcceptClicked(ActionEvent evt) {
    jointmod.accept();

    setInfoText(getInfoText());
  }

  // --------------------------------------------------------------------------------

  protected void btnCancelClicked(ActionEvent evt) {
    jointmod.cancel();
  }

  // --------------------------------------------------------------------------------

  @SuppressWarnings("unchecked")
  protected List<MBase> initDataList() throws Exception {
    return (List<MBase>) PropertyUtils.getProperty( //
        jointmod.getLftObj(), jointmod.getLftLstProperty());
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
