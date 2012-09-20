/*
 * Created on 09/02/2007
 */
package com.minotauro.sandbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_tst_crud_b")
@Proxy(lazy = false)
public class MCrudB extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private List<MMultJointAB> multJointABList = new ArrayList<MMultJointAB>();

  private List<MSingJointAB> singJointABList = new ArrayList<MSingJointAB>();
  
  private List<MSingJointPostB> singJointPostBList = new ArrayList<MSingJointPostB>();

  // --------------------------------------------------------------------------------

  public MCrudB() {
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

  @OneToMany(mappedBy = _PropMMultJointAB.CRUD_BREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MMultJointAB> getMultJointABList() {
    return multJointABList;
  }

  public void setMultJointABList(List<MMultJointAB> multJointABList) {
    this.multJointABList = multJointABList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMSingJointAB.CRUD_BREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MSingJointAB> getSingJointABList() {
    return singJointABList;
  }

  public void setSingJointABList(List<MSingJointAB> singJointABList) {
    this.singJointABList = singJointABList;
  }
  
  @OneToMany(mappedBy = _PropMSingJointPostB.CRUD_BREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MSingJointPostB> getSingJointPostBList() {
    return singJointPostBList;
  }

  public void setSingJointPostBList(List<MSingJointPostB> singJointPostBList) {
    this.singJointPostBList = singJointPostBList;
  }
}
