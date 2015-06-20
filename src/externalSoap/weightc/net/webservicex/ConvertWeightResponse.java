
package externalSoap.weightc.net.webservicex;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="ConvertWeightResult" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "convertWeightResult"
})
@XmlRootElement(name = "ConvertWeightResponse")
public class ConvertWeightResponse {

    @XmlElement(name = "ConvertWeightResult")
    protected double convertWeightResult;

    /**
     * Gets the value of the convertWeightResult property.
     * 
     */
    public double getConvertWeightResult() {
        return convertWeightResult;
    }

    /**
     * Sets the value of the convertWeightResult property.
     * 
     */
    public void setConvertWeightResult(double value) {
        this.convertWeightResult = value;
    }

}
