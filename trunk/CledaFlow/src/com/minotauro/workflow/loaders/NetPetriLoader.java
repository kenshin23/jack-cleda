/*
 * Created on 11/01/2007
 */
package com.minotauro.workflow.loaders;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.base.model.MBase;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.user.model.MRole;
import com.minotauro.user.model._PropMRole;
import com.minotauro.workflow.model.MDocSection;
import com.minotauro.workflow.model.MDocSectionEditState;
import com.minotauro.workflow.model.MDocSectionValidateState;
import com.minotauro.workflow.model.MDocSectionViewState;
import com.minotauro.workflow.model.MDocType;
import com.minotauro.workflow.model.MNetEdge;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MNetStateGrp;
import com.minotauro.workflow.model.MNetStateSet;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MNetTransSetRole;
import com.minotauro.workflow.model.MPlaceState;
import com.minotauro.workflow.model.MWorklist;
import com.minotauro.workflow.prop.MDocTypeProp;
import com.minotauro.workflow.prop.MWorklistProp;
import com.minotauro.workflow.xml.netpetri.DocSectionState;
import com.minotauro.workflow.xml.netpetri.MetaData;
import com.minotauro.workflow.xml.netpetri.NetPetriDef;
import com.minotauro.workflow.xml.netpetri.ObjectFactory;
import com.minotauro.workflow.xml.netpetri.Place;
import com.minotauro.workflow.xml.netpetri.PlaceList;
import com.minotauro.workflow.xml.netpetri.PosPlace;
import com.minotauro.workflow.xml.netpetri.PrePlace;
import com.minotauro.workflow.xml.netpetri.Role;
import com.minotauro.workflow.xml.netpetri.RolesList;
import com.minotauro.workflow.xml.netpetri.StateGrp;
import com.minotauro.workflow.xml.netpetri.StateSet;
import com.minotauro.workflow.xml.netpetri.Trans;
import com.minotauro.workflow.xml.netpetri.TransSet;
import com.minotauro.workflow.xml.netpetri.Work;

/**
 * @author Demián Gutierrez
 */
public class NetPetriLoader {

  protected MNetTransSet initNetTransSet;

  protected MNetPetri netPetri;

  protected MDocType docType;

  protected Session session;

  // --------------------------------------------------------------------------------

  protected Map<String, MNetTransSetRole> netTransSetRoleByRoleNameMap = //
  new LinkedHashMap<String, MNetTransSetRole>();

  protected Map<String, MNetPlace> netPlaceByNameMap = //
  new HashMap<String, MNetPlace>();

  // --------------------------------------------------------------------------------

  public NetPetriLoader(Session session) {
    this.session = session;
  }

  // --------------------------------------------------------------------------------
  // Misc
  // --------------------------------------------------------------------------------

