/*
 * Created on 14/01/2007
 */
package com.minotauro.workflow.loaders;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.hibernate.Session;

import com.minotauro.base.i18n._I18NLoader;
import com.minotauro.cleda.model.CledaConnector;
import com.minotauro.metadata.model.MMetaDataParent;
import com.minotauro.workflow.model.MDocSection;
import com.minotauro.workflow.model.MDocType;
import com.minotauro.workflow.xml.doctype.DocSection;
import com.minotauro.workflow.xml.doctype.DocType;
import com.minotauro.workflow.xml.doctype.MetaData;
import com.minotauro.workflow.xml.doctype.ObjectFactory;

/**
 * @author Demián Gutierrez
 */
public class DocTypeLoader {

  protected MDocType docType;

  protected Session session;

  // --------------------------------------------------------------------------------

  public DocTypeLoader(Session session) {
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

  protected void loadDocSectionList(List<DocSection> docSectionList) //
      throws Exception {

    Map<String, MDocSection> docSectionByNameMap = //
    new HashMap<String, MDocSection>();

    // ----------------------------------------

    for (DocSection xmlDocSection : docSectionList) {

      // ----------------------------------------
      // Check not already defined
      // ----------------------------------------

      if (docSectionByNameMap.get(xmlDocSection.getName()) != null) {
        throw new Exception( //
            _I18NLoader.duplicatedField( //
                MDocSection.class, xmlDocSection.getName()));
      }

      // ----------------------------------------
      // Create the MDocSection
      // ----------------------------------------

      MDocSection docSection = new MDocSection();
      docSection.setName(xmlDocSection.getName());

      docType.getDocSectionList().add(docSection);
      docSection.setDocTypeRef(docType);

      docSectionByNameMap.put(docSection.getName(), docSection);
    }
  }

  // --------------------------------------------------------------------------------
  // Load a MDocType
  // --------------------------------------------------------------------------------

  public MDocType load(InputStream fis) throws Exception {
    docType = new MDocType();

    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class.getPackage().getName());
    Unmarshaller unmarshaller = jc.createUnmarshaller();

    DocType xmlDocType = (DocType) unmarshaller.unmarshal(fis);
    docType.setName(xmlDocType.getName());

    loadMetaData(docType, xmlDocType.getMetaData());
    loadDocSectionList(xmlDocType.getDocSection());

    return docType;
  }

  // --------------------------------------------------------------------------------

  public static void loadDocType(InputStream fis) throws Exception {
    Session session = CledaConnector.getInstance().getSession();
    session.beginTransaction();

    DocTypeLoader docTypeLoader = new DocTypeLoader(session);
    MDocType docType = docTypeLoader.load(fis);

    session.saveOrUpdate(docType);

    session.getTransaction().commit();
    session.close();
  }
}
