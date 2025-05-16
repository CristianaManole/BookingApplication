package clase;
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
        ArrayList<ArrayList<Camera>> camereCazare = new ArrayList<>();

        for (int i = 0; i < cazari.size(); i++) {
            ArrayList<Camera> camere = new ArrayList<>();
            camere.add(new Camera(TipCamera.SINGLE, 100.0f));
            camere.add(new Camera(TipCamera.DOUBLE, 150.0f));
            camere.add(new Camera(TipCamera.TWIN,   140.0f));
            camere.add(new Camera(TipCamera.TRIPLE, 180.0f));
            camere.add(new Camera(TipCamera.SUITE,  250.0f));

            camereCazare.add(camere);
            for (Camera c : camere) {             // fără lambda, deci fără restricţie
                cazari.get(i).adaugareCamere(c);
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
            System.out.print("Esti client sau angajat? (C/A) Sau tasteaza X pentru a iesi din aplicatie.");
            String s = sc.nextLine();

            if (s.equalsIgnoreCase("X")) {
                System.out.println("Aplicatia a fost inchisa. La revedere!");
                aplicatieDeschisa = false;
                break;
            }

            if (s.equals("A")) {
                /* autentificare */
                Angajat angajatCurent = null;
                System.out.print("Ai cont? (Y/N) ");
                if (sc.nextLine().equalsIgnoreCase("Y")) {
                    System.out.print("CNP: ");
                    String cnp = sc.nextLine();
                    if (angajat.getCNP().equals(cnp)) angajatCurent = angajat;
                    if (angajatCurent == null) {
                        System.out.println("CNP incorect.");
                        continue;
                    }
                } else {
                    System.out.print("Nume: ");
                    String n = sc.nextLine();
                    System.out.print("Prenume: ");
                    String p = sc.nextLine();
                    System.out.print("Sex M/F: ");
                    char sx = sc.nextLine().charAt(0);
                    System.out.print("CNP: ");
                    String cnp = sc.nextLine();
                    System.out.print("Telefon: ");
                    String tel = sc.nextLine();
                    System.out.print("Rol (Administrator/ManagerReceptie/AgentOferte): ");
                    TipAngajat rol;
                    try {
                        rol = TipAngajat.valueOf(sc.nextLine());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Rol invalid.");
                        continue;
                    }
                    angajatCurent = new Angajat(n, p, sx, cnp, tel, rol);
                }
                boolean ruleazaA = true;
                while (ruleazaA) {
                    System.out.println("\nMENIU ANGAJAT");
                    System.out.println("1  Vezi destinatii");
                    System.out.println("2  Adauga destinatie");
                    System.out.println("3  Sterge destinatie");
                    System.out.println("4  Editeaza destinatie");
                    System.out.println("5  Adauga cazare");
                    System.out.println("6  Sterge cazare (nume)");
                    System.out.println("7  Editeaza cazare (nume)");
                    System.out.println("8  Adauga camera");
                    System.out.println("9  Sterge camera");
                    System.out.println("10 Editeaza camera");
                    System.out.println("11 Adauga facilitati");
                    System.out.println("12 Adauga masina");
                    System.out.println("13 Achizitioneaza masina");
                    System.out.println("14 Achizitioneaza bilet avion");
                    System.out.println("15 Valideaza vacanta");
                    System.out.println("16 Modifica detalii vacanta");
                    System.out.println("17 Log out");
                    System.out.print("Alege: ");
                    String optA = sc.nextLine();

                    switch (optA) {
                        /* DESTINATII */
                        case "1":
                            destinatii.forEach(Destinatie::afisareDestinatii);
                            break;
                        case "2":
                            System.out.print("Introdu Tara: ");
                            String nt = sc.nextLine();
                            System.out.print("Introdu Oras: ");
                            String no = sc.nextLine();
                            Destinatie dn = new Destinatie(nt, no);
                            destinatii.add(dn);
                            angajatCurent.AdaugaDestinatie(dn);
                            break;
                        case "3":
                            System.out.println("Destinatii existente:");
                            destinatii.forEach(Destinatie::afisareDestinatii);
                            System.out.print("Ce destinatie doresti sa stergi?: ");
                            String orasDel = sc.nextLine();
                            Destinatie dDel = destinatii.stream()
                                    .filter(d -> d.getOras().equalsIgnoreCase(orasDel))
                                    .findFirst().orElse(null);
                            if (dDel != null) {
                                angajatCurent.StergeDestinatie(dDel);
                                destinatii.remove(dDel);
                            } else System.out.println("Nu exista.");
                            break;
                        case "4":     // Editeaza destinatie
                            System.out.println("Destinatii existente:");
                            destinatii.forEach(Destinatie::afisareDestinatii);

                            System.out.print("Ce oras alegi sa editezi?: ");
                            String orasEd = sc.nextLine().trim();

                            Destinatie dEd = destinatii.stream()
                                    .filter(d -> d.getOras().trim()
                                            .equalsIgnoreCase(orasEd))
                                    .findFirst()
                                    .orElse(null);

                            if (dEd != null)
                                angajatCurent.EditeazaDestinatie(dEd);
                            else
                                System.out.println("Nu exista destinatia.");
                            break;

                        case "5":   // Adaugă cazare
                            System.out.println("Destinaţii disponibile:");
                            destinatii.forEach(Destinatie::afisareDestinatii);

                            System.out.print("Oras destinaţie: ");
                            String orasCazNou = sc.nextLine();
                            Destinatie destSelect = destinatii.stream()
                                    .filter(d -> d.getOras().equalsIgnoreCase(orasCazNou))
                                    .findFirst().orElse(null);
                            if (destSelect == null) {
                                System.out.println("Destinaţie inexistentă.");
                                break;
                            }

                            System.out.print("Nume cazare: ");
                            String numeC = sc.nextLine();
                            System.out.print("Adresa: ");
                            String adr = sc.nextLine();
                            System.out.print("Stele: ");
                            String st = sc.nextLine();
                            System.out.print("Tip: ");
                            String tip = sc.nextLine();
                            System.out.print("Nr maşini: ");
                            int nrM = Integer.parseInt(sc.nextLine());
                            System.out.print("Nr bilete avion: ");
                            int nrB = Integer.parseInt(sc.nextLine());

                            Cazare cazNoua = new Cazare(numeC, adr, st, tip, nrM, nrB);
                            angajatCurent.AdaugaCazare(destSelect, cazNoua);
                            cazari.add(cazNoua);
                            camereCazare.add(new ArrayList<>());                 // vector paralel
                            break;

                        case "6":   // Şterge cazare
                            System.out.println("Cazări existente:");
                            cazari.forEach(Cazare::afisare);

                            System.out.print("Nume cazare: ");
                            String numeSterg = sc.nextLine();
                            Cazare czDeSters = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeSterg))
                                    .findFirst()
                                    .orElse(null);
                            if (czDeSters == null) { System.out.println("Nu există cazarea."); break; }

                            // cauți destinația care conține acea cazare
                            Destinatie dPar = destinatii.stream()
                                    .filter(d -> d.cazari.contains(czDeSters))
                                    .findFirst()
                                    .orElse(null);

                            if (dPar != null)
                                angajatCurent.StergeCazare(dPar, czDeSters);

                            cazari.remove(czDeSters);
                            camereCazare.removeIf(list -> list == null || list.isEmpty()); // dacă ții paralelism
                            System.out.println("Cazarea a fost ștearsă.");
                            break;

                        case "7":   // Editează cazare
                            System.out.println("Cazări existente:");
                            cazari.forEach(Cazare::afisare);

                            System.out.print("Nume cazare: ");
                            String numeEdit = sc.nextLine();
                            Cazare czEdit = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeEdit))
                                    .findFirst().orElse(null);
                            if (czEdit != null) angajatCurent.EditeazaCazare(czEdit);
                            else System.out.println("Nu există cazarea.");
                            break;

                        case "8":   // Adaugă cameră
                            System.out.println("Cazări existente:");
                            cazari.forEach(Cazare::afisare);

                            System.out.print("Nume cazare: ");
                            String numeAddCam = sc.nextLine();
                            int idxAddCam = -1;
                            for (int i = 0; i < cazari.size(); i++)
                                if (cazari.get(i).getNume().equalsIgnoreCase(numeAddCam)) {
                                    idxAddCam = i;
                                    break;
                                }

                            if (idxAddCam == -1) {
                                System.out.println("Nu există.");
                                break;
                            }

                            System.out.print("Tip (SINGLE/DOUBLE/TWIN/TRIPLE/SUITE): ");
                            TipCamera tpCam = TipCamera.valueOf(sc.nextLine().toUpperCase());
                            System.out.print("Preţ: ");
                            float prCam = Float.parseFloat(sc.nextLine());

                            Camera camNoua = new Camera(tpCam, prCam);
                            camereCazare.get(idxAddCam).add(camNoua);
                            cazari.get(idxAddCam).adaugareCamere(camNoua);
                            System.out.println("Camera adăugată.");
                            break;

                        case "9":   // Şterge cameră
                            System.out.println("Cazări existente:");
                            cazari.forEach(c -> System.out.println(" • " + c.getNume()));

                            System.out.print("Nume cazare: ");
                            String numeDelCam = sc.nextLine();
                            int idxDelCam = -1;
                            for (int i = 0; i < cazari.size(); i++)
                                if (cazari.get(i).getNume().equalsIgnoreCase(numeDelCam)) {
                                    idxDelCam = i;
                                    break;
                                }

                            if (idxDelCam == -1) {
                                System.out.println("Nu există.");
                                break;
                            }

                            ArrayList<Camera> listDel = camereCazare.get(idxDelCam);
                            if (listDel.isEmpty()) {
                                System.out.println("Nu are camere.");
                                break;
                            }

                            for (int i = 0; i < listDel.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                listDel.get(i).afisare();
                            }
                            System.out.print("Index camera: ");
                            int iDel = Integer.parseInt(sc.nextLine()) - 1;
                            if (iDel >= 0 && iDel < listDel.size()) {
                                Camera cDel = listDel.remove(iDel);
                                cazari.get(idxDelCam).stergereCamera(cDel);
                                System.out.println("Camera ştearsă.");
                            } else System.out.println("Index invalid.");
                            break;

                        case "10":  // Editează cameră
                            System.out.println("Cazări existente:");
                            cazari.forEach(c -> System.out.println(" • " + c.getNume()));

                            System.out.print("Nume cazare: ");
                            String numeEdCam = sc.nextLine();
                            int idxEdCam = -1;
                            for (int i = 0; i < cazari.size(); i++)
                                if (cazari.get(i).getNume().equalsIgnoreCase(numeEdCam)) {
                                    idxEdCam = i;
                                    break;
                                }

                            if (idxEdCam == -1) {
                                System.out.println("Nu există.");
                                break;
                            }

                            ArrayList<Camera> listEd = camereCazare.get(idxEdCam);
                            if (listEd.isEmpty()) {
                                System.out.println("Nu are camere.");
                                break;
                            }

                            for (int i = 0; i < listEd.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                listEd.get(i).afisare();
                            }
                            System.out.print("Index camera: ");
                            int iEd = Integer.parseInt(sc.nextLine()) - 1;
                            if (iEd >= 0 && iEd < listEd.size())
                                cazari.get(idxEdCam).editeazaCamera(listEd.get(iEd));
                            else
                                System.out.println("Index invalid.");
                            break;
                        case "11":      // Adaugă facilități
                            System.out.println("Cazări existente:");
                            cazari.forEach(Cazare::afisare);

                            System.out.print("Nume cazare: ");
                            String numeFac = sc.nextLine();
                            Cazare czFac = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeFac))
                                    .findFirst().orElse(null);

                            if (czFac == null) {
                                System.out.println("Nu există.");
                                break;
                            }

                            System.out.print("Facilități (Piscina,Spa,Parcare,…) separate prin virgulă: ");
                            String[] tok = sc.nextLine().split(",");
                            ArrayList<TipFacilitati> lf = new ArrayList<>();
                            for (String f : tok)
                                try {
                                    lf.add(TipFacilitati.valueOf(f.trim()));
                                } catch (IllegalArgumentException ignored) {
                                }
                            czFac.adaugareFacilitati(new Facilitati(lf));
                            break;
                        case "12":      // Adaugă maşină
                            System.out.println("Cazări existente:");
                            cazari.forEach(c -> System.out.println(" • " + c.getNume()));

                            System.out.print("Nume cazare: ");
                            String numeMasAdd = sc.nextLine();
                            Cazare czMasAdd = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeMasAdd))
                                    .findFirst().orElse(null);

                            if (czMasAdd == null) {
                                System.out.println("Nu există această cazare.");
                                break;
                            }

                            System.out.print("Marca maşină: ");
                            String marcaAdd = sc.nextLine();
                            angajatCurent.AdaugaMasina(marcaAdd, czMasAdd);          // ← apel din Angajat
                            break;

                        case "13":      // Achiziţionează (scoate) maşină
                            System.out.println("Cazări existente:");
                            cazari.forEach(c -> System.out.println(" • " + c.getNume()));

                            System.out.print("Nume cazare: ");
                            String numeMasRem = sc.nextLine();
                            Cazare czMasRem = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeMasRem))
                                    .findFirst().orElse(null);

                            if (czMasRem == null) {
                                System.out.println("Nu există această cazare.");
                                break;
                            }

                            System.out.print("Marca maşină de scos: ");
                            String marcaRem = sc.nextLine();
                            angajatCurent.AchizitionareMasina(marcaRem, czMasRem);   // ← apel din Angajat
                            break;

                        case "14":      // Achiziţionează bilet avion
                            System.out.println("Cazări şi bilete de avion disponibile:");
                            cazari.forEach(c -> {
                                System.out.print(" • " + c.getNume() + " → ");
                                c.afisareAvioane();
                            });

                            System.out.print("Nume cazare: ");
                            String numeAv = sc.nextLine();
                            Cazare czAv = cazari.stream()
                                    .filter(c -> c.getNume().equalsIgnoreCase(numeAv))
                                    .findFirst().orElse(null);

                            if (czAv != null)
                                angajatCurent.AchizitionareAvion(czAv);              // ← apel din Angajat
                            else
                                System.out.println("Nu există această cazare.");
                            break;

                        case "15":      // Validează o vacanţă
                            if (angajatCurent.getRezervari().isEmpty()) {
                                System.out.println("Nu există rezervări.");
                                break;
                            }
                            for (int i = 0; i < angajatCurent.getRezervari().size(); i++) {
                                System.out.print((i + 1) + ". ");
                                angajatCurent.getRezervari().get(i).afisare();
                            }
                            System.out.print("Index rezervare: ");
                            int idxVal = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxVal >= 0 && idxVal < angajatCurent.getRezervari().size())
                                angajatCurent.ValideazaVacanta(angajatCurent.getRezervari().get(idxVal));  // ← apel
                            else
                                System.out.println("Index invalid.");
                            break;
                        case "16":      // Modifică detalii vacanţă după numele rezervării
                            if (angajatCurent.getRezervari().isEmpty()) {
                                System.out.println("Nu există rezervări.");
                                break;
                            }

                            System.out.println("Rezervările angajatului:");
                            angajatCurent.getRezervari()
                                    .forEach(r -> System.out.println(" • " + r.getNume()));

                            System.out.print("Introduceţi numele rezervării de modificat: ");
                            String numeRez = sc.nextLine();

                            Rezervare rezSelect = angajatCurent.getRezervari()
                                    .stream()
                                    .filter(r -> r.getNume().equalsIgnoreCase(numeRez))
                                    .findFirst()
                                    .orElse(null);

                            if (rezSelect == null) {
                                System.out.println("Nu există rezervare cu acest nume.");
                                break;
                            }

                            angajatCurent.ModificaDetaliiVacanta(rezSelect);     // ← apel la metoda deja definită
                            break;

                        case "17":      // Log out angajat
                            System.out.println("Te-ai delogat cu succes din contul de angajat.");
                            ruleazaA = false;        // iese din meniul angajat şi revine la meniul principal
                            break;
                    }
                }
            }
                else if (s.equals("C")) {
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
                            System.out.println("Autentificare reușita.");
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
                    System.out.println("1. Vizualizeaza destinatiile disponibile");
                    System.out.println("2. Cauta hoteluri");
                    System.out.println("3. Rezerva camera");
                    System.out.println("4. Vezi rezervarile tale");
                    System.out.println("5. Modifica o rezervare");
                    System.out.println("6. Anuleaza o rezervare");
                    System.out.println("7. Scrie feedback");
                    System.out.println("8. Log out");
                    System.out.println("0. Iesire aplicatie");
                    System.out.print("Alege o optiune: ");
                    String opt = sc.nextLine();

                    switch (opt) {
                        case "1":
                            for (Destinatie d : destinatii) {
                                d.afisareDestinatii();
                            }
                            break;
                        case "2":
                            System.out.print("Introduceti tara: ");
                            String tara = sc.nextLine();
                            System.out.print("Introduceti orașul: ");
                            String oras = sc.nextLine();
                            for (Destinatie d : destinatii) {
                                if (d.getTara().equalsIgnoreCase(tara) && d.getOras().equalsIgnoreCase(oras)) {
                                    d.afisare();
                                }
                            }
                            break;
                        case "3":
                            boolean rezervareReusita = false;
                            while (!rezervareReusita) {
                                System.out.println("Unde doresti să mergi?");

                                System.out.print("Introduceti numele rezervarii: ");
                                String nume = sc.nextLine();
                                System.out.print("Introduceti tara: ");
                                String tara1 = sc.nextLine();
                                System.out.print("Introduceti orasul: ");
                                String oras1 = sc.nextLine();
                                System.out.print("Introduceti data check-in (dd.MM.yyyy): ");
                                String dataCheckIn = sc.nextLine();
                                System.out.print("Introduceti data check-out (dd.MM.yyyy): ");
                                String dataCheckOut = sc.nextLine();
                                System.out.print("Introduceti numarul de persoane: ");
                                int nrPers = sc.nextInt();
                                System.out.print("Introduceti numarul de camere: ");
                                int nrCam = sc.nextInt();
                                sc.nextLine();

                                if (clientCurent.rezervareCamera(nume, tara1, oras1, dataCheckIn, dataCheckOut, nrPers, nrCam)) {
                                    System.out.println("Rezervarea a fost realizata cu succes.");
                                    rezervareReusita = true;
                                } else {
                                    System.out.println("Date invalide. Incercați din nou.\n");
                                }
                            }

                            // Aici se va face plata
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
                            System.out.println("Rezervarea a fost efectuată cu succes!");
                            break;
                        case "4":
                            clientCurent.afisare();
                            break;
                        case "5":
                            ArrayList<Rezervare> rezervari = clientCurent.getRezervari();
                            if (rezervari.isEmpty()) {
                                System.out.println("Nu ai rezervari.");
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
                                System.out.println("Nu ai rezervari.");
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
                                System.out.println("Rezervarea a fost anulata.");
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
                            System.out.println("Opțiune invalida.");
                    }
                }
            }
        }
    }
}