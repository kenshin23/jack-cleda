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
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_tst_crud_c")
@Proxy(lazy = false)
public class MCrudC extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private List<MMultJointAC> multJointACList = new ArrayList<MMultJointAC>();

  private List<MSingJointAC> singJointACList = new ArrayList<MSingJointAC>();

  // --------------------------------------------------------------------------------

  public MCrudC() {
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

  @OneToMany(mappedBy = _PropMMultJointAC.CRUD_CREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MMultJointAC> getMultJointACList() {
    return multJointACList;
  }

  public void setMultJointACList(List<MMultJointAC> multJointACList) {
    this.multJointACList = multJointACList;
  }

  @OneToMany(mappedBy = _PropMSingJointAC.CRUD_CREF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MSingJointAC> getSingJointACList() {
    return singJointACList;
  }

  public void setSingJointACList(List<MSingJointAC> singJointACList) {
    this.singJointACList = singJointACList;
  }
}
