//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.21 at 10:39:14 PM VET 
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
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}description"/>
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}attributes"/>
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}editForm"/>
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}listForm"/>
 *         &lt;element ref="{http://www.cledaCrud.org/crud.xsd}profile" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="package" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modelName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="modelPackage" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="group" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "attributes",
    "editForm",
    "listForm",
    "profile"
})
@XmlRootElement(name = "crud")
public class Crud {

    @XmlElement(required = true)
    protected Description description;
    @XmlElement(required = true)
    protected Attributes attributes;
    @XmlElement(required = true)
    protected EditForm editForm;
    @XmlElement(required = true)
    protected ListForm listForm;
    @XmlElement(required = true)
    protected List<Profile> profile;
    @XmlAttribute(name = "package", required = true)
    protected String _package;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "modelName", required = true)
    protected String modelName;
    @XmlAttribute(name = "modelPackage", required = true)
    protected String modelPackage;
    @XmlAttribute(name = "group", required = true)
    protected String group;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the attributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of the attributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setAttributes(Attributes value) {
        this.attributes = value;
    }

    /**
     * Gets the value of the editForm property.
     * 
     * @return
     *     possible object is
     *     {@link EditForm }
     *     
     */
    public EditForm getEditForm() {
        return editForm;
    }

    /**
     * Sets the value of the editForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link EditForm }
     *     
     */
    public void setEditForm(EditForm value) {
        this.editForm = value;
    }

    /**
     * Gets the value of the listForm property.
     * 
     * @return
     *     possible object is
     *     {@link ListForm }
     *     
     */
    public ListForm getListForm() {
        return listForm;
    }

    /**
     * Sets the value of the listForm property.
     * 
     * @param value
     *     allowed object is
     *     {@link ListForm }
     *     
     */
    public void setListForm(ListForm value) {
        this.listForm = value;
    }

    /**
     * Gets the value of the profile property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the profile property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProfile().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Profile }
     * 
     * 
     */
    public List<Profile> getProfile() {
        if (profile == null) {
            profile = new ArrayList<Profile>();
        }
        return this.profile;
    }

    /**
     * Gets the value of the package property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPackage() {
        return _package;
    }

    /**
     * Sets the value of the package property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPackage(String value) {
        this._package = value;
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
     * Gets the value of the modelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelName() {
        return modelName;
    }

    /**
     * Sets the value of the modelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelName(String value) {
        this.modelName = value;
    }

    /**
     * Gets the value of the modelPackage property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModelPackage() {
        return modelPackage;
    }

    /**
     * Sets the value of the modelPackage property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModelPackage(String value) {
        this.modelPackage = value;
    }

    /**
     * Gets the value of the group property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroup() {
        return group;
    }

    /**
     * Sets the value of the group property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroup(String value) {
        this.group = value;
    }

}
