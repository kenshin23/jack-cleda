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
@Table(name = "t_aut_prof_role")
@Proxy(lazy = false)
public class MProfRole extends MBase {

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MProf profRef;
  private MRole roleRef;

  // --------------------------------------------------------------------------------

  public MProfRole() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MProf getProfRef() {
    return profRef;
  }

  public void setProfRef(MProf profRef) {
    this.profRef = profRef;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MRole getRoleRef() {
    return roleRef;
  }

  public void setRoleRef(MRole roleRef) {
    this.roleRef = roleRef;
  }
}
