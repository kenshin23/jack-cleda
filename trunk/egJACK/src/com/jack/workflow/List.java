//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.24 at 03:49:56 PM UTC 
//


package com.jack.workflow;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *       &lt;attribute name="targetModelName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="relationModelName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="listGuiPackage" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="visibleOnList" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="editabletiOnList" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="relationAttName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="firstRelAttName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="secondRelAttName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="edit" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="propRelName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="relationFirstAtt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="relationSecAtt" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="relModGetter" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "list")
public class List {

    @XmlAttribute(name = "targetModelName", required = true)
    protected String targetModelName;
    @XmlAttribute(name = "relationModelName", required = true)
    protected String relationModelName;
    @XmlAttribute(name = "type", required = true)
    protected String type;
    @XmlAttribute(name = "listGuiPackage", required = true)
    protected String listGuiPackage;
    @XmlAttribute(name = "visibleOnList", required = true)
    protected boolean visibleOnList;
    @XmlAttribute(name = "editabletiOnList", required = true)
    protected boolean editabletiOnList;
    @XmlAttribute(name = "relationAttName", required = true)
    protected String relationAttName;
    @XmlAttribute(name = "firstRelAttName", required = true)
    protected String firstRelAttName;
    @XmlAttribute(name = "secondRelAttName", required = true)
    protected String secondRelAttName;
    @XmlAttribute(name = "edit", required = true)
    protected boolean edit;
    @XmlAttribute(name = "propRelName", required = true)
    protected String propRelName;
    @XmlAttribute(name = "relationFirstAtt", required = true)
    protected String relationFirstAtt;
    @XmlAttribute(name = "relationSecAtt", required = true)
    protected String relationSecAtt;
    @XmlAttribute(name = "relModGetter", required = true)
    protected String relModGetter;

    /**
     * Gets the value of the targetModelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTargetModelName() {
        return targetModelName;
    }

    /**
     * Sets the value of the targetModelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTargetModelName(String value) {
        this.targetModelName = value;
    }

    /**
     * Gets the value of the relationModelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationModelName() {
        return relationModelName;
    }

    /**
     * Sets the value of the relationModelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationModelName(String value) {
        this.relationModelName = value;
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
     * Gets the value of the listGuiPackage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListGuiPackage() {
        return listGuiPackage;
    }

    /**
     * Sets the value of the listGuiPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListGuiPackage(String value) {
        this.listGuiPackage = value;
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
     * Gets the value of the relationAttName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationAttName() {
        return relationAttName;
    }

    /**
     * Sets the value of the relationAttName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationAttName(String value) {
        this.relationAttName = value;
    }

    /**
     * Gets the value of the firstRelAttName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstRelAttName() {
        return firstRelAttName;
    }

    /**
     * Sets the value of the firstRelAttName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstRelAttName(String value) {
        this.firstRelAttName = value;
    }

    /**
     * Gets the value of the secondRelAttName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondRelAttName() {
        return secondRelAttName;
    }

    /**
     * Sets the value of the secondRelAttName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondRelAttName(String value) {
        this.secondRelAttName = value;
    }

    /**
     * Gets the value of the edit property.
     * 
     */
    public boolean isEdit() {
        return edit;
    }

    /**
     * Sets the value of the edit property.
     * 
     */
    public void setEdit(boolean value) {
        this.edit = value;
    }

    /**
     * Gets the value of the propRelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropRelName() {
        return propRelName;
    }

    /**
     * Sets the value of the propRelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropRelName(String value) {
        this.propRelName = value;
    }

    /**
     * Gets the value of the relationFirstAtt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationFirstAtt() {
        return relationFirstAtt;
    }

    /**
     * Sets the value of the relationFirstAtt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationFirstAtt(String value) {
        this.relationFirstAtt = value;
    }

    /**
     * Gets the value of the relationSecAtt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelationSecAtt() {
        return relationSecAtt;
    }

    /**
     * Sets the value of the relationSecAtt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelationSecAtt(String value) {
        this.relationSecAtt = value;
    }

    /**
     * Gets the value of the relModGetter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelModGetter() {
        return relModGetter;
    }

    /**
     * Sets the value of the relModGetter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelModGetter(String value) {
        this.relModGetter = value;
    }

}
