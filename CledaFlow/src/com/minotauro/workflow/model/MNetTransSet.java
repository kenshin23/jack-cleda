/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
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
import com.minotauro.workflow.prop.MDocSectionEditStateProp;
import com.minotauro.workflow.prop.MNetTransProp;
import com.minotauro.workflow.prop.MNetTransSetMetaDataProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_trans_set")
@Proxy(lazy = false)
public class MNetTransSet extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  private String/**/agentType;
  private int/*   */agentTime;

  private boolean init;

  private int defaultDocSectionEditState = MDocSectionEditState.STATE_NOTDEF_INT;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  private MWorklist worklistRef;

  private MNetTransSetRole netTransSetRoleRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetTrans> netTransList = new ArrayList<MNetTrans>();

  private List<MDocSectionEditState> docSectionEditStateList = //
  new ArrayList<MDocSectionEditState>();

  private Map<String, MNetTransSetMetaData> netTransSetMetaDataMap = //
  new HashMap<String, MNetTransSetMetaData>();

  // ----------------------------------------

  public MNetTransSet() {
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

  public String getAgentType() {
    return agentType;
  }

  public void setAgentType(String agentType) {
    this.agentType = agentType;
  }

  // ----------------------------------------

  public int getAgentTime() {
    return agentTime;
  }

  public void setAgentTime(int agentTime) {
    this.agentTime = agentTime;
  }

  // ----------------------------------------

  @Column(columnDefinition = "INTEGER")
  public boolean isInit() {
    return init;
  }

  public void setInit(boolean init) {
    this.init = init;
  }

  // ----------------------------------------

  public int getDefaultDocSectionEditState() {
    return defaultDocSectionEditState;
  }

  public void setDefaultDocSectionEditState(int defaultDocSectionEditState) {
    this.defaultDocSectionEditState = defaultDocSectionEditState;
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
  public MWorklist getWorklistRef() {
    return worklistRef;
  }

  public void setWorklistRef(MWorklist worklistRef) {
    this.worklistRef = worklistRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetTransSetRole getNetTransSetRoleRef() {
    return netTransSetRoleRef;
  }

  public void setNetTransSetRoleRef(MNetTransSetRole netTransSetRoleRef) {
    this.netTransSetRoleRef = netTransSetRoleRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransProp.NET_TRANS_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetTrans> getNetTransList() {
    return netTransList;
  }

  public void setNetTransList(List<MNetTrans> netTransList) {
    this.netTransList = netTransList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionEditStateProp.NET_TRANS_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionEditState> getDocSectionEditStateList() {
    return docSectionEditStateList;
  }

  public void setDocSectionEditStateList(List<MDocSectionEditState> docSectionEditStateList) {
    this.docSectionEditStateList = docSectionEditStateList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransSetMetaDataProp.NET_TRANS_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MNetTransSetMetaData> getNetTransSetMetaDataMap() {
    return netTransSetMetaDataMap;
  }

  public void setNetTransSetMetaDataMap(Map<String, MNetTransSetMetaData> netTransSetMetaDataMap) {
    this.netTransSetMetaDataMap = netTransSetMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(netTransSetMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        netTransSetMetaDataMap, key, val, this, MNetTransSetMetaData.class);
  }
}
