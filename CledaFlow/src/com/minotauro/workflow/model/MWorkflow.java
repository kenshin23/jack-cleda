/*
 * Created on 25/12/2005
 */
package com.minotauro.workflow.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Proxy;

import com.minotauro.base.model.MBase;
import com.minotauro.user.model.MUser;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.prop.MActorProp;
import com.minotauro.workflow.prop.MWorkflowProp;
import com.minotauro.workflow.prop.MWrkPlaceProp;
import com.minotauro.workflow.prop.MWrkTransSetProp;

/**
 * @author Demián Gutierrez
 */
@Entity
@Table(name = "t_wrk_workflow")
@Proxy(lazy = false)
public class MWorkflow extends MBase {

  // ----------------------------------------
  // ----- Optimistic Locking
  // ----------------------------------------

  protected int version;

  // ----------------------------------------
  // ----- Props
  // ----------------------------------------

  private Calendar begDate;

  private Calendar endDate;

  // ----------------------------------------
  // ----- 1 Relation Ships
  // ----------------------------------------

  private MNetPetri netPetriRef;

  private MDocument documentRef;

  private MUser userRef;

  private MNetStateGrp netStateGrpRef;

  private MNetStateSet netStateSetRef;

  // ----------------------------------------
  // ----- n Relation Ships
  // ----------------------------------------

  private List<MActor> actorList = //
  new ArrayList<MActor>();

  private List<MWrkPlace> wrkPlaceList = //
  new ArrayList<MWrkPlace>();

  private List<MWrkTransSet> wrkTransSetList = //
  new ArrayList<MWrkTransSet>();

  // ----------------------------------------

  public MWorkflow() {
    // Empty
  }

  // ----------------------------------------
  // ----- Optimistic Locking Methods
  // ----------------------------------------

  @Version
  @Column(name = "optlock")
  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
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

