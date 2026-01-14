package model;

public class Ticket {
    private Passagier passagier;
    private Reis reis;
    private String klasse;
    private int aantalPlaatsen;
    private double totalePrijs;

    public Ticket(Passagier passagier, Reis reis, String klasse, int aantalPlaatsen, double totalePrijs) {
        this.passagier = passagier;
        this.reis = reis;
        this.klasse = klasse;
        this.aantalPlaatsen = aantalPlaatsen;
        this.totalePrijs = totalePrijs;
    }

    // Methode om ticketinformatie als tekst te genereren
    public String genereerTicketInformatie() {
        return String.format(
                "Ticketinformatie:\n" +
                        "Passagier: %s %s\n" +
                        "Reis: %s -> %s\n" +
                        "Vertrek: %s, Aankomst: %s\n" +
                        "Klasse: %s\n" +
                        "Aantal plaatsen: %d\n" +
                        "Totale prijs: €%.2f",
                passagier.getNaam(),
                passagier.getAchternaam(),
                reis.getVertrekStation(),
                reis.getAankomstStation(),
                reis.getVertrekTijd().toLocalTime(),
                reis.getAankomTijd().toLocalTime(),
                klasse,
                aantalPlaatsen,
                totalePrijs
        );
    }

    // Getters
    public Passagier getPassagier() {
        return passagier;
    }

    public Reis getReis() {
        return reis;
    }

    public String getKlasse() {
        return klasse;
    }

    public int getAantalPlaatsen() {
        return aantalPlaatsen;
    }

    public double getTotalePrijs() {
        return totalePrijs;
    }
}
