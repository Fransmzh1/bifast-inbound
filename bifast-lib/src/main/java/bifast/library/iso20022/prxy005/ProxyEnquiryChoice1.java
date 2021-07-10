//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.28 at 04:50:20 PM ICT 
//


package bifast.library.iso20022.prxy005;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ProxyEnquiryChoice1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiryChoice1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="RegnId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}Max35Text"/&gt;
 *         &lt;element name="ScndId" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ScndIdDefinition1"/&gt;
 *         &lt;element name="AccOnly" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyEnquiry12"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiryChoice1", propOrder = {
    "regnId",
    "scndId",
    "accOnly"
})
public class ProxyEnquiryChoice1 {

    @XmlElement(name = "RegnId")
    protected String regnId;
    @XmlElement(name = "ScndId")
    protected ScndIdDefinition1 scndId;
    @XmlElement(name = "AccOnly")
    protected ProxyEnquiry12 accOnly;

    /**
     * Gets the value of the regnId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegnId() {
        return regnId;
    }

    /**
     * Sets the value of the regnId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegnId(String value) {
        this.regnId = value;
    }

    /**
     * Gets the value of the scndId property.
     * 
     * @return
     *     possible object is
     *     {@link ScndIdDefinition1 }
     *     
     */
    public ScndIdDefinition1 getScndId() {
        return scndId;
    }

    /**
     * Sets the value of the scndId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ScndIdDefinition1 }
     *     
     */
    public void setScndId(ScndIdDefinition1 value) {
        this.scndId = value;
    }

    /**
     * Gets the value of the accOnly property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyEnquiry12 }
     *     
     */
    public ProxyEnquiry12 getAccOnly() {
        return accOnly;
    }

    /**
     * Sets the value of the accOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyEnquiry12 }
     *     
     */
    public void setAccOnly(ProxyEnquiry12 value) {
        this.accOnly = value;
    }

}