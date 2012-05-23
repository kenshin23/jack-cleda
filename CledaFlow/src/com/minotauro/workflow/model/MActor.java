/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.prop.MWrkTransSetProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "t_wrk_actor")
@Proxy(lazy = false)
public abstract class MActor extends MBase {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MUser userRef;

  private MNetTransSetRole netTransSetRoleRef;

  private MWorkflow workflowRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MWrkTransSet> wrkTransSetList = new ArrayList<MWrkTransSet>();

  // ----------------------------------------

  public MActor() {
    // Empty
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetTransSetRole getNetTransSetRoleRef() {
    return netTransSetRoleRef;
  }

  public void setNetTransSetRoleRef(MNetTransSetRole netTransSetRoleRef) {
    this.netTransSetRoleRef = netTransSetRoleRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MUser getUserRef() {
    return userRef;
  }

  public void setUserRef(MUser userRef) {
    this.userRef = userRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MWorkflow getWorkflowRef() {
    return workflowRef;
  }

  public void setWorkflowRef(MWorkflow workflowRef) {
    this.workflowRef = workflowRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MWrkTransSetProp.ACTOR_REF)
  @LazyCollection(LazyCollectionOption.TRUE)
  // @Cascade({CascadeType.ALL, CascadeType.DELETE_ORPHAN}) // Don't Cascade
  public List<MWrkTransSet> getWrkTransSetList() {
    return wrkTransSetList;
  }

  public void setWrkTransSetList(List<MWrkTransSet> wrkTransSetList) {
    this.wrkTransSetList = wrkTransSetList;
  }
}
