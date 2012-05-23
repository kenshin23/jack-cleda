/*
 * Created on 04/02/2008
 */
package com.minotauro.workflow.i18n;

import com.minotauro.i18n.base.BaseI18NMain;
import com.minotauro.workflow.api.WorkflowFactory;
import com.minotauro.workflow.engine.EEngPetri;
import com.minotauro.workflow.engine.EEngPetriCreate;
import com.minotauro.workflow.engine.EEngPetriSelect;
import com.minotauro.workflow.engine.EEngPetriTrans;
import com.minotauro.workflow.impl.WorkflowFacadeImpl;
import com.minotauro.workflow.impl.WorkflowSchedulerEngineImpl;
import com.minotauro.workflow.impl.WorkflowSchedulerFacadeImpl;
import com.minotauro.workflow.util.WorkflowLock;

/**
 * @author Demián Gutierrez
 */
public class Main extends BaseI18NMain {

  public static void main(String[] args) throws Exception {
    Main m = new Main();

    m.i18n(WorkflowSchedulerFacadeImpl.class.getSimpleName());
    m.i18n(WorkflowSchedulerEngineImpl.class.getSimpleName());
    m.i18n(WorkflowFacadeImpl.class.getSimpleName());
    m.i18n(WorkflowFactory.class.getSimpleName());
    m.i18n(WorkflowLock.class.getSimpleName());

    m.i18n(EEngPetri.class.getSimpleName());
    m.i18n(EEngPetriCreate.class.getSimpleName());
    m.i18n(EEngPetriSelect.class.getSimpleName());
    m.i18n(EEngPetriTrans.class.getSimpleName());
  }
}
