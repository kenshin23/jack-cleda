[#ftl]
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------
package ${pckName};

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
  public static SimpleDateFormat get${methodBean.capitalizedName}Formatter() {
    return MessageBase.getInstance().getDateFormatter(locale, RES_NAME, "${methodBean.key}");
  }

  public static String format${methodBean.capitalizedName}(Calendar val) throws MessageException {
    return MessageBase.getInstance().formatCalendarValue(locale, RES_NAME, "${methodBean.key}", val);
  }

  public static Calendar parse${methodBean.capitalizedName}(String val) throws MessageException, ParseException {
    return MessageBase.getInstance().parseCalendarValue(locale, RES_NAME, "${methodBean.key}", val);
  }

  [/#list]
}
