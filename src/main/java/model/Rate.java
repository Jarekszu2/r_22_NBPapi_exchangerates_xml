package model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@XmlRootElement(name = "Rate")
public class Rate {

    @XmlElement(name = "No")
    private String No;

    @XmlElement(name = "EffectiveDate")
    private String EffectiveDate;

    @XmlElement(name = "Mid")
    private double Mid;
}
