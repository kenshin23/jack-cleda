//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.26 at 04:06:42 AM COT 
//


package com.jack.workflow;

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
 *         &lt;element ref="{http://www.cledaWorkflow.org/workflow.xsd}places" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.cledaWorkflow.org/workflow.xsd}states" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.cledaWorkflow.org/workflow.xsd}roles" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.cledaWorkflow.org/workflow.xsd}transets" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="doc-type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "places",
    "states",
    "roles",
    "transets"
})
@XmlRootElement(name = "petri-net")
public class PetriNet {

    @XmlElement(required = true)
    protected List<Places> places;
    @XmlElement(required = true)
    protected List<States> states;
    @XmlElement(required = true)
    protected List<Roles> roles;
    @XmlElement(required = true)
    protected List<Transets> transets;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "doc-type", required = true)
    protected String docType;

    /**
     * Gets the value of the places property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the places property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlaces().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Places }
     * 
     * 
     */
    public List<Places> getPlaces() {
        if (places == null) {
            places = new ArrayList<Places>();
        }
        return this.places;
    }

    /**
     * Gets the value of the states property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the states property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStates().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link States }
     * 
     * 
     */
    public List<States> getStates() {
        if (states == null) {
            states = new ArrayList<States>();
        }
        return this.states;
    }

    /**
     * Gets the value of the roles property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the roles property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRoles().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Roles }
     * 
     * 
     */
    public List<Roles> getRoles() {
        if (roles == null) {
            roles = new ArrayList<Roles>();
        }
        return this.roles;
    }

    /**
     * Gets the value of the transets property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transets property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransets().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Transets }
     * 
     * 
     */
    public List<Transets> getTransets() {
        if (transets == null) {
            transets = new ArrayList<Transets>();
        }
        return this.transets;
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

    /**
     * Gets the value of the docType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocType() {
        return docType;
    }

    /**
     * Sets the value of the docType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocType(String value) {
        this.docType = value;
    }

}
