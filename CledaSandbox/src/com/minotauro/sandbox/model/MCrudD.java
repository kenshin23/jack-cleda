/*
 * Created on 27/08/2011
 */
package com.minotauro.sandbox.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_tst_crud_d")
@Proxy(lazy = false)
public class MCrudD extends MBase {

  private String name;
  private String desc;

  private String valB1;
  private String valB2;
  private String valB3;
  private String valB4;
  private String valB5;

  private String valC1;
  private String valC2;
  private String valC3;
  private String valC4;
  private String valC5;

  private String valD1;
  private String valD2;
  private String valD3;
  private String valD4;
  private String valD5;

  private String valE1;
  private String valE2;
  private String valE3;
  private String valE4;
  private String valE5;

  // --------------------------------------------------------------------------------

  public MCrudD() {
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

  public String getValB1() {
    return valB1;
  }

  public void setValB1(String valB1) {
    this.valB1 = valB1;
  }

  // --------------------------------------------------------------------------------

  public String getValB2() {
    return valB2;
  }

  public void setValB2(String valB2) {
    this.valB2 = valB2;
  }

  // --------------------------------------------------------------------------------

  public String getValB3() {
    return valB3;
  }

  public void setValB3(String valB3) {
    this.valB3 = valB3;
  }

  // --------------------------------------------------------------------------------

  public String getValB4() {
    return valB4;
  }

  public void setValB4(String valB4) {
    this.valB4 = valB4;
  }

  // --------------------------------------------------------------------------------

  public String getValB5() {
    return valB5;
  }

  public void setValB5(String valB5) {
    this.valB5 = valB5;
  }

  // --------------------------------------------------------------------------------

  public String getValC1() {
    return valC1;
  }

  public void setValC1(String valC1) {
    this.valC1 = valC1;
  }

  // --------------------------------------------------------------------------------

  public String getValC2() {
    return valC2;
  }

  public void setValC2(String valC2) {
    this.valC2 = valC2;
  }

  // --------------------------------------------------------------------------------

  public String getValC3() {
    return valC3;
  }

  public void setValC3(String valC3) {
    this.valC3 = valC3;
  }

  // --------------------------------------------------------------------------------

  public String getValC4() {
    return valC4;
  }

  public void setValC4(String valC4) {
    this.valC4 = valC4;
  }

  // --------------------------------------------------------------------------------

  public String getValC5() {
    return valC5;
  }

  public void setValC5(String valC5) {
    this.valC5 = valC5;
  }

  // --------------------------------------------------------------------------------

  public String getValD1() {
    return valD1;
  }

  public void setValD1(String valD1) {
    this.valD1 = valD1;
  }

  // --------------------------------------------------------------------------------

  public String getValD2() {
    return valD2;
  }

  public void setValD2(String valD2) {
    this.valD2 = valD2;
  }

  // --------------------------------------------------------------------------------

  public String getValD3() {
    return valD3;
  }

  public void setValD3(String valD3) {
    this.valD3 = valD3;
  }

  // --------------------------------------------------------------------------------

  public String getValD4() {
    return valD4;
  }

  public void setValD4(String valD4) {
    this.valD4 = valD4;
  }

  // --------------------------------------------------------------------------------

  public String getValD5() {
    return valD5;
  }

  public void setValD5(String valD5) {
    this.valD5 = valD5;
  }

  // --------------------------------------------------------------------------------

  public String getValE1() {
    return valE1;
  }

  public void setValE1(String valE1) {
    this.valE1 = valE1;
  }

  // --------------------------------------------------------------------------------

  public String getValE2() {
    return valE2;
  }

  public void setValE2(String valE2) {
    this.valE2 = valE2;
  }

  // --------------------------------------------------------------------------------

  public String getValE3() {
    return valE3;
  }

  public void setValE3(String valE3) {
    this.valE3 = valE3;
  }

  // --------------------------------------------------------------------------------

  public String getValE4() {
    return valE4;
  }

  public void setValE4(String valE4) {
    this.valE4 = valE4;
  }

  // --------------------------------------------------------------------------------

  public String getValE5() {
    return valE5;
  }

  public void setValE5(String valE5) {
    this.valE5 = valE5;
  }
}
