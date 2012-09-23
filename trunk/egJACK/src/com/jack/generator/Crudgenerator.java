package com.jack.generator;
//import com.minotauro.sandbox.gui.mcrudpost;

import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.crud.*;
import com.jack.wizard.*;
//import com.minotauro.sandbox.gui.mcrudpost._CledaI18N;

import freemarker.cache.URLTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class Crudgenerator extends URLTemplateLoader {

	public void run(String[] args) throws Exception {
		
		if(args[2].toString().equals("crud")){
			
			System.out.println("crud");
					
			Crud crudInput = leerCrud(args[0]);
	
			Configuration configuration = new Configuration();
			configuration.setObjectWrapper(new BeansWrapper());
			configuration.setTemplateLoader(this);
	
			Configuration configuration2 = new Configuration();
			configuration2.setObjectWrapper(new BeansWrapper());
			configuration2.setTemplateLoader(this);
	
			Configuration configuration3 = new Configuration();
			configuration3.setObjectWrapper(new BeansWrapper());
			configuration3.setTemplateLoader(this);
			
			FileWriter fileWriter = new FileWriter(args[1]+crudInput.getPackage().replace('.', '/')+"/_CledaI18N.java");
			FileWriter fileWriter2 = new FileWriter(args[1]+crudInput.getPackage().replace('.', '/')+"/Frm"+crudInput.getModelName()+"Edit.java");
			FileWriter fileWriter3 = new FileWriter(args[1]+crudInput.getPackage().replace('.', '/')+"/Frm"+crudInput.getModelName()+"List.java");
			
			
			configuration.getTemplate("/com/jack/templates/CledaI18N-template.ftl").process(crudInput, fileWriter);
			configuration2.getTemplate("/com/jack/templates/FrmCrudEdit.ftl").process(crudInput, fileWriter2);
			configuration3.getTemplate("/com/jack/templates/FrmCrudList.ftl").process(crudInput, fileWriter3);
	
			fileWriter.close();
			fileWriter2.close();
			fileWriter3.close();
		}
		
		if(args[2].toString().equals("wizard")){
			
			System.out.println("wizard");
			
			Wizard wizInput = leerWiz(args[0]);
	
			Configuration configuration = new Configuration();
			configuration.setObjectWrapper(new BeansWrapper());
			configuration.setTemplateLoader(this);
	
			Configuration configuration2 = new Configuration();
			configuration2.setObjectWrapper(new BeansWrapper());
			configuration2.setTemplateLoader(this);
	
			Configuration configuration3 = new Configuration();
			configuration3.setObjectWrapper(new BeansWrapper());
			configuration3.setTemplateLoader(this);
			
			FileWriter fileWriter = new FileWriter(args[1]+wizInput.getPackage().replace('.', '/')+"/_CledaI18N.java");
			FileWriter fileWriter2 = new FileWriter(args[1]+wizInput.getPackage().replace('.', '/')+"/Frm"+wizInput.getModelName()+"Edit.java");
			FileWriter fileWriter3 = new FileWriter(args[1]+wizInput.getPackage().replace('.', '/')+"/Frm"+wizInput.getModelName()+"List.java");
			
			
			configuration.getTemplate("/com/jack/templates/CledaI18N-template.ftl").process(wizInput, fileWriter);
			configuration2.getTemplate("/com/jack/templates/FrmCrudEdit.ftl").process(wizInput, fileWriter2);
			//configuration3.getTemplate("/com/jack/templates/FrmCrudList.ftl").process(wizInput, fileWriter3);
	
			fileWriter.close();
			fileWriter2.close();
			fileWriter3.close();
			
		}
	}

	private Crud leerCrud(String XMLpath) throws Exception {
		// ----------------------------------------
		// Obtener el InputStream
		// ----------------------------------------

		//InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/XMLobjects/crudPost.xml");
		InputStream is = ClassLoader.getSystemResourceAsStream(XMLpath);
		
		// ----------------------------------------
		// Inicializar JAXB y leer el XML
		// ----------------------------------------
		
		JAXBContext jc = JAXBContext.newInstance(com.jack.crud.ObjectFactory.class.getPackage().getName());
	
		Unmarshaller unmarshaller = jc.createUnmarshaller();
	
		Crud xmlcrud = (Crud) unmarshaller.unmarshal(is);
	
		return xmlcrud;
		
	}

	private Wizard leerWiz(String XMLpath) throws Exception {
		// ----------------------------------------
		// Obtener el InputStream
		// ----------------------------------------

		//InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/XMLobjects/crudPost.xml");
		InputStream is = ClassLoader.getSystemResourceAsStream(XMLpath);
		
		// ----------------------------------------
		// Inicializar JAXB y leer el XML
		// ----------------------------------------
		
		JAXBContext jc = JAXBContext.newInstance(com.jack.crud.ObjectFactory.class.getPackage().getName());
	
		Unmarshaller unmarshaller = jc.createUnmarshaller();
	
		Wizard xmlwiz = (Wizard) unmarshaller.unmarshal(is);
	
		return xmlwiz;
		
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
		if (args.length != 3) {
            System.out.println("Ha de enviar dos parametros\n"
                            + "Fuente del XML y Paquete Destino y tipo");
            System.exit(1);
    }
			new Crudgenerator().run(args);
	//	new _CledaI18N().main();
	}
}
