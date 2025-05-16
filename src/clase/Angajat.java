package clase;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Angajat extends Persoana {
    private TipAngajat tip;
    private ArrayList<Destinatie> destinatii;
    private ArrayList<Rezervare> rezervari;

    public Angajat(String nume, String prenume, char sex, String CNP, String telefon, TipAngajat tip) {
        super(nume, prenume, sex, CNP, telefon);
        this.tip = tip;
        this.destinatii = new ArrayList<>();
        this.rezervari = new ArrayList<>();
    }

    // ----- DESTINATII -----
    public void AdaugaDestinatie(Destinatie d) {
        destinatii.add(d);
        System.out.println("Destinatia a fost adaugata.");
    }

    public void StergeDestinatie(Destinatie d) {
        if (!destinatii.contains(d)) {
            System.out.println("Destinatia nu a fost gasita.");
            return;
        }
        if (destinatii.remove(d))
            System.out.println("Destinatia a fost stearsa.");
        else
            System.out.println("Destinatia nu a fost gasita in lista.");
    }

    public void EditeazaDestinatie(Destinatie d) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Vrei sa modifici tara destinatiei? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noua tara: ");
            d.setTara(sc.nextLine());
        }
        System.out.print("Vrei sa modifici orasul destinatiei? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul oras: ");
            d.setOras(sc.nextLine());
        }
        System.out.println("Destinatia a fost actualizata.");
    }

    // ----- CAZARE -----
    public void AdaugaCazare(Destinatie destinatie, Cazare cazare) {
        destinatie.adaugaCazare(cazare);
        System.out.println("Cazare adaugata in destinatia " + destinatie.getOras());
    }

    public void StergeCazare(Destinatie destinatie, Cazare cazare) {
        if (destinatie.cazari.remove(cazare))
            System.out.println("Cazarea a fost stearsa.");
        else
            System.out.println("Cazarea nu a fost gasita.");
    }

    public void EditeazaCazare(Cazare cazare) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Vrei sa modifici numele cazarii? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul nume: ");
            cazare.setNume(sc.nextLine());
        }
        System.out.print("Vrei sa modifici adresa cazarii? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noua adresa: ");
            cazare.setAdresa(sc.nextLine());
        }
        System.out.print("Vrei sa modifici numarul de stele? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul nr de stele: ");
            cazare.setNrStele(sc.nextLine());
        }
        System.out.print("Vrei sa modifici tipul cazarii? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul tip: ");
            cazare.setTip(sc.nextLine());
        }
        System.out.print("Vrei sa modifici numarul de masini disponibile? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul numar masini: ");
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n >= 0) cazare.setNrMasini(n);
                else System.out.println("Valoare invalida!");
            } catch (NumberFormatException e) { System.out.println("Format invalid!"); }
        }
        System.out.print("Vrei sa modifici numarul de bilete de avion disponibile? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul numar de bilete de avion: ");
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n >= 0) cazare.setNrBileteAvion(n);
                else System.out.println("Valoare invalida!");
            } catch (NumberFormatException e) { System.out.println("Format invalid!"); }
        }
        System.out.println("Cazarea a fost actualizata.");
    }

    // ----- REZERVARI -----
    public void AdaugaRezervare(Rezervare r) {
        rezervari.add(r);
        System.out.println("Rezervarea a fost adaugata.");
    }

    public void StergeRezervare(Rezervare r) {
        if (rezervari.remove(r))
            System.out.println("Rezervarea a fost stearsa.");
        else
            System.out.println("Rezervarea nu a fost gasita.");
    }

    public void ModificaDetaliiVacanta(Rezervare r) {
        if (!rezervari.contains(r)) {
            System.out.println("Rezervarea nu se gaseste in lista angajatului.");
            return;
        }
        Scanner sc = new Scanner(System.in);
        // nr de persoane
        System.out.print("Vrei sa modifici nr. persoane? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul numar: ");
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n > 0) r.setNrPersoane(n);
                else System.out.println("Nr invalid!");
            } catch (NumberFormatException e) { System.out.println("Format invalid!"); }
        }
        // nr de camere
        System.out.print("Vrei sa modifici nr. camere? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu noul numar: ");
            try {
                int n = Integer.parseInt(sc.nextLine());
                if (n > 0) r.setNrCamere(n);
                else System.out.println("Nr invalid!");
            } catch (NumberFormatException e) { System.out.println("Format invalid!"); }
        }
        // datele de check-in si check-out
        LocalDate checkIn = null, checkOut = null;
        System.out.print("Vrei sa modifici data check-in? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu data (YYYY-MM-DD): ");
            try { checkIn = LocalDate.parse(sc.nextLine()); }
            catch (DateTimeParseException e) { System.out.println("Data invalida!"); }
        }
        System.out.print("Vrei sa modifici data check-out? (Y/N): ");
        if (sc.nextLine().equalsIgnoreCase("Y")) {
            System.out.print("Introdu data (YYYY-MM-DD): ");
            try { checkOut = LocalDate.parse(sc.nextLine()); }
            catch (DateTimeParseException e) { System.out.println("Data invalida!"); }
        }
        if (checkIn != null && checkOut != null) {
            if (checkOut.isAfter(checkIn)) {
                r.setDataCheckin(checkIn.toString());
                r.setDataCheckout(checkOut.toString());
            } else System.out.println("Check-out trebuie sa fie dupa check-in!");
        }
        System.out.println("Detaliile rezervarii au fost actualizate.");
    }

    public void ValideazaVacanta(Rezervare r) {
        boolean valida = r.getNrPersoane() > 0 && r.getNrCamere() > 0;
        System.out.println(valida ? "Rezervare VALIDA." : "Rezervare INVALIDA.");
    }
    public void AdaugaMasina(String marca, Cazare c) {
        c.masini.add(marca);
        c.setNrMasini(c.getNrMasini() + 1);
        System.out.println("Masina adaugata: " + marca);
    }

    public void AchizitionareAvion(Cazare c) {
        if (c.getNrBileteAvion() <= 0) {
            System.out.println("Nu mai sunt bilete de avion disponibile.");
            return;
        }
        c.setNrBileteAvion(c.getNrBileteAvion() - 1);
        System.out.println("Bilet de avion achizitionat. Mai sunt disponibile: " + c.getNrBileteAvion());
    }


    public void AchizitionareMasina(String marca, Cazare c) {
        if (!c.masini.contains(marca)) {
            System.out.println("Masina cu marca " + marca + " nu este disponibila.");
            return;
        }
        c.masini.remove(marca);
        c.setNrMasini(Math.max(0, c.getNrMasini() - 1));
        System.out.println("Masina achizitionata: " + marca);
    }



    public TipAngajat getTip(){ 
        return tip; 
    }
    public ArrayList<Destinatie> getDestinatii() { 
        return destinatii; 
    }
    public ArrayList<Rezervare> getRezervari(){ 
        return rezervari; 
    }
}
