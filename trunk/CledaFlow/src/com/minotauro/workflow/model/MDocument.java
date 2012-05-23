/*
 * Created on 03/01/2008
 */
package com.minotauro.workflow.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.workflow.prop.MWorkflowProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_document")
@Proxy(lazy = false)
@Inheritance(strategy = InheritanceType.JOINED)
public class MDocument extends MBase {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  protected MWorkflow workflowRef;

  // ----------------------------------------

  public MDocument() {
    // Empty
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @OneToOne(mappedBy = MWorkflowProp.DOCUMENT_REF)
  @LazyToOne(LazyToOneOption.FALSE)
  @Cascade({CascadeType.ALL})
  public MWorkflow getWorkflowRef() {
    return workflowRef;
  }

  public void setWorkflowRef(MWorkflow workflowRef) {
    this.workflowRef = workflowRef;
  }
}
