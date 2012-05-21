/*
 * Created on 11/01/2007
 */
package com.minotauro.sandbox.testpnetio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.minotauro.workflow.xml.netpetri.NetPetriDef;
import com.minotauro.workflow.xml.netpetri.ObjectFactory;

/**
 * @author Demián Gutierrez
 */
public class NetPetriLoader {

	public static void loadAndSave(InputStream fis, OutputStream fos)
			throws Exception {
		JAXBContext jc = JAXBContext.newInstance( //
				ObjectFactory.class.getPackage().getName());

		Unmarshaller unmarshaller = jc.createUnmarshaller();
		NetPetriDef xmlNetPetriDef = (NetPetriDef) unmarshaller.unmarshal(fis);

		Marshaller marshaller = jc.createMarshaller();
		marshaller.marshal(xmlNetPetriDef, fos);
	}

	// --------------------------------------------------------------------------------

	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream( //
				"src/com/minotauro/sandbox/init/simple/w_main_net-petri-def.xml");

		FileOutputStream fos = new FileOutputStream("net-petri-copy-delete.xml");

		loadAndSave(fis, fos);

		fis.close();
		fos.close();
	}
}
