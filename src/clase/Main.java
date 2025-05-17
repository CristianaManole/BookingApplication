package clase;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Client> clienti = new ArrayList<>();
        ArrayList<Rezervare> waitinglist = new ArrayList<>();
        ArrayList<Cazare> cazariAvion = new ArrayList<>();
        ArrayList<Cazare> cazariMasina = new ArrayList<>();
        Client persoana = new Client("Popescu", "Ion", 'M', "1234567890123", "0722222222", TipClient.TURIST);
        Client client = new Client("Ionescu", "Maria", 'F', "1234567890123", "0733333333", TipClient.CUPLU);
        clienti.add(persoana);
        clienti.add(client);



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
        ArrayList<Angajat> angajati = new ArrayList<>();
        Angajat angajat = new Angajat("Georgescu", "Vasile", 'M', "1234567890123", "0744444444", TipAngajat.Administrator, destinatii);
        angajati.add(angajat);

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


        ArrayList<ArrayList<Camera>> camereCazare = new ArrayList<>();

        for(int i = 0; i < cazari.size(); i++) {
            ArrayList<Camera> camere = new ArrayList<>();
            camere.add(new Camera(TipCamera.SINGLE, 100.0f));
            camere.add(new Camera(TipCamera.DOUBLE, 150.0f));
            camere.add(new Camera(TipCamera.TWIN,   140.0f));
            camere.add(new Camera(TipCamera.TRIPLE, 180.0f));
            camere.add(new Camera(TipCamera.SUITE,  250.0f));

            camereCazare.add(camere);
            for (Camera c : camere) {
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

            if (s.equalsIgnoreCase("A")) {

                Angajat angajatCurent = null;
                String raspuns;
                do {
                    System.out.print("Ai cont? (Y/N) ");
                    raspuns = sc.nextLine().trim().toUpperCase();
                } while (!raspuns.equals("Y") && !raspuns.equals("N"));

                if (raspuns.equals("Y")) {
                    System.out.print("Introdu CNP: ");
                    String cnp = sc.nextLine().trim();

                    for (Angajat a : angajati)
                        if (a.getCNP().equals(cnp)) {
                            angajatCurent = a;
                            break;
                        }

                    if (angajatCurent == null) {
                        System.out.println("CNP incorect sau cont inexistent.");
                        continue;
                    }

                } else {
                    System.out.print("Nume angajat: ");
                    String nume = sc.nextLine();
                    System.out.print("Prenume angajat: ");
                    String prenume = sc.nextLine();
                    System.out.print("Sex (M/F): ");
                    char sex = sc.nextLine().trim().toUpperCase().charAt(0);
                    System.out.print("CNP angajat: ");
                    String cnp = sc.nextLine();
                    System.out.print("Telefon angajat: ");
                    String tel = sc.nextLine();

                    TipAngajat rol;
                    while (true) {
                        System.out.print("Rol angajat (Administrator / ManagerReceptie / AgentOferte): ");
                        String r = sc.nextLine().trim();
                        try { rol = TipAngajat.valueOf(r); break; }
                        catch (IllegalArgumentException e) { System.out.println("Rol invalid."); }
                    }

                    angajatCurent = new Angajat(nume, prenume, sex, cnp, tel, rol, destinatii);
                    angajati.add(angajatCurent);
                    System.out.println("Cont de angajat creat cu succes.");
                }
                boolean ruleazaA = true;
                while (ruleazaA) {
                    System.out.println("\n--- MENIU PRINCIPAL ---");
                    System.out.println(" 1. Vezi destinatii");
                    System.out.println(" 2. Adauga destinatie");
                    System.out.println(" 3. Sterge destinatie");
                    System.out.println(" 4. Editeaza destinatie");
                    System.out.println(" 5. Adauga cazare");
                    System.out.println(" 6. Sterge cazare ");
                    System.out.println(" 7. Editeaza cazare ");
                    System.out.println(" 8. Adauga camera");
                    System.out.println(" 9. Sterge camera");
                    System.out.println("10. Editeaza camera");
                    System.out.println("11. Adauga facilitati");
                    System.out.println("12. Adauga masina");
                    System.out.println("13. Achizitioneaza masina");
                    System.out.println("14. Achizitioneaza bilet avion");
                    System.out.println("15. Valideaza vacanta");
                    System.out.println("16. Modifica detalii vacanta");
                    System.out.println("17. Log out");
                    System.out.println(" 0. Iesire aplicatie");
                    System.out.print("Alege: ");
                    String optA = sc.nextLine();

                    switch (optA) {
                        /* DESTINATII */
                        case "1":
                            destinatii.forEach(Destinatie::afisareDestinatii);
                            break;
                        /* Adauga destinatie */
                        case "2":
                            System.out.print("Introdu Tara: ");
                            String nt = sc.nextLine();
                            System.out.print("Introdu Oras: ");
                            String no = sc.nextLine();
                            Destinatie dn = new Destinatie(nt, no);
                            angajatCurent.AdaugaDestinatie(dn);
                            break;
                        /* Sterge destinație */
                        case "3": {
                            if (destinatii.isEmpty()) {
                                System.out.println("Nu există destinații.");
                                break;
                            }

                            System.out.println("Destinații disponibile:");
                            for (int i = 0; i < destinatii.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1,
                                        destinatii.get(i).getOras());

                            System.out.print("Index destinație de șters: ");
                            int idxDel = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxDel < 0 || idxDel >= destinatii.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            angajatCurent.StergeDestinatie(destinatii.get(idxDel));
                            break;
                        }

                        /*  Editeaza destinatie */
                        case "4": {
                            if (destinatii.isEmpty()) {
                                System.out.println("Nu exista destinatii.");
                                break;
                            }

                            System.out.println("Destinatii disponibile:");
                            for (int i = 0; i < destinatii.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                destinatii.get(i).afisareDestinatii();   // afiseaza doar tara + oras
                            }

                            System.out.print("Index destinatie de editat: ");
                            int idx = Integer.parseInt(sc.nextLine()) - 1;
                            if (idx < 0 || idx >= destinatii.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            System.out.print("Noua tara (Enter pentru neschimbat): ");
                            String taraNoua = sc.nextLine();

                            System.out.print("Noul oras (Enter pentru neschimbat): ");
                            String orasNou = sc.nextLine();

                            boolean ok = angajatCurent.EditeazaDestinatie(destinatii.get(idx), taraNoua, orasNou);
                            System.out.println(ok ? "Destinatia a fost actualizata."
                                    : "Nu s-a modificat nimic / date invalide.");
                            break;
                        }

                        case "5":
                            System.out.println("Destinații disponibile:");
                            destinatii.forEach(Destinatie::afisareDestinatii);

                            System.out.print("Oraș destinație: ");
                            String oras = sc.nextLine().trim();
                            Destinatie dest = destinatii.stream()
                                    .filter(d -> d.getOras().equalsIgnoreCase(oras))
                                    .findFirst().orElse(null);

                            if (dest == null) { System.out.println("Destinația nu există."); break; }

                            // date cazare
                            System.out.print("Nume cazare: ");   String nume = sc.nextLine();
                            System.out.print("Adresa: ");        String adr  = sc.nextLine();
                            System.out.print("Stele: ");         String st   = sc.nextLine();
                            System.out.print("Tip: ");           String tip  = sc.nextLine();
                            System.out.print("Nr mașini: ");     int nrM     = Integer.parseInt(sc.nextLine());
                            System.out.print("Nr bilete avion: ");int nrB    = Integer.parseInt(sc.nextLine());

                            Cazare cNoua = new Cazare(nume, adr, st, tip, nrM, nrB);
                            angajatCurent.AdaugaCazare(dest, cNoua);
                            break;

                        case "6": {
                            ArrayList<Destinatie> destList = new ArrayList<>();
                            ArrayList<Cazare>     cazList  = new ArrayList<>();
                            for (Destinatie d : destinatii)
                                for (Cazare c : d.cazari) {
                                    destList.add(d);
                                    cazList.add(c);
                                }

                            if (cazList.isEmpty()) {
                                System.out.println("Nu există cazări.");
                                break;
                            }

                            System.out.println("Cazări existente:");
                            for (int i = 0; i < cazList.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazList.get(i).getNume());

                            System.out.print("Index cazare de șters: ");
                            int idx = Integer.parseInt(sc.nextLine()) - 1;
                            if (idx < 0 || idx >= cazList.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            angajatCurent.StergeCazare(destList.get(idx), cazList.get(idx));
                            break;
                        }


                        /* 7  Editează cazare */
                        case "7": {
                            ArrayList<Cazare> listaCazari = new ArrayList<>();
                            for (Destinatie d : destinatii)
                                listaCazari.addAll(d.cazari);

                            if (listaCazari.isEmpty()) {
                                System.out.println("Nu există nicio cazare în sistem.");
                                break;
                            }

                            System.out.println("Cazări existente:");
                            for (int i = 0; i < listaCazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, listaCazari.get(i).getNume());

                            System.out.print("Index cazare de editat: ");
                            int idx = Integer.parseInt(sc.nextLine()) - 1;
                            if (idx < 0 || idx >= listaCazari.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            angajatCurent.EditeazaCazare(listaCazari.get(idx));
                            break;
                        }


                        /* Adauga camera */
                        case "8": {
                            if (cazari.isEmpty()) {
                                System.out.println("Nu există cazări.");
                                break;
                            }

                            System.out.println("Cazări disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idxAdd = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxAdd < 0 || idxAdd >= cazari.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            System.out.print("Tip (SINGLE/DOUBLE/TWIN/TRIPLE/SUITE): ");
                            TipCamera tipCam = TipCamera.valueOf(sc.nextLine().toUpperCase());

                            System.out.print("Preţ: ");
                            float pret = Float.parseFloat(sc.nextLine());

                            Camera camNoua = new Camera(tipCam, pret);
                            cazari.get(idxAdd).adaugareCamere(camNoua);
                            camereCazare.get(idxAdd).add(camNoua);
                            System.out.println("Camera adăugată.");
                            break;
                        }

                        /* Sterge camera */
                        case "9": {
                            if (cazari.isEmpty()) {
                                System.out.println("Nu exista cazari.");
                                break;
                            }

                            System.out.println("Cazari disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idxDel = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxDel < 0 || idxDel >= cazari.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            ArrayList<Camera> lista = camereCazare.get(idxDel);
                            if (lista.isEmpty()) {
                                System.out.println("Aceasta cazare nu are camere.");
                                break;
                            }

                            for (int i = 0; i < lista.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                lista.get(i).afisare();
                            }

                            System.out.print("Index camera de sters: ");
                            int iDel = Integer.parseInt(sc.nextLine()) - 1;
                            if (iDel < 0 || iDel >= lista.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            Camera camDel = lista.remove(iDel);
                            cazari.get(idxDel).stergereCamera(camDel);
                            System.out.println("Camera ştearsă.");
                            break;
                        }

                        /* Editeaza camera */
                        case "10": {
                            if (cazari.isEmpty()) {
                                System.out.println("Nu exista cazari.");
                                break;
                            }
                            System.out.println("Cazari disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idxCaz = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxCaz < 0 || idxCaz >= cazari.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            ArrayList<Camera> listaCam = camereCazare.get(idxCaz);
                            if (listaCam.isEmpty()) {
                                System.out.println("Aceasta cazare nu are camere.");
                                break;
                            }

                            for (int i = 0; i < listaCam.size(); i++) {
                                System.out.print((i + 1) + ". ");
                                listaCam.get(i).afisare();
                            }

                            System.out.print("Index camera de editat: ");
                            int idxCam = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxCam < 0 || idxCam >= listaCam.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }
                            cazari.get(idxCaz).editeazaCamera(listaCam.get(idxCam));
                            break;
                        }

                        /*  Adaugă facilitati */
                        case "11": {
                            if (cazari.isEmpty()) {
                                System.out.println("Nu exista cazari.");
                                break;
                            }

                            System.out.println("Cazari disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idx = Integer.parseInt(sc.nextLine()) - 1;
                            if (idx < 0 || idx >= cazari.size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            Cazare cz = cazari.get(idx);

                            System.out.println("\nDetalii cazare selectata:");
                            cz.afisare();

                            System.out.println("\nFacilitati disponibile:");
                            TipFacilitati[] opt = TipFacilitati.values();
                            for (int i = 0; i < opt.length; i++)
                                System.out.printf("%d. %s%n", i + 1, opt[i]);

                            System.out.print(
                                    "\nIntrodu facilitatile dorite: ");
                            String[] tok = sc.nextLine().split(",");

                            ArrayList<TipFacilitati> listaFac = new ArrayList<>();

                            for (String token : tok) {
                                String fac = token.trim();
                                if (fac.isEmpty()) continue;
                                try {
                                    int nr = Integer.parseInt(fac);
                                    if (nr >= 1 && nr <= opt.length)
                                        listaFac.add(opt[nr - 1]);
                                    continue;
                                } catch (NumberFormatException ignored) {  }

                                try {
                                    listaFac.add(TipFacilitati.valueOf(fac));
                                } catch (IllegalArgumentException ignored) {
                                    System.out.println("Facilitate necunoscuta: " + fac);
                                }
                            }

                            if (listaFac.isEmpty()) {
                                System.out.println("Nu s-a adaugat nicio facilitate valida.");
                                break;
                            }

                            cz.adaugareFacilitati(new Facilitati(listaFac));
                            System.out.println("Facilitati adaugate.");
                            break;
                        }

                        /* 12 Adauga mașina */
                        case "12": {
                            if (cazari.isEmpty()) { System.out.println("Nu există cazări."); break; }

                            System.out.println("Cazari disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idxMasAdd = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxMasAdd < 0 || idxMasAdd >= cazari.size()) {
                                System.out.println("Index invalid."); break;
                            }

                            System.out.print("Marcă mașina de adaugat: ");
                            String marcaAdd = sc.nextLine().trim();

                            angajatCurent.AdaugaMasina(marcaAdd, cazari.get(idxMasAdd));
                            break;
                        }

                        /*  Achiziționează mașina */
                        case "13": {
                            if (cazari.isEmpty()) { System.out.println("Nu exista cazari."); break; }

                            System.out.println("Cazari disponibile:");
                            for (int i = 0; i < cazari.size(); i++)
                                System.out.printf("%2d. %s%n", i + 1, cazari.get(i).getNume());

                            System.out.print("Alege index cazare: ");
                            int idxMasRem = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxMasRem < 0 || idxMasRem >= cazari.size()) {
                                System.out.println("Index invalid."); break;
                            }

                            System.out.print("Marcă mașina de scos: ");
                            String marcaRem = sc.nextLine().trim();

                            angajatCurent.AchizitionareMasina(marcaRem, cazari.get(idxMasRem));
                            break;
                        }

                        /*  Achizitioneaza bilet avion */
                        case "14": {

                            ArrayList<Cazare> toate = new ArrayList<>();
                            for (Destinatie d : destinatii)
                                toate.addAll(d.cazari);

                            if (toate.isEmpty()) {
                                System.out.println("Nu exista nicio cazare în sistem.");
                                break;
                            }

                            System.out.println("Cazari si bilete de avion disponibile:");
                            for (int i = 0; i < toate.size(); i++) {
                                Cazare c = toate.get(i);
                                System.out.printf("%2d. %s → ", i + 1, c.getNume());
                                c.afisareAvioane();
                            }

                            System.out.print("Alege index cazare: ");
                            int idx = Integer.parseInt(sc.nextLine()) - 1;
                            if (idx < 0 || idx >= toate.size()) { System.out.println("Index invalid."); break; }

                            angajatCurent.AchizitionareAvion(toate.get(idx));
                            break;
                        }
                        case "15":      // Validează o vacanţă
                            if (waitinglist != null) {
                                for (Rezervare r : waitinglist) {
                                    angajatCurent.AdaugaRezervare(r);
                                }
                                waitinglist.clear();
                            }
                            if (angajatCurent.getRezervari(false).isEmpty()) {
                                System.out.println("Nu există rezervări.");
                                break;
                            }
                            for (int i = 0; i < angajatCurent.getRezervari(false).size(); i++) {
                                System.out.print((i + 1) + ". ");
                                angajatCurent.getRezervari(false).get(i).afisare();
                            }
                            System.out.print("Index rezervare: ");
                            int idxVal = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxVal >= 0 && idxVal < angajatCurent.getRezervari(false).size())
                                angajatCurent.ValideazaVacanta(angajatCurent.getRezervari(false).get(idxVal));  // apel
                            else
                                System.out.println("Index invalid.");
                            break;

                        case "16":      // Modifică detalii vacanţă
                            if (angajatCurent.getRezervari(true).isEmpty()) {
                                System.out.println("Nu există vacanţe planificate şi validate.");
                                break;
                            }

                            for (int i = 0; i < angajatCurent.getRezervari(true).size(); i++) {
                                System.out.print((i + 1) + ". ");
                                angajatCurent.getRezervari(true).get(i).afisare();
                            }

                            System.out.print("Index rezervare de modificat: ");
                            int idxValVerificare = Integer.parseInt(sc.nextLine()) - 1;
                            if (idxValVerificare < 0 ||
                                    idxValVerificare >= angajatCurent.getRezervari(true).size()) {
                                System.out.println("Index invalid.");
                                break;
                            }

                            Rezervare rezMod = angajatCurent.getRezervari(true).get(idxValVerificare);

                            System.out.print("Vrei sa modifici nr. persoane? (Y/N): ");
                            Integer nrPersoaneNou = rezMod.getNrPersoane();
                            if (sc.nextLine().equalsIgnoreCase("Y")) {
                                System.out.print("Introdu noul numar: ");
                                try {
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n > 0) nrPersoaneNou = n;
                                    else System.out.println("Nr invalid!");
                                } catch (NumberFormatException e) {
                                    System.out.println("Format invalid!");
                                }
                            }

                            System.out.print("Vrei sa modifici nr. camere? (Y/N): ");
                            Integer nrCamereNou = rezMod.getNrCamere();
                            if (sc.nextLine().equalsIgnoreCase("Y")) {
                                System.out.print("Introdu noul numar: ");
                                try {
                                    int n = Integer.parseInt(sc.nextLine());
                                    if (n > 0) nrCamereNou = n;
                                    else System.out.println("Nr invalid!");
                                } catch (NumberFormatException e) {
                                    System.out.println("Format invalid!");
                                }
                            }

                            System.out.print("Vrei sa modifici data check-in? (Y/N): ");
                            LocalDate checkInNou = LocalDate.parse(rezMod.getDataCheckout(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                            if (sc.nextLine().equalsIgnoreCase("Y")) {
                                System.out.print("Introdu data (YYYY-MM-DD): ");
                                try {
                                    checkInNou = LocalDate.parse(sc.nextLine());
                                } catch (DateTimeParseException e) {
                                    System.out.println("Data invalida!");
                                }
                            }

                            System.out.print("Vrei sa modifici data check-out? (Y/N): ");
                            LocalDate checkOutNou = LocalDate.parse(rezMod.getDataCheckout(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                            if (sc.nextLine().equalsIgnoreCase("Y")) {
                                System.out.print("Introdu data (YYYY-MM-DD): ");
                                try {
                                    checkOutNou = LocalDate.parse(sc.nextLine());
                                } catch (DateTimeParseException e) {
                                    System.out.println("Data invalida!");
                                }
                            }
                            System.out.print("Vrei sa modifici pretul? (Y/N): ");
                            Float pret = rezMod.getPretTotal();
                            if (sc.nextLine().equalsIgnoreCase("Y")) {
                                System.out.print("Introdu noul pret: ");
                                try {
                                    Float n = Float.parseFloat(sc.nextLine());
                                    if (n > 0) pret = n;
                                    else System.out.println("Nr invalid!");
                                } catch (NumberFormatException e) {
                                    System.out.println("Format invalid!");
                                }
                            }

                            angajatCurent.ModificaDetaliiVacanta(rezMod, nrPersoaneNou, nrCamereNou, checkInNou, checkOutNou,pret);

                            System.out.println("Rezervarea a fost actualizată.");
                            break;
                        case "17":      // Log out angajat
                            System.out.println("Te-ai delogat cu succes din contul de angajat.");
                            ruleazaA = false;        // iese din meniul angajat şi revine la meniul principal
                            break;
                        case "0":      // Iesire aplicatie
                            System.out.println("Aplicatia a fost inchisa. La revedere!");
                            aplicatieDeschisa = false;
                            ruleazaA = false;        // iese din meniul angajat
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
                    System.out.println("4. Vezi rezervarile tale in asteptare");
                    System.out.println("5. Modifica o rezervare");
                    System.out.println("6. Anuleaza o rezervare");
                    System.out.println("7. Scrie feedback");
                    System.out.println("8. Vezi rezervarile tale confirmate");
                    System.out.println("9. Log out");
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
                                System.out.print("Introduceti tara: ");
                                String tara1 = sc.nextLine();
                                System.out.print("Introduceti orasul: ");
                                String oras1 = sc.nextLine();

                                Destinatie destinatieAleasa = null;
                                for (Destinatie d : destinatii) {
                                    if (d.getTara().equalsIgnoreCase(tara1) && d.getOras().equalsIgnoreCase(oras1)) {
                                        destinatieAleasa = d;
                                        break;
                                    }
                                }

                                if (destinatieAleasa == null) {
                                    System.out.println("Destinatia nu exista.");
                                    continue;
                                }

                                // 2. Alege cazarea (prima din listă)
                                if (destinatieAleasa.getCazari().isEmpty()) {
                                    System.out.println("Nu exista cazări pentru această destinație.");
                                    continue;
                                }
                                Cazare cazare = destinatieAleasa.getCazari().get(0);

                                // 3. Afișează camerele disponibile
                                System.out.println("Camere disponibile de la " + cazare.getNume() + ": ");
                                for (Camera c : cazare.getCamere()) {
                                    if (c != null && c.getDisponibilitate()) {
                                        c.afisare();
                                    }
                                }
                                // 4. Alege tipul de cameră
                                TipCamera tipAles = null;
                                while (tipAles == null) {
                                    System.out.print("Ce tip de cameră dorești (SINGLE, DOUBLE, TWIN, TRIPLE, SUITE): ");
                                    String input = sc.nextLine().trim().toUpperCase();
                                    try {
                                        tipAles = TipCamera.valueOf(input);
                                    } catch (IllegalArgumentException e) {
                                        System.out.println("Tip de cameră invalid. Încearcă din nou.");
                                    }
                                }
                                System.out.print("Introduceti data check-in (dd.MM.yyyy): ");
                                String dataCheckIn = sc.nextLine();
                                System.out.print("Introduceti data check-out (dd.MM.yyyy): ");
                                String dataCheckOut = sc.nextLine();
                                System.out.print("Introduceti numarul de persoane: ");
                                int nrPers = sc.nextInt();
                                System.out.print("Introduceti numarul de camere: ");
                                int nrCam = sc.nextInt();
                                sc.nextLine();

                                if (clientCurent.rezervareCamera(tara1, oras1, dataCheckIn, dataCheckOut, nrPers, nrCam)) {
                                    System.out.println("Rezervarea camerei a fost realizata cu succes.");
                                    List<Rezervare> rezi = clientCurent.getRezervari();
                                    if (!rezi.isEmpty()) {
                                        Rezervare ultima = rezi.get(rezi.size() - 1);
                                        waitinglist.add(ultima);
                                    }
                                    rezervareReusita = true;

                                } else {
                                    System.out.println("Date invalide. Incercați din nou.\n");
                                }
                                // transport
                                System.out.println("Vrei sa achizitionezi bilet de avion? (da/nu)");
                                String optiune = sc.nextLine();
                                if (optiune.equalsIgnoreCase("da")) {
                                    System.out.println("Cerere trimisa!");
                                    cazariAvion.add(cazare);
                                }
                                //masina
                                System.out.println("Vrei sa inchiriezi o masina? (da/nu)");
                                String optiuneMasina = sc.nextLine();
                                if (optiuneMasina.equalsIgnoreCase("da")) {
                                    System.out.println("Cerere trimisa!");
                                    cazariMasina.add(cazare);
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
                            System.out.println("Rezervarea a se afla in asteptare!");
                            break;
                        case "4":
                            ArrayList<Rezervare> rezervariInAsteptare = clientCurent.getRezervari();
                            if (rezervariInAsteptare.isEmpty()) {
                                System.out.println("Nu ai rezervari in asteptare.");
                                break;
                            }
                            int count = rezervariInAsteptare.size();
                            for (int i = 0; i < rezervariInAsteptare.size(); i++) {
                                if(!rezervariInAsteptare.get(i).este_confirmata()) {
                                    System.out.print((i + 1) + ". ");
                                    rezervariInAsteptare.get(i).afisare();
                                    System.out.println("Bilete de avion: " + cazariAvion.size());
                                    System.out.println("Masini: " + cazariMasina.size());
                                    count -= 1;
                                }
                            }
                            if(count == rezervariInAsteptare.size()) {
                                System.out.println("Nu ai rezervari in asteptare.");
                            }

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
                            rezervari = clientCurent.getRezervari();
                            if (rezervari.isEmpty()) {
                                System.out.println("Nu ai rezervari.");
                                break;
                            }
                            int count2 = rezervari.size();

                            for (int i = 0; i < rezervari.size(); i++) {
                                if(rezervari.get(i).este_confirmata()) {
                                    System.out.print((i + 1) + ". ");
                                    rezervari.get(i).afisare();
                                    System.out.println("Bilete de avion: " + cazariAvion.size());
                                    System.out.println("Masini: " + cazariMasina.size());
                                    count2 -= 1;
                                }
                            }
                            if(count2 == rezervari.size()) {
                                System.out.println("Nu ai rezervari confirmate.");
                            }
                            break;

                        case "9":
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