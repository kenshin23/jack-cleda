package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.crud.*;



public class CrudRead {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/XMLObjects/crudPost.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

   
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    Crud xmlCrud = (Crud) unmarshaller.unmarshal(is);
    
    System.out.println("Nombre Crud leido: " + xmlCrud.getName());
    System.out.println("Descripcion: " + xmlCrud.getDescription().getValue());
    
        
  }
  
}