/*
 * Created on 08/02/2006
 */
package com.minotauro.audit.model;

import java.util.ArrayList;
import java.util.Calendar;
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
import com.minotauro.user.model.MUser;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_adm_transaction_audit")
@Proxy(lazy = false)
public class MTransactionAudit extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private Calendar date;

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MUser userRef;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private List<MObjectAudit> objectAuditList = new ArrayList<MObjectAudit>();

  // --------------------------------------------------------------------------------

  public MTransactionAudit() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public Calendar getDate() {
    return date;
  }

  public void setDate(Calendar date) {
    this.date = date;
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MUser getUserRef() {
    return userRef;
  }

  public void setUserRef(MUser userRef) {
    this.userRef = userRef;
  }

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships Methods
  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = "transactionAuditRef")
  @LazyCollection(LazyCollectionOption.EXTRA)
  @Cascade({CascadeType.ALL, CascadeType.EVICT})
  public List<MObjectAudit> getObjectAuditList() {
    return objectAuditList;
  }

  public void setObjectAuditList(List<MObjectAudit> objectAuditList) {
    this.objectAuditList = objectAuditList;
  }
}
