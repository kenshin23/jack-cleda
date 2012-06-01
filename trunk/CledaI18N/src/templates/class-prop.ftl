[#ftl]
/*
 * Generated code, do not edit
 */
package ${pckName};

public class ${clsName} {

  private ${clsName}() {
    // Empty
  }

  [#list propertyBeanList as propertyBean]
  public static final String ${propertyBean.propertyName} = "${propertyBean.propertyValue}";
  [/#list]
}
