package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Personeel extends Persoon {
    private String rol;
    private List<String> certificeringen;

    public Personeel(String naam,String achternaam, String rijksregisternummer, LocalDate geboortedatum,String rol) {
        super(naam, achternaam, rijksregisternummer, geboortedatum);
        this.rol = rol;
        this.certificeringen = new ArrayList<>();
    }

    //Getter en setters
    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public List<String> getCertificeringen() {
        return certificeringen;
    }


    //Certificering toevoegen aan Personeel
    public void addCertificeringen(String certificeringen) {
        this.certificeringen.add(certificeringen);
    }

    public void removeCertificeringen(String certificeringen) {
        this.certificeringen.remove(certificeringen);
    }


}
