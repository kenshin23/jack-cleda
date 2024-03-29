/*
 * Created on 25/12/2005
 */
package com.minotauro.user.model;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "t_aut_role")
@Proxy(lazy = false)
public class MRole extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String name;
  private String description;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private List<MProfRole> profRoleList = new ArrayList<MProfRole>();

  // --------------------------------------------------------------------------------

  public MRole() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // --------------------------------------------------------------------------------

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships Methods
  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMProfRole.ROLE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MProfRole> getProfRoleList() {
    return profRoleList;
  }

  public void setProfRoleList(List<MProfRole> profRoleList) {
    this.profRoleList = profRoleList;
  }
}
