/*
 * Created on 25/12/2005
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
import com.minotauro.workflow.prop.MDocSectionEditStateProp;
import com.minotauro.workflow.prop.MDocSectionValidateStateProp;
import com.minotauro.workflow.prop.MDocSectionViewStateProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_doc_section")
@Proxy(lazy = false)
public class MDocSection extends MBase {

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private String name;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MDocType docTypeRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MDocSectionEditState> docSectionEditStateList = //
  new ArrayList<MDocSectionEditState>();

  private List<MDocSectionViewState> docSectionViewStateList = //
  new ArrayList<MDocSectionViewState>();

  // ----------------------------------------

  private List<MDocSectionValidateState> docSectionValidateStateList = //
  new ArrayList<MDocSectionValidateState>();

  // ----------------------------------------

  public MDocSection() {
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
  public MDocType getDocTypeRef() {
    return docTypeRef;
  }

  public void setDocTypeRef(MDocType docTypeRef) {
    this.docTypeRef = docTypeRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionEditStateProp.DOC_SECTION_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionEditState> getDocSectionEditStateList() {
    return docSectionEditStateList;
  }

  public void setDocSectionEditStateList(List<MDocSectionEditState> docSectionEditStateList) {
    this.docSectionEditStateList = docSectionEditStateList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionViewStateProp.DOC_SECTION_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionViewState> getDocSectionViewStateList() {
    return docSectionViewStateList;
  }

  public void setDocSectionViewStateList(List<MDocSectionViewState> docSectionViewStateList) {
    this.docSectionViewStateList = docSectionViewStateList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MDocSectionValidateStateProp.DOC_SECTION_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MDocSectionValidateState> getDocSectionValidateStateList() {
    return docSectionValidateStateList;
  }

  public void setDocSectionValidateStateList(List<MDocSectionValidateState> docSectionValidateStateList) {
    this.docSectionValidateStateList = docSectionValidateStateList;
  }
}
