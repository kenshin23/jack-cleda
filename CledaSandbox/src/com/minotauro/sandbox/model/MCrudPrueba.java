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
 * @author Karla Moreno
 */

@Entity
@Table(name = "t_tst_crud_post")
@Proxy(lazy = false)
public class MCrudPrueba extends MBase{

	
	  private String integer;
	  private String email;
	  private String campo1;
	  private String campo2;
	  private String campo3;
	  private String campo4;
	  private String campo5;
	  private String campo6;
	  

	  public MCrudPrueba() {
	    // Empty
	  }

	// --------------------------------------------------------------------------------
	public String getInteger() {
		return integer;
	}

	
	public void setInteger(String integer) {
		this.integer = integer;
	}

	// --------------------------------------------------------------------------------
	public String getEmail() {
		return email;
	}

	
	public void setEmail(String email) {
		this.email = email;
	}

	// --------------------------------------------------------------------------------
	public String getCampo1() {
		return campo1;
	}


	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}

	// --------------------------------------------------------------------------------
	public String getCampo2() {
		return campo2;
	}


	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}

	// --------------------------------------------------------------------------------
	public String getCampo3() {
		return campo3;
	}


	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}

	// --------------------------------------------------------------------------------
	public String getCampo4() {
		return campo4;
	}


	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}

	// --------------------------------------------------------------------------------
	public String getCampo5() {
		return campo5;
	}


	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}

	// --------------------------------------------------------------------------------
	public String getCampo6() {
		return campo6;
	}


	public void setCampo6(String campo6) {
		this.campo6 = campo6;
	}
	 
	 
}






  
