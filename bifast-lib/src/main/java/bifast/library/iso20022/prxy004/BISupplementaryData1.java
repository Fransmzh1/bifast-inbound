//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.06.28 at 04:50:00 PM ICT 
//


package bifast.library.iso20022.prxy004;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BI_SupplementaryData1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BI_SupplementaryData1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PlcAndNm" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}BI_Max350Text" minOccurs="0"/&gt;
 *         &lt;element name="Envlp" type="{urn:iso:std:iso:20022:tech:xsd:prxy.004.001.01}BI_SupplementaryDataEnvelope1"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BI_SupplementaryData1", propOrder = {
    "plcAndNm",
    "envlp"
})
public class BISupplementaryData1 {

    @XmlElement(name = "PlcAndNm")
    protected String plcAndNm;
    @XmlElement(name = "Envlp", required = true)
    protected BISupplementaryDataEnvelope1 envlp;

    /**
     * Gets the value of the plcAndNm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlcAndNm() {
        return plcAndNm;
    }

    /**
     * Sets the value of the plcAndNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlcAndNm(String value) {
        this.plcAndNm = value;
    }

    /**
     * Gets the value of the envlp property.
     * 
     * @return
     *     possible object is
     *     {@link BISupplementaryDataEnvelope1 }
     *     
     */
    public BISupplementaryDataEnvelope1 getEnvlp() {
        return envlp;
    }

    /**
     * Sets the value of the envlp property.
     * 
     * @param value
     *     allowed object is
     *     {@link BISupplementaryDataEnvelope1 }
     *     
     */
    public void setEnvlp(BISupplementaryDataEnvelope1 value) {
        this.envlp = value;
    }

}
