/*
 * Created on 14/01/2007
 */
package com.minotauro.workflow.loaders;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.workflow.model.MWorklist;
import com.minotauro.workflow.xml.worklist.MetaData;
import com.minotauro.workflow.xml.worklist.ObjectFactory;
import com.minotauro.workflow.xml.worklist.Worklist;
import com.minotauro.workflow.xml.worklist.WorklistList;

/**
 * @author Demián Gutierrez
 */
public class WorklistLoader {

  protected List<MWorklist> worklistList = new ArrayList<MWorklist>();

  protected Session session;

  // --------------------------------------------------------------------------------

  public WorklistLoader(Session session) {
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

  protected void loadWorklistList(List<Worklist> xmlWorklistList) throws Exception {

    Map<String, MWorklist> worklistByNameMap = //
    new HashMap<String, MWorklist>();

    // ----------------------------------------

    for (Worklist xmlWorklist : xmlWorklistList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (worklistByNameMap.get(xmlWorklist.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField(MWorklist.class, xmlWorklist.getName()));
      }

      // ----------------------------------------
      // Create the MWorklist
      // ----------------------------------------

      MWorklist worklist = new MWorklist();
      worklist.setName(xmlWorklist.getName());

      loadMetaData(worklist, xmlWorklist.getMetaData());

      worklistByNameMap.put(xmlWorklist.getName(), worklist);
      worklistList.add(worklist);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a List<MWorklist>
  // --------------------------------------------------------------------------------

  public List<MWorklist> load(InputStream fis) throws Exception {
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    WorklistList xmlWorklistList = (WorklistList) unmarshaller.unmarshal(fis);

    loadWorklistList(xmlWorklistList.getWorklist());

    return worklistList;
  }

  // --------------------------------------------------------------------------------

  public static void loadWorklist(InputStream fis) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    WorklistLoader worklistLoader = new WorklistLoader(session);
    List<MWorklist> list = worklistLoader.load(fis);

    for (MWorklist worklist : list) {
      session.saveOrUpdate(worklist);
    }

    session.getTransaction().commit();
    session.close();
  }
}
