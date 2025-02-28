package org.example;

import java.util.Date;

public class CardInfo extends DBBank {
    String NumerKarty;
    Double LimitKarty;
    Date TerminWaznosci;
    String CVV;

    public CardInfo(String NumerKarty, Double LimitKarty, Date TerminWaznosci, String CVV) {
        this.NumerKarty = NumerKarty;
        this.LimitKarty = LimitKarty;
        this.TerminWaznosci = TerminWaznosci;
        this.CVV = CVV;
    }
    @Override
    public String toString() {return NumerKarty;}
}
