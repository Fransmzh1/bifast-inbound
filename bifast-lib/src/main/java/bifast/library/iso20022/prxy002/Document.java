//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.28 at 04:48:56 PM ICT 
//


package bifast.library.iso20022.prxy002;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Document complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Document"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxyRegnRspn" type="{urn:iso:std:iso:20022:tech:xsd:prxy.002.001.01}ProxyRegistrationResponseV01"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {
    "prxyRegnRspn"
})
public class Document {

    @XmlElement(name = "PrxyRegnRspn", required = true)
    protected ProxyRegistrationResponseV01 prxyRegnRspn;

    /**
     * Gets the value of the prxyRegnRspn property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyRegistrationResponseV01 }
     *     
     */
    public ProxyRegistrationResponseV01 getPrxyRegnRspn() {
        return prxyRegnRspn;
    }

    /**
     * Sets the value of the prxyRegnRspn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyRegistrationResponseV01 }
     *     
     */
    public void setPrxyRegnRspn(ProxyRegistrationResponseV01 value) {
        this.prxyRegnRspn = value;
    }

}