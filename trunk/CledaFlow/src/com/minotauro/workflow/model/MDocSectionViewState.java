/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.base.model.MBase;
import com.minotauro.workflow.exception.WorkflowException;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_doc_section_view_state")
@Proxy(lazy = false)
public class MDocSectionViewState extends MBase {

  public static final int STATE_NOTDEF_INT = -1;

  // ----------------------------------------

  public static final int STATE_VISIBLE_INT = 0;
  public static final int STATE_HIDDEN_INT = 1;

  public static final String STATE_VISIBLE_STR = "visible";
  public static final String STATE_HIDDEN_STR = "hidden";

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int state;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MDocSection docSectionRef;

  private MNetStateGrp netStateGrpRef;

  // ----------------------------------------

  public MDocSectionViewState() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MDocSection getDocSectionRef() {
    return docSectionRef;
  }

  public void setDocSectionRef(MDocSection docSectionRef) {
    this.docSectionRef = docSectionRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetStateGrp getNetStateGrpRef() {
    return netStateGrpRef;
  }

  public void setNetStateGrpRef(MNetStateGrp netStateGrpRef) {
    this.netStateGrpRef = netStateGrpRef;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public static int intStateFromStr(String str) throws WorkflowException {
    if (StringUtils.equals(str, STATE_VISIBLE_STR)) {
      return STATE_VISIBLE_INT;
    }

    if (StringUtils.equals(str, STATE_HIDDEN_STR)) {
      return STATE_HIDDEN_INT;
    }

    throw new WorkflowException(_I18NLoader.invalidState(str));
  }

  // ----------------------------------------

  public static String strStateFromInt(int val) throws WorkflowException {
    switch (val) {
      case STATE_VISIBLE_INT :
        return STATE_VISIBLE_STR;
      case STATE_HIDDEN_INT :
        return STATE_HIDDEN_STR;
      default :
        throw new WorkflowException(_I18NLoader.invalidState(new Integer(val)));
    }
  }
}
