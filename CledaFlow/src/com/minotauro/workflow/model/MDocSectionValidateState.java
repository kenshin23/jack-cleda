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
@Table(name = "t_wrk_doc_section_validate_state")
@Proxy(lazy = false)
public class MDocSectionValidateState extends MBase {

  public static final int STATE_NOTDEF_INT = -1;

  // ----------------------------------------

  public static final int STATE_DOVALIDATE_INT = 0;
  public static final int STATE_NOVALIDATE_INT = 1;

  public static final String STATE_DOVALIDATE_STR = "dovalidate";
  public static final String STATE_NOVALIDATE_STR = "novalidate";

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private int state;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MDocSection docSectionRef;

  private MNetTrans netTransRef;

  // ----------------------------------------

  public MDocSectionValidateState() {
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
  public MNetTrans getNetTransRef() {
    return netTransRef;
  }

  public void setNetTransRef(MNetTrans netTransRef) {
    this.netTransRef = netTransRef;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public static int intStateFromStr(String str) throws WorkflowException {
    if (StringUtils.equals(str, STATE_DOVALIDATE_STR)) {
      return STATE_DOVALIDATE_INT;
    }

    if (StringUtils.equals(str, STATE_NOVALIDATE_STR)) {
      return STATE_NOVALIDATE_INT;
    }

    throw new WorkflowException(_I18NLoader.invalidState(str));
  }

  // ----------------------------------------

  public static String strStateFromInt(int val) throws WorkflowException {
    switch (val) {
      case STATE_DOVALIDATE_INT :
        return STATE_DOVALIDATE_STR;
      case STATE_NOVALIDATE_INT :
        return STATE_NOVALIDATE_STR;
      default :
        throw new WorkflowException(_I18NLoader.invalidState(new Integer(val)));
    }
  }
}
