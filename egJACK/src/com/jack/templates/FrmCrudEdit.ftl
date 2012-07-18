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

import com.minotauro.sandbox.gui.mmultjointmposta.FrmMMultJointMPostAList;

//TODO:plantilla agregar dinamicamente imports.

import ${modelPackage}.MMultJointMPostA;
import ${modelPackage}._Prop${modelName};
import ${modelPackage}._PropMMultJointMPostA;
import ${modelPackage}.${modelName};



public class Frm${modelName}Edit extends FrmEditBase {


[#list attributes.att as currentAtt]
  protected FieldModel fm${currentAtt.name};
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

	${currentAtt.editFieldType} txt${currentAtt.name} = new ${currentAtt.editFieldType}();
    txt${currentAtt.name}.setWidth(new Extent(204));

    fm${currentAtt.name} = new FieldModel();
    fm${currentAtt.name}.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.${currentAtt.name}()));
    fm${currentAtt.name}.setFieldCmp(txt${currentAtt.name});
    fm${currentAtt.name}.setKey(_PropMCrudPost.${currentAtt.name?upper_case});
    fm${currentAtt.name}.setProperty(_PropMCrudPost.${currentAtt.name?upper_case});
	[#list currentAtt.validator as currentValidator]
   	fm${currentAtt.name}.getValidatorList().add(new ${currentValidator.name}(_I18NFrmMCrudPostEdit.${currentAtt.name}(), txt${currentAtt.name}));
	[/#list]
    sectionModel.addChild(fm${currentAtt.name});

[/#list]

[#list attributes.list as currentlist]
	sectionModel.addChild(fm${currentlist.relationModelName} = init${currentlist.relationModelName}());
[/#list]
 }
  
[#list attributes.list as currentlist]
  [#if currentlist.type=="Joint"]
  protected FieldModel init${currentlist.relationModelName}() {

	    EJointModel jointModel = new EJointModel();

		
	    jointModel.init(
	        data,
	        ${currentlist.relationModelName}.class,
	        _Prop${modelName}.${currentlist.relationAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.secondRelAttName});

	    EJointButton jntJoint = new EJointButton(
	    		Frm${currentlist.relationModelName}List.class, jointModel, this);

	    jntJoint.setEditMode(editMode);

	    FieldModel fmJoint = new FieldModel();
	    fmJoint.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.jointAB())); //TODO: editar properties
	    fmJoint.setFieldCmp(jntJoint);
	    fmJoint.setKey(_Prop${modelName}.MULT_JOINT_MPOST_ALIST);

	    return fmJoint;
	  }
  [/#if]
[/#list]    	  
}
