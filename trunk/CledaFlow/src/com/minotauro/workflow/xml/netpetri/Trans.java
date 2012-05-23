//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.0.5-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.02.15 at 09:25:05 AM VET 
//


package com.minotauro.workflow.xml.netpetri;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.cleda.org/net-petri-def.xsd}meta-data" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.cleda.org/net-petri-def.xsd}default-doc-section-state" minOccurs="0"/>
 *         &lt;element ref="{http://www.cleda.org/net-petri-def.xsd}doc-section-state" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{http://www.cleda.org/net-petri-def.xsd}pre-place" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.cleda.org/net-petri-def.xsd}pos-place" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "metaData",
    "defaultDocSectionState",
    "docSectionState",
    "prePlace",
    "posPlace"
})
@XmlRootElement(name = "trans")
public class Trans {

    @XmlElement(name = "meta-data")
    protected List<MetaData> metaData;
    @XmlElement(name = "default-doc-section-state")
    protected DefaultDocSectionState defaultDocSectionState;
    @XmlElement(name = "doc-section-state")
    protected List<DocSectionState> docSectionState;
    @XmlElement(name = "pre-place", required = true)
    protected List<PrePlace> prePlace;
    @XmlElement(name = "pos-place", required = true)
    protected List<PosPlace> posPlace;
    @XmlAttribute(required = true)
    protected String name;

    /**
     * Gets the value of the metaData property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the metaData property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMetaData().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MetaData }
     * 
     * 
     */
    public List<MetaData> getMetaData() {
        if (metaData == null) {
            metaData = new ArrayList<MetaData>();
        }
        return this.metaData;
    }

    /**
     * Gets the value of the defaultDocSectionState property.
     * 
     * @return
     *     possible object is
     *     {@link DefaultDocSectionState }
     *     
     */
    public DefaultDocSectionState getDefaultDocSectionState() {
        return defaultDocSectionState;
    }

    /**
     * Sets the value of the defaultDocSectionState property.
     * 
     * @param value
     *     allowed object is
     *     {@link DefaultDocSectionState }
     *     
     */
    public void setDefaultDocSectionState(DefaultDocSectionState value) {
        this.defaultDocSectionState = value;
    }

    /**
     * Gets the value of the docSectionState property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docSectionState property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocSectionState().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocSectionState }
     * 
     * 
     */
    public List<DocSectionState> getDocSectionState() {
        if (docSectionState == null) {
            docSectionState = new ArrayList<DocSectionState>();
        }
        return this.docSectionState;
    }

    /**
     * Gets the value of the prePlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the prePlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrePlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PrePlace }
     * 
     * 
     */
    public List<PrePlace> getPrePlace() {
        if (prePlace == null) {
            prePlace = new ArrayList<PrePlace>();
        }
        return this.prePlace;
    }

    /**
     * Gets the value of the posPlace property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the posPlace property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPosPlace().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PosPlace }
     * 
     * 
     */
    public List<PosPlace> getPosPlace() {
        if (posPlace == null) {
            posPlace = new ArrayList<PosPlace>();
        }
        return this.posPlace;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

}
