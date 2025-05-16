package clase;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rezervare {

    private String nume;
    private String data_checkin;
    private String data_checkout;
    private int    nrPersoane;
    private int    nrCamere;
    private float  pretTotal;
    private ArrayList<Destinatie> destinatii;

    public Rezervare() {
        this("", "", "", 0, 0);
        this.pretTotal = 0f;
    }

    public Rezervare(String nume, String data_checkin, String data_checkout,
                     int nrPersoane, int nrCamere) {

        Random random = new Random();
        float min = 200f, max = 800f;

        this.data_checkin = data_checkin;
        this.data_checkout = data_checkout;
        this.nrPersoane = nrPersoane;
        this.nrCamere   = nrCamere;
        this.pretTotal  = min + random.nextFloat() * (max - min);
        this.destinatii = new ArrayList<>();
    }

    public void addDestinatie(Destinatie d) { destinatii.add(d); }

    public void afisare() {
        System.out.println("Check-in: " + data_checkin +
                           ", Check-out: " + data_checkout +
                           ", Persoane: " + nrPersoane +
                           ", Camere: "   + nrCamere +
                           ", Pret total: " + pretTotal);
        for (Destinatie d : destinatii) d.afisare();
    }

    public void actualizareRezervare() {
        Scanner s = new Scanner(System.in);

        System.out.println("Vrei sa actualizezi nr de camere? (da/nu)");
        if (s.nextLine().equalsIgnoreCase("da")) {
            System.out.print("Introduceti noul nr de camere: ");
            nrCamere = s.nextInt(); s.nextLine();
            pretTotal += 100;
        }

        System.out.println("Vrei sa actualizezi nr de persoane? (da/nu)");
        if (s.nextLine().equalsIgnoreCase("da")) {
            System.out.print("Introduceti noul nr de persoane: ");
            nrPersoane = s.nextInt(); s.nextLine();
            pretTotal += 75;
        }

        System.out.println("Vrei sa actualizezi data check-in? (da/nu)");
        if (s.nextLine().equalsIgnoreCase("da")) {
            System.out.print("Introduceti noua data check-in: ");
            data_checkin = s.nextLine();
            pretTotal += 50;
        }

        System.out.println("Vrei sa actualizezi data check-out? (da/nu)");
        if (s.nextLine().equalsIgnoreCase("da")) {
            System.out.print("Introduceti noua data check-out: ");
            data_checkout = s.nextLine();
            pretTotal += 50;
        }

        System.out.println("Rezervarea a fost actualizata cu succes!");
    }

    public String getDataCheckin(){ 
        return data_checkin; 
    }
    public String getDataCheckout(){ 
        return data_checkout; 
    }
    public int getNrPersoane(){ 
        return nrPersoane;   
    }
    public int getNrCamere(){ 
        return nrCamere;     
    }
    public float getPretTotal(){ 
        return pretTotal;   
    }
    public ArrayList<Destinatie> getDestinatii(){ 
        return destinatii; 
    }

    public void setDataCheckin(String data){
        this.data_checkin  = data; 
    }
    public void setDataCheckout(String data){
        this.data_checkout = data; 
    }
    public void setNrPersoane(int nr){
        this.nrPersoane = nr;   
    }
    public void setNrCamere(int nr){
        this.nrCamere = nr;   
    }
    public void setPretTotal(float pret){
        this.pretTotal = pret; 
    }
    public String getNume() { return nume; }
    public void setNume(String nume) { this.nume = nume; }

}
