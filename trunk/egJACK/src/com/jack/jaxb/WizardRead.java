package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.wizard.xml.*;



public class WizardRead {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = ClassLoader.getSystemResourceAsStream("com/jack/jaxb/wizardTest.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

   
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    WIZARD xmlWizard = (WIZARD) unmarshaller.unmarshal(is);
    
    System.err.println("Nombre del Wizard: " + xmlWizard.getName());
    
        
  }
  
}