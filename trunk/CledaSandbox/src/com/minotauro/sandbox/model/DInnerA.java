/*
 * Created on 09/02/2007
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
@Table(name = "t_doc_inner_a")
@Proxy(lazy = false)
public class DInnerA extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private DMainA mainARef;

  // --------------------------------------------------------------------------------

  public DInnerA() {
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
}
