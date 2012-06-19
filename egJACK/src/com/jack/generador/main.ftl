[#ftl]	
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------

package com.jack.generador;

public class Salida {

  public static void main(String[] args) {

    System.out.println("numero");
			    System.out.println("${name}");

	   

  [#list attributes as currentattribute]
		    
		  [#list att as currentatt]  
		  System.out.println("item: ${currentatt.name}");
		    
  [/#list]
[/#list]
    System.out.println("monto");
        
    
  }
}
