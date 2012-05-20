/*
 * Created on 08/02/2006
 */
package com.minotauro.audit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_adm_object_audit")
@Proxy(lazy = false)
public class MObjectAudit extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  public enum OperationType {
    INSERT, UPDATE, DELETE;
  }

  // --------------------------------------------------------------------------------

  private String objectType;

  private int objectId;

  private OperationType operationType;

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MTransactionAudit transactionAuditRef;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private List<MFieldAudit> fieldAuditList = new ArrayList<MFieldAudit>();

  // --------------------------------------------------------------------------------

  public MObjectAudit() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public String getObjectType() {
    return objectType;
  }

  public void setObjectType(String objectType) {
    this.objectType = objectType;
  }

  // --------------------------------------------------------------------------------

  public int getObjectId() {
    return objectId;
  }

  public void setObjectId(int objectId) {
    this.objectId = objectId;
  }

  // --------------------------------------------------------------------------------

  public OperationType getOperationType() {
    return operationType;
  }

  public void setOperationType(OperationType operationType) {
    this.operationType = operationType;
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MTransactionAudit getTransactionAuditRef() {
    return transactionAuditRef;
  }

  public void setTransactionAuditRef(MTransactionAudit transactionAuditRef) {
    this.transactionAuditRef = transactionAuditRef;
  }

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships Methods
  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = "objectAuditRef")
  @LazyCollection(LazyCollectionOption.EXTRA)
  @Cascade({CascadeType.ALL, CascadeType.EVICT})
  public List<MFieldAudit> getFieldAuditList() {
    return fieldAuditList;
  }

  public void setFieldAuditList(List<MFieldAudit> fieldAuditList) {
    this.fieldAuditList = fieldAuditList;
  }
}