  protected void loadMetaData(MMetaDataParent metaDataParent, List<MetaData> metaDataList) {
    for (MetaData metaData : metaDataList) {
      metaDataParent.setMetaData(metaData.getKey(), metaData.getVal());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadDocType(MNetPetri netPetri2, NetPetriDef xmlNetPetriDef) throws Exception {

    // ----------------------------------------
    // Try to load the MDocType
    // ----------------------------------------

    docType = MBase.loadByField(//
        session, MDocType.class, MDocTypeProp.NAME, xmlNetPetriDef.getDocType());

    if (docType == null) {
      throw new Exception( //
          _I18NLoader.notFound(MDocType.class, xmlNetPetriDef.getName()));
    }

    // ----------------------------------------
    // Create the relationship
    // ----------------------------------------

    docType.setNetPetriRef(netPetri);
    netPetri.setDocTypeRef(docType);
  }

  // --------------------------------------------------------------------------------
  // Doc State
  // --------------------------------------------------------------------------------

  protected void loadDocSectionState(MNetTransSet netTransSet, List<DocSectionState> docSectionStateList) //
      throws Exception {

    Map<String, MDocSectionEditState> docSectionEditStateByNameMap = //
    new HashMap<String, MDocSectionEditState>();

    // ----------------------------------------

    for (DocSectionState xmlDocSectionState : docSectionStateList) {
      MDocSection docSection = docType.getDocSectionByName(xmlDocSectionState.getName());

      // ----------------------------------------
      // Try to get the MDocSection
      // ----------------------------------------

      if (docSection == null) {
        throw new Exception( //
            _I18NLoader.notFound(MDocSection.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (docSectionEditStateByNameMap.get(xmlDocSectionState.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField( //
                MDocSectionEditState.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Create the MDocSectionEditState
      // ----------------------------------------

      MDocSectionEditState docSectionEditState = new MDocSectionEditState();
      docSectionEditState.setState( //
          MDocSectionEditState.intStateFromStr(xmlDocSectionState.getState()));

      docSectionEditState.setDocSectionRef(docSection);
      docSection.getDocSectionEditStateList().add(docSectionEditState);

      docSectionEditState.setNetTransSetRef(netTransSet);
      netTransSet.getDocSectionEditStateList().add(docSectionEditState);

      docSectionEditStateByNameMap.put(xmlDocSectionState.getState(), docSectionEditState);
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadDocSectionState(MNetStateGrp netStateGrp, List<DocSectionState> docSectionStateList) //
      throws Exception {

    Map<String, MDocSectionViewState> docSectionViewStateByNameMap = //
    new HashMap<String, MDocSectionViewState>();

    // ----------------------------------------

    for (DocSectionState xmlDocSectionState : docSectionStateList) {
      MDocSection docSection = docType.getDocSectionByName(xmlDocSectionState.getName());

      // ----------------------------------------
      // Try to get the MDocSection
      // ----------------------------------------

      if (docSection == null) {
        throw new Exception( //
            _I18NLoader.notFound(MDocSection.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (docSectionViewStateByNameMap.get(xmlDocSectionState.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField( //
                MDocSectionViewState.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Create the MDocSectionViewState
      // ----------------------------------------

      MDocSectionViewState docSectionViewState = new MDocSectionViewState();
      docSectionViewState.setState( //
          MDocSectionViewState.intStateFromStr(xmlDocSectionState.getState()));

      docSectionViewState.setDocSectionRef(docSection);
      docSection.getDocSectionViewStateList().add(docSectionViewState);

      docSectionViewState.setNetStateGrpRef(netStateGrp);
      netStateGrp.getDocSectionViewStateList().add(docSectionViewState);

      docSectionViewStateByNameMap.put(xmlDocSectionState.getState(), docSectionViewState);
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadDocSectionState(MNetTrans netTrans, List<DocSectionState> docSectionStateList) //
      throws Exception {

    Map<String, MDocSectionValidateState> docSectionValidateStateByNameMap = //
    new HashMap<String, MDocSectionValidateState>();

    // ----------------------------------------

    for (DocSectionState xmlDocSectionState : docSectionStateList) {
      MDocSection docSection = docType.getDocSectionByName(xmlDocSectionState.getName());

      // ----------------------------------------
      // Try to get the MDocSection
      // ----------------------------------------

      if (docSection == null) {
        throw new Exception( //
            _I18NLoader.notFound(MDocSection.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (docSectionValidateStateByNameMap.get(xmlDocSectionState.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField( //
                MDocSectionValidateState.class, xmlDocSectionState.getName()));
      }

      // ----------------------------------------
      // Create the MDocSectionViewState
      // ----------------------------------------

      MDocSectionValidateState docSectionValidateState = new MDocSectionValidateState();
      docSectionValidateState.setState( //
          MDocSectionValidateState.intStateFromStr(xmlDocSectionState.getState()));

      docSectionValidateState.setDocSectionRef(docSection);
      docSection.getDocSectionValidateStateList().add(docSectionValidateState);

      docSectionValidateState.setNetTransRef(netTrans);
      netTrans.getDocSectionValidateStateList().add(docSectionValidateState);

      docSectionValidateStateByNameMap.put(xmlDocSectionState.getState(), docSectionValidateState);
    }
  }

  // --------------------------------------------------------------------------------
  // roles-list
  // --------------------------------------------------------------------------------

  protected void loadRolesList(RolesList rolesList) throws Exception {
    for (Role xmlRole : rolesList.getRole()) {

      // ----------------------------------------
      // Try to load the role
      // ----------------------------------------

      MRole role = MBase.loadByField(//
          session, MRole.class, _PropMRole.NAME, xmlRole.getName());

      if (role == null) {
        throw new Exception( //
            _I18NLoader.notFound(MRole.class, xmlRole.getName()));
      }

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      MNetTransSetRole netTransSetRole = netTransSetRoleByRoleNameMap.get( //
          xmlRole.getName());

      if (netTransSetRole != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetPlace.class, xmlRole.getName()));
      }

      // ----------------------------------------
      // Create the MNetTransSetRole
      // ----------------------------------------

      netTransSetRole = new MNetTransSetRole();
      netTransSetRole.setDelegator(xmlRole.getDelegator());
      netTransSetRole.setPriority(netTransSetRoleByRoleNameMap.size());

      netTransSetRole.setRoleRef(role);

      netPetri.getNetTransSetRoleList().add(netTransSetRole);
      netTransSetRole.setNetPetriRef(netPetri);

      netTransSetRoleByRoleNameMap.put(role.getName(), netTransSetRole);
    }
  }

  // --------------------------------------------------------------------------------
  // place-list
  // --------------------------------------------------------------------------------

  protected void loadPlaceList(PlaceList placeList) throws Exception {
    for (Place xmlPlace : placeList.getPlace()) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netPlaceByNameMap.get(xmlPlace.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetPlace.class, xmlPlace.getName()));
      }

      // ----------------------------------------
      // Create the MNetPlace
      // ----------------------------------------

      MNetPlace netPlace = new MNetPlace();

      netPlace.setName(xmlPlace.getName());

      netPetri.getNetPlaceList().add(netPlace);
      netPlace.setNetPetriRef(netPetri);

      netPlaceByNameMap.put(netPlace.getName(), netPlace);

      // ----------------------------------------

      loadMetaData(netPlace, xmlPlace.getMetaData());
    }
  }

  // --------------------------------------------------------------------------------
  // trans-list
  // --------------------------------------------------------------------------------

  protected void loadTransSet(List<TransSet> transSetList) throws Exception {

    Map<String, MNetTransSet> netTransSetByNameMap = //
    new HashMap<String, MNetTransSet>();

    // ----------------------------------------

    for (TransSet xmlTransSet : transSetList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netTransSetByNameMap.get(xmlTransSet.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetTransSet.class, xmlTransSet.getName()));
      }

      // ----------------------------------------
      // Create the MNetTransSetRole
      // ----------------------------------------

      MNetTransSet netTransSet = new MNetTransSet();

      netTransSet.setName(xmlTransSet.getName());
      netTransSet.setInit(xmlTransSet.isInit());

      if (xmlTransSet.getDefaultDocSectionState() != null) {
        netTransSet.setDefaultDocSectionEditState(MDocSectionEditState.intStateFromStr( //
            xmlTransSet.getDefaultDocSectionState().getState()));
      }

      // ----------------------------------------
      // Check for duplicated init
      // ----------------------------------------

      if (netTransSet.isInit()) {
        if (initNetTransSet != null) {
          throw new Exception(_I18NLoader.duplicatedField( //
              "init", netTransSet.getName() + "/" + initNetTransSet.getName()));
        }

        initNetTransSet = netTransSet;
      }

      // ----------------------------------------

      if (xmlTransSet.getAuto() != null) {
        netTransSet.setAgentType(xmlTransSet.getAuto().getType());
        netTransSet.setAgentTime(xmlTransSet.getAuto().getTime());
      }

      netPetri.getNetTransSetList().add(netTransSet);
      netTransSet.setNetPetriRef(netPetri);

      netTransSetByNameMap.put(netTransSet.getName(), netTransSet);

      // ----------------------------------------
      // Children
      // ----------------------------------------

      loadDocSectionState(netTransSet, xmlTransSet.getDocSectionState());

      loadMetaData(netTransSet, xmlTransSet.getMetaData());

      loadTrans(netTransSet, xmlTransSet.getTrans());

      loadRole(netTransSet, xmlTransSet.getRole());
      loadWork(netTransSet, xmlTransSet.getWork());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadRole(MNetTransSet netTransSet, Role xmlRole) throws Exception {

    // ----------------------------------------
    // No role specified
    // ----------------------------------------

    if (xmlRole == null) {
      return;
    }

    // ----------------------------------------
    // Try to get the MNetTransSetRole
    // ----------------------------------------

    MNetTransSetRole netTransSetRole = netTransSetRoleByRoleNameMap.get(xmlRole.getName());

    if (netTransSetRole == null) {
      throw new Exception( //
          _I18NLoader.notFound(MNetTransSetRole.class, xmlRole.getName()));
    }

    // ----------------------------------------
    // Create the relationship
    // ----------------------------------------

    netTransSetRole.getNetTransSetList().add(netTransSet);
    netTransSet.setNetTransSetRoleRef(netTransSetRole);
  }

  // --------------------------------------------------------------------------------

  protected void loadWork(MNetTransSet netTransSet, Work xmlWork) throws Exception {

    // ----------------------------------------
    // No Worklist specified
    // ----------------------------------------

    if (xmlWork == null) {
      return;
    }

    // ----------------------------------------
    // Try to load the Worklist
    // ----------------------------------------

    MWorklist worklist = MBase.loadByField( //
        session, MWorklist.class, MWorklistProp.NAME, xmlWork.getName());

    if (worklist == null) {
      throw new Exception( //
          _I18NLoader.notFound(MWorklist.class, xmlWork.getName()));
    }

    // ----------------------------------------
    // Create the relationship
    // ----------------------------------------

    netTransSet.setWorklistRef(worklist);
  }

  // --------------------------------------------------------------------------------
  // trans
  // --------------------------------------------------------------------------------

  protected void loadTrans(MNetTransSet netTransSet, List<Trans> transList) //
      throws Exception {

    Map<String, MNetTrans> netTransByNameMap = //
    new HashMap<String, MNetTrans>();

    // ----------------------------------------

    for (Trans xmlTrans : transList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netTransByNameMap.get(xmlTrans.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetTrans.class, xmlTrans.getName()));
      }

      // ----------------------------------------
      // Create the MNetTransSetRole
      // ----------------------------------------

      MNetTrans netTrans = new MNetTrans();

      netTrans.setName(xmlTrans.getName());

      if (xmlTrans.getDefaultDocSectionState() != null) {
        netTrans.setDefaultDocSectionValidateState(MDocSectionValidateState.intStateFromStr( //
            xmlTrans.getDefaultDocSectionState().getState()));
      }

      netTransSet.getNetTransList().add(netTrans);
      netTrans.setNetTransSetRef(netTransSet);

      netTransByNameMap.put(netTrans.getName(), netTrans);

      // ----------------------------------------
      // Children
      // ----------------------------------------

      loadDocSectionState(netTrans, xmlTrans.getDocSectionState());

      loadMetaData(netTrans, xmlTrans.getMetaData());
      loadPrePlace(netTrans, xmlTrans.getPrePlace());
      loadPosPlace(netTrans, xmlTrans.getPosPlace());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadPrePlace(MNetTrans netTrans, List<PrePlace> prePlaceList) //
      throws Exception {

    for (PrePlace prePlace : prePlaceList) {
      addNetEdge(netTrans, prePlace.getName(), //
          MNetEdge.DIRECTION_PLACE_TO_TRANS, prePlace.isInhibitor());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadPosPlace(MNetTrans netTrans, List<PosPlace> posPlaceList) //
      throws Exception {

    for (PosPlace posPlace : posPlaceList) {
      addNetEdge(netTrans, posPlace.getName(), //
          MNetEdge.DIRECTION_TRANS_TO_PLACE, false);
    }
  }

  // --------------------------------------------------------------------------------

  protected void addNetEdge( //
      MNetTrans netTrans, String placeName, int direction, boolean inhibitor) //
      throws Exception {

    // ----------------------------------------
    // Try to get the MNetPlace
    // ----------------------------------------

    MNetPlace netPlace = netPlaceByNameMap.get(placeName);

    if (netPlace == null) {
      throw new Exception( //
          _I18NLoader.notFound(MNetPlace.class, placeName));
    }

    // ----------------------------------------
    // Create the MNetEdge
    // ----------------------------------------

    MNetEdge netEdge = new MNetEdge();
    netEdge.setDirection(direction);
    netEdge.setInhibitor(inhibitor);

    netEdge.setNetPlaceRef(netPlace);
    netPlace.getNetEdgeList().add(netEdge);

    netEdge.setNetTransRef(netTrans);
    netTrans.getNetEdgeList().add(netEdge);
  }

  // --------------------------------------------------------------------------------
  // state-list
  // --------------------------------------------------------------------------------

  protected void loadStateGrp(List<StateGrp> stateGrpList) throws Exception {

    Map<String, MNetStateGrp> netStateGrpByNameMap = //
    new HashMap<String, MNetStateGrp>();

    // ----------------------------------------

    for (StateGrp xmlStateGrp : stateGrpList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netStateGrpByNameMap.get(xmlStateGrp.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetStateGrp.class, xmlStateGrp.getName()));
      }

      // ----------------------------------------
      // Create the MNetStateGrp
      // ----------------------------------------

      MNetStateGrp netStateGrp = new MNetStateGrp();

      netStateGrp.setName(xmlStateGrp.getName());
      netStateGrp.setTerminal(xmlStateGrp.isTerminal());

      if (xmlStateGrp.getDefaultDocSectionState() != null) {
        netStateGrp.setDefaultDocSectionViewState(MDocSectionViewState.intStateFromStr( //
            xmlStateGrp.getDefaultDocSectionState().getState()));
      }

      netPetri.getNetStateGrpList().add(netStateGrp);
      netStateGrp.setNetPetriRef(netPetri);

      netStateGrpByNameMap.put(netStateGrp.getName(), netStateGrp);

      // ----------------------------------------
      // Children
      // ----------------------------------------

      loadDocSectionState(netStateGrp, xmlStateGrp.getDocSectionState());

      loadMetaData(netStateGrp, xmlStateGrp.getMetaData());
      loadStateSet(netStateGrp, xmlStateGrp.getStateSet());
    }
  }

  // --------------------------------------------------------------------------------
  // state-set
  // --------------------------------------------------------------------------------

  protected void loadStateSet(MNetStateGrp netStateGrp, List<StateSet> stateSetList) //
      throws Exception {

    Map<String, MNetStateSet> netStateSetByNameMap = //
    new HashMap<String, MNetStateSet>();

    // ----------------------------------------

    for (StateSet xmlStateSet : stateSetList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netStateSetByNameMap.get(xmlStateSet.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MNetStateSet.class, xmlStateSet.getName()));
      }

      // ----------------------------------------
      // Create the MNetStateSet
      // ----------------------------------------

      MNetStateSet netStateSet = new MNetStateSet();

      netStateSet.setName(xmlStateSet.getName());

      netStateGrp.getNetStateSetList().add(netStateSet);
      netStateSet.setNetStateGrpRef(netStateGrp);

      netStateSetByNameMap.put(netStateSet.getName(), netStateSet);

      // ----------------------------------------
      // Children
      // ----------------------------------------

      loadPlace(netStateSet, xmlStateSet.getPlace());
    }
  }

  // --------------------------------------------------------------------------------

  protected void loadPlace(MNetStateSet netStateSet, List<Place> placeList) //
      throws Exception {

    Map<String, MPlaceState> netPlaceStateByNameMap = //
    new HashMap<String, MPlaceState>();

    // ----------------------------------------

    for (Place xmlPlace : placeList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (netPlaceStateByNameMap.get(xmlPlace.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MPlaceState.class, xmlPlace.getName()));
      }

      // ----------------------------------------
      // Try to get the MNetPlace
      // ----------------------------------------

      MNetPlace netPlace = netPlaceByNameMap.get(xmlPlace.getName());

      if (netPlace == null) {
        throw new Exception( //
            _I18NLoader.notFound(MNetPlace.class, xmlPlace.getName()));
      }

      // ----------------------------------------
      // Create the MPlaceState
      // ----------------------------------------

      MPlaceState placeState = new MPlaceState();
      placeState.setTokens(xmlPlace.getTokens());

      netPlace.getPlaceStateList().add(placeState);
      placeState.setNetPlaceRef(netPlace);

      netStateSet.getPlaceStateList().add(placeState);
      placeState.setNetStateSetRef(netStateSet);

      netPlaceStateByNameMap.put(netPlace.getName(), placeState);
    }

    // ----------------------------------------
    // Fill the remaining with 0 tokens
    // ----------------------------------------

    for (MNetPlace netPlace : netPlaceByNameMap.values()) {
      if (netPlaceStateByNameMap.get(netPlace.getName()) != null) {
        continue;
      }

      // ----------------------------------------
      // Create the MPlaceState
      // ----------------------------------------

      MPlaceState placeState = new MPlaceState();

      netPlace.getPlaceStateList().add(placeState);
      placeState.setNetPlaceRef(netPlace);

      netStateSet.getPlaceStateList().add(placeState);
      placeState.setNetStateSetRef(netStateSet);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a MNetPetri
  // --------------------------------------------------------------------------------

  public MNetPetri load(InputStream fis) throws Exception {
    netPetri = new MNetPetri();

    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    NetPetriDef xmlNetPetriDef = (NetPetriDef) unmarshaller.unmarshal(fis);

    netPetri.setName(xmlNetPetriDef.getName());

    if (xmlNetPetriDef.getDefaultDocSectionValidateState() != null) {
      netPetri.setDefaultDocSectionValidateState(MDocSectionValidateState.intStateFromStr( //
          xmlNetPetriDef.getDefaultDocSectionValidateState().getState()));
    }

    if (xmlNetPetriDef.getDefaultDocSectionEditState() != null) {
      netPetri.setDefaultDocSectionEditState(MDocSectionEditState.intStateFromStr( //
          xmlNetPetriDef.getDefaultDocSectionEditState().getState()));
    }

    if (xmlNetPetriDef.getDefaultDocSectionViewState() != null) {
      netPetri.setDefaultDocSectionViewState(MDocSectionViewState.intStateFromStr( //
          xmlNetPetriDef.getDefaultDocSectionViewState().getState()));
    }

    loadDocType(netPetri, xmlNetPetriDef);

    loadMetaData(netPetri, xmlNetPetriDef.getMetaData());

    loadRolesList(xmlNetPetriDef.getRolesList());
    loadPlaceList(xmlNetPetriDef.getPlaceList());

    loadTransSet(xmlNetPetriDef.getTransList().getTransSet());
    loadStateGrp(xmlNetPetriDef.getStateList().getStateGrp());

    return netPetri;
  }

  // --------------------------------------------------------------------------------

  public static void loadNetPetri(InputStream fis) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    NetPetriLoader netPetriLoader = new NetPetriLoader(session);
    MNetPetri netPetri = netPetriLoader.load(fis);

    session.saveOrUpdate(netPetri);

    session.getTransaction().commit();
    session.close();
  }
}
