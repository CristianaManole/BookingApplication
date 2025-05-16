package clase;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Client extends Persoana{
    private TipClient tip;
    private String nrcard;
    private String dataExpirare;
    private String numeCard;
    private int CCV;
    private ArrayList<Rezervare> rezervari;


    public Client(String nume, String prenume, char sex, String CNP, String telefon, TipClient tip) {
        super(nume, prenume, sex, CNP, telefon);
        this.tip = tip;
        this.rezervari = new ArrayList<>();
    }

    public ArrayList<Rezervare> getRezervari() {
        return rezervari;
    }

    public boolean rezervareCamera(String tara, String oras, String dataCheckIn, String dataCheckOut, int nrPersoane, int nrCamere) {
        if (tara == null || tara.trim().isEmpty()) return false;
        if (oras == null || oras.trim().isEmpty()) return false;

        LocalDate checkIn, checkOut;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            checkIn = LocalDate.parse(dataCheckIn, formatter);
            checkOut = LocalDate.parse(dataCheckOut, formatter);
            if (checkOut.isBefore(checkIn)) return false;
        } catch (DateTimeParseException e) {
            return false;
        }
        if (nrPersoane <= 0 || nrCamere <= 0) return false;

        Rezervare rez = new Rezervare(dataCheckIn, dataCheckOut, nrPersoane, nrCamere);
        Destinatie destinatie = new Destinatie(tara, oras);
        rez.addDestinatie(destinatie);
        rezervari.add(rez);
        return true;
    }

    public boolean efectuarePlata(String numeCard, String nrcard, String dataExpirare, int CCV) {
        if (numeCard == null || numeCard.trim().isEmpty()) {
            return false;
        }
        if (nrcard == null || nrcard.trim().length() < 16) {
            return false;
        }
        if (dataExpirare == null || !dataExpirare.matches("\\d{2}/\\d{2}")) {
            return false;
        }
        if (String.valueOf(CCV).length() != 3) {
            return false;
        }

        // Setează doar dacă totul e valid
        this.numeCard = numeCard;
        this.nrcard = nrcard;
        this.dataExpirare = dataExpirare;
        this.CCV = CCV;

        return true;
    }

    @Override
    public void afisare() {
        super.afisare();
        System.out.println("Tip Client: " + tip);
        for(Rezervare rezervare : rezervari) {
            rezervare.afisare();
        }
    }
}
