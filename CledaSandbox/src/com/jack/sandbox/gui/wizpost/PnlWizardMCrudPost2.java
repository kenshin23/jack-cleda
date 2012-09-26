

package com.jack.sandbox.gui.wizpost;

import java.util.List;
import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.base.model.MBase;
import com.minotauro.echo.base.EnumEditMode;
import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.cleda.wizard.FrmWizardBase;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;

import com.minotauro.sandbox.gui.*;
import com.minotauro.sandbox.gui.minnerpostc.*;
import com.minotauro.sandbox.gui.mcrudpost.*;
import com.minotauro.sandbox.gui.mmultjointmposta.*;
import com.minotauro.sandbox.gui.msingjointpostb.*;
import com.minotauro.sandbox.model.*;

//TODO:plantilla agregar dinamicamente imports.





public class PnlWizardMCrudPost2 extends PnlWizardBase {

  protected FieldModel fmDesc;
  

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost2(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------


	ETextAreaEx txtDesc = new ETextAreaEx();
    txtDesc.setWidth(new Extent(204));

    fmDesc = new FieldModel();
    fmDesc.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.desc()));
    fmDesc.setFieldCmp(txtDesc);
    fmDesc.setKey(_PropMCrudPost.DESC);
    fmDesc.setProperty(_PropMCrudPost.DESC);
 	fmDesc.getValidatorList()
			.add(ConditionalValidator		
				.CHAIN(true)
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
			.add(ConditionalValidator		
				.CHAIN(true)
   		.add(new NotEmptyValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
   		.add(new IntegerValidator(_I18NFrmMCrudPostEdit.desc(), txtDesc))
				)
				)
	;

	sectionModel.addChild(fmDesc);
	

	
	}
}
