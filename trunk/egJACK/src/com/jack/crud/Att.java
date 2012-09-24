//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.24 at 02:58:14 PM UTC 
//


package com.jack.crud;

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
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}validatorChoice" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="visibleOnList" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="editabletiOnList" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="filterByThis" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="editFieldType" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "validatorChoice"
})
@XmlRootElement(name = "att")
public class Att {

    @XmlElement(required = true)
    protected List<ValidatorChoice> validatorChoice;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "visibleOnList", required = true)
    protected boolean visibleOnList;
    @XmlAttribute(name = "editabletiOnList", required = true)
    protected boolean editabletiOnList;
    @XmlAttribute(name = "filterByThis", required = true)
    protected boolean filterByThis;
    @XmlAttribute(name = "editFieldType", required = true)
    protected String editFieldType;

    /**
     * Gets the value of the validatorChoice property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validatorChoice property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidatorChoice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidatorChoice }
     * 
     * 
     */
    public List<ValidatorChoice> getValidatorChoice() {
        if (validatorChoice == null) {
            validatorChoice = new ArrayList<ValidatorChoice>();
        }
        return this.validatorChoice;
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
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

    /**
     * Gets the value of the visibleOnList property.
     * 
     */
    public boolean isVisibleOnList() {
        return visibleOnList;
    }

    /**
     * Sets the value of the visibleOnList property.
     * 
     */
    public void setVisibleOnList(boolean value) {
        this.visibleOnList = value;
    }

    /**
     * Gets the value of the editabletiOnList property.
     * 
     */
    public boolean isEditabletiOnList() {
        return editabletiOnList;
    }

    /**
     * Sets the value of the editabletiOnList property.
     * 
     */
    public void setEditabletiOnList(boolean value) {
        this.editabletiOnList = value;
    }

    /**
     * Gets the value of the filterByThis property.
     * 
     */
    public boolean isFilterByThis() {
        return filterByThis;
    }

    /**
     * Sets the value of the filterByThis property.
     * 
     */
    public void setFilterByThis(boolean value) {
        this.filterByThis = value;
    }

    /**
     * Gets the value of the editFieldType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEditFieldType() {
        return editFieldType;
    }

    /**
     * Sets the value of the editFieldType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEditFieldType(String value) {
        this.editFieldType = value;
    }

}
