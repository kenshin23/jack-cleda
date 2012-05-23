/*
 * Created on 21/08/2006
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.workflow.prop.MWrkTransLogProp;
import com.minotauro.workflow.prop.MWrkTransProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_wrk_trans_set")
@Proxy(lazy = false)
public class MWrkTransSet extends MBase {

  public static final int STATUS_DISABLED = 0;
  public static final int STATUS_ENABLED = 1;

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private Calendar begDate;

  private Calendar/**/agentDate;
  private String/*  */agentTask;
  private String/*  */agentPrev;

  private int status;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MActor actorRef;

  private MNetTransSet netTransSetRef;

  private MWorkflow workflowRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MWrkTrans> wrkTransList = new ArrayList<MWrkTrans>();

  private List<MWrkTransLog> wrkTransLogList = new ArrayList<MWrkTransLog>();

  // ----------------------------------------

  public MWrkTransSet() {
    // Empty
  }

  // ----------------------------------------
  // ----- Props Methods
  // ----------------------------------------

  public Calendar getBegDate() {
    return begDate;
  }

  public void setBegDate(Calendar begDate) {
    this.begDate = begDate;
  }

  // ----------------------------------------

  public Calendar getAgentDate() {
    return agentDate;
  }

  public void setAgentDate(Calendar agentDate) {
    this.agentDate = agentDate;
  }

  // ----------------------------------------

  public String getAgentTask() {
    return agentTask;
  }

  public void setAgentTask(String agentTask) {
    this.agentTask = agentTask;
  }

  // ----------------------------------------

  public String getAgentPrev() {
    return agentPrev;
  }

  public void setAgentPrev(String agentPrev) {
    this.agentPrev = agentPrev;
  }

  // ----------------------------------------

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MActor getActorRef() {
    return actorRef;
  }

  public void setActorRef(MActor actorRef) {
    this.actorRef = actorRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetTransSet getNetTransSetRef() {
    return netTransSetRef;
  }

  public void setNetTransSetRef(MNetTransSet netTransSetRef) {
    this.netTransSetRef = netTransSetRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MWorkflow getWorkflowRef() {
    return workflowRef;
  }

  public void setWorkflowRef(MWorkflow workflowRef) {
    this.workflowRef = workflowRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MWrkTransLogProp.WRK_TRANS_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MWrkTransLog> getWrkTransLogList() {
    return wrkTransLogList;
  }

  public void setWrkTransLogList(List<MWrkTransLog> wrkTransLogList) {
    this.wrkTransLogList = wrkTransLogList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MWrkTransProp.WRK_TRANS_SET_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.FALSE)
  @Cascade({CascadeType.ALL})
  public List<MWrkTrans> getWrkTransList() {
    return wrkTransList;
  }

  public void setWrkTransList(List<MWrkTrans> wrkTransList) {
    this.wrkTransList = wrkTransList;
  }
}
