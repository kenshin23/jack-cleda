/*
 * Created on 25/12/2005
 */
package com.minotauro.user.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_aut_user_prof")
@Proxy(lazy = false)
public class MUserProf extends MBase {

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MUser userRef;
  private MProf profRef;

  // --------------------------------------------------------------------------------

  public MUserProf() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MUser getUserRef() {
    return userRef;
  }

  public void setUserRef(MUser userRef) {
    this.userRef = userRef;
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
