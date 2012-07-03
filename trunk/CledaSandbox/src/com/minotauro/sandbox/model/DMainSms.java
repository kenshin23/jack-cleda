
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
  
  
 }


 

