package service;

import model.Reis;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reisbeheer {
    private List<Reis> reizen;

    public Reisbeheer() {
        this.reizen = new ArrayList<>();
    }

    // Voeg een reis toe
    public void voegReisToe(Reis reis) {
        reizen.add(reis);
    }

    // Haal alle reizen op
    public List<Reis> getAlleReizen() {
        return reizen;
    }

    // Zoek reizen tussen twee stations
    public List<Reis> zoekReizen(String vertrekStation, String aankomstStation) {
        List<Reis> resultaat = new ArrayList<>();
        for (Reis reis : reizen) {
            if (reis.getVertrekStation().equals(vertrekStation) && reis.getAankomstStation().equals(aankomstStation)) {
                resultaat.add(reis);
            }
        }
        return resultaat;
    }

    // Zoek reizen op vertrektijd (bijv. tussen twee datums)
    public List<Reis> zoekReizenOpTijd(LocalDateTime start, LocalDateTime eind) {
        List<Reis> resultaat = new ArrayList<>();
        for (Reis reis : reizen) {
            if (reis.getVertrekTijd().isAfter(start) && reis.getVertrekTijd().isBefore(eind)) {
                resultaat.add(reis);
            }
        }
        return resultaat;
    }
}
