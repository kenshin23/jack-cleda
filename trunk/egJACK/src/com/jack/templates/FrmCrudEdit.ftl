[#ftl]	

package com.minotauro.sandbox.gui.mcrud${name};

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;
import com.minotauro.echo.validator.impl.ConditionalValidator;
import com.minotauro.echo.validator.impl.IntegerValidator;

import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;

//TODO:plantilla agregar dinamicamente imports.

import ${modelPackage}.MMultJointMPostA;
import ${modelPackage}._Prop${modelName};
import ${modelPackage}._PropMMultJointMPostA;
import ${modelPackage}.${modelName};



public class Frm${modelName}Edit extends FrmEditBase {


[#list attributes.att as currentAtt]
  protected FieldModel fm${currentAtt.name?cap_first};
[/#list]
  
  [#list attributes.list as currentlist]
  protected FieldModel fm${currentlist.relationModelName};
  [/#list]

  // --------------------------------------------------------------------------------

  public FrmMCrud${name?cap_first}Edit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((${modelName}) data).getName(); 
    setTitle(_I18NFrm${modelName}Edit.title(name == null ? "-" : name)); 

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------

[#list attributes.att as currentAtt]

	${currentAtt.editFieldType} txt${currentAtt.name?cap_first} = new ${currentAtt.editFieldType}();
    txt${currentAtt.name?cap_first}.setWidth(new Extent(204));

    fm${currentAtt.name?cap_first} = new FieldModel();
    fm${currentAtt.name?cap_first}.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.${currentAtt.name}()));
    fm${currentAtt.name?cap_first}.setFieldCmp(txt${currentAtt.name?cap_first});
    fm${currentAtt.name?cap_first}.setKey(_PropMCrudPost.${currentAtt.name?upper_case});
    fm${currentAtt.name?cap_first}.setProperty(_PropMCrudPost.${currentAtt.name?upper_case});
 	
 	fm${currentAtt.name?cap_first}.getValidatorList()
	[#list currentAtt.validatorChoice as currentValidatorChoice]
[#if currentValidatorChoice.bandera==true]
   	.add(new ${currentValidatorChoice.validator.name}(_I18NFrmMCrudPostEdit.${currentAtt.name}(), txt${currentAtt.name?cap_first})
[/#if]
	[#if currentValidatorChoice.bandera==false]	
		.add(ConditionalValidator
		.CHAIN(true)
[/#if]	

	[/#list]
	);
 sectionModel.addChild(fm${currentAtt.name?cap_first});
 }
  

[/#list]
}
