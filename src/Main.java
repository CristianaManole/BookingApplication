import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client clientCurent = null; // clientul care e "logat"

        Client persoana = new Client("Popescu", "Ion", 'M', "1234567890123", "0722222222", TipClient.TURIST);
        persoana.afisare();
        persoana.stergere();
        System.out.println();

        Client client = new Client("Ionescu", "Maria", 'F', "1234567890123", "0733333333", TipClient.CUPLU);
        client.afisare();
        System.out.println();
        ArrayList<Client> clienti = new ArrayList<>();
        clienti.add(persoana);
        clienti.add(client);

        Angajat angajat = new Angajat("Georgescu", "Vasile", 'M', "1234567890123", "0744444444", TipAngajat.Administrator);
        angajat.afisare();

        System.out.println("Esti client sau angajat?(A/C)");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        ArrayList<Destinatie> destinatii = new ArrayList<>(List.of(
                new Destinatie("Spania", "Barcelona"),
                new Destinatie("Franta", "Paris"),
                new Destinatie("Italia", "Roma"),
                new Destinatie("Germania", "Berlin"),
                new Destinatie("Marea Britanie", "Londra"),
                new Destinatie("Grecia", "Atena"),
                new Destinatie("Portugalia", "Lisabona"),
                new Destinatie("Olanda", "Amsterdam"),
                new Destinatie("Austria", "Viena"),
                new Destinatie("Elvetia", "Zurich")
        ));

        ArrayList<Cazare> cazari = new ArrayList<>();
        cazari.add(new Cazare("Hotel Barcelona", "Carrer de Mallorca 1, Barcelona", "4", "Hotel", 10, 2));
        cazari.add(new Cazare("Paris Inn", "Rue de Lyon 12, Paris", "3", "Pensiune", 5, 1));
        cazari.add(new Cazare("Roma Suites", "Via Nazionale 25, Roma", "5", "Hotel", 12, 3));
        cazari.add(new Cazare("Berlin Comfort", "Alexanderplatz 3, Berlin", "4", "Hostel", 8, 0));
        cazari.add(new Cazare("London Stay", "Oxford Street 44, Londra", "3", "Hotel", 6, 1));
        cazari.add(new Cazare("Athens View", "Plaka 10, Atena", "4", "Pensiune", 4, 1));
        cazari.add(new Cazare("Lisbon Palace", "Avenida da Liberdade 50, Lisabona", "5", "Hotel", 9, 2));
        cazari.add(new Cazare("Amsterdam Lodge", "Damrak 15, Amsterdam", "3", "Hostel", 3, 0));
        cazari.add(new Cazare("Vienna Central", "Ringstrasse 20, Viena", "4", "Hotel", 7, 1));
        cazari.add(new Cazare("Zurich Heights", "Bahnhofstrasse 100, Zurich", "5", "Hotel", 11, 2));

        destinatii.get(0).adaugaCazare(cazari.get(0)); // Barcelona
        destinatii.get(1).adaugaCazare(cazari.get(1)); // Paris
        destinatii.get(2).adaugaCazare(cazari.get(2)); // Roma
        destinatii.get(3).adaugaCazare(cazari.get(3)); // Berlin
        destinatii.get(4).adaugaCazare(cazari.get(4)); // Londra
        destinatii.get(5).adaugaCazare(cazari.get(5)); // Atena
        destinatii.get(6).adaugaCazare(cazari.get(6)); // Lisabona
        destinatii.get(7).adaugaCazare(cazari.get(7)); // Amsterdam
        destinatii.get(8).adaugaCazare(cazari.get(8)); // Viena
        destinatii.get(9).adaugaCazare(cazari.get(9)); // Zurich

        for (Cazare cazare : cazari) {
            ArrayList<Camera> camere = new ArrayList<>();

            camere.add(new Camera(TipCamera.SINGLE, 100.0f));
            camere.add(new Camera(TipCamera.DOUBLE, 150.0f));
            camere.add(new Camera(TipCamera.TWIN, 140.0f));
            camere.add(new Camera(TipCamera.TRIPLE, 180.0f));
            camere.add(new Camera(TipCamera.SUITE, 250.0f));

            for (Camera camera : camere) {
                cazare.adaugareCamere(camera);
            }
        }

        for (Cazare cazare : cazari) {
            ArrayList<TipFacilitati> listaFacilitati = new ArrayList<>();
            listaFacilitati.add(TipFacilitati.Parcare);
            listaFacilitati.add(TipFacilitati.Piscina);
            listaFacilitati.add(TipFacilitati.ApresSki);

            Facilitati facilitati = new Facilitati(listaFacilitati);
            cazare.adaugareFacilitati(facilitati);
        }

        if (s.equals("A")) {
            //SE COMPLETEAZA PT ANGAJAT
        }
        // creare cont
        else if (s.equals("C")) {
            System.out.println("Ai cont?(Y/N)");
            s = sc.nextLine();
            if (s.equals("N")) {
                System.out.println("Nume client: ");
                String nume = sc.nextLine();
                System.out.println("Prenume client: ");
                String prenume = sc.nextLine();
                System.out.println("Sex client M/F: ");
                String sex = sc.nextLine();
                System.out.println("CNP client: ");
                String cnp = sc.nextLine();
                System.out.println("Telefon client: ");
                String telefon = sc.nextLine();
                System.out.print("Alege AFACERIST, TURIST, PENSIONAR, FAMILIE, CUPLU, GRUP: ");
                TipClient t = TipClient.valueOf(sc.nextLine().toUpperCase());
                Client c1 = new Client(nume, prenume, sex.charAt(0), cnp, telefon, t);
                clienti.add(c1);
                System.out.println("Cont client efectuat cu succes!");
            } else if (s.equals("Y")) {
                System.out.println("Introdu CNP-ul tău pentru autentificare:");
                String cnp = sc.nextLine();
                for (Client c : clienti) {
                    if (c.getCNP().equals(cnp)) {
                        clientCurent = c;
                        System.out.println("Autentificare reușită.");
                        break;
                    }
                }

                // vizualizare destinatii
                System.out.println("Vrei sa vizualizezi destinatiile disponibile? (Y/N)");
                s = sc.nextLine();
                if (s.equals("Y")) {
                    System.out.println("Destinatii disponibile");
                    for (Destinatie d : destinatii) {
                        d.afisareDestinatii();
                    }
                }

                System.out.println("Vezi hotelurile noastre! :)");

                // vizualizare hoteluri in functie de destinatie
                System.out.println("Introdu tara unde vrei sa mergi");
                String t = sc.nextLine();
                System.out.println("Introdu orasul");
                String oras = sc.nextLine();
                for (Destinatie d : destinatii) {
                    if (d.getTara().equals(t) && d.getOras().equals(oras)) {
                        d.afisare();

                    }
                }

                // rezervare camera + plata
                System.out.println("Vrei sa rezervi o camera? (Y/N)");
                s = sc.nextLine();
                if (s.equals("Y")) {
                    clientCurent.rezervareCamera();
                    clientCurent.efectuarePlata();
                    System.out.println("Rezervare realizata cu succes!");
                }
                // vizualizare rezervari
                System.out.println("Vrei sa vezi rezervarile tale? (Y/N)");
                s = sc.nextLine();
                if (s.equals("Y")) {
                    clientCurent.afisare();
                }

                // Scrie feedback
                System.out.println("Vrei sa scrii un feedback? (Y/N)");
                s = sc.nextLine();
                if (s.equals("Y")) {
                    System.out.println("Scrie feedback-ul tau:");
                    String feedback = sc.nextLine();
                    System.out.println("Feedback-ul a fost adaugat cu succes: " + feedback);
                }

                // Rezerva transport - de modificat
                /*System.out.println("Vrei sa rezervi transport? (Y/N)");
                s = sc.nextLine();
                if (s.equals("Y")) {
                    System.out.println("Introdu data plecarii:");
                    String dataPlecare = sc.nextLine();
                    System.out.println("Introdu data intoarcerii:");
                    String dataIntoarcere = sc.nextLine();
                    System.out.println("Introdu tipul de transport (autocar/tren/avion):");
                    String tipTransport = sc.nextLine();
                    System.out.println("Transportul a fost rezervat cu succes!");
                }*/

                System.out.println("Vrei să modifici o rezervare existentă? (Y/N)");
                s = sc.nextLine();

                if (s.equalsIgnoreCase("Y")) {
                    if (clientCurent.getRezervari().isEmpty()) {
                        System.out.println("Nu ai nicio rezervare.");
                    } else {
                        System.out.println("Alege rezervarea pe care vrei să o modifici:");
                        ArrayList<Rezervare> rezervariClient = clientCurent.getRezervari();

                        // afișează rezervările numerotate
                        for (int i = 0; i < rezervariClient.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            rezervariClient.get(i).afisare();
                        }

                        System.out.println("Introdu numărul rezervării:");
                        int index = sc.nextInt();
                        sc.nextLine(); // consumă \n

                        if (index >= 1 && index <= rezervariClient.size()) {
                            Rezervare deModificat = rezervariClient.get(index - 1);
                            deModificat.actualizareRezervare();
                            System.out.println("Rezervarea a fost actualizată.");
                        } else {
                            System.out.println("Index invalid.");
                        }
                    }
                }

                //Anuleaza rezervare
                System.out.println("Vrei sa anulezi o rezervare? (Y/N)");
                s = sc.nextLine();
                if (s.equalsIgnoreCase("Y")) {
                    if (clientCurent.getRezervari().isEmpty()) {
                        System.out.println("Nu ai nicio rezervare.");
                    } else {
                        System.out.println("Alege rezervarea pe care vrei să o anulezi:");
                        ArrayList<Rezervare> rezervariClient = clientCurent.getRezervari();

                        // afișează rezervările numerotate
                        for (int i = 0; i < rezervariClient.size(); i++) {
                            System.out.print((i + 1) + ". ");
                            rezervariClient.get(i).afisare();
                        }

                        System.out.println("Introdu numărul rezervării:");
                        int index = sc.nextInt();
                        sc.nextLine(); // consumă \n

                        if (index >= 1 && index <= rezervariClient.size()) {
                            Rezervare deAnulat = rezervariClient.get(index - 1);
                            clientCurent.getRezervari().remove(deAnulat);
                            System.out.println("Rezervarea a fost anulată. Vei primii banii înapoi în termen de 3 zile lucrătoare.");
                        } else {
                            System.out.println("Index invalid.");
                        }
                    }
                }





            }

        }
    }
}