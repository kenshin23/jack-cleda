/*
 * Created on 13/12/2007
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.user.model.MRole;
import com.minotauro.workflow.prop.MNetTransSetProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_trans_set_role")
@Proxy(lazy = false)
public class MNetTransSetRole extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String delegator;

  private int priority;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  private MRole roleRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetTransSet> netTransSetList = new ArrayList<MNetTransSet>();

  // ----------------------------------------

  public MNetTransSetRole() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public String getDelegator() {
    return delegator;
  }

  public void setDelegator(String delegator) {
    this.delegator = delegator;
  }

  // ----------------------------------------

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetPetri getNetPetriRef() {
    return netPetriRef;
  }

  public void setNetPetriRef(MNetPetri netPetriRef) {
    this.netPetriRef = netPetriRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MRole getRoleRef() {
    return roleRef;
  }

  public void setRoleRef(MRole roleRef) {
    this.roleRef = roleRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransSetProp.NET_TRANS_SET_ROLE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetTransSet> getNetTransSetList() {
    return netTransSetList;
  }

  public void setNetTransSetList(List<MNetTransSet> netTransSetList) {
    this.netTransSetList = netTransSetList;
  }
}
