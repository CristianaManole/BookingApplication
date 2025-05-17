package clase;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Angajat extends Persoana {
    private TipAngajat tip;
    private ArrayList<Destinatie> destinatii;
    private ArrayList<Rezervare> rezervari;

    public Angajat(String nume, String prenume, char sex,
                   String CNP, String telefon, TipAngajat tip,
                   ArrayList<Destinatie> destinatiiGlobal) {
        super(nume, prenume, sex, CNP, telefon);
        this.tip = tip;
        this.destinatii = destinatiiGlobal;
        this.rezervari = new ArrayList<>();
    }

    private static boolean numeInvalid(String s) {
        return s == null || s.trim().isEmpty() || !s.matches("[A-Za-zăâîșţĂÂÎȘŢ -]+");
    }

    public boolean AdaugaDestinatie(Destinatie d) {

        if (d == null || numeInvalid(d.getTara()) || numeInvalid(d.getOras())) {
            System.out.println("Date invalide pentru destinatie.");
            return false;
        }
        // duplicat
        boolean exista = destinatii.stream()
                .anyMatch(x -> x.getOras().equalsIgnoreCase(d.getOras())
                        && x.getTara().equalsIgnoreCase(d.getTara()));
        if (exista) {
            System.out.println("Destinatia exista deja.");
            return false;
        }

        // daca totul e valid
        destinatii.add(d);
        System.out.println("Destinatia a fost adaugata.");
        return true;
    }

    public boolean StergeDestinatie(Destinatie d) {

        //daca destinatia nu exista
        if (d == null) { System.out.println("Destinatie nula."); return false; }

        if (destinatii.remove(d)) {
            System.out.println("Destinatia a fost stearsa.");
            return true;
        } else {
            System.out.println("Destinatia nu a fost gasita.");
            return false;
        }
    }


    public boolean EditeazaDestinatie(Destinatie d, String taraNoua, String orasNou) {

        if (d == null || !destinatii.contains(d))  return false;
        boolean schimbat = false;

        if (taraNoua != null && !taraNoua.trim().isEmpty()
                && !numeInvalid(taraNoua)) {
            d.setTara(taraNoua.trim());
            schimbat = true;
        }
        if (orasNou != null && !orasNou.trim().isEmpty()
                && !numeInvalid(orasNou)) {
            d.setOras(orasNou.trim());
            schimbat = true;
        }
        return schimbat;     // true dacă am facut macar o schimbare
    }


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

    public void AdaugaRezervare(Rezervare r) {
        rezervari.add(r);
    }

    public void StergeRezervare(Rezervare r) {
        if (rezervari.remove(r))
            System.out.println("Rezervarea a fost stearsa.");
        else
            System.out.println("Rezervarea nu a fost gasita.");
    }
    public ArrayList<Rezervare> getRezervari(boolean active){
        ArrayList<Rezervare> returnArray= new ArrayList<>();
        if(this.rezervari.isEmpty()){
            return returnArray;
        }
        for (Rezervare r : rezervari){
            if(r.este_confirmata() == active){
                returnArray.add(r);
            }
        }
        return returnArray;

    }
    public boolean ModificaDetaliiVacanta(Rezervare r, Integer nrPersoaneNou, Integer nrCamereNou,
                                          LocalDate checkInNou, LocalDate checkOutNou,Float pret) {
        if (!rezervari.contains(r)) {
            System.out.println("Rezervarea nu se gaseste in lista angajatului.");
            return false;
        }

        // Validare număr persoane
        if (nrPersoaneNou != null) {
            if (nrPersoaneNou > 0) {
                r.setNrPersoane(nrPersoaneNou);
            } else {
                System.out.println("Nr persoane invalid!");
                return false;
            }
        }
        // Vailidare pret
        if (pret != null) {
            if (pret > 0) {
                r.setPretTotal(pret);
            } else {
                System.out.println("Pret total invalid!");
                return false;
            }
        }
        // Validare număr camere
        if (nrCamereNou != null) {
            if (nrCamereNou > 0) {
                r.setNrCamere(nrCamereNou);
            } else {
                System.out.println("Nr camere invalid!");
                return false;
            }
        }

        // Validare date check-in / check-out
        if (checkInNou != null && checkOutNou != null) {
            if (checkOutNou.isAfter(checkInNou)) {
                r.setDataCheckin(checkInNou.toString());
                r.setDataCheckout(checkOutNou.toString());
            } else {
                System.out.println("Check-out trebuie sa fie dupa check-in!");
                return false;
            }
        } else if (checkInNou != null || checkOutNou != null) {
            // unul este null, altul nu => combinație incompletă
            System.out.println("Ambele date check-in și check-out trebuie completate împreună.");
            return false;
        }

        System.out.println("Detaliile rezervarii au fost actualizate.");
        return true;
    }

    public void ValideazaVacanta(Rezervare r) {
        boolean valida = r.getNrPersoane() > 0 && r.getNrCamere() > 0;
        r.confirmare();
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


    public boolean AchizitionareMasina(String marca, Cazare c) {
        if (!c.masini.contains(marca)) {
            System.out.println("Masina cu marca " + marca + " nu este disponibila.");
            return false;
        }
        c.masini.remove(marca);
        c.setNrMasini(Math.max(0, c.getNrMasini() - 1));
        System.out.println("Masina achizitionata: " + marca);
        return true;
    }


}
