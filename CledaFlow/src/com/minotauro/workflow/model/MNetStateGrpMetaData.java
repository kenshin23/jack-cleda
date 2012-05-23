/*
 * Created on 28/12/2006
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
@Table(name = "t_wrk_net_state_grp_meta_data")
@Proxy(lazy = false)
public class MNetStateGrpMetaData extends MMetaData {

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetStateGrp netStateGrpRef;

  // ----------------------------------------

  public MNetStateGrpMetaData() {
    // Empty
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetStateGrp getNetStateGrpRef() {
    return netStateGrpRef;
  }

  public void setNetStateGrpRef(MNetStateGrp netStateGrpRef) {
    this.netStateGrpRef = netStateGrpRef;
  }

  // ----------------------------------------

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getNetStateGrpRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setNetStateGrpRef((MNetStateGrp) metaDataParent);
  }
}
