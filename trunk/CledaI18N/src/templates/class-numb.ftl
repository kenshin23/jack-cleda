[#ftl]
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package ${pckName};

import java.text.DecimalFormat;
import java.text.ParseException;
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
  public static DecimalFormat get${methodBean.capitalizedName}Formatter() {
    return MessageBase.getInstance().getNumberFormatter(locale, RES_NAME, "${methodBean.key}");
  }

  public static String format${methodBean.capitalizedName}(double val) throws MessageException {
    return MessageBase.getInstance().formatNumberValue(locale, RES_NAME, "${methodBean.key}", val);
  }

  public static double parse${methodBean.capitalizedName}(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseNumberValue(locale, RES_NAME, "${methodBean.key}", val);
  }

  [/#list]
}
