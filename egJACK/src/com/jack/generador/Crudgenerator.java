package com.jack.generador;

import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.crud.xml.*;

import freemarker.cache.URLTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;

public class Crudgenerator extends URLTemplateLoader {

  public void run() throws Exception {
    Configuration configuration = new Configuration();
    configuration.setObjectWrapper(new BeansWrapper());
    configuration.setTemplateLoader(this);
    
    Configuration configuration2 = new Configuration();
    configuration2.setObjectWrapper(new BeansWrapper());
    configuration2.setTemplateLoader(this);
    
    Configuration configuration3 = new Configuration();
    configuration3.setObjectWrapper(new BeansWrapper());
    configuration3.setTemplateLoader(this);

    FileWriter fileWriter = new FileWriter("src/com/jack/generador/Salida.java");
    FileWriter fileWriter2 = new FileWriter("src/com/jack/generador/Salida2.java");
    FileWriter fileWriter3 = new FileWriter("src/com/jack/generador/Salida3.java");

    CRUD crud = leerXML();

    configuration.getTemplate("/com/jack/generador/CledaI18N-template.ftl").process(crud, fileWriter);
    
    configuration2.getTemplate("/com/jack/generador/FrmCrudList.ftl").process(crud, fileWriter);
    
    configuration3.getTemplate("/com/jack/generador/FrmCrudList.ftl").process(crud, fileWriter);

    fileWriter.close();
  }

  private CRUD leerXML() throws Exception {
    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = //
    ClassLoader.getSystemResourceAsStream("com/jack/XMLObjectsTest/CrudPost.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

    JAXBContext jc = JAXBContext.newInstance( //
        ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    CRUD xmlcrud = (CRUD) unmarshaller.unmarshal(is);

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
  }
}
