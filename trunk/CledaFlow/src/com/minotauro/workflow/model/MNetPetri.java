/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.metadata.model.MMetaData;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.workflow.prop.MDocTypeProp;
import com.minotauro.workflow.prop.MNetPetriMetaDataProp;
import com.minotauro.workflow.prop.MNetPlaceProp;
import com.minotauro.workflow.prop.MNetStateGrpProp;
import com.minotauro.workflow.prop.MNetTransSetProp;
import com.minotauro.workflow.prop.MNetTransSetRoleProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_petri")
@Proxy(lazy = false)
public class MNetPetri extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  // ----------------------------------------

  private int defaultDocSectionValidateState = MDocSectionValidateState.STATE_NOTDEF_INT;

  // ----------------------------------------

  private int defaultDocSectionEditState = MDocSectionEditState.STATE_NOTDEF_INT;
  private int defaultDocSectionViewState = MDocSectionViewState.STATE_NOTDEF_INT;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MDocType docTypeRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetTransSetRole> netTransSetRoleList = new ArrayList<MNetTransSetRole>();

  private List<MNetPlace> netPlaceList = new ArrayList<MNetPlace>();

  private List<MNetTransSet> netTransSetList = new ArrayList<MNetTransSet>();
  private List<MNetStateGrp> netStateGrpList = new ArrayList<MNetStateGrp>();

  private Map<String, MNetPetriMetaData> netPetriMetaDataMap = //
  new HashMap<String, MNetPetriMetaData>();

  // ----------------------------------------

  public MNetPetri() {
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

  public int getDefaultDocSectionValidateState() {
    return defaultDocSectionValidateState;
  }

  public void setDefaultDocSectionValidateState(int defaultDocSectionValidateState) {
    this.defaultDocSectionValidateState = defaultDocSectionValidateState;
  }

  // ----------------------------------------

  public int getDefaultDocSectionEditState() {
    return defaultDocSectionEditState;
  }

  public void setDefaultDocSectionEditState(int defaultDocSectionEditState) {
    this.defaultDocSectionEditState = defaultDocSectionEditState;
  }

  // ----------------------------------------

  public int getDefaultDocSectionViewState() {
    return defaultDocSectionViewState;
  }

  public void setDefaultDocSectionViewState(int defaultDocSectionViewState) {
    this.defaultDocSectionViewState = defaultDocSectionViewState;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @OneToOne(mappedBy = MDocTypeProp.NET_PETRI_REF)
  @LazyToOne(LazyToOneOption.FALSE)
  @Cascade({CascadeType.ALL})
  public MDocType getDocTypeRef() {
    return docTypeRef;
  }

  public void setDocTypeRef(MDocType docTypeRef) {
    this.docTypeRef = docTypeRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransSetRoleProp.NET_PETRI_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetTransSetRole> getNetTransSetRoleList() {
    return netTransSetRoleList;
  }

  public void setNetTransSetRoleList(List<MNetTransSetRole> netTransSetRoleList) {
    this.netTransSetRoleList = netTransSetRoleList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetPlaceProp.NET_PETRI_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetPlace> getNetPlaceList() {
    return netPlaceList;
  }

  public void setNetPlaceList(List<MNetPlace> netPlaceList) {
    this.netPlaceList = netPlaceList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransSetProp.NET_PETRI_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetTransSet> getNetTransSetList() {
    return netTransSetList;
  }

  public void setNetTransSetList(List<MNetTransSet> netTransSetList) {
    this.netTransSetList = netTransSetList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetStateGrpProp.NET_PETRI_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetStateGrp> getNetStateGrpList() {
    return netStateGrpList;
  }

  public void setNetStateGrpList(List<MNetStateGrp> netStateGrpList) {
    this.netStateGrpList = netStateGrpList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetPetriMetaDataProp.NET_PETRI_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MNetPetriMetaData> getNetPetriMetaDataMap() {
    return netPetriMetaDataMap;
  }

  public void setNetPetriMetaDataMap(Map<String, MNetPetriMetaData> netPetriMetaDataMap) {
    this.netPetriMetaDataMap = netPetriMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(netPetriMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        netPetriMetaDataMap, key, val, this, MNetPetriMetaData.class);
  }

  // ----------------------------------------

  @Transient
  public MNetStateSet getNetStateSetByName(String name) {
    for (MNetStateGrp netStateGrp : netStateGrpList) {
      for (MNetStateSet netStateSet : netStateGrp.getNetStateSetList()) {
        if (name.equals(netStateSet.getName())) {
          return netStateSet;
        }
      }
    }

    return null;
  }

  // ----------------------------------------

  @Transient
  public MNetStateSet getDefaultNetStateSet() {
    for (MNetStateGrp netStateGrp : netStateGrpList) {
      for (MNetStateSet netStateSet : netStateGrp.getNetStateSetList()) {
        if (netStateSet.isDefaultNetStateSet()) {
          return netStateSet;
        }
      }
    }

    return null;
  }

  // ----------------------------------------

  @Transient
  public MNetTrans getNetTransByName(String name) {
    for (MNetTransSet netTransSet : netTransSetList) {
      for (MNetTrans netTrans : netTransSet.getNetTransList()) {
        if (name.equals(netTrans.getName())) {
          return netTrans;
        }
      }
    }

    return null;
  }
}
