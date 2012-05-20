/*
 * Created on 25/12/2005
 */
package com.minotauro.sandbox.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.user.model.MProf;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_doc_user_reg_prof")
@Proxy(lazy = false)
public class DUserRegProf extends MBase {

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private DUserReg userRegRef;

  private MProf profRef;

  // --------------------------------------------------------------------------------

  public DUserRegProf() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public DUserReg getUserRegRef() {
    return userRegRef;
  }

  public void setUserRegRef(DUserReg userRegRef) {
    this.userRegRef = userRegRef;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MProf getProfRef() {
    return profRef;
  }

  public void setProfRef(MProf profRef) {
    this.profRef = profRef;
  }
}
