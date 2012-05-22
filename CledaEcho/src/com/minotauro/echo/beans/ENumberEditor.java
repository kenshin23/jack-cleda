package com.minotauro.echo.beans;

import java.text.NumberFormat;
import java.text.ParseException;

import nextapp.echo.app.Alignment;
import nextapp.echo.app.TextField;
import nextapp.echo.app.text.Document;
import nextapp.echo.app.text.StringDocument;

/** 
 * @author Alejandro Salas 
 * <br> Created on Feb 5, 2007
 */
public class ENumberEditor extends TextField {

  private double number;

  private NumberFormat numberFormat;

  public ENumberEditor() {
    this(new StringDocument());
  }

  public ENumberEditor(NumberFormat numberFormat) {
    this(new StringDocument());
    this.numberFormat = numberFormat;
  }

  /**
   * Creates a new <code>NumberEditor</code> with the specified
   * <code>Document</code> model.
   * 
   * @param document the document
   */
  public ENumberEditor(Document document) {
    super(document);
    initEditor();
  }

  /**
   * Creates a new <code>NumberEditor</code> with the specified
   * <code>Document</code> model, initial text, and column width.
   * 
   * @param document the document
   * @param text the initial text (may be null)
   * @param columns the number of columns to display
   */
  public ENumberEditor(Document document, String text, int columns) {
    super(document, text, columns);
    initEditor();
  }

  /**
   * Creates a new <code>NumberEditor</code> with the specified text in a
   * <code>StringDocument</code> model.
   * 
   * @param text the text to be placed into a <code>StringDocument</code>
   */
  public ENumberEditor(String text) {
    super(new StringDocument());
    if (text != null) {
      setText(text);
    }
    initEditor();
  }

  /**
   * Creates a new <code>NumberEditor</code> with the initial text, and
   * column width.
   * 
   * @param text the initial text (may be null)
   * @param columns the number of columns to display
   */
  public ENumberEditor(String text, int columns) {
    this(new StringDocument(), text, columns);
  }

  /**
   * Creates a new <code>NumberEditor</code> with the specified column width.
   * 
   * @param columns the number of columns to display
   */
  public ENumberEditor(int columns) {
    this(new StringDocument(), null, columns);
  }

  private void initEditor() {
    setAlignment(new Alignment(Alignment.RIGHT, Alignment.DEFAULT));
  }

  public void setNumber(double number) {
    this.number = number;
    super.setText(numberFormat.format(number));
  }

  public double getNumber() {
    try {
      number = numberFormat.parse(getText()).doubleValue();
    } catch (ParseException e) {
    }

    setNumber(number);
    return number;
  }

  public NumberFormat getNumberFormat() {
    return numberFormat;
  }

  public void setNumberFormat(NumberFormat numberFormat) {
    this.numberFormat = numberFormat;
  }
}