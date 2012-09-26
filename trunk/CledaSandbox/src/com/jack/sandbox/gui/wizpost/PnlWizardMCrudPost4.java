

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





public class PnlWizardMCrudPost4 extends PnlWizardBase {

  
  protected FieldModel fmMInnerPost;

  // --------------------------------------------------------------------------------

  public PnlWizardMCrudPost4(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------


	sectionModel.addChild(fmMInnerPost = initinnerPostC());
	
	}
  
  
  
    protected FieldModel initinnerPostC() {

	   EInnerButton innInner = new EInnerButton(
	   	  FrmMInnerPostList.class,
	      (MBase)data,
	      _PropMCrudPost.INNER_POST_CLIST,
	      _PropMInnerPost.CRUD_POST_REF,
	      parent);

	   innInner.setEditMode(EnumEditMode.UPDATE);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.innerPostC()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_PropMCrudPost.INNER_POST_CLIST);

	   return fmInner;
	 }
  
  
  
}
