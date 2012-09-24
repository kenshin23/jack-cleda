

package com.minotauro.sandbox.gui.mcrudpost;

import java.util.List;
import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;

import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.minnerpostc.FrmMInnerPostList;
import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;
import com.minotauro.sandbox.gui.msingjointpostb.FrmMSingJointPostBList;
import com.minotauro.sandbox.model.*;

//TODO:plantilla agregar dinamicamente imports.





public class PnlWizardMCrudPost1 extends PnlWizardBase {

  protected FieldModel fmName;
  protected FieldModel fmBody;
  

  // --------------------------------------------------------------------------------

  public PnlMCrudPostEdit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrudPost) data).getName(); 
    setTitle(_I18NFrmMCrudPostEdit.title(name == null ? "-" : name)); 

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------


	TextField txtName = new TextField();
    txtName.setWidth(new Extent(204));

    fmName = new FieldModel();
    fmName.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmName.setFieldCmp(txtName);
    fmName.setKey(_PropMCrudPost.NAME);
    fmName.setProperty(_PropMCrudPost.NAME);
 	fmName.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.name(), txtName))
	;

	sectionModel.addChild(fmName);
	

	TextField txtBody = new TextField();
    txtBody.setWidth(new Extent(204));

    fmBody = new FieldModel();
    fmBody.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.body()));
    fmBody.setFieldCmp(txtBody);
    fmBody.setKey(_PropMCrudPost.BODY);
    fmBody.setProperty(_PropMCrudPost.BODY);
 	fmBody.getValidatorList()
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.body(), txtBody))
	;

	sectionModel.addChild(fmBody);
	

	
	}
}
