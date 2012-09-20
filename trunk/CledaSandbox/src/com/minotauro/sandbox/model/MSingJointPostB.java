/*
 * Created on 15/04/2007
 */
package com.minotauro.sandbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author e.j.:jack
 */
@Entity
@Table(name = "t_tst_sing_joint_a_c")
@Proxy(lazy = false)
public class MSingJointPostB extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private MCrudPost crudPostRef;
  private MCrudB crudBRef;

  // --------------------------------------------------------------------------------

  public MSingJointPostB() {
    // Empty
  }

  // --------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // --------------------------------------------------------------------------------

  @Column(name = "odesc")
  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MCrudPost getCrudPostRef() {
    return crudPostRef;
  }

  public void setCrudPostRef(MCrudPost crudPostRef) {
    this.crudPostRef = crudPostRef;
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
