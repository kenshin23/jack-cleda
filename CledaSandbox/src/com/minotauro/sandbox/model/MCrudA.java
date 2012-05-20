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
@Table(name = "t_tst_crud_a")
@Proxy(lazy = false)
public class MCrudA extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private List<MMultJointAB> multJointABList = new ArrayList<MMultJointAB>();
  private List<MMultJointAC> multJointACList = new ArrayList<MMultJointAC>();

  private List<MSingJointAB> singJointABList = new ArrayList<MSingJointAB>();
  private List<MSingJointAC> singJointACList = new ArrayList<MSingJointAC>();

  private List<MInnerA> innerACList = new ArrayList<MInnerA>();

  // --------------------------------------------------------------------------------

  public MCrudA() {
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

  @OneToMany(mappedBy = _PropMMultJointAB.CRUD_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MMultJointAB> getMultJointABList() {
    return multJointABList;
  }

  public void setMultJointABList(List<MMultJointAB> multJointABList) {
    this.multJointABList = multJointABList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMMultJointAC.CRUD_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MMultJointAC> getMultJointACList() {
    return multJointACList;
  }

  public void setMultJointACList(List<MMultJointAC> multJointACList) {
    this.multJointACList = multJointACList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMSingJointAB.CRUD_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MSingJointAB> getSingJointABList() {
    return singJointABList;
  }

  public void setSingJointABList(List<MSingJointAB> singJointABList) {
    this.singJointABList = singJointABList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMSingJointAC.CRUD_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MSingJointAC> getSingJointACList() {
    return singJointACList;
  }

  public void setSingJointACList(List<MSingJointAC> singJointACList) {
    this.singJointACList = singJointACList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMInnerA.CRUD_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  @OrderBy(clause = _PropMInnerA.NAME)
  public List<MInnerA> getInnerACList() {
    return innerACList;
  }

  public void setInnerACList(List<MInnerA> innerACList) {
    this.innerACList = innerACList;
  }
}
