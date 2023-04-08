package model;

import lombok.Data;
import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@ToString
@XmlRootElement(name = "Rate")
public class Rate {
    // pola klasy dla jsona zaczynamy z małej litery; dla xml z dużej (najlepiej wczytać URL-a i zobaczyć charakterystyke wyników)
    // jeżeli chcemy korzystać i z xml i json możemy:
    // - opisać dla json z małej litery i zrobić gettery z opisaniem dla xml (@XmlElement(nazwa="Zdużej")
    // - opisać dla xml z dużj z adnotacją(@XmlElement..), a dla jsona dodać adnotację @SerializedName(value="zMałej")
    @XmlElement(name = "No")
    private String No;

    @XmlElement(name = "EffectiveDate")
    private String EffectiveDate;

    @XmlElement(name = "Mid")
    private double Mid;

    private double bid;

    private double ask;

    @XmlElement(name = "Bid")
    public double getBid() {
        return bid;
    }

    @XmlElement(name = "Ask")
    public double getAsk() {
        return ask;
    }
}
