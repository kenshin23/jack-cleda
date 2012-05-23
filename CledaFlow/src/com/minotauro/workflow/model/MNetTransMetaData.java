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
@Table(name = "t_wrk_net_trans_meta_data")
@Proxy(lazy = false)
public class MNetTransMetaData extends MMetaData {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetTrans netTransRef;

  // ----------------------------------------

  public MNetTransMetaData() {
    // Empty
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetTrans getNetTransRef() {
    return netTransRef;
  }

  public void setNetTransRef(MNetTrans netTransRef) {
    this.netTransRef = netTransRef;
  }

  // ----------------------------------------

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getNetTransRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setNetTransRef((MNetTrans) metaDataParent);
  }
}
