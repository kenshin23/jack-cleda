//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.24 at 01:37:01 AM VET 
//


package com.jack.wizard;

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
 *         &lt;element ref="{http://www.cledaWizard.org/Wizard.xsd}validatorChoice" maxOccurs="2"/>
 *       &lt;/sequence>
 *       &lt;attribute name="chained" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
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
@XmlRootElement(name = "if")
public class If {

    @XmlElement(required = true)
    protected List<ValidatorChoice> validatorChoice;
    @XmlAttribute(name = "chained", required = true)
    protected boolean chained;

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
     * Gets the value of the chained property.
     * 
     */
    public boolean isChained() {
        return chained;
    }

    /**
     * Sets the value of the chained property.
     * 
     */
    public void setChained(boolean value) {
        this.chained = value;
    }

}
