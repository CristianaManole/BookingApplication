import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Client> clienti = new ArrayList<>();
        Client persoana = new Client("Popescu", "Ion", 'M', "1234567890123", "0722222222", TipClient.TURIST);
        Client client = new Client("Ionescu", "Maria", 'F', "1234567890123", "0733333333", TipClient.CUPLU);
        clienti.add(persoana);
        clienti.add(client);

        Angajat angajat = new Angajat("Georgescu", "Vasile", 'M', "1234567890123", "0744444444", TipAngajat.Administrator);

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

        for (int i = 0; i < destinatii.size(); i++) {
            destinatii.get(i).adaugaCazare(cazari.get(i));
        }

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

        boolean aplicatieDeschisa = true;
        while (aplicatieDeschisa) {
            System.out.println("Esti client sau angajat? (C/A) Sau tasteaza X pentru a iesi din aplicatie.");
            String s = sc.nextLine();

            if (s.equalsIgnoreCase("X")) {
                System.out.println("Aplicatia a fost inchisa. La revedere!");
                aplicatieDeschisa = false;
                break;
            }

            if (s.equals("A")) {
                // SE COMPLETEAZA PT ANGAJAT
            } else if (s.equals("C")) {
                Client clientCurent = null;
                System.out.print("Ai cont? (Y/N)");
                s = sc.nextLine();
                if (s.equals("N")) {
                    System.out.print("Nume client: ");
                    String nume = sc.nextLine();
                    System.out.print("Prenume client: ");
                    String prenume = sc.nextLine();
                    System.out.print("Sex client M/F: ");
                    String sex = sc.nextLine();
                    System.out.print("CNP client: ");
                    String cnp = sc.nextLine();
                    System.out.print("Telefon client: ");
                    String telefon = sc.nextLine();
                    System.out.print("Alege AFACERIST, TURIST, PENSIONAR, FAMILIE, CUPLU, GRUP: ");
                    TipClient t = TipClient.valueOf(sc.nextLine().toUpperCase());
                    clientCurent = new Client(nume, prenume, sex.charAt(0), cnp, telefon, t);
                    clienti.add(clientCurent);
                    System.out.println("Cont client efectuat cu succes!");
                } else if (s.equals("Y")) {
                    System.out.print("Introdu CNP-ul tău pentru autentificare: ");
                    String cnp = sc.nextLine();
                    for (Client c : clienti) {
                        if (c.getCNP().equals(cnp)) {
                            clientCurent = c;
                            System.out.println("Autentificare reușită.");
                            break;
                        }
                    }
                }

                if (clientCurent == null) {
                    System.out.println("Eroare: niciun client conectat.");
                    continue;
                }

                boolean ruleaza = true;
                while (ruleaza) {
                    System.out.println("\n--- MENIU PRINCIPAL ---");
                    System.out.println("1. Vizualizează destinațiile disponibile");
                    System.out.println("2. Caută hoteluri");
                    System.out.println("3. Rezervă cameră");
                    System.out.println("4. Vezi rezervările tale");
                    System.out.println("5. Modifică o rezervare");
                    System.out.println("6. Anulează o rezervare");
                    System.out.println("7. Scrie feedback");
                    System.out.println("8. Log out");
                    System.out.println("0. Ieșire aplicație");
                    System.out.print("Alege o opțiune: ");
                    String opt = sc.nextLine();

                    switch (opt) {
                        case "1":
                            for (Destinatie d : destinatii) {
                                d.afisareDestinatii();
                            }
                            break;
                        case "2":
                            System.out.print("Introdu țara: ");
                            String tara = sc.nextLine();
                            System.out.print("Introdu orașul: ");
                            String oras = sc.nextLine();
                            for (Destinatie d : destinatii) {
                                if (d.getTara().equalsIgnoreCase(tara) && d.getOras().equalsIgnoreCase(oras)) {
                                    d.afisare();
                                }
                            }
                            break;
                        case "3":
                            clientCurent.rezervareCamera();
                            boolean plataReusita = false;
                            while (!plataReusita) {
                                System.out.println("Efectuare plata");
                                System.out.print("Introdu numele de pe card: ");
                                String nume = sc.nextLine();
                                System.out.print("Introdu numar card: ");
                                String nrCard = sc.nextLine();
                                System.out.print("Introdu data expirare: ");
                                String data = sc.nextLine();
                                System.out.print("Introdu CCV: ");
                                int ccv = sc.nextInt();
                                sc.nextLine();

                                if (clientCurent.efectuarePlata(nume, nrCard, data, ccv)) {
                                    System.out.println("Plata a fost realizată cu succes.");
                                    plataReusita = true;
                                } else {
                                    System.out.println("Plata nu a fost efectuata. Introdu iar datele.\n");
                                }
                            }
                            break;
                        case "4":
                            clientCurent.afisare();
                            break;
                        case "5":
                            ArrayList<Rezervare> rezervari = clientCurent.getRezervari();
                            if (rezervari.isEmpty()) {
                                System.out.println("Nu ai rezervări.");
                                break;
                            }
                            for (int i = 0; i < rezervari.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                rezervari.get(i).afisare();
                            }
                            System.out.print("Alege rezervarea: ");
                            int idx = Integer.parseInt(sc.nextLine());
                            if (idx >= 1 && idx <= rezervari.size()) {
                                rezervari.get(idx - 1).actualizareRezervare();
                            } else {
                                System.out.println("Index invalid.");
                            }
                            break;
                        case "6":
                            rezervari = clientCurent.getRezervari();
                            if (rezervari.isEmpty()) {
                                System.out.println("Nu ai rezervări.");
                                break;
                            }
                            for (int i = 0; i < rezervari.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                rezervari.get(i).afisare();
                            }
                            System.out.print("Alege rezervarea: ");
                            int index = Integer.parseInt(sc.nextLine());
                            if (index >= 1 && index <= rezervari.size()) {
                                rezervari.remove(index - 1);
                                System.out.println("Rezervarea a fost anulată.");
                            } else {
                                System.out.println("Index invalid.");
                            }
                            break;
                        case "7":
                            System.out.print("Scrie feedback-ul tău: ");
                            String feedback = sc.nextLine();
                            System.out.println("Feedback salvat: " + feedback);
                            break;
                        case "8":
                            System.out.println("Te-ai delogat cu succes.");
                            ruleaza = false;
                            break;
                        case "0":
                            System.out.println("Aplicatia a fost inchisa. La revedere!");
                            ruleaza = false;
                            aplicatieDeschisa = false;
                            break;
                        default:
                            System.out.println("Opțiune invalidă.");
                    }
                }
            }
        }
    }
}