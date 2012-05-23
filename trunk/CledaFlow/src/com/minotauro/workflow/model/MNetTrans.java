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
import com.minotauro.workflow.prop.MDocSectionValidateStateProp;
import com.minotauro.workflow.prop.MNetEdgeProp;
import com.minotauro.workflow.prop.MNetTransMetaDataProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_net_trans")
@Proxy(lazy = false)
public class MNetTrans extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  private int defaultDocSectionValidateState = MDocSectionValidateState.STATE_NOTDEF_INT;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetTransSet netTransSetRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetEdge> netEdgeList = new ArrayList<MNetEdge>();

  private List<MDocSectionValidateState> docSectionValidateStateList = //
  new ArrayList<MDocSectionValidateState>();

  private Map<String, MNetTransMetaData> netTransMetaDataMap = //
  new HashMap<String, MNetTransMetaData>();

  // ----------------------------------------

  public MNetTrans() {
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
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetTransSet getNetTransSetRef() {
    return netTransSetRef;
  }

  public void setNetTransSetRef(MNetTransSet netTransSetRef) {
    this.netTransSetRef = netTransSetRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetEdgeProp.NET_TRANS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MNetEdge> getNetEdgeList() {
    return netEdgeList;
  }

  public void setNetEdgeList(List<MNetEdge> netEdgeList) {
    this.netEdgeList = netEdgeList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionValidateStateProp.NET_TRANS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionValidateState> getDocSectionValidateStateList() {
    return docSectionValidateStateList;
  }

  public void setDocSectionValidateStateList(List<MDocSectionValidateState> docSectionValidateStateList) {
    this.docSectionValidateStateList = docSectionValidateStateList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransMetaDataProp.NET_TRANS_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MNetTransMetaData> getNetTransMetaDataMap() {
    return netTransMetaDataMap;
  }

  public void setNetTransMetaDataMap(Map<String, MNetTransMetaData> netTransMetaDataMap) {
    this.netTransMetaDataMap = netTransMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(netTransMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        netTransMetaDataMap, key, val, this, MNetTransMetaData.class);
  }
}
