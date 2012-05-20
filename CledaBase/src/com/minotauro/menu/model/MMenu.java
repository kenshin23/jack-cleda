/*
 * Created on 16/08/2011
 */
package com.minotauro.menu.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
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
import com.minotauro.user.model.MPriv;
import com.minotauro.user.model.MRole;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_con_menu")
@Proxy(lazy = false)
public class MMenu extends MBase implements MMetaDataParent {

  // --------------------------------------------------------------------------------

  public enum Type {
    ENTRY, GROUP, EVENT
  }

  // --------------------------------------------------------------------------------

  public enum Scope {
    EXTERNAL, INTERNAL, ALL
  }

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected Type type;

  protected String route;

  protected int order;

  protected String i18nCls;
  protected String i18nKey;

  protected String handler;

  protected Scope scope;

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships
  // --------------------------------------------------------------------------------

  protected MPriv privRef;
  protected MRole roleRef;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private Map<String, MMenuMetaData> menuMetaDataMap = //
  new HashMap<String, MMenuMetaData>();

  // --------------------------------------------------------------------------------

  public MMenu() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  // --------------------------------------------------------------------------------

  public String getRoute() {
    return route;
  }

  public void setRoute(String route) {
    this.route = route;
  }

  // --------------------------------------------------------------------------------

  @Column(name = "oorder")
  public int getOrder() {
    return order;
  }

  public void setOrder(int order) {
    this.order = order;
  }

  // --------------------------------------------------------------------------------

  public String getI18nCls() {
    return i18nCls;
  }

  public void setI18nCls(String i18nCls) {
    this.i18nCls = i18nCls;
  }

  // --------------------------------------------------------------------------------

  public String getI18nKey() {
    return i18nKey;
  }

  public void setI18nKey(String i18nKey) {
    this.i18nKey = i18nKey;
  }

  // --------------------------------------------------------------------------------

  public String getHandler() {
    return handler;
  }

  public void setHandler(String handler) {
    this.handler = handler;
  }

  // --------------------------------------------------------------------------------

  public Scope getScope() {
    return scope;
  }

  public void setScope(Scope scope) {
    this.scope = scope;
  }

  // --------------------------------------------------------------------------------
  // ----- 1 Relation Ships Methods
  // --------------------------------------------------------------------------------

  @ManyToOne
  public MPriv getPrivRef() {
    return privRef;
  }

  public void setPrivRef(MPriv privRef) {
    this.privRef = privRef;
  }

  // --------------------------------------------------------------------------------

  @ManyToOne
  public MRole getRoleRef() {
    return roleRef;
  }

  public void setRoleRef(MRole roleRef) {
    this.roleRef = roleRef;
  }

  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMMenuMetaData.MENU_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  @MapKeyColumn(name = "okey")
  public Map<String, MMenuMetaData> getMenuMetaDataMap() {
    return menuMetaDataMap;
  }

  public void setMenuMetaDataMap(Map<String, MMenuMetaData> menuMetaDataMap) {
    this.menuMetaDataMap = menuMetaDataMap;
  }

  // --------------------------------------------------------------------------------
  // ----- Misc Methods
  // --------------------------------------------------------------------------------

  public String getMetaData(String key) {
    return MMetaData.getMetaData(menuMetaDataMap, key);
  }

  public void setMetaData(String key, String val) {
    MMetaData.setMetaData( //
        menuMetaDataMap, key, val, this, MMenuMetaData.class);
  }
}
