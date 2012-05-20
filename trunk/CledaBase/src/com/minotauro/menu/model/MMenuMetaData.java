/*
 * Created on 21/08/2011
 */
package com.minotauro.menu.model;

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
@Table(name = "t_con_menu_meta_data")
@Proxy(lazy = false)
public class MMenuMetaData extends MMetaData {

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  private MMenu menuRef;

  // --------------------------------------------------------------------------------

  public MMenuMetaData() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MMenu getMenuRef() {
    return menuRef;
  }

  public void setMenuRef(MMenu menuRef) {
    this.menuRef = menuRef;
  }

  // --------------------------------------------------------------------------------

  @Transient
  @Override
  public MMetaDataParent getParent() {
    return getMenuRef();
  }

  @Override
  public void setParent(MMetaDataParent metaDataParent) {
    setMenuRef((MMenu) metaDataParent);
  }
}
