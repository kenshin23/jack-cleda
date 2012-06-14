package com.jack.jaxb;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class Leer {

  public static void main(String[] args) throws Exception {

    // ----------------------------------------
    // Obtener el InputStream
    // ----------------------------------------

    InputStream is = //
    ClassLoader.getSystemResourceAsStream( //
        "com/minotauro/factura/demo/factura_1.xml");

    // ----------------------------------------
    // Inicializar JAXB y leer el XML
    // ----------------------------------------

   
