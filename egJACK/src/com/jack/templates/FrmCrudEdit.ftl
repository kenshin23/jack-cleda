[#ftl]	

package com.minotauro.sandbox.gui.mcrud${name};

import nextapp.echo.app.Extent;
import nextapp.echo.app.TextField;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.cleda.edit.FrmEditBase;
import com.minotauro.echo.grid.FieldModel;
import com.minotauro.echo.grid.SectionModel;
import com.minotauro.echo.validator.impl.DuplicatedValidator;
import com.minotauro.echo.validator.impl.NotEmptyValidator;

import com.minotauro.sandbox.model.MCrud${name?cap_first};

import com.minotauro.sandbox.model._PropMCrud${name?cap_first};


public class FrmMCrud${name?cap_first}Edit extends FrmEditBase {


[#list attributes.att as currentAtt]

  private FieldModel fmN${currentAtt.name};

[/#list]

  // --------------------------------------------------------------------------------

  public FrmMCrud${name?cap_first}Edit() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @Override
  protected void initGUI() {
    String name = ((MCrud${name?cap_first}) data).getName(); //*** pregunta getName
    setTitle(_I18NFrmMCrud${name?cap_first}Edit.title(name == null ? "-" : name)); // preguntar "name"

    // --------------------------------------------------------------------------------

    SectionModel sectionModel = formModel.initSingleSectionModel();

    // --------------------------------------------------------------------------------



[#list attributes.att as currentAtt]

	${currentAtt.editFieldType} txt${currentAtt.name} = new ${currentAtt.editFieldType}();
    txt${currentAtt.name}.setWidth(new Extent(204));

    fmN${currentAtt.name} = new FieldModel();
    fmN${currentAtt.name}.setLabelCmp(new EFieldLabel(_I18NFrmMCrudPostEdit.name()));
    fmN${currentAtt.name}.setFieldCmp(txt${currentAtt.name});
    fmN${currentAtt.name}.setKey(_PropMCrudPost.NAME);
    fmN${currentAtt.name}.setProperty(_PropMCrudPost.NAME);

	[#list currentAtt.validator as currentValidator]
	
    fmN${currentAtt.name}.getValidatorList().add(new ${currentValidator.name}(_I18NFrmMCrudPostEdit.${currentAtt.name}(), txt${currentAtt.name})); //investigar especificaciones de validadores

	[/#list]    
	
    sectionModel.addChild(fmN${currentAtt.name});
	

[/#list]

    
    
  }
}
