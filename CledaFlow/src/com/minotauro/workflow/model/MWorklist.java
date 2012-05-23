/*
 * Created on 22/12/2006
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
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
import com.minotauro.workflow.prop.MNetTransSetProp;
import com.minotauro.workflow.prop.MWorklistMetaDataProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_worklist")
@Proxy(lazy = false)
public class MWorklist extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MNetTransSet> netTransSetList = new ArrayList<MNetTransSet>();

  private Map<String, MWorklistMetaData> worklistMetaDataMap = //
  new HashMap<String, MWorklistMetaData>();

  // ----------------------------------------

  public MWorklist() {
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
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MNetTransSetProp.WORKLIST_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MNetTransSet> getNetTransSetList() {
    return netTransSetList;
  }

  public void setNetTransSetList(List<MNetTransSet> netTransSetList) {
    this.netTransSetList = netTransSetList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MWorklistMetaDataProp.WORKLIST_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MWorklistMetaData> getWorklistMetaDataMap() {
    return worklistMetaDataMap;
  }

  public void setWorklistMetaDataMap(Map<String, MWorklistMetaData> workListMetaDataMap) {
    this.worklistMetaDataMap = workListMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(worklistMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        worklistMetaDataMap, key, val, this, MWorklistMetaData.class);
  }
}
