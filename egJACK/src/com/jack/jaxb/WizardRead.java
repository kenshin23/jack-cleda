package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.wizard.*;



public class WizardRead {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/XMLobjects/wizardTest.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

   
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    Wizard xmlWizard = (Wizard) unmarshaller.unmarshal(is);
    
    System.err.println("Nombre del Wizard: " + xmlWizard.getName());
    //System.err.println("Descripcion: " + xmlWizard.getDesc());
    
        
  }
  
}