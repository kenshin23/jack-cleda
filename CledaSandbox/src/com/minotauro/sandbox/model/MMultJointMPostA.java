/*
 * Created on 15/04/2007
 */
package com.minotauro.sandbox.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Karla "sugar" Moreno
 */
@Entity
@Table(name = "t_tst_mult_joint_mpost_a")
@Proxy(lazy = false)
public class MMultJointMPostA extends MBase {

	  private MCrudPost crudPostRef;
	  private MCrudA crudARef;


  // --------------------------------------------------------------------------------------

  public MMultJointMPostA() {
    // Empty
  }



  @ManyToOne
  public MCrudPost getcrudPostRef() {
    return crudPostRef;
  }

  public void setcrudPostRef(MCrudPost crudPostRef) {
    this.crudPostRef = crudPostRef;
  }
  
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MCrudA getCrudARef() {
    return crudARef;
  }

  public void setCrudARef(MCrudA crudARef) {
    this.crudARef = crudARef;
  }

  // --------------------------------------------------------------------------------
  
  
}
