import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Rezervare {
    private String data_checkin;
    private String data_checkout;
    private int nrPersoane;
    private int nrCamere;
    private float pretTotal;
    private ArrayList<Destinatie> destinatii;

    public Rezervare(String data_checkin, String data_checkout, int nrPersoane, int nrCamere) {
        Random random = new Random();
        float min = 200f;
        float max = 800f;

        this.data_checkin = data_checkin;
        this.data_checkout = data_checkout;
        this.nrPersoane = nrPersoane;
        this.nrCamere = nrCamere;
        this.pretTotal = min + random.nextFloat() * (max - min);
        this.destinatii = new ArrayList<>();
    }

    void addDestinatie(Destinatie destinatie) {
        destinatii.add(destinatie);
    }

    public void afisare() {
        System.out.println("Data Check-in: " + data_checkin + ", Data Check-out: " + data_checkout + ", Nr. Persoane: " + nrPersoane + ", Nr. Camere: " + nrCamere + ", Pret Total: " + pretTotal);
        for (Destinatie destinatie : destinatii) {
            destinatie.afisare();
        }
    }

    public void ActualizareRezervare(){
        Scanner s = new Scanner(System.in);
        System.out.println("Vrei sa actualizezi nr de camere? (da/nu)");
        String raspuns = s.nextLine();
        if(raspuns.equalsIgnoreCase("da")) {
            System.out.println("Introduceti noul nr de camere:");
            nrCamere = s.nextInt();
            pretTotal += 100;
        }
        System.out.println("Vrei sa actualizezi nr de persoane? (da/nu)");
        raspuns = s.nextLine();
        if(raspuns.equalsIgnoreCase("da")) {
            System.out.println("Introduceti noul nr de persoane:");
            nrPersoane = s.nextInt();
            pretTotal += 75;
        }
        System.out.println("Vrei sa actualizezi data check-in? (da/nu)");
        raspuns = s.nextLine();
        if(raspuns.equalsIgnoreCase("da")) {
            System.out.println("Introduceti noua data check-in:");
            data_checkin = s.nextLine();
            pretTotal += 50;
        }
        System.out.println("Vrei sa actualizezi data check-out? (da/nu)");
        raspuns = s.nextLine();
        if(raspuns.equalsIgnoreCase("da")) {
            System.out.println("Introduceti noua data check-out:");
            data_checkout = s.nextLine();
            pretTotal += 50;
        }


    }
}
