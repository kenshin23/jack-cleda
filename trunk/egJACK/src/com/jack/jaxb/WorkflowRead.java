package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.jack.workflow.xml.Workflow;
import com.jack.workflow.xml.*;

public class WorkflowRead {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = //
    ClassLoader.getSystemResourceAsStream( //
        "com/jack/jaxb/WorkflowPrueba.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

    JAXBContext jc = JAXBContext.newInstance( //
        ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    Workflow xmlWorkflow = (Workflow) unmarshaller.unmarshal(is);

    // ----------------------------------------
    // Procesar el arbol de objetos generado
    // ----------------------------------------

    System.err.println("Nombre Workflow: " + xmlWorkflow.getName());
    System.err.println( //
        "Descripcion: " + xmlWorkflow.getDescripcion());

  }
}
