package com.minotauro.workflow.prop;

import com.minotauro.i18n.base.BaseI18NMain;
import com.minotauro.workflow.model.MActor;
import com.minotauro.workflow.model.MDocSection;
import com.minotauro.workflow.model.MDocSectionEditState;
import com.minotauro.workflow.model.MDocSectionValidateState;
import com.minotauro.workflow.model.MDocSectionViewState;
import com.minotauro.workflow.model.MDocType;
import com.minotauro.workflow.model.MDocTypeMetaData;
import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MNetEdge;
import com.minotauro.workflow.model.MNetPetri;
import com.minotauro.workflow.model.MNetPetriMetaData;
import com.minotauro.workflow.model.MNetPlace;
import com.minotauro.workflow.model.MNetStateGrp;
import com.minotauro.workflow.model.MNetStateGrpMetaData;
import com.minotauro.workflow.model.MNetStateSet;
import com.minotauro.workflow.model.MNetTrans;
import com.minotauro.workflow.model.MNetTransMetaData;
import com.minotauro.workflow.model.MNetTransSet;
import com.minotauro.workflow.model.MNetTransSetMetaData;
import com.minotauro.workflow.model.MNetTransSetRole;
import com.minotauro.workflow.model.MPlaceState;
import com.minotauro.workflow.model.MWorkflow;
import com.minotauro.workflow.model.MWorklist;
import com.minotauro.workflow.model.MWorklistMetaData;
import com.minotauro.workflow.model.MWrkPlace;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransLog;
import com.minotauro.workflow.model.MWrkTransSet;

public class Main extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    Main m = new Main();

    m.prop(MActor.class);
    m.prop(MDocument.class);
    m.prop(MDocSection.class);
    m.prop(MDocSectionEditState.class);
    m.prop(MDocSectionValidateState.class);
    m.prop(MDocSectionViewState.class);
    m.prop(MDocType.class);
    m.prop(MDocTypeMetaData.class);
    m.prop(MNetEdge.class);
    m.prop(MNetPetri.class);
    m.prop(MNetPetriMetaData.class);
    m.prop(MNetPlace.class);
    m.prop(MNetStateGrp.class);
    m.prop(MNetStateGrpMetaData.class);
    m.prop(MNetStateSet.class);
    m.prop(MNetTrans.class);
    m.prop(MNetTransMetaData.class);
    m.prop(MNetTransSet.class);
    m.prop(MNetTransSetMetaData.class);
    m.prop(MNetTransSetRole.class);
    m.prop(MPlaceState.class);
    m.prop(MWorkflow.class);
    m.prop(MWorklist.class);
    m.prop(MWorklistMetaData.class);
    m.prop(MWrkPlace.class);
    m.prop(MWrkTrans.class);
    m.prop(MWrkTransLog.class);
    m.prop(MWrkTransSet.class);
  }
}
