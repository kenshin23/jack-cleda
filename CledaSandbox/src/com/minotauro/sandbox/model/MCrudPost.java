package com.minotauro.sandbox.model;


//import java.util.ArrayList;
//import java.util.List;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.OrderBy;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Karla "azucar" Moreno
 */

@Entity
@Table(name = "t_tst_crud_post")
@Proxy(lazy = false)
public class MCrudPost extends MBase{

	
	  private String name;
	  private String desc;
	  private String body;

	  // --------------------------------------------------------------------------------

	  private List<MMultJointMPostA> multJointMPostAList = new ArrayList<MMultJointMPostA>();
	  
	  private List<MSingJointPostB> singJointPostBList = new ArrayList<MSingJointPostB>();
	  
	  private List<MInnerPost> InnerPostCList = new ArrayList<MInnerPost>();


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

	  @OneToMany(mappedBy = _PropMMultJointMPostA.CRUD_POST_REF, orphanRemoval = true)
	  @LazyCollection(LazyCollectionOption.TRUE)
	  @Cascade({CascadeType.ALL})
	  public List<MMultJointMPostA> getMultJointMPostAList() {
		  return multJointMPostAList;
	  }

	  public void setMultJointMPostAList(List<MMultJointMPostA> multJointMPostAList) {
		  this.multJointMPostAList = multJointMPostAList;
	  }
	  
	  @OneToMany(mappedBy = _PropMInnerPost.CRUD_POST_REF, orphanRemoval = true)
	  @LazyCollection(LazyCollectionOption.TRUE)
	  @Cascade({CascadeType.ALL})
	  @OrderBy(clause = _PropMInnerPost.NAME)
	  public List<MInnerPost> getInnerPostCList() {
	    return InnerPostCList;
	  }

	  public void setInnerPostCList(List<MInnerPost> InnerPostCList) {
	    this.InnerPostCList = InnerPostCList;
	  }
	  
	  @OneToMany(mappedBy = _PropMSingJointPostB.CRUD_POST_REF, orphanRemoval = true)
	  @LazyCollection(LazyCollectionOption.TRUE)
	  @Cascade({CascadeType.ALL})
	  public List<MSingJointPostB> getSingJointPostBList() {
		  return singJointPostBList;
	  }

	  public void setSingJointPostBList(List<MSingJointPostB> singJointPostBList){
		  this.singJointPostBList = singJointPostBList;
	  }


}






  
