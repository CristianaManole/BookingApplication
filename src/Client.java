import java.util.ArrayList;
import java.util.Scanner;

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

    public void rezervareCamera() {
        Rezervare rez;
        Scanner s = new Scanner(System.in);

        System.out.println("Unde dorești să mergi?");
        System.out.print("Introduceți țara: ");
        String tara = s.nextLine();
        System.out.print("Introduceți orașul: ");
        String oras = s.nextLine();

        System.out.println("Introdu data de check-in:");
        String dc = s.nextLine();
        System.out.println("Introdu data de check-out:");
        String dc2 = s.nextLine();
        System.out.println("Introdu nr de persoane:");
        int nrp = s.nextInt();
        System.out.println("Introdu nr de camere:");
        int nrc = s.nextInt();

        rez = new Rezervare(dc, dc2, nrp, nrc);
        Destinatie destinatie = new Destinatie(tara, oras);
        rez.addDestinatie(destinatie);
        rezervari.add(rez);

        System.out.println("Rezervare realizată cu succes.");
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
