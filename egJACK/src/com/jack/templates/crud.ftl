[#ftl]	
// ----------------------------------------
// Generated code, do not edit
// ----------------------------------------

package com.minotauro.sandbox.model;


//import java.util.ArrayList;
//import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author E.G JACK™
 */

@Entity
@Table(name = "t_tst_crud_${name}")
@Proxy(lazy = false)
public class ${modelName}} extends MBase{


[#list attributes.att as currentAtt]
  private ${currentAtt.type?cap_first} ${currentAtt.name};
[/#list]

	  // --------------------------------------------------------------------------------

	  //private List<MMultJointAB> multJointABList = new ArrayList<MMultJointAB>();

	  //private List<MSingJointAB> singJointABList = new ArrayList<MSingJointAB>();

	  // --------------------------------------------------------------------------------

	  public MCrudPost() {
	    // Empty
	  }

	  // --------------------------------------------------------------------------------

	  public String getName() {
	    return name;
	  }

	  public void setName(String name) {
	    this.name = name;
	  }

	  // --------------------------------------------------------------------------------

	  @Column(name = "odesc")
	  public String getDesc() {
	    return desc;
	  }

	  public void setDesc(String desc) {
	    this.desc = desc;
	  }

	  // --------------------------------------------------------------------------------

	  public String getBody() {
		  return body;
	  }

	  public void setBody(String body) {
		  this.body = body;
	  }

	  //@OneToMany(mappedBy = _PropMMultJointAB.CRUD_BREF, orphanRemoval = true)
	  //@LazyCollection(LazyCollectionOption.TRUE)
	  //@Cascade({CascadeType.ALL})
	  /*public List<MMultJointAB> getMultJointABList() {
	    return multJointABList;
	  }

	  public void setMultJointABList(List<MMultJointAB> multJointABList) {
	    this.multJointABList = multJointABList;
	  }
*/
	  // --------------------------------------------------------------------------------

	/*  @OneToMany(mappedBy = _PropMSingJointAB.CRUD_BREF, orphanRemoval = true)
	  @LazyCollection(LazyCollectionOption.TRUE)
	  @Cascade({CascadeType.ALL})
	  public List<MSingJointAB> getSingJointABList() {
	    return singJointABList;
	  }

	  public void setSingJointABList(List<MSingJointAB> singJointABList) {
	    this.singJointABList = singJointABList;
	  }
*/
}