[#ftl]	

package ${package};

import com.minotauro.echo.cleda.wizard.FrmWizardDta;
import com.minotauro.echo.cleda.wizard.PnlWizardBase;
import com.minotauro.echo.util.gui.MessageBox;
import com.minotauro.echo.util.gui._I18NMessageBox;
import com.minotauro.sandbox.model.${modelName};

//TODO:plantilla agregar dinamicamente imports.


public class FrmWizard${modelName} extends FrmWizardDta {

  // --------------------------------------------------------------------------------

  public FrmWizard${modelName}() {
    super(_I18NFrmWizard${modelName}.title());
  }

  // --------------------------------------------------------------------------------

 @Override
  protected void initGUI() {
  [#list attributes.group as currentGroup]
    pnlWizardBaseList.add(new PnlWizard${modelName}${currentGroup.num}(this));
  [/#list]
   
  }
  
// --------------------------------------------------------------------------------

  @Override
  protected void doFnshClicked(PnlWizardBase pnlWizardBase) {
    super.doFnshClicked(pnlWizardBase);

    MessageBox.msgBox(_I18NMessageBox.information(),
        _I18NFrmWizard${modelName}.finalMessage(), null,
        250, 100, MessageBox.OK_OPT);
  }

  // --------------------------------------------------------------------------------

  @Override
  protected Object initData() {
    return new ${modelName}();
  }
}
