/*
 * Created on 16/04/2006
 */
package com.minotauro.workflow.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Proxy;

import com.minotauro.metadata.model.MMetaData;
import com.minotauro.metadata.model.MMetaDataParent;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_doc_type_meta_data")
@Proxy(lazy = false)
public class MDocTypeMetaData extends MMetaData {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MDocType docTypeRef;

  // ----------------------------------------

  public MDocTypeMetaData() {
    // Empty
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

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getDocTypeRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setDocTypeRef((MDocType) metaDataParent);
  }
}
