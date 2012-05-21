/*
 * Created on 16/09/2011
 */
package com.minotauro.sandbox.gui.duserreg;

import java.util.List;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.sandbox.gui.user.FrmMUserProfList;
import com.minotauro.sandbox.model.DUserReg;
import com.minotauro.sandbox.model.DUserRegProf;
import com.minotauro.sandbox.model._PropDUserReg;
import com.minotauro.sandbox.model._PropDUserRegProf;

/**
 * @author Demián Gutierrez
 */
public class PnlProf extends PnlWizardBase {

  protected FieldModel fmUserProf;

  // --------------------------------------------------------------------------------

  public PnlProf(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

    sectionModel.addChild(fmUserProf = initUserProf());
  }

  // --------------------------------------------------------------------------------

  protected FieldModel initUserProf() {
    EJointModel modelJointFactory = new EJointModel();

    modelJointFactory.init((MBase) data, DUserRegProf.class, //
        _PropDUserReg.USER_REG_PROF_LIST, //
        _PropDUserRegProf.USER_REG_REF, _PropDUserRegProf.PROF_REF);

    EJointButton jntJoint = new EJointButton( //
        FrmMUserProfList.class, modelJointFactory, parent) {
      protected String getInfoText() {
        List<DUserRegProf> userRegProfList = //
        ((DUserReg) data).getUserRegProfList();

        return !userRegProfList.isEmpty() ? //
            userRegProfList.size() + " elements selected..."
            : null;
      }
    };
    jntJoint.setEditMode(EnumEditMode.UPDATE);

    FieldModel fmJoint = new FieldModel();
    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrmWizardDUserReg.prof()));
    fmJoint.setFieldCmp(jntJoint);
    fmJoint.setKey(_PropDUserReg.USER_REG_PROF_LIST);

    return fmJoint;
  }
}
