package model;

import java.time.LocalDate;
import java.time.Period;

public class Passagier extends Persoon {

    // Constructor
    public Passagier() {
        super("","","00.00.00-000.00",LocalDate.now());

    }

    public Passagier(String naam,String achternaam, String rijksregisternummer, LocalDate geboortedatum) {
        super(naam, achternaam, rijksregisternummer, geboortedatum);

    }

    //Methode om +18 te shecken
    public boolean IsOuderDan18(){
        Period leeftijd = Period.between(this.getGeboortedatum(), LocalDate.now());
        return leeftijd.getYears() >= 18;
    }

}
