[#ftl]
[#-- TODO: Pasar este macro a un archivo y usarlo con import--]	
[#macro recurseValidator validatorChoice currentAttribute]
	[#list validatorChoice as currentValidatorChoice]
		[#if currentValidatorChoice.isConditionalVal==false]
   		.add(new ${currentValidatorChoice.validator.name}(_I18NFrm${modelName}Edit.${currentAttribute.name}(), txt${currentAttribute.name?cap_first}))
		[/#if]
		[#if currentValidatorChoice.isConditionalVal==true]	
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

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.wrk.FrmEditWrk;
import com.minotauro.echo.cleda.list.var.EInnerButton;
import com.minotauro.echo.cleda.list.var.EJointButton;
import com.minotauro.echo.cleda.list.var.EJointModel;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.*;
import com.minotauro.sandbox.gui.dinnersmsb.FrmDInnerSmsList;
import com.minotauro.sandbox.gui.dmultjointsmsb.FrmDMultJointSmsBList;
import com.minotauro.sandbox.gui.dsingjointsmsb.FrmDSingJointSmsBList;
import com.minotauro.sandbox.model.${modelName};
import com.minotauro.sandbox.model.DMultJointSmsB;
import com.minotauro.sandbox.model.DSingJointSmsB;
import com.minotauro.sandbox.model._PropDInnerSms;
import com.minotauro.sandbox.model._Prop${modelName};
import com.minotauro.sandbox.model._PropDMultJointSmsB;
import com.minotauro.sandbox.model._PropDSingJointSmsB;

/**
 * @author E.G. Jack
 * 
 */
public class FrmDMainSmsEdit extends FrmEditWrk {

[#list attributes.att as currentAtt]
  protected FieldModel fm${currentAtt.name?cap_first};
[/#list]
[#list attributes.list as currentlist]
  protected FieldModel fm${currentlist.relationModelName};
[/#list]

	// --------------------------------------------------------------------------------

	public Frm${modelName}Edit() {
		super("ID", "sms-net", "enterdoc");
		
		//TODO: buscar que representan estos parametros
	}

	// --------------------------------------------------------------------------------

	protected void initGUI() {
		String name = ((${modelName}) data).getName();
		setTitle(_I18NFrm${modelName}Edit.title(name == null ? "-" : name));

		setW(new Extent(450));
		setH(new Extent(500));

		// --------------------------------------------------------------------------------

		SectionModel sectionModel = formModel.initSingleSectionModel();

		// --------------------------------------------------------------------------------

		[#list attributes.att as currentAtt]

	${currentAtt.editFieldType} txt${currentAtt.name?cap_first} = new ${currentAtt.editFieldType}();
    txt${currentAtt.name?cap_first}.setWidth(new Extent(204));

    fm${currentAtt.name?cap_first} = new FieldModel();
    fm${currentAtt.name?cap_first}.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.${currentAtt.name}()));
    fm${currentAtt.name?cap_first}.setFieldCmp(txt${currentAtt.name?cap_first});
    fm${currentAtt.name?cap_first}.setKey(_Prop${modelName}.${currentAtt.name?upper_case});
    fm${currentAtt.name?cap_first}.setProperty(_Prop${modelName}.${currentAtt.name?upper_case});
 	fm${currentAtt.name?cap_first}.getValidatorList()
		[@recurseValidator validatorChoice=currentAtt.validatorChoice currentAttribute=currentAtt /]
	;

	sectionModel.addChild(fm${currentAtt.name?cap_first});
	
[/#list]

[#list attributes.list as currentlist]
	sectionModel.addChild(fm${currentlist.relationModelName} = init${currentlist.propRelName}());
[/#list]
	
	}
[#list attributes.list as currentlist]
  [#if currentlist.type=="MultJoint"]
  protected FieldModel init${currentlist.propRelName}() {

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
	        data,
	        ${currentlist.relationModelName}.class,
	        _Prop${modelName}.${currentlist.relationAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	        _Prop${currentlist.relationModelName}.${currentlist.secondRelAttName});
	        
	        
	    EJointButton jntJoint = new EJointButton(
        		Frm${currentlist.relationModelName}List.class, jointModel, this) {

      	protected String getInfoText() {
        List<${currentlist.relationModelName}> aux =
            ((${modelName}) data).${currentlist.relModGetter}();

        return !aux.isEmpty()
            ? aux.get(0).get${currentlist.relationSecAtt}().getName() +
                " / " + aux.get(0).get${currentlist.relationFirstAtt}().getName() //getname por defecto por ahora
            : null;
      		}
    	};

	    
	    jntJoint.setEditMode(editMode);

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
	      data,
	      _Prop${modelName}.${currentlist.relationAttName},
	      _Prop${currentlist.relationModelName}.${currentlist.firstRelAttName},
	      this);

	   innInner.setEditMode(editMode);

	   FieldModel fmInner = new FieldModel();
	   fmInner.setLabelCmp(new EFieldLabel(_I18NFrm${modelName}Edit.${currentlist.propRelName}()));
	   fmInner.setFieldCmp(innInner);
	   fmInner.setKey(_Prop${modelName}.${currentlist.relationAttName});

	   return fmInner;
	 }
  
  
  [/#if]
  
[/#list]

	// --------------------------------------------------------------------------------

	@Override
	protected DMainSms initMDocument() {
		return new DMainSms();
	}

}