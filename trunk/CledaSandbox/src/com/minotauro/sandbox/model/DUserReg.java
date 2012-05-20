/*
 * Created on 09/02/2007
 */
package com.minotauro.sandbox.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.workflow.model.MDocument;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_doc_user_reg")
@Proxy(lazy = false)
public class DUserReg extends MDocument {

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  private String user;
  private String pass;

  // --------------------------------------------------------------------------------
  // ----- n Relation Ships
  // --------------------------------------------------------------------------------

  private List<DUserRegProf> userRegProfList =
      new ArrayList<DUserRegProf>();

  // --------------------------------------------------------------------------------

  public DUserReg() {
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
  // ----- n Relation Ships Methods
  // --------------------------------------------------------------------------------

  @OneToMany(mappedBy = _PropDUserRegProf.USER_REG_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<DUserRegProf> getUserRegProfList() {
    return userRegProfList;
  }

  public void setUserRegProfList(List<DUserRegProf> userRegProfList) {
    this.userRegProfList = userRegProfList;
  }
}
