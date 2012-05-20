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
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_doc_sing_joint_a_c")
@Proxy(lazy = false)
public class DSingJointAC extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private DMainA mainARef;
  private MCrudC crudCRef;

  // --------------------------------------------------------------------------------

  public DSingJointAC() {
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
  public DMainA getMainARef() {
    return mainARef;
  }

  public void setMainARef(DMainA mainARef) {
    this.mainARef = mainARef;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MCrudC getCrudCRef() {
    return crudCRef;
  }

  public void setCrudCRef(MCrudC crudCRef) {
    this.crudCRef = crudCRef;
  }
}
