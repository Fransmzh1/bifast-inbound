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
 * <p>Java class for ProxyEnquiry14 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProxyEnquiry14"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PrxyNqry" type="{urn:iso:std:iso:20022:tech:xsd:prxy.005.001.01}ProxyDefinition1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProxyEnquiry14", propOrder = {
    "prxyNqry"
})
public class ProxyEnquiry14 {

    @XmlElement(name = "PrxyNqry", required = true)
    protected ProxyDefinition1 prxyNqry;

    /**
     * Gets the value of the prxyNqry property.
     * 
     * @return
     *     possible object is
     *     {@link ProxyDefinition1 }
     *     
     */
    public ProxyDefinition1 getPrxyNqry() {
        return prxyNqry;
    }

    /**
     * Sets the value of the prxyNqry property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProxyDefinition1 }
     *     
     */
    public void setPrxyNqry(ProxyDefinition1 value) {
        this.prxyNqry = value;
    }

}
