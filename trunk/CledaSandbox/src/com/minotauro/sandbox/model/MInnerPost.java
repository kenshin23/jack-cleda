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
 * @author Karla "azucar" Moreno
 */
@Entity
@Table(name = "t_tst_inner_post")
@Proxy(lazy = false)
public class MInnerPost extends MBase {

	private String name;
	  private String desc;
	  private String body;

  // --------------------------------------------------------------------------------

  private MCrudPost crudPostRef;

  // --------------------------------------------------------------------------------

  public MInnerPost() {
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
  public MCrudPost getCrudARef() {
    return crudPostRef;
  }

  public void setCrudPostRef(MCrudPost a) {
    this.crudPostRef = a;
  }

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}



}
