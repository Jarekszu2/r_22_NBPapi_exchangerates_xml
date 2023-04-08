package model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@ToString
@XmlRootElement(name = "ExchangeRatesSeries")
public class ExchangeRatesSeries {

    @XmlElement(name = "Table")
    private String Table;

    @XmlElement(name = "Currency")
    private String Currency;

    @XmlElement(name = "Code")
    private String Code;

    @XmlElement(name = "Rate")
    @XmlElementWrapper(name = "Rates")
    private List<Rate> Rates;
}
