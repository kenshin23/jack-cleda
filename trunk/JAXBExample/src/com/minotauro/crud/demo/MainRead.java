package com.minotauro.crud.demo;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.minotauro.crud.xml.CRUD;
import com.minotauro.crud.xml.ObjectFactory;

public class MainRead {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = //
    ClassLoader.getSystemResourceAsStream( //
        "com/minotauro/factura/demo/crud.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

    JAXBContext jc = JAXBContext.newInstance( //
    		ObjectFactory.class.getPackage().getName());

    Unmarshaller unmarshaller = jc.createUnmarshaller();

    CRUD xmlFactura = (CRUD) unmarshaller.unmarshal(is);

    // ----------------------------------------
    // Procesar el arbol de objetos generado
    // ----------------------------------------

    System.err.println("factura numero: " + xmlFactura.getNumero());
    System.err.println( //
        "cliente cedula: " + xmlFactura.getCliente().getCedula());

    for (String xmlItem : xmlFactura.getName()) {
      System.err.println( //
          xmlItem.getDescripcion() + ";" + xmlItem.getMonto());
    }

    System.err.println(//
        "monto total: " + xmlFactura.getTotal().getMonto());
  }
}
