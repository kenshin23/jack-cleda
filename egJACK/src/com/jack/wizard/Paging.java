//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.26 at 11:46:16 AM VET 
//


package com.jack.wizard;

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
 *       &lt;attribute name="opt1" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="opt2" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "paging")
public class Paging {

    @XmlAttribute(name = "opt1", required = true)
    protected String opt1;
    @XmlAttribute(name = "opt2", required = true)
    protected String opt2;

    /**
     * Gets the value of the opt1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpt1() {
        return opt1;
    }

    /**
     * Sets the value of the opt1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpt1(String value) {
        this.opt1 = value;
    }

    /**
     * Gets the value of the opt2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpt2() {
        return opt2;
    }

    /**
     * Sets the value of the opt2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpt2(String value) {
        this.opt2 = value;
    }

}
