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
@Table(name = "t_tst_crud_post")
@Proxy(lazy = false)
public class MCrudPrueba extends MBase{

	
	  private String numero;
	  private String email;
	  private String notblankfield;
	  private String duplicatedfield;
	  private String idnumber;
	  private String conditional;
	  private String regex;
	  private String truefield;
	  private String notempty;
	  private String rango;
	  
	  public MCrudPrueba() {
		    // Empty
		  }
	// --------------------------------------------------------------------------------
	  public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
	}

	// --------------------------------------------------------------------------------
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	// --------------------------------------------------------------------------------
	public String getNotblankfield() {
		return notblankfield;
	}


	public void setNotblankfield(String notblankfield) {
		this.notblankfield = notblankfield;
	}

	// --------------------------------------------------------------------------------
	public String getDuplicatedfield() {
		return duplicatedfield;
	}


	public void setDuplicatedfield(String duplicatedfield) {
		this.duplicatedfield = duplicatedfield;
	}

    // --------------------------------------------------------------------------------
	public String getIdnumber() {
		return idnumber;
	}


	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}

	// --------------------------------------------------------------------------------
	public String getConditional() {
		return conditional;
	}


	public void setConditional(String conditional) {
		this.conditional = conditional;
	}

   // --------------------------------------------------------------------------------
	public String getRegex() {
		return regex;
	}


	public void setRegex(String regex) {
		this.regex = regex;
	}

	// --------------------------------------------------------------------------------
	public String getTruefield() {
		return truefield;
	}


	public void setTruefield(String truefield) {
		this.truefield = truefield;
	}

	// --------------------------------------------------------------------------------
	public String getNotempty() {
		return notempty;
	}


	public void setNotempty(String notempty) {
		this.notempty = notempty;
	}

	// --------------------------------------------------------------------------------
	public String getRango() {
		return rango;
	}


	public void setRango(String rango) {
		this.rango = rango;
	}

 
}






  
