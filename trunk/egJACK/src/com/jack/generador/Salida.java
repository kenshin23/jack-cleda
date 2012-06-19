// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------

package com.jack.generador;

public class Salida {

  public static void main(String[] args) {

    System.out.println("numero");
			    System.out.println("NOMBRE_CRUD");

	   


Expected collection or sequence. attributes evaluated instead to freemarker.ext.beans.StringModel on line 17, column 10 in com/jack/generador/main.ftl.
The problematic instruction:
----------
==> list attributes as currentattribute [on line 17, column 3 in com/jack/generador/main.ftl]
----------

Java backtrace for programmers:
----------
freemarker.template.TemplateException: Expected collection or sequence. attributes evaluated instead to freemarker.ext.beans.StringModel on line 17, column 10 in com/jack/generador/main.ftl.
	at freemarker.core.TemplateObject.invalidTypeException(TemplateObject.java:135)
	at freemarker.core.IteratorBlock$Context.runLoop(IteratorBlock.java:183)
	at freemarker.core.Environment.visit(Environment.java:351)
	at freemarker.core.IteratorBlock.accept(IteratorBlock.java:95)
	at freemarker.core.Environment.visit(Environment.java:196)
	at freemarker.core.MixedContent.accept(MixedContent.java:92)
	at freemarker.core.Environment.visit(Environment.java:196)
	at freemarker.core.Environment.process(Environment.java:176)
	at freemarker.template.Template.process(Template.java:232)
	at com.jack.generador.Crudgenerator.run(Crudgenerator.java:29)
	at com.jack.generador.Crudgenerator.main(Crudgenerator.java:70)
