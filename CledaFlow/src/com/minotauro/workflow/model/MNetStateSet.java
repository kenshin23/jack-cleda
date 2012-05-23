/*
 * Created on 12/02/2006
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.workflow.prop.MPlaceStateProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_state_set")
@Proxy(lazy = false)
public class MNetStateSet extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  private boolean defaultNetStateSet;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetStateGrp netStateGrpRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MPlaceState> placeStateList = new ArrayList<MPlaceState>();

  // ----------------------------------------

  public MNetStateSet() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  // ----------------------------------------

  @Transient
  public boolean isDefaultNetStateSet() {
    return defaultNetStateSet;
  }

  public void setDefaultNetStateSet(boolean defaultNetStateSet) {
    this.defaultNetStateSet = defaultNetStateSet;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetStateGrp getNetStateGrpRef() {
    return netStateGrpRef;
  }

  public void setNetStateGrpRef(MNetStateGrp netStateGrpRef) {
    this.netStateGrpRef = netStateGrpRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MPlaceStateProp.NET_STATE_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MPlaceState> getPlaceStateList() {
    return placeStateList;
  }

  public void setPlaceStateList(List<MPlaceState> placeStateList) {
    this.placeStateList = placeStateList;
  }
}
