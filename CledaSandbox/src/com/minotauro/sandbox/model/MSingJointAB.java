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
@Table(name = "t_tst_sing_joint_a_b")
@Proxy(lazy = false)
public class MSingJointAB extends MBase {

  private MCrudA crudARef;
  private MCrudB crudBRef;

  // --------------------------------------------------------------------------------

  public MSingJointAB() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MCrudA getCrudARef() {
    return crudARef;
  }

  public void setCrudARef(MCrudA crudARef) {
    this.crudARef = crudARef;
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
