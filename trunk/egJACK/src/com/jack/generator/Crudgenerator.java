package com.jack.generator;
//import com.minotauro.sandbox.gui.mcrudpost;

import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.crud.*;
//import com.minotauro.sandbox.gui.mcrudpost._CledaI18N;

import freemarker.cache.URLTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class Crudgenerator extends URLTemplateLoader {

	public void run() throws Exception {
		Crud crudInput = leerXML();

		Configuration configuration = new Configuration();
		configuration.setObjectWrapper(new BeansWrapper());
		configuration.setTemplateLoader(this);

		Configuration configuration2 = new Configuration();
		configuration2.setObjectWrapper(new BeansWrapper());
		configuration2.setTemplateLoader(this);

		Configuration configuration3 = new Configuration();
		configuration3.setObjectWrapper(new BeansWrapper());
		configuration3.setTemplateLoader(this);

		FileWriter fileWriter = new FileWriter("../CledaSandbox/src/com/minotauro/sandbox/gui/mcrudpost/_CledaI18N.java");
		
		//FileWriter fileWriter = new FileWriter("src/com/jack/generator/_CledaI18N.java");
		FileWriter fileWriter2 = new FileWriter("../CledaSandbox/src/com/minotauro/sandbox/gui/mcrudpost/FrmMCrudPostEdit.java");
		FileWriter fileWriter3 = new FileWriter("../CledaSandbox/src/com/minotauro/sandbox/gui/mcrudpost/FrmMCrudPostList.java");

		configuration.getTemplate("/com/jack/templates/CledaI18N-template.ftl").process(crudInput, fileWriter);
		configuration2.getTemplate("/com/jack/templates/FrmCrudEdit.ftl").process(crudInput, fileWriter2);
		configuration3.getTemplate("/com/jack/templates/FrmCrudList.ftl").process(crudInput, fileWriter3);

		fileWriter.close();
		fileWriter2.close();
		fileWriter3.close();
	}

	private Crud leerXML() throws Exception {
		// ----------------------------------------
		// Obtener el InputStream
		// ----------------------------------------

		InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/XMLobjects/crudPost.xml");

		// ----------------------------------------
		// Inicializar JAXB y leer el XML
		// ----------------------------------------

		JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());

		Unmarshaller unmarshaller = jc.createUnmarshaller();

		Crud xmlcrud = (Crud) unmarshaller.unmarshal(is);

		return xmlcrud;
	}

	// ----------------------------------------
	// URLTemplateLoader
	// ----------------------------------------

	@Override
	protected URL getURL(String name) {
		return getClass().getClassLoader().getResource(name);
	}

	// ----------------------------------------

	public static void main(String[] args) throws Exception {
		new Crudgenerator().run();
	//	new _CledaI18N().main();
	}
}
