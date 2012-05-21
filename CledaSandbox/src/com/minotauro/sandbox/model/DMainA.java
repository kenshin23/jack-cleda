/*
 * Created on 26/06/2008
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

import com.minotauro.workflow.model.MDocument;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_doc_main_a")
@Proxy(lazy = false)
public class DMainA extends MDocument {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private List<DMultJointAB> multJointABList = new ArrayList<DMultJointAB>();
  private List<DMultJointAC> multJointACList = new ArrayList<DMultJointAC>();

  private List<DSingJointAB> singJointABList = new ArrayList<DSingJointAB>();
  private List<DSingJointAC> singJointACList = new ArrayList<DSingJointAC>();

  private List<DInnerA> innerACList = new ArrayList<DInnerA>();

  // --------------------------------------------------------------------------------

  public DMainA() {
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

  public void setDesc(String description) {
    this.desc = description;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDMultJointAB.MAIN_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DMultJointAB> getMultJointABList() {
    return multJointABList;
  }

  public void setMultJointABList(List<DMultJointAB> multJointABList) {
    this.multJointABList = multJointABList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDMultJointAC.MAIN_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DMultJointAC> getMultJointACList() {
    return multJointACList;
  }

  public void setMultJointACList(List<DMultJointAC> multJointACList) {
    this.multJointACList = multJointACList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDSingJointAB.MAIN_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DSingJointAB> getSingJointABList() {
    return singJointABList;
  }

  public void setSingJointABList(List<DSingJointAB> singJointABList) {
    this.singJointABList = singJointABList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDSingJointAC.MAIN_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DSingJointAC> getSingJointACList() {
    return singJointACList;
  }

  public void setSingJointACList(List<DSingJointAC> singJointACList) {
    this.singJointACList = singJointACList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDInnerA.MAIN_AREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  @OrderBy(clause = _PropDInnerA.NAME)
  public List<DInnerA> getInnerACList() {
    return innerACList;
  }

  public void setInnerACList(List<DInnerA> innerACList) {
    this.innerACList = innerACList;
  }
}
