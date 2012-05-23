/*
 * Created on 28/12/2006
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
import com.minotauro.workflow.prop.MDocSectionViewStateProp;
import com.minotauro.workflow.prop.MNetStateGrpMetaDataProp;
import com.minotauro.workflow.prop.MNetStateSetProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_state_grp")
@Proxy(lazy = false)
public class MNetStateGrp extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  private boolean terminal;

  private int defaultDocSectionViewState = MDocSectionViewState.STATE_NOTDEF_INT;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetStateSet> netStateSetList = new ArrayList<MNetStateSet>();

  private List<MDocSectionViewState> docSectionViewStateList = //
  new ArrayList<MDocSectionViewState>();

  private Map<String, MNetStateGrpMetaData> netStateGrpMetaDataMap = //
  new HashMap<String, MNetStateGrpMetaData>();

  // ----------------------------------------

  public MNetStateGrp() {
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

  @Column(columnDefinition = "INTEGER")
  public boolean isTerminal() {
    return terminal;
  }

  public void setTerminal(boolean terminal) {
    this.terminal = terminal;
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

  @OneToMany(mappedBy = MNetStateSetProp.NET_STATE_GRP_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetStateSet> getNetStateSetList() {
    return netStateSetList;
  }

  public void setNetStateSetList(List<MNetStateSet> netStateSetList) {
    this.netStateSetList = netStateSetList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionViewStateProp.NET_STATE_GRP_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionViewState> getDocSectionViewStateList() {
    return docSectionViewStateList;
  }

  public void setDocSectionViewStateList(List<MDocSectionViewState> docSectionViewStateList) {
    this.docSectionViewStateList = docSectionViewStateList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetStateGrpMetaDataProp.NET_STATE_GRP_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MNetStateGrpMetaData> getNetStateGrpMetaDataMap() {
    return netStateGrpMetaDataMap;
  }

  public void setNetStateGrpMetaDataMap(Map<String, MNetStateGrpMetaData> netStateGrpMetaDataMap) {
    this.netStateGrpMetaDataMap = netStateGrpMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(netStateGrpMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        netStateGrpMetaDataMap, key, val, this, MNetStateGrpMetaData.class);
  }
}