  public Calendar getEndDate() {
    return endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  // ----------------------------------------
  // ----- 1 Relation Ships Methods
  // ----------------------------------------

  @ManyToOne
  public MNetPetri getNetPetriRef() {
    return netPetriRef;
  }

  public void setNetPetriRef(MNetPetri netPetriRef) {
    this.netPetriRef = netPetriRef;
  }

  // ----------------------------------------

  @OneToOne
  @JoinColumn(name = MWorkflowProp.DOCUMENT_REF)
  @Cascade(CascadeType.ALL)
  public MDocument getDocumentRef() {
    return documentRef;
  }

  public void setDocumentRef(MDocument documentRef) {
    this.documentRef = documentRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MUser getUserRef() {
    return userRef;
  }

  public void setUserRef(MUser userRef) {
    this.userRef = userRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetStateGrp getNetStateGrpRef() {
    return netStateGrpRef;
  }

  public void setNetStateGrpRef(MNetStateGrp netStateGrpRef) {
    this.netStateGrpRef = netStateGrpRef;
  }

  // ----------------------------------------

  @ManyToOne
  public MNetStateSet getNetStateSetRef() {
    return netStateSetRef;
  }

  public void setNetStateSetRef(MNetStateSet netStateSetRef) {
    this.netStateSetRef = netStateSetRef;
  }

  // ----------------------------------------
  // ----- n Relation Ships Methods
  // ----------------------------------------

  @OneToMany(mappedBy = MActorProp.WORKFLOW_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MActor> getActorList() {
    return actorList;
  }

  public void setActorList(List<MActor> actorList) {
    this.actorList = actorList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MWrkPlaceProp.WORKFLOW_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MWrkPlace> getWrkPlaceList() {
    return wrkPlaceList;
  }

  public void setWrkPlaceList(List<MWrkPlace> wrkPlaceList) {
    this.wrkPlaceList = wrkPlaceList;
  }

  // ----------------------------------------

  @OneToMany(mappedBy = MWrkTransSetProp.WORKFLOW_REF, orphanRemoval = true)
  @LazyCollection(LazyCollectionOption.TRUE)
  @Cascade({CascadeType.ALL})
  public List<MWrkTransSet> getWrkTransSetList() {
    return wrkTransSetList;
  }

  public void setWrkTransSetList(List<MWrkTransSet> wrkTransSetList) {
    this.wrkTransSetList = wrkTransSetList;
  }

  // ----------------------------------------
  // ----- Misc Methods
  // ----------------------------------------

  public MNetStateSet calculatePlaceSet() throws WorkflowException {
    List<MNetStateSet> placeSetIncList = new ArrayList<MNetStateSet>();
    List<MNetStateSet> placeSetExcList = new ArrayList<MNetStateSet>();

    // ----------------------------------------
    // Go for all work places
    // ----------------------------------------

    for (MWrkPlace wrkPlace : wrkPlaceList) {
      updatePlaceSetLists(wrkPlace, placeSetIncList, placeSetExcList);
    }

    // ----------------------------------------
    // We should have only one place set
    // ----------------------------------------

    if (placeSetIncList.size() != 1) {
      throw new WorkflowException( //
          "placeSetIncList.size() != 1: " + createPlaceStateString());
    }

    return placeSetIncList.get(0);
  }

  // ----------------------------------------

  protected String createPlaceStateString() {
    StringBuffer ret = new StringBuffer();

    ret.append("{ ");

    Iterator<MWrkPlace> itt = wrkPlaceList.iterator();

    while (itt.hasNext()) {
      MWrkPlace wrkPlace = (MWrkPlace) itt.next();

      ret.append(wrkPlace.getNetPlaceRef().getName());
      ret.append("=");
      ret.append(wrkPlace.getTokens());

      if (itt.hasNext()) {
        ret.append(", ");
      }
    }

    ret.append(" }");

    return ret.toString();
  }

  // ----------------------------------------

  protected void updatePlaceSetLists( //
      MWrkPlace wrkPlace, List<MNetStateSet> placeSetIncList, List<MNetStateSet> placeSetExcList) {

    for (MPlaceState placeState : wrkPlace.getNetPlaceRef().getPlaceStateList()) {
      if (wrkPlace.getTokens() == placeState.getTokens()) {

        // ----------------------------------------
        // Match and not excluded before
        // ----------------------------------------

        if (!placeSetExcList.contains(placeState.getNetStateSetRef())
            && !placeSetIncList.contains(placeState.getNetStateSetRef())) {
          placeSetIncList.add(placeState.getNetStateSetRef());
        }
      } else {

        // ----------------------------------------
        // Don't match
        // ----------------------------------------

        placeSetIncList.remove(placeState.getNetStateSetRef());
        placeSetExcList.add(placeState.getNetStateSetRef());
      }
    }
  }

  // ----------------------------------------

  public MActor createActorFromCurrNetStateGrp() {
    MActor ret;

    if (netStateGrpRef.isTerminal()) {
      ret = new MActorHist();
    } else {
      ret = new MActorCurr();
    }

    return ret;
  }

  // ----------------------------------------

  public void updateActorFromCurrNetStateGrp() {
    MActor[] actorArray = actorList.toArray(new MActor[0]);
    actorList.clear();

    for (MActor prev : actorArray) {
      MActor curr;

      if (netStateGrpRef.isTerminal()) {
        if (prev instanceof MActorHist) {
          curr = prev;
        } else {
          curr = createActorFromCurrNetStateGrp();
          copyActorAndReplace(curr, prev);
        }
      } else {
        if (prev instanceof MActorCurr) {
          curr = prev;
        } else {
          curr = createActorFromCurrNetStateGrp();
          copyActorAndReplace(curr, prev);
        }
      }

      actorList.add(curr);
    }
  }

  // ----------------------------------------

  protected void copyActorAndReplace(MActor curr, MActor prev) {
    curr.setUserRef(/*           */prev.getUserRef());
    curr.setNetTransSetRoleRef(/**/prev.getNetTransSetRoleRef());
    curr.setWorkflowRef(/*       */prev.getWorkflowRef());

    prev.setUserRef(/*           */null);
    prev.setNetTransSetRoleRef(/**/null);
    prev.setWorkflowRef(/*       */null);

    MWrkTransSet[] wrkTransSetArray = prev.getWrkTransSetList().toArray( //
        new MWrkTransSet[0]);
    prev.getWrkTransSetList().clear();

    for (MWrkTransSet wrkTransSet : wrkTransSetArray) {
      curr.getWrkTransSetList().add(wrkTransSet);
      wrkTransSet.setActorRef(curr);
    }
  }

  // ----------------------------------------

  public List<MWrkTransSet> getEnabledWrkTransSetForUser(MUser user) {
    List<MWrkTransSet> ret = new ArrayList<MWrkTransSet>();

    for (MActor actor : actorList) {
      MUser curUser = actor.getUserRef();

      if (curUser == null || //
          !curUser.getUser().equals(user.getUser())) {
        continue;
      }

      for (MWrkTransSet wrkTransSet : actor.getWrkTransSetList()) {
        if (wrkTransSet.getStatus() == MWrkTransSet.STATUS_ENABLED) {
          ret.add(wrkTransSet);
        }
      }
    }

    return ret;
  }
}
