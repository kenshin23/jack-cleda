/*
 * Created on 05/06/2007
 */
package com.minotauro.echo.wrapper.base;

import nextapp.echo.app.CheckBox;
import nextapp.echo.app.PasswordField;
import nextapp.echo.app.SelectField;
import nextapp.echo.app.TextArea;
import nextapp.echo.app.TextField;
import nextapp.echo.extras.app.RichTextArea;

import com.minotauro.echo.beans.EFieldLabel;
import com.minotauro.echo.beans.ENumberEditor;
import com.minotauro.echo.beans.ETextAreaEx;
import com.minotauro.echo.validator.base.ValidatorList;
import com.minotauro.echo.wrapper.impl.CheckBoxWrapper;
import com.minotauro.echo.wrapper.impl.DateFieldWrapper;
import com.minotauro.echo.wrapper.impl.EFieldLabelWrapper;
import com.minotauro.echo.wrapper.impl.RichTextAreaWrapper;
import com.minotauro.echo.wrapper.impl.SelectFieldWrapper;
import com.minotauro.echo.wrapper.impl.TextAreaWrapper;
import com.minotauro.echo.wrapper.impl.TextFieldWrapper;

import echopoint.jquery.DateField;

/**
 * @author Demián Gutierrez
 */
public class InitEchoComponentWrapperMap extends ValidatorList {

  public static void init() {
    ComponentWrapperMap.getInstance().addComponentWrapper(TextField.class,/*    */new TextFieldWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(PasswordField.class,/**/new TextFieldWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(ENumberEditor.class,/**/new TextFieldWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(TextArea.class,/*     */new TextAreaWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(ETextAreaEx.class,/*  */new TextAreaWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(CheckBox.class,/*     */new CheckBoxWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(RichTextArea.class,/* */new RichTextAreaWrapper());
//    ComponentWrapperMap.getInstance().addComponentWrapper(ERichTextArea.class,/**/new RichTextAreaWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(SelectField.class,/*  */new SelectFieldWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(DateField.class,/*    */new DateFieldWrapper());
    ComponentWrapperMap.getInstance().addComponentWrapper(EFieldLabel.class,/*  */new EFieldLabelWrapper());
  }
}
