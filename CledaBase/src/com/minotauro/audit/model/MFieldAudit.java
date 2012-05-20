/*
 * Created on 08/02/2006
 */
package com.minotauro.audit.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_adm_field_audit")
@Proxy(lazy = false)
public class MFieldAudit extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String name;

  private String prevValue;
  private String nextValue;

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MObjectAudit objectAuditRef;

  // --------------------------------------------------------------------------------

  public MFieldAudit() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // --------------------------------------------------------------------------------

  public String getPrevValue() {
    return prevValue;
  }

  public void setPrevValue(String prevValue) {
    this.prevValue = prevValue;
  }

  // --------------------------------------------------------------------------------

  public String getNextValue() {
    return nextValue;
  }

  public void setNextValue(String nextValue) {
    this.nextValue = nextValue;
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MObjectAudit getObjectAuditRef() {
    return objectAuditRef;
  }

  public void setObjectAuditRef(MObjectAudit objectAuditRef) {
    this.objectAuditRef = objectAuditRef;
  }
}
