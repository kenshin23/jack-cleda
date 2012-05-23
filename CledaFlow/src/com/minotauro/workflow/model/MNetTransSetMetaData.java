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
@Table(name = "t_wrk_net_trans_set_meta_data")
@Proxy(lazy = false)
public class MNetTransSetMetaData extends MMetaData {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetTransSet netTransSetRef;

  // ----------------------------------------

  public MNetTransSetMetaData() {
    // Empty
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

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getNetTransSetRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setNetTransSetRef((MNetTransSet) metaDataParent);
  }
}
