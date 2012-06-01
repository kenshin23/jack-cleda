[#ftl]
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package ${pckName};

import java.util.Locale;

import com.minotauro.i18n.base.MessageBase;
import com.minotauro.i18n.base.MessageException;

public class ${clsName} {

  public static final Locale locale = new Locale("${language}", "${country}", "${variant}");

  public static final String RES_NAME = "${pckName}.${resName}";

  private ${clsName}() {
    // Empty
  }

  [#list methodBeanList as methodBean]
  public static String ${methodBean.name}([@compress single_line=true]
  	[#if methodBean.args > -1]
  		[#list 0..methodBean.args as indx]
  			Object arg${indx}[#if indx != methodBean.args], [/#if][/#list][/#if]) throws MessageException {
  	[/@compress]

    return MessageBase.getInstance().locateValue(locale, RES_NAME, "${methodBean.key}", new Object[]{[@compress single_line=true]
  	[#if methodBean.args > -1]
  		[#list 0..methodBean.args as indx]
  			arg${indx}[#if indx != methodBean.args], [/#if][/#list][/#if]});[/@compress]
  }

  [/#list]
}
