package org.example;

import java.util.Date;

public class CardInfo extends DBBank {
    String NumerKarty;
    Double LimitKarty;
    Date TerminWaznosci;
    int CVV;

    public CardInfo(String numerKarty, Double limitKarty, Date dataWaznosci, int CVV) {
        this.NumerKarty = numerKarty;
        this.LimitKarty = limitKarty;
        this.TerminWaznosci = dataWaznosci;
        this.CVV = CVV;
    }
}
