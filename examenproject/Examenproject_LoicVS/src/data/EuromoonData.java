package data;

import model.Personeel;
import model.Reis;
import model.Treinen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EuromoonData{

    //Treinen
    public static HashMap<Integer, Treinen> treinenToevoegen() {
        HashMap<Integer, Treinen> trein = new HashMap<>();

        trein.put(356, new Treinen(356,(short)20));
        trein.put(373 , new Treinen(373 ,(short)12));
        trein.put(374 , new Treinen(356,(short)14));

        return trein;
    }

    //Personeel
    public static HashMap<String, Personeel>
    personeelToevoegen() {
        HashMap<String, Personeel> personeel = new HashMap<>();
        personeel.put("0123",new Personeel("Jan","Jassen","92022899874", LocalDate.of(1980,5,15),"Bestuurder"));

        personeel.get("0123").addCertificeringen("Veiligheid");
        personeel.get("0123").addCertificeringen("Rijbewijs A3");
        personeel.get("0123").addCertificeringen("Toerisme");

        return personeel;
    }

    //Reizen
    public static List<Reis> reizenToevoegen(HashMap<Integer, Treinen> treinen, HashMap<String, Personeel> personeel) {
        List<Reis> reizen = new ArrayList<>();

        // Voorbeeld: Reizen tussen Brussel en Amsterdam
        Treinen trein356 = treinen.get(356);
        List<Personeel> personeelLijst = new ArrayList<>(personeel.values());

        // Reis 1: Brussel -> Amsterdam, 10:30 - 11:50
        reizen.add(new Reis(
                "Brussel", "Amsterdam",
                LocalDateTime.of(2026, 3, 15, 10, 30),
                LocalDateTime.of(2026, 3, 15, 11, 50),
                trein356, personeelLijst,
                49.99,29.99
        ));

        // Reis 2: Brussel -> Amsterdam, 12:30 - 13:50
        reizen.add(new Reis(
                "Brussel", "Amsterdam",
                LocalDateTime.of(2026, 3, 15, 12, 30),
                LocalDateTime.of(2026, 3, 15, 13, 50),
                trein356, personeelLijst,
                49.99,29.99

        ));

        // Reis 3: Brussel -> Amsterdam, 14:00 - 15:20
        reizen.add(new Reis(
                "Brussel", "Amsterdam",
                LocalDateTime.of(2026, 3, 15, 14, 0),
                LocalDateTime.of(2026, 3, 15, 15, 20),
                trein356, personeelLijst,
                49.99,29.99
        ));


        return reizen;
    }





}
