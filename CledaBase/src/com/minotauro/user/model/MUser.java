/*
 * Created on 25/12/2005
 */
package com.minotauro.user.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_aut_user")
@Proxy(lazy = false)
public class MUser extends MBase {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String user;
  private String pass;

  private String language;

  private String country;
  private String variant;

  private Locale locale;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private List<MUserProf> userProfList = new ArrayList<MUserProf>();

  // --------------------------------------------------------------------------------
  // ----- Misc
  // --------------------------------------------------------------------------------

  private transient List<MPriv> privList = new ArrayList<MPriv>();
  private transient List<MRole> roleList = new ArrayList<MRole>();

  // --------------------------------------------------------------------------------

  public MUser() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  // --------------------------------------------------------------------------------

  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  // --------------------------------------------------------------------------------

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  // --------------------------------------------------------------------------------

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  // --------------------------------------------------------------------------------

  public String getVariant() {
    return variant;
  }

  public void setVariant(String variant) {
    this.variant = variant;
  }

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships Methods
  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropMUserProf.USER_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MUserProf> getUserProfList() {
    return userProfList;
  }

  public void setUserProfList(List<MUserProf> userRoleList) {
    this.userProfList = userRoleList;
  }

  // --------------------------------------------------------------------------------
  // ----- Priv
  // --------------------------------------------------------------------------------

  @Transient
  public List<MPriv> getPrivList() {
    return privList;
  }

  public void setPrivList(List<MPriv> privList) {
    this.privList = privList;
  }

  // --------------------------------------------------------------------------------

  public boolean hasPriv(String name) {
    for (MPriv priv : privList) {
      if (name.equals(priv.getName())) {
        return true;
      }
    }

    return false;
  }

  // --------------------------------------------------------------------------------

  public void loadPrivList() {
    Set<MPriv> privSet = new HashSet<MPriv>();

    for (MUserProf userProf : userProfList) {
      MProf prof = userProf.getProfRef();

      for (MProfPriv profPriv : prof.getProfPrivList()) {
        privSet.add(profPriv.getPrivRef());
      }
    }

    privList.clear();
    privList.addAll(privSet);
  }

  // --------------------------------------------------------------------------------
  // ----- Role
  // --------------------------------------------------------------------------------

  @Transient
  public List<MRole> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<MRole> roleList) {
    this.roleList = roleList;
  }

  // --------------------------------------------------------------------------------

  public boolean hasRole(String name) {
    for (MRole role : roleList) {
      if (name.equals(role.getName())) {
        return true;
      }
    }

    return false;
  }

  // --------------------------------------------------------------------------------

  public void loadRoleList() {
    Set<MRole> roleSet = new HashSet<MRole>();

    for (MUserProf userProf : userProfList) {
      MProf prof = userProf.getProfRef();

      for (MProfRole profRole : prof.getProfRoleList()) {
        roleSet.add(profRole.getRoleRef());
      }
    }

    roleList.clear();
    roleList.addAll(roleSet);
  }

  // --------------------------------------------------------------------------------
  // ----- Misc
  // --------------------------------------------------------------------------------

  @Transient
  public Locale getLocale() {
    if (language != null && locale == null) {
      locale = new Locale(language, country, variant);
    }

    return locale;
  }
}
