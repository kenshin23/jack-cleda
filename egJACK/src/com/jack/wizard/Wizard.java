//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.06.26 at 11:03:19 PM VET 
//


package com.jack.wizard;

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
 *         &lt;element ref="{}att"/>
 *         &lt;element ref="{}validator"/>
 *         &lt;element ref="{}screen"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="desc" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="bean-class" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "att",
    "validator",
    "screen"
})
@XmlRootElement(name = "wizard")
public class Wizard {

    @XmlElement(required = true)
    protected Att att;
    @XmlElement(required = true)
    protected Validator validator;
    @XmlElement(required = true)
    protected Screen screen;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "desc", required = true)
    protected String desc;
    @XmlAttribute(name = "bean-class", required = true)
    protected String beanClass;

    /**
     * Gets the value of the att property.
     * 
     * @return
     *     possible object is
     *     {@link Att }
     *     
     */
    public Att getAtt() {
        return att;
    }

    /**
     * Sets the value of the att property.
     * 
     * @param value
     *     allowed object is
     *     {@link Att }
     *     
     */
    public void setAtt(Att value) {
        this.att = value;
    }

    /**
     * Gets the value of the validator property.
     * 
     * @return
     *     possible object is
     *     {@link Validator }
     *     
     */
    public Validator getValidator() {
        return validator;
    }

    /**
     * Sets the value of the validator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Validator }
     *     
     */
    public void setValidator(Validator value) {
        this.validator = value;
    }

    /**
     * Gets the value of the screen property.
     * 
     * @return
     *     possible object is
     *     {@link Screen }
     *     
     */
    public Screen getScreen() {
        return screen;
    }

    /**
     * Sets the value of the screen property.
     * 
     * @param value
     *     allowed object is
     *     {@link Screen }
     *     
     */
    public void setScreen(Screen value) {
        this.screen = value;
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
     * Gets the value of the desc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets the value of the desc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesc(String value) {
        this.desc = value;
    }

    /**
     * Gets the value of the beanClass property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBeanClass() {
        return beanClass;
    }

    /**
     * Sets the value of the beanClass property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBeanClass(String value) {
        this.beanClass = value;
    }

}
