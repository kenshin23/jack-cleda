/*
 * Created on 30/03/2006
 */
package com.minotauro.cleda.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.minotauro.cleda.util.CledaStringUtils;

/**
 * @author Demián Gutierrez
 */
public class BaseHandler extends DefaultHandler {

  protected StringBuffer characters = new StringBuffer();
  protected String text;

  // ----------------------------------------

  public BaseHandler() {
    // Empty
  }

  // ----------------------------------------
  // DefaultHandler Methods
  // ----------------------------------------

  public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
    characters = new StringBuffer();
  }

  public void endElement(String uri, String localName, String qName) throws SAXException {
    text = characters.toString();
    text = CledaStringUtils.trim(text, " \r\n\t");
  }

  public void characters(char[] ch, int start, int length) throws SAXException {
    characters.append(ch, start, length);
  }
}
