/*
 * Created on 15/04/2007
 */
package com.minotauro.sandbox.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_doc_mult_joint_Sms_b")
@Proxy(lazy = false)
public class DMultJointSmsB extends MBase {

  private DMainSms mainSmsRef;
  private MCrudB crudBRef;

  // --------------------------------------------------------------------------------

  public DMultJointSmsB() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public DMainSms getMainSmsRef() {
    return mainSmsRef;
  }

  public void setMainSmsRef(DMainSms mainSmsRef) {
    this.mainSmsRef = mainSmsRef;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MCrudB getCrudBRef() {
    return crudBRef;
  }

  public void setCrudBRef(MCrudB crudBRef) {
    this.crudBRef = crudBRef;
  }
}
