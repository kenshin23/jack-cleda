/*
 * Created on 07/01/2008
 */
package com.minotauro.workflow.api;

import java.util.Map;

import org.hibernate.Session;

import com.minotauro.workflow.model.MDocument;
import com.minotauro.workflow.model.MWrkTrans;
import com.minotauro.workflow.model.MWrkTransSet;

/**
 * @author Demián Gutierrez
 */
public interface WorkflowAgent {

  public void init(MDocument document, Session session);

  // ----------------------------------------

  public MWrkTrans execute(Map<String, MWrkTrans> wrkTransByName) //
      throws Exception;

  // ----------------------------------------

  public void executePre(MWrkTransSet wrkTransSet) //
      throws Exception;

  public void executePos(MWrkTransSet wrkTransSet) //
      throws Exception;
}
