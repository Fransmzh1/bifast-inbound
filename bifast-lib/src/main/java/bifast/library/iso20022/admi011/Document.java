//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.28 at 04:41:00 PM ICT 
//


package bifast.library.iso20022.admi011;

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
 *         &lt;element name="SysEvtAck" type="{urn:iso:std:iso:20022:tech:xsd:admi.011.001.01}SystemEventAcknowledgementV01"/&gt;
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
    "sysEvtAck"
})
public class Document {

    @XmlElement(name = "SysEvtAck", required = true)
    protected SystemEventAcknowledgementV01 sysEvtAck;

    /**
     * Gets the value of the sysEvtAck property.
     * 
     * @return
     *     possible object is
     *     {@link SystemEventAcknowledgementV01 }
     *     
     */
    public SystemEventAcknowledgementV01 getSysEvtAck() {
        return sysEvtAck;
    }

    /**
     * Sets the value of the sysEvtAck property.
     * 
     * @param value
     *     allowed object is
     *     {@link SystemEventAcknowledgementV01 }
     *     
     */
    public void setSysEvtAck(SystemEventAcknowledgementV01 value) {
        this.sysEvtAck = value;
    }

}
