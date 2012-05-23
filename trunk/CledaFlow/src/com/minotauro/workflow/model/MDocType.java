/*
 * Created on 30/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.metadata.model.MMetaData;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.workflow.prop.MDocSectionProp;
import com.minotauro.workflow.prop.MDocTypeMetaDataProp;
import com.minotauro.workflow.prop.MNetPetriProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_doc_type")
@Proxy(lazy = false)
public class MDocType extends MBase implements MMetaDataParent {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  // ----------------------------------------
  // ----- 1-1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MDocSection> docSectionList = new ArrayList<MDocSection>();

  private Map<String, MDocTypeMetaData> docTypeMetaDataMap = //
  new HashMap<String, MDocTypeMetaData>();

  // ----------------------------------------

  public MDocType() {
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
  // ----- 1-1 Relation Ships Methods
  // ----------------------------------------

  @OneToOne
  @JoinColumn(name = MNetPetriProp.DOC_TYPE_REF)
  @Cascade(CascadeType.ALL)
  public MNetPetri getNetPetriRef() {
    return netPetriRef;
  }

  public void setNetPetriRef(MNetPetri netPetriRef) {
    this.netPetriRef = netPetriRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionProp.DOC_TYPE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MDocSection> getDocSectionList() {
    return docSectionList;
  }

  public void setDocSectionList(List<MDocSection> docSectionList) {
    this.docSectionList = docSectionList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocTypeMetaDataProp.DOC_TYPE_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MDocTypeMetaData> getDocTypeMetaDataMap() {
    return docTypeMetaDataMap;
  }

  public void setDocTypeMetaDataMap(Map<String, MDocTypeMetaData> docTypeMetaDataMap) {
    this.docTypeMetaDataMap = docTypeMetaDataMap;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(docTypeMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        docTypeMetaDataMap, key, val, this, MDocTypeMetaData.class);
  }

  // ----------------------------------------

  public MDocSection getDocSectionByName(String name) {
    for (MDocSection docSection : docSectionList) {
      if (name.equals(docSection.getName())) {
        return docSection;
      }
    }

    return null;
  }
}
