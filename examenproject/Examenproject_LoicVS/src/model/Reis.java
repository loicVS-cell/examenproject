package model;

import model.Personeel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reis {
    private String vertrekStation;
    private String aankomstStation;
    private LocalDateTime vertrekTijd;
    private LocalDateTime aankomTijd;
    private Treinen trein;
    private List<Personeel> personeel;
    private double eersteKlassePrijs;
    private double tweedeKlassePrijs;

    // Constructor
    public Reis(String vertrekStation, String aankomstStation, LocalDateTime vertrekTijd, LocalDateTime aankomTijd, Treinen trein, List<Personeel> personeel, double eersteKlassePrijs, double tweedeKlassePrijs) {
        this.vertrekStation = vertrekStation;
        this.aankomstStation = aankomstStation;
        this.vertrekTijd = vertrekTijd;
        this.aankomTijd = aankomTijd;
        this.trein = trein;
        this.personeel = new ArrayList<>();
        this.eersteKlassePrijs = eersteKlassePrijs;
        this.tweedeKlassePrijs = tweedeKlassePrijs;
    }

    // Getters en setters
    public String getVertrekStation() {
        return vertrekStation;
    }

    public void setVertrekStation(String vertrekStation) {
        this.vertrekStation = vertrekStation;
    }

    public String getAankomstStation() {
        return aankomstStation;
    }

    public void setAankomstStation(String aankomstStation) {
        this.aankomstStation = aankomstStation;
    }

    public LocalDateTime getAankomTijd() {
        return aankomTijd;
    }

    public void setAankomTijd(LocalDateTime aankomTijd) {
        this.aankomTijd = aankomTijd;
    }

    public LocalDateTime getVertrekTijd() {
        return vertrekTijd;
    }

    public void setVertrekTijd(LocalDateTime vertrekTijd) {
        this.vertrekTijd = vertrekTijd;
    }

    public Treinen getTrein() {
        return trein;
    }

    public void setTrein(Treinen trein) {
        this.trein = trein;
    }

    public List<Personeel> getPersoneel() {
        return personeel;
    }

    // Voeg een personeelslid toe
    public void addPersoneel(Personeel p) {
        this.personeel.add(p);
    }

    // Verwijder een personeelslid
    public void removePersoneel(Personeel p) {
        this.personeel.remove(p);
    }

    public double getEersteKlassePrijs() {
        return eersteKlassePrijs;
    }

    public void setEersteKlassePrijs(double eersteKlassePrijs) {
        this.eersteKlassePrijs = eersteKlassePrijs;
    }

    public double getTweedeKlassePrijs() {
        return tweedeKlassePrijs;
    }

    public void setTweedeKlassePrijs(double tweedeKlassePrijs) {
        this.tweedeKlassePrijs = tweedeKlassePrijs;
    }

    // Controleer of de reis voldoende personeel heeft
    public boolean heeftVoldoendePersoneel() {
        int bestuurders = 0;
        int stewards = 0;
        for (Personeel p : personeel) {
            if (p.getRol().equals("Bestuurder")) {
                bestuurders++;
            } else if (p.getRol().equals("Steward")) {
                stewards++;
            }
        }
        return bestuurders >= 1 && stewards >= 3;
    }

}
