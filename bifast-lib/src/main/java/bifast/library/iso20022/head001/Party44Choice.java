//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.30 at 08:08:02 PM ICT 
//


package bifast.library.iso20022.head001;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Party44Choice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Party44Choice"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="OrgId" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}PartyIdentification135"/&gt;
 *         &lt;element name="FIId" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}BranchAndFinancialInstitutionIdentification6"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Party44Choice", propOrder = {
    "orgId",
    "fiId"
})
public class Party44Choice {

    @XmlElement(name = "OrgId")
    protected PartyIdentification135 orgId;
    @XmlElement(name = "FIId")
    protected BranchAndFinancialInstitutionIdentification6 fiId;

    /**
     * Gets the value of the orgId property.
     * 
     * @return
     *     possible object is
     *     {@link PartyIdentification135 }
     *     
     */
    public PartyIdentification135 getOrgId() {
        return orgId;
    }

    /**
     * Sets the value of the orgId property.
     * 
     * @param value
     *     allowed object is
     *     {@link PartyIdentification135 }
     *     
     */
    public void setOrgId(PartyIdentification135 value) {
        this.orgId = value;
    }

    /**
     * Gets the value of the fiId property.
     * 
     * @return
     *     possible object is
     *     {@link BranchAndFinancialInstitutionIdentification6 }
     *     
     */
    public BranchAndFinancialInstitutionIdentification6 getFIId() {
        return fiId;
    }

    /**
     * Sets the value of the fiId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BranchAndFinancialInstitutionIdentification6 }
     *     
     */
    public void setFIId(BranchAndFinancialInstitutionIdentification6 value) {
        this.fiId = value;
    }

}
