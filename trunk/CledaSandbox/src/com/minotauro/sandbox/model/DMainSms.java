
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
 * @author Karla "azucar" Moreno
 */
@Entity
@Table(name = "t_doc_main_sms")
@Proxy(lazy = false)
public class DMainSms extends MDocument {

  private String name;
  private String desc;
  private String tipo;
  
  // --------------------------------------------------------------------------------
  private List<DMultJointSmsB> multJointSmsBList = new ArrayList<DMultJointSmsB>();
 
  private List<DSingJointSmsB> singJointSmsBList = new ArrayList<DSingJointSmsB>();

  private List<DInnerSms> innerSmsCList = new ArrayList<DInnerSms>();
  //--------------------------------------------------------------------------------

  public DMainSms() {
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

  
  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }
  
  //--------------------------------------------------------------------------------
  @OneToMany(mappedBy = _PropDMultJointSmsB.MAIN_SMS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DMultJointSmsB> getMultJointSmsBList() {
    return multJointSmsBList;
  }

  public void setMultJointSmsBList(List<DMultJointSmsB> multJointSmsBList) {
    this.multJointSmsBList = multJointSmsBList;
  }

  // --------------------------------------------------------------------------------

  
  @OneToMany(mappedBy = _PropDSingJointSmsB.MAIN_SMS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DSingJointSmsB> getSingJointSmsBList() {
    return singJointSmsBList;
  }

  public void setSingJointSmsBList(List<DSingJointSmsB> singJointSmsBList) {
    this.singJointSmsBList = singJointSmsBList;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDInnerSms.MAIN_SMS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  @OrderBy(clause = _PropDInnerSms.NAME)
  public List<DInnerSms> getInnerSmsCList() {
    return innerSmsCList;
  }

  public void setInnerSmsCList(List<DInnerSms> innerSmsCList) {
    this.innerSmsCList = innerSmsCList;
  }

  
 }


 

