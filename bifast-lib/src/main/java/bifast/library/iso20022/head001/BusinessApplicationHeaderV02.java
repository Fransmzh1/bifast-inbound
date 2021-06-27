//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.2 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.30 at 08:08:02 PM ICT 
//


package bifast.library.iso20022.head001;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;



/**
 * <p>Java class for BusinessApplicationHeaderV02 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessApplicationHeaderV02"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CharSet" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}UnicodeChartsCode" minOccurs="0"/&gt;
 *         &lt;element name="Fr" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}Party44Choice"/&gt;
 *         &lt;element name="To" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}Party44Choice"/&gt;
 *         &lt;element name="BizMsgIdr" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}Max35Text"/&gt;
 *         &lt;element name="MsgDefIdr" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}Max35Text"/&gt;
 *         &lt;element name="BizSvc" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}Max35Text" minOccurs="0"/&gt;
 *         &lt;element name="MktPrctc" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}ImplementationSpecification1" minOccurs="0"/&gt;
 *         &lt;element name="CreDt" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}ISODateTime"/&gt;
 *         &lt;element name="BizPrcgDt" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}ISODateTime" minOccurs="0"/&gt;
 *         &lt;element name="CpyDplct" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}CopyDuplicate1Code" minOccurs="0"/&gt;
 *         &lt;element name="PssblDplct" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}YesNoIndicator" minOccurs="0"/&gt;
 *         &lt;element name="Prty" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}BusinessMessagePriorityCode" minOccurs="0"/&gt;
 *         &lt;element name="Sgntr" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}SignatureEnvelope" minOccurs="0"/&gt;
 *         &lt;element name="Rltd" type="{urn:iso:std:iso:20022:tech:xsd:head.001.001.02}BusinessApplicationHeader5" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessApplicationHeaderV02", propOrder = {
    "charSet",
    "fr",
    "to",
    "bizMsgIdr",
    "msgDefIdr",
    "bizSvc",
    "mktPrctc",
    "creDt",
    "bizPrcgDt",
    "cpyDplct",
    "pssblDplct",
    "prty",
    "sgntr",
    "rltd"
})
public class BusinessApplicationHeaderV02 {

    @XmlElement(name = "CharSet")
    protected String charSet;
    @XmlElement(name = "Fr", required = true)
    protected Party44Choice fr;
    @XmlElement(name = "To", required = true)
    protected Party44Choice to;
    @XmlElement(name = "BizMsgIdr", required = true)
    protected String bizMsgIdr;
    @XmlElement(name = "MsgDefIdr", required = true)
    protected String msgDefIdr;
    @XmlElement(name = "BizSvc")
    protected String bizSvc;
    @XmlElement(name = "MktPrctc")
    protected ImplementationSpecification1 mktPrctc;
    
    @XmlElement(name = "CreDt", required = true)
    @XmlSchemaType(name = "dateTime")
//    @XmlJavaTypeAdapter(GregorianCalendarXMLAdapter.class)
    protected XMLGregorianCalendar creDt;
//    @XmlElement(name = "CreDt", required = true)
//    protected String creDt;

    @XmlElement(name = "BizPrcgDt")
    @XmlSchemaType(name = "dateTime")
    protected LocalDateTime bizPrcgDt;
    @XmlElement(name = "CpyDplct")
    @XmlSchemaType(name = "string")
    protected String cpyDplct;
    @XmlElement(name = "PssblDplct")
    protected Boolean pssblDplct;
    @XmlElement(name = "Prty")
    protected String prty;
    @XmlElement(name = "Sgntr")
    protected SignatureEnvelope sgntr;
    @XmlElement(name = "Rltd")
    protected List<BusinessApplicationHeader5> rltd;

    /**
     * Gets the value of the charSet property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharSet() {
        return charSet;
    }

    /**
     * Sets the value of the charSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharSet(String value) {
        this.charSet = value;
    }

    /**
     * Gets the value of the fr property.
     * 
     * @return
     *     possible object is
     *     {@link Party44Choice }
     *     
     */
    public Party44Choice getFr() {
        return fr;
    }

    /**
     * Sets the value of the fr property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party44Choice }
     *     
     */
    public void setFr(Party44Choice value) {
        this.fr = value;
    }

    /**
     * Gets the value of the to property.
     * 
     * @return
     *     possible object is
     *     {@link Party44Choice }
     *     
     */
    public Party44Choice getTo() {
        return to;
    }

    /**
     * Sets the value of the to property.
     * 
     * @param value
     *     allowed object is
     *     {@link Party44Choice }
     *     
     */
    public void setTo(Party44Choice value) {
        this.to = value;
    }

    /**
     * Gets the value of the bizMsgIdr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizMsgIdr() {
        return bizMsgIdr;
    }

    /**
     * Sets the value of the bizMsgIdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizMsgIdr(String value) {
        this.bizMsgIdr = value;
    }

    /**
     * Gets the value of the msgDefIdr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMsgDefIdr() {
        return msgDefIdr;
    }

    /**
     * Sets the value of the msgDefIdr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMsgDefIdr(String value) {
        this.msgDefIdr = value;
    }

    /**
     * Gets the value of the bizSvc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBizSvc() {
        return bizSvc;
    }

    /**
     * Sets the value of the bizSvc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBizSvc(String value) {
        this.bizSvc = value;
    }

    /**
     * Gets the value of the mktPrctc property.
     * 
     * @return
     *     possible object is
     *     {@link ImplementationSpecification1 }
     *     
     */
    public ImplementationSpecification1 getMktPrctc() {
        return mktPrctc;
    }

    /**
     * Sets the value of the mktPrctc property.
     * 
     * @param value
     *     allowed object is
     *     {@link ImplementationSpecification1 }
     *     
     */
    public void setMktPrctc(ImplementationSpecification1 value) {
        this.mktPrctc = value;
    }

    /**
     * Gets the value of the creDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCreDt() {
        return creDt;
    }

    /**
     * Sets the value of the creDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCreDt(XMLGregorianCalendar value) {
        this.creDt = value;
    }

    /**
     * Gets the value of the bizPrcgDt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public LocalDateTime getBizPrcgDt() {
        return bizPrcgDt;
    }

    /**
     * Sets the value of the bizPrcgDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBizPrcgDt(LocalDateTime value) {
        this.bizPrcgDt = value;
    }

    /**
     * Gets the value of the cpyDplct property.
     * 
     * @return
     *     possible object is
     *     {@link CopyDuplicate1Code }
     *     
     */
    public String getCpyDplct() {
        return cpyDplct;
    }

    /**
     * Sets the value of the cpyDplct property.
     * 
     * @param value
     *     allowed object is
     *     {@link CopyDuplicate1Code }
     *     
     */
    public void setCpyDplct(String value) {
        this.cpyDplct = value;
    }

    /**
     * Gets the value of the pssblDplct property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPssblDplct() {
        return pssblDplct;
    }

    /**
     * Sets the value of the pssblDplct property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPssblDplct(Boolean value) {
        this.pssblDplct = value;
    }

    /**
     * Gets the value of the prty property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrty() {
        return prty;
    }

    /**
     * Sets the value of the prty property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrty(String value) {
        this.prty = value;
    }

    /**
     * Gets the value of the sgntr property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureEnvelope }
     *     
     */
    public SignatureEnvelope getSgntr() {
        return sgntr;
    }

    /**
     * Sets the value of the sgntr property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureEnvelope }
     *     
     */
    public void setSgntr(SignatureEnvelope value) {
        this.sgntr = value;
    }

    /**
     * Gets the value of the rltd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rltd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRltd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessApplicationHeader5 }
     * 
     * 
     */
    public List<BusinessApplicationHeader5> getRltd() {
        if (rltd == null) {
            rltd = new ArrayList<BusinessApplicationHeader5>();
        }
        return this.rltd;
    }

}