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
@Table(name = "t_doc_inner_sms")
@Proxy(lazy = false)
public class DInnerSms extends MBase {

  private String name;
  private String desc;

  // --------------------------------------------------------------------------------

  private DMainSms mainSmsRef;

  // --------------------------------------------------------------------------------

  public DInnerSms() {
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
  public DMainSms getMainSmsRef() {
    return mainSmsRef;
  }

  public void setMainSmsRef(DMainSms mainSmsRef) {
    this.mainSmsRef = mainSmsRef;
  }
}
