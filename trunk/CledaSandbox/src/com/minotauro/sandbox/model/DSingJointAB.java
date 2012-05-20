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
@Table(name = "t_doc_sing_joint_a_b")
@Proxy(lazy = false)
public class DSingJointAB extends MBase {

  private DMainA mainARef;
  private MCrudB crudBRef;

  // --------------------------------------------------------------------------------

  public DSingJointAB() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public DMainA getMainARef() {
    return mainARef;
  }

  public void setMainARef(DMainA mainARef) {
    this.mainARef = mainARef;
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
