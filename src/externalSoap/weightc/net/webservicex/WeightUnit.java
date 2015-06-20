
package externalSoap.weightc.net.webservicex;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for WeightUnit.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="WeightUnit">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Grains"/>
 *     &lt;enumeration value="Scruples"/>
 *     &lt;enumeration value="Carats"/>
 *     &lt;enumeration value="Grams"/>
 *     &lt;enumeration value="Pennyweight"/>
 *     &lt;enumeration value="DramAvoir"/>
 *     &lt;enumeration value="DramApoth"/>
 *     &lt;enumeration value="OuncesAvoir"/>
 *     &lt;enumeration value="OuncesTroyApoth"/>
 *     &lt;enumeration value="Poundals"/>
 *     &lt;enumeration value="PoundsTroy"/>
 *     &lt;enumeration value="PoundsAvoir"/>
 *     &lt;enumeration value="Kilograms"/>
 *     &lt;enumeration value="Stones"/>
 *     &lt;enumeration value="QuarterUS"/>
 *     &lt;enumeration value="Slugs"/>
 *     &lt;enumeration value="weight100UScwt"/>
 *     &lt;enumeration value="ShortTons"/>
 *     &lt;enumeration value="MetricTonsTonne"/>
 *     &lt;enumeration value="LongTons"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "WeightUnit")
@XmlEnum
public enum WeightUnit {

    @XmlEnumValue("Grains")
    GRAINS("Grains"),
    @XmlEnumValue("Scruples")
    SCRUPLES("Scruples"),
    @XmlEnumValue("Carats")
    CARATS("Carats"),
    @XmlEnumValue("Grams")
    GRAMS("Grams"),
    @XmlEnumValue("Pennyweight")
    PENNYWEIGHT("Pennyweight"),
    @XmlEnumValue("DramAvoir")
    DRAM_AVOIR("DramAvoir"),
    @XmlEnumValue("DramApoth")
    DRAM_APOTH("DramApoth"),
    @XmlEnumValue("OuncesAvoir")
    OUNCES_AVOIR("OuncesAvoir"),
    @XmlEnumValue("OuncesTroyApoth")
    OUNCES_TROY_APOTH("OuncesTroyApoth"),
    @XmlEnumValue("Poundals")
    POUNDALS("Poundals"),
    @XmlEnumValue("PoundsTroy")
    POUNDS_TROY("PoundsTroy"),
    @XmlEnumValue("PoundsAvoir")
    POUNDS_AVOIR("PoundsAvoir"),
    @XmlEnumValue("Kilograms")
    KILOGRAMS("Kilograms"),
    @XmlEnumValue("Stones")
    STONES("Stones"),
    @XmlEnumValue("QuarterUS")
    QUARTER_US("QuarterUS"),
    @XmlEnumValue("Slugs")
    SLUGS("Slugs"),
    @XmlEnumValue("weight100UScwt")
    WEIGHT_100_U_SCWT("weight100UScwt"),
    @XmlEnumValue("ShortTons")
    SHORT_TONS("ShortTons"),
    @XmlEnumValue("MetricTonsTonne")
    METRIC_TONS_TONNE("MetricTonsTonne"),
    @XmlEnumValue("LongTons")
    LONG_TONS("LongTons");
    private final String value;

    WeightUnit(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static WeightUnit fromValue(String v) {
        for (WeightUnit c: WeightUnit.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
