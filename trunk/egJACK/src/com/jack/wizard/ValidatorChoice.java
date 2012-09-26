//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2012.09.26 at 03:55:05 AM COT 
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
 *       &lt;all>
 *         &lt;element ref="{http://www.cledaWizard.org/Wizard.xsd}validator"/>
 *         &lt;element ref="{http://www.cledaWizard.org/Wizard.xsd}validators"/>
 *       &lt;/all>
 *       &lt;attribute name="bandera" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "validatorChoice")
public class ValidatorChoice {

    @XmlElement(required = true)
    protected Validator validator;
    @XmlElement(required = true)
    protected Validators validators;
    @XmlAttribute(name = "bandera", required = true)
    protected boolean bandera;

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
     * Gets the value of the validators property.
     * 
     * @return
     *     possible object is
     *     {@link Validators }
     *     
     */
    public Validators getValidators() {
        return validators;
    }

    /**
     * Sets the value of the validators property.
     * 
     * @param value
     *     allowed object is
     *     {@link Validators }
     *     
     */
    public void setValidators(Validators value) {
        this.validators = value;
    }

    /**
     * Gets the value of the bandera property.
     * 
     */
    public boolean isBandera() {
        return bandera;
    }

    /**
     * Sets the value of the bandera property.
     * 
     */
    public void setBandera(boolean value) {
        this.bandera = value;
    }

}
