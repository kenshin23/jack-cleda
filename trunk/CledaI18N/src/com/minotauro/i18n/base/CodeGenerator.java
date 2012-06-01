/*
 * Created on 04/11/2006
 */
package com.minotauro.i18n.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Locale;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.SystemUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.minotauro.cleda.util.FreeMarkerUtil;

/**
 * @author Demián Gutierrez
 */
public class CodeGenerator {

  protected static final Logger log = LoggerFactory.getLogger( //
      CodeGenerator.class.getName());

  // --------------------------------------------------------------------------------
  // Mode enum
  // --------------------------------------------------------------------------------

  public enum Mode {

    I18N("/templates/class-i18n.ftl"), //
    NUMB("/templates/class-numb.ftl"), //
    DATE("/templates/class-date.ftl");

    public String template;

    // ----------------------------------------

    private Mode(String template) {
      this.template = template;
    }

    // ----------------------------------------

    public String getTemplate() {
      return template;
    }

    // ----------------------------------------

    public String getPrefix() {
      switch (this) {
        case I18N :
          return "I18N";
        case NUMB :
          return "Numb";
        case DATE :
          return "Date";
      }

      throw new RuntimeException(
          "impossible: " + this);
    }
  }

  // --------------------------------------------------------------------------------

  private Locale locale;

  private String ind;
  private String prp;
  private String out;
  private String pck;
  private String ext;

  private Mode mode;

  private MessageList messageList = new MessageList();

  // --------------------------------------------------------------------------------

  public CodeGenerator(Locale locale, String ind, String prp, String out, String pck, String ext, Mode mode) {
    this.locale = locale;

    this.ind = ind;
    this.prp = prp;
    this.out = out;
    this.pck = pck;
    this.ext = ext;

    this.mode = mode;
  }

  // --------------------------------------------------------------------------------

  public void run() throws Exception {
    File root = new File(ind);

    // ----------------------------------------
    // Load all {prp}_*.properties files
    // ----------------------------------------

    log.info("Properties base name: " + prp);

    String[] list = (String[]) ArrayUtils.addAll(
        root.list(new WildcardFileFilter(prp + /**/"_*.properties")),
        root.list(new WildcardFileFilter(prp + /*  */".properties")));

    for (int i = 0; i < list.length; i++) {
      String name = ind + SystemUtils.FILE_SEPARATOR + list[i];

      log.info("Loading: " + list[i] + " - " + name);

      messageList.load(new FileInputStream(name));
    }

    // ----------------------------------------
    // Test than all keys are consistent
    // ----------------------------------------

    if (!messageList.test()) {
      throw new Exception("!messageList.test()");
    }

    // ----------------------------------------
    // Create the template model
    // ----------------------------------------

    ObjectBean objectBean = new ObjectBean();

    objectBean.setLanguage(locale.getLanguage());
    objectBean.setCountry(locale.getCountry());
    objectBean.setVariant(locale.getVariant());

    objectBean.setResName(prp);
    objectBean.setPckName(pck);
    objectBean.setExtName(ext);
    objectBean.setClsName("_" + mode.getPrefix() + prp);
    objectBean.setMethodBeanList(messageList.getKeyList());

    // ----------------------------------------
    // Write the template
    // ----------------------------------------

    FileWriter fileWriter = new FileWriter( //
        out + SystemUtils.FILE_SEPARATOR + objectBean.getClsName() + ".java");

    FreeMarkerUtil.getInstance().process(mode.getTemplate(), objectBean, fileWriter);
    fileWriter.close();
  }
}
