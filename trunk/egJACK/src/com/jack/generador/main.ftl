[#ftl]	
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------

package com.jack.generador;

public class Salida {

  public static void main(String[] args) {

    System.out.println("numero");
			    System.out.println("${name}");

	   

  [#list attributes.att as currentAttribute]
		  System.out.println("item: ${currentAttribute.name}");
		    
  [/#list]
    System.out.println("monto");
        
    
  }
}
