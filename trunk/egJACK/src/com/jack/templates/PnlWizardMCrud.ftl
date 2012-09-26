[#ftl]	
[#macro recurseValidator validatorChoice currentAttribute]
	[#list validatorChoice as currentValidatorChoice]
		[#if currentValidatorChoice.bandera==true]
   		.add(new ${currentValidatorChoice.validator.name}(_I18NFrmMCrudPostEdit.${currentAttribute.name}(), txt${currentAttribute.name?cap_first}))
		[/#if]
		[#if currentValidatorChoice.bandera==false]	
			.add(ConditionalValidator		
			[#list currentValidatorChoice.validators.conditionalValidators as currentCondValidator]
				.${currentCondValidator.type?upper_case}(true)
				[@recurseValidator validatorChoice=currentCondValidator.chain.validatorChoice currentAttribute=currentAttribute/]
				)
			[/#list]
		[/#if]	
	[/#list]
[/#macro]


package ${package};

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





public class PnlWizard${modelName}${wizIterator} extends PnlWizardBase {

[#list attributes.group as curGroup]

[#if wizIterator == curGroup.num]


[#list curGroup.att as currentAtt]
  protected FieldModel fm${currentAtt.name?cap_first};
[/#list]
  
[#list curGroup.list as currentlist]
  protected FieldModel fm${currentlist.relationModelName};
[/#list]

  // --------------------------------------------------------------------------------

  public PnlWizard${modelName}${wizIterator}(FrmWizardBase parent) {
    super(parent);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    
    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

[#list curGroup.att as currentAtt]

	${currentAtt.editFieldType} txt${currentAtt.name?cap_first} = new ${currentAtt.editFieldType}();
    txt${currentAtt.name?cap_first}.setWidth(new Extent(204));

    fm${currentAtt.name?cap_first} = new FieldModel();
    fm${currentAtt.name?cap_first}.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.${currentAtt.name}()));
    fm${currentAtt.name?cap_first}.setFieldCmp(txt${currentAtt.name?cap_first});
    fm${currentAtt.name?cap_first}.setKey(_PropMCrudPost.${currentAtt.name?upper_case});
    fm${currentAtt.name?cap_first}.setProperty(_PropMCrudPost.${currentAtt.name?upper_case});
 	fm${currentAtt.name?cap_first}.getValidatorList()
		[@recurseValidator validatorChoice=currentAtt.validatorChoice currentAttribute=currentAtt /]
	;

	sectionModel.addChild(fm${currentAtt.name?cap_first});
	
[/#list]

[#list curGroup.list as currentlist]
	sectionModel.addChild(fm${currentlist.relationModelName} = init${currentlist.propRelName}());
[/#list]
	
	}
[#list curGroup.list as currentlist]
  [#if currentlist.type=="MultJoint"]
  protected FieldModel init${currentlist.propRelName}() {

	    EJointModel jointModel = new EJointModel();

		
	    jointModel.init(
	        (MBase) data,
	        ${currentlist.relationModelName}.class,
	        _Prop${modelName}.${currentlist.relationAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.secondRelAttName});

	    EJointButton jntJoint = new EJointButton(
	    		Frm${currentlist.relationModelName}List.class, jointModel, parent);

	    jntJoint.setEditMode(EnumEditMode.UPDATE);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.${currentlist.propRelName}())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_Prop${modelName}.${currentlist.relationAttName});

	    return fmJoint;
	  }
 	[/#if]
  
  [#if currentlist.type=="SingJoint"]
  protected FieldModel init${currentlist.propRelName}() {

	    EJointModel jointModel = new EJointModel();
		
		
	   	jointModel.init(
	        (MBase) data,
	        ${currentlist.relationModelName}.class,
	        _Prop${modelName}.${currentlist.relationAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.secondRelAttName});
	        
	        
	    EJointButton jntJoint = new EJointButton(
        		Frm${currentlist.relationModelName}List.class, jointModel, parent) {

      	protected String getInfoText() {
        List<${currentlist.relationModelName}> aux =
            ((${modelName}) data).${currentlist.relModGetter}();

        return !aux.isEmpty()
            ? aux.get(0).get${currentlist.relationSecAtt}().getName() +
                " / " + aux.get(0).getName() //getname por defecto por ahora
            : null;
      		}
    	};

	    
	    jntJoint.setEditMode(EnumEditMode.UPDATE);

	    FieldModel fmJoint = new FieldModel();		
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.${currentlist.propRelName}())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_Prop${modelName}.${currentlist.relationAttName});

	    return fmJoint;
	  }
  [/#if]
  
  [#if currentlist.type=="Inner"]
  
    protected FieldModel init${currentlist.propRelName}() {

	   EInnerButton innInner = new EInnerButton(
	   	  Frm${currentlist.relationModelName}List.class,
	      (MBase)data,
	      _Prop${modelName}.${currentlist.relationAttName},
	      _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	      parent);

	   innInner.setEditMode(EnumEditMode.UPDATE);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.${currentlist.propRelName}()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_Prop${modelName}.${currentlist.relationAttName});

	   return fmInner;
	 }
  
  
  [/#if]
  
[/#list]  

[#break]

[/#if]
[/#list]    	  
}
	