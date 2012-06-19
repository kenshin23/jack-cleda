package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.crud.xml.*;



public class ReadCrud {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/jaxb/crudTest.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

   
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    CRUD xmlCrud = (CRUD) unmarshaller.unmarshal(is);
    
    System.err.println("factura numero: " + xmlCrud.getName());
    
        
  }
  
}