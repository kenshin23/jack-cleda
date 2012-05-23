/*
 * Created on 02/01/2007
 */
package com.minotauro.workflow.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.minotauro.user.model.MUser;
import com.minotauro.workflow.exception.WorkflowException;
import com.minotauro.workflow.i18n.EEngPetriI18N;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public class EEngPetri {

  protected Map<MNetPlace, EEngPlace> engByModelPlace = //
  new HashMap<MNetPlace, EEngPlace>();

  protected Map<MNetTrans, EEngTrans> engByModelTrans = //
  new HashMap<MNetTrans, EEngTrans>();

  // --------------------------------------------------------------------------------
  // ----- Props
  // --------------------------------------------------------------------------------

  protected EEngPetriCreate engPetriCreate = //
  new EEngPetriCreate(this);

  protected EEngPetriSelect engPetriSelect = //
  new EEngPetriSelect(this);

  protected EEngPetriState engPetriState = //
  new EEngPetriState(this);

  protected EEngPetriTrans engPetriTrans = //
  new EEngPetriTrans(this);

  protected EEngPetriViews engPetriViews = //
  new EEngPetriViews(this);

  protected EEngPetriActor engPetriActor = //
  new EEngPetriActor(this);

  // --------------------------------------------------------------------------------

  protected MNetTrans firingNetTrans;

  // --------------------------------------------------------------------------------

  protected List<EEngPlace> engPlaceList = //
  new ArrayList<EEngPlace>();

  protected List<EEngTrans> engTransList = //
  new ArrayList<EEngTrans>();

  // --------------------------------------------------------------------------------

  protected List<MWrkTransSet> addToSchedulerList = //
  new ArrayList<MWrkTransSet>();

  protected List<MWrkTransSet> delToSchedulerList = //
  new ArrayList<MWrkTransSet>();

  // --------------------------------------------------------------------------------

  protected EngineAgentDelegator engineAgentDelegator;
  protected EngineActorDelegator engineActorDelegator;

  // --------------------------------------------------------------------------------

  protected MNetPetri netPetri;

  protected MWorkflow workflow;

  // --------------------------------------------------------------------------------

  protected MUser user;

  // --------------------------------------------------------------------------------

  public EEngPetri() {
    // Empty
  }

  // --------------------------------------------------------------------------------
  // ----- Props Methods
  // --------------------------------------------------------------------------------

  public EEngPetriCreate getEngPetriCreate() {
    return engPetriCreate;
  }

  public EEngPetriSelect getEngPetriSelect() {
    return engPetriSelect;
  }

  // --------------------------------------------------------------------------------

  public EEngPetriState getEngPetriState() {
    return engPetriState;
  }

  public EEngPetriTrans getEngPetriTrans() {
    return engPetriTrans;
  }

  public EEngPetriViews getEngPetriViews() {
    return engPetriViews;
  }

  public EEngPetriActor getEngPetriActor() {
    return engPetriActor;
  }

  // --------------------------------------------------------------------------------

  public MNetTrans getFiringNetTrans() {
    return firingNetTrans;
  }

  public void setFiringNetTrans(MNetTrans firingNetTrans) {
    this.firingNetTrans = firingNetTrans;
  }

  // --------------------------------------------------------------------------------

  public List<EEngPlace> getEngPlaceList() {
    return engPlaceList;
  }

  public List<EEngTrans> getEngTransList() {
    return engTransList;
  }

  // --------------------------------------------------------------------------------

  public List<MWrkTransSet> getAddToSchedulerList() {
    return addToSchedulerList;
  }

  public List<MWrkTransSet> getDelToSchedulerList() {
    return delToSchedulerList;
  }

  // --------------------------------------------------------------------------------

  public EngineAgentDelegator getEngineAgentDelegator() {
    return engineAgentDelegator;
  }

  public void setEngineAgentDelegator(EngineAgentDelegator engineAgentDelegator) {
    this.engineAgentDelegator = engineAgentDelegator;
  }

  // --------------------------------------------------------------------------------

  public EngineActorDelegator getEngineActorDelegator() {
    return engineActorDelegator;
  }

  public void setEngineActorDelegator(EngineActorDelegator engineActorDelegator) {
    this.engineActorDelegator = engineActorDelegator;
  }

  // --------------------------------------------------------------------------------

  public MNetPetri getNetPetri() {
    return netPetri;
  }

  public void setNetPetri(MNetPetri netPetri) {
    this.netPetri = netPetri;
  }

  // --------------------------------------------------------------------------------

  public MWorkflow getWorkflow() {
    return workflow;
  }

  public void setWorkflow(MWorkflow workflow) {
    this.workflow = workflow;
  }

  // --------------------------------------------------------------------------------

  public MUser getUser() {
    return user;
  }

  public void setUser(MUser user) {
    this.user = user;
  }

  // --------------------------------------------------------------------------------

  public EEngPlace getEngByModelPlace(MNetPlace netPlace) //
      throws WorkflowException {

    EEngPlace ret = engByModelPlace.get(netPlace);

    if (ret == null) {
      throw new WorkflowException(EEngPetriI18N. //
          engPlaceNotFoundForNetPlace(netPlace.getName()));
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public void putEngByModelPlace(MNetPlace netPlace, EEngPlace engPlace) {
    engByModelPlace.put(netPlace, engPlace);
  }

  // --------------------------------------------------------------------------------

  public EEngTrans getEngByModelTrans(MNetTrans netTrans) //
      throws WorkflowException {

    EEngTrans ret = engByModelTrans.get(netTrans);

    if (ret == null) {
      throw new WorkflowException(EEngPetriI18N. //
          engTransNotFoundForNetTrans(netTrans.getName()));
    }

    return ret;
  }

  // --------------------------------------------------------------------------------

  public void putEngByModelTrans(MNetTrans netTrans, EEngTrans engTrans) {
    engByModelTrans.put(netTrans, engTrans);
  }

  // --------------------------------------------------------------------------------
  // ----- toString Methods
  // --------------------------------------------------------------------------------

  protected String engListToString(List<?> engList) {
    StringBuffer ret = new StringBuffer();

    for (Object engObject : engList) {
      ret.append(engObject.toString());
      ret.append("\n");
    }

    return ret.toString();
  }

  // --------------------------------------------------------------------------------

  public String toString() {
    StringBuffer ret = new StringBuffer();

    ret.append("engPlaceList:\n");
    ret.append(engListToString(engPlaceList));
    ret.append("engTransList:\n");
    ret.append(engListToString(engTransList));
    ret.append("\n");

    return ret.toString();
  }
}
