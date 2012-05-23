/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.minotauro.metadata.model.MMetaData;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.workflow.prop.MNetEdgeProp;
import com.minotauro.workflow.prop.MPlaceStateProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_place")
@Proxy(lazy = false)
public class MNetPlace extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetEdge> netEdgeList = new ArrayList<MNetEdge>();

  private List<MPlaceState> placeStateList = new ArrayList<MPlaceState>();

  private Map<String, MNetTransMetaData> netPlaceMetaDataMap = //
  new HashMap<String, MNetTransMetaData>();

  // ----------------------------------------

  public MNetPlace() {
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
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetEdgeProp.NET_PLACE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetEdge> getNetEdgeList() {
    return netEdgeList;
  }

  public void setNetEdgeList(List<MNetEdge> netEdgeList) {
    this.netEdgeList = netEdgeList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MPlaceStateProp.NET_PLACE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MPlaceState> getPlaceStateList() {
    return placeStateList;
  }

  public void setPlaceStateList(List<MPlaceState> placeStateList) {
    this.placeStateList = placeStateList;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(netPlaceMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        netPlaceMetaDataMap, key, val, this, MNetTransMetaData.class);
  }
}
