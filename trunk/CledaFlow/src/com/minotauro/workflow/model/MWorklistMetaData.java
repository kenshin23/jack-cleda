/*
 * Created on 21/08/2006
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
@Table(name = "t_wrk_worklist_meta_data")
@Proxy(lazy = false)
public class MWorklistMetaData extends MMetaData {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MWorklist worklistRef;

  // ----------------------------------------

  public MWorklistMetaData() {
    // Empty
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MWorklist getWorklistRef() {
    return worklistRef;
  }

  public void setWorklistRef(MWorklist worklistRef) {
    this.worklistRef = worklistRef;
  }

  // ----------------------------------------

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getWorklistRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setWorklistRef((MWorklist) metaDataParent);
  }
}
