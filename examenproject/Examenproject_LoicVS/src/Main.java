import data.EuromoonData;
import model.Passagier;
import model.Personeel;
import model.Reis;
import service.Reisbeheer;
import model.Ticket;
import model.Treinen;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Laad voorbeelddata
        HashMap<Integer, Treinen> treinen = EuromoonData.treinenToevoegen();
        HashMap<String, Personeel> personeel = EuromoonData.personeelToevoegen();
        List<Reis> reizen = EuromoonData.reizenToevoegen(treinen, personeel);
        Reisbeheer reisbeheer = new Reisbeheer();
        reizen.forEach(reisbeheer::voegReisToe);

        boolean doorgaan = true;

        while (doorgaan) {
            try {
                // Haal unieke vertrekstations op
                Set<String> vertrekStations = reizen.stream()
                        .map(Reis::getVertrekStation)
                        .collect(Collectors.toSet());

                // Toon menu voor vertrekstation
                System.out.println("\nBeschikbare vertrekstations:");
                vertrekStations.forEach(System.out::println);
                System.out.print("Kies een vertrekstation: ");
                String gekozenVertrek = scanner.nextLine();

                // Haal unieke aankomststations op voor het gekozen vertrekstation
                Set<String> aankomstStations = reizen.stream()
                        .filter(reis -> reis.getVertrekStation().equals(gekozenVertrek))
                        .map(Reis::getAankomstStation)
                        .collect(Collectors.toSet());

                // Toon menu voor aankomststation
                System.out.println("\nBeschikbare aankomststations:");
                aankomstStations.forEach(System.out::println);
                System.out.print("Kies een aankomststation: ");
                String gekozenAankomst = scanner.nextLine();

                // Haal beschikbare reizen op voor de gekozen route
                List<Reis> beschikbareReizen = reisbeheer.zoekReizen(gekozenVertrek, gekozenAankomst);

                // Filter reizen die na het huidige tijdstip vertrekken
                LocalDateTime nu = LocalDateTime.now();
                List<Reis> toekomstigeReizen = beschikbareReizen.stream()
                        .filter(reis -> reis.getVertrekTijd().isAfter(nu))
                        .collect(Collectors.toList());

                // Toon beschikbare tijden
                System.out.println("\nBeschikbare reizen:");
                for (int i = 0; i < toekomstigeReizen.size(); i++) {
                    Reis reis = toekomstigeReizen.get(i);
                    System.out.printf(
                            "%d. %s - %s (Vertrek: %s, Aankomst: %s)%n",
                            i + 1,
                            reis.getVertrekStation(),
                            reis.getAankomstStation(),
                            reis.getVertrekTijd().toLocalTime(),
                            reis.getAankomTijd().toLocalTime()
                    );
                }

                // Laat de gebruiker een reis kiezen
                System.out.print("\nKies een reis (nummer): ");
                int reisKeuze = Integer.parseInt(scanner.nextLine());

                if (reisKeuze > 0 && reisKeuze <= toekomstigeReizen.size()) {
                    Reis gekozenReis = toekomstigeReizen.get(reisKeuze - 1);
                    System.out.printf(
                            "\nJe hebt gekozen voor: %s -> %s, vertrek om %s, aankomst om %s%n",
                            gekozenReis.getVertrekStation(),
                            gekozenReis.getAankomstStation(),
                            gekozenReis.getVertrekTijd().toLocalTime(),
                            gekozenReis.getAankomTijd().toLocalTime()
                    );

                    // Vraag om klassekeuze
                    System.out.print("\nKies een klasse (1 voor Eerste Klasse, 2 voor Tweede Klasse): ");
                    int klasseKeuze = Integer.parseInt(scanner.nextLine());

                    if (klasseKeuze != 1 && klasseKeuze != 2) {
                        System.out.println("\nOngeldige klassekeuze. Voer 1 of 2 in.");
                        continue;
                    }

                    String klasse = klasseKeuze == 1 ? "Eerste" : "Tweede";

                    // Vraag om aantal plaatsen
                    System.out.print("Hoeveel plaatsen wil je boeken? (max. 80): ");
                    int aantalPlaatsen = Integer.parseInt(scanner.nextLine());

                    if (aantalPlaatsen <= 0 || aantalPlaatsen > 80) {
                        System.out.println("\nOngeldig aantal plaatsen. Voer een getal tussen 1 en 80 in.");
                        continue;
                    }

                    // Bereken totale prijs
                    double prijsPerPlaats = (klasseKeuze == 1) ? gekozenReis.getEersteKlassePrijs() : gekozenReis.getTweedeKlassePrijs();
                    double totalePrijs = prijsPerPlaats * aantalPlaatsen;

                    // Vraag passagiersgegevens
                    System.out.println("\nVul je persoonlijke gegevens in:");
                    System.out.print("Voer je voornaam in: ");
                    String voornaam = scanner.nextLine();

                    System.out.print("Voer je achternaam in: ");
                    String achternaam = scanner.nextLine();

                    String rijksregisternummer;
                    while (true) {
                        System.out.print("Voer je rijksregisternummer in (11 cijfers): ");
                        rijksregisternummer = scanner.nextLine();
                        if (rijksregisternummer.length() == 11 && rijksregisternummer.matches("\\d+")) {
                            break;
                        } else {
                            System.out.println("Ongeldig rijksregisternummer. Het moet exact 11 cijfers bevatten.");
                        }
                    }

                    LocalDate geboortedatum;
                    while (true) {
                        try {
                            System.out.print("Voer je geboortedatum in (DD/MM/YYYY): ");
                            String datumInput = scanner.nextLine();
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                            geboortedatum = LocalDate.parse(datumInput, formatter);
                            break;
                        } catch (Exception e) {
                            System.out.println("Ongeldige datum. Voer de datum in als DD/MM/YYYY.");
                        }
                    }

                    Passagier passagier = new Passagier(voornaam, achternaam, rijksregisternummer, geboortedatum);

                    // Controleer of de passagier ouder is dan 18
                    if (!passagier.IsOuderDan18()) {
                        System.out.println("\nJe bent jonger dan 18 jaar. Je hebt geen pincode nodig.");
                    } else {
                        String pincode;
                        while (true) {
                            System.out.print("Voer je pincode in (max. 4 cijfers): ");
                            pincode = scanner.nextLine();
                            if (pincode.length() <= 4 && pincode.matches("\\d+")) {
                                break;
                            } else {
                                System.out.println("Ongeldige pincode. Voer max. 4 cijfers in.");
                            }
                        }
                        System.out.println("\nBetaling verwerkt met pincode.");
                    }

                    // Toon reis- en prijsinformatie
                    System.out.printf(
                            "\nJe hebt gekozen voor: %s -> %s, vertrek om %s, aankomst om %s%n",
                            gekozenReis.getVertrekStation(),
                            gekozenReis.getAankomstStation(),
                            gekozenReis.getVertrekTijd().toLocalTime(),
                            gekozenReis.getAankomTijd().toLocalTime()
                    );
                    System.out.printf("Klasse: %s, Aantal plaatsen: %d, Totale prijs: €%.2f%n",
                            klasse,
                            aantalPlaatsen,
                            totalePrijs);

                    // Bevestigingsvraag
                    System.out.print("\nBevestig je boeking (Y/N): ");
                    String bevestiging = scanner.nextLine().trim().toUpperCase();

                    while (!bevestiging.equals("Y") && !bevestiging.equals("N")) {
                        System.out.print("\nOngeldige invoer. Voer 'Y' in om te bevestigen of 'N' om te annuleren: ");
                        bevestiging = scanner.nextLine().trim().toUpperCase();
                    }

                    if (bevestiging.equals("Y")) {
                        // Maak een ticket aan
                        Ticket ticket = new Ticket(passagier, gekozenReis, klasse, aantalPlaatsen, totalePrijs);

                        // Toon ticketinformatie
                        System.out.println("\n" + ticket.genereerTicketInformatie());
                        System.out.println("\nBoeking bevestigd! Bedankt voor je aankoop.");
                        doorgaan = false;
                    } else {
                        System.out.println("\nBoeking geannuleerd.");
                        doorgaan = vraagOmDoorTeGaan(scanner, "Wil je een nieuwe reis zoeken? (Y/N)");
                    }
                } else {
                    System.out.println("\nDeze invoer is ongeldig.");
                    doorgaan = vraagOmDoorTeGaan(scanner, "Wil je het opnieuw proberen? (Y/N)");
                }
            } catch (NumberFormatException e) {
                System.out.println("\nVoer een geldig nummer in.");
                doorgaan = vraagOmDoorTeGaan(scanner, "Wil je het opnieuw proberen? (Y/N)");
            } catch (Exception e) {
                System.out.println("\nEr is een onverwachte fout opgetreden: " + e.getMessage());
                doorgaan = vraagOmDoorTeGaan(scanner, "Wil je het opnieuw proberen? (Y/N)");
            }
        }

        System.out.println("\nBedankt voor het gebruik van Euromoon. Tot ziens!");
        scanner.close();
    }

    // Hulpmethode om te vragen of de gebruiker door wil gaan
    private static boolean vraagOmDoorTeGaan(Scanner scanner, String vraag) {
        System.out.print("\n" + vraag + " ");
        String antwoord = scanner.nextLine().trim().toUpperCase();

        while (!antwoord.equals("Y") && !antwoord.equals("N")) {
            System.out.print("\nOngeldige invoer. Voer 'Y' in om door te gaan of 'N' om te stoppen: ");
            antwoord = scanner.nextLine().trim().toUpperCase();
        }

        return antwoord.equals("Y");
    }
}