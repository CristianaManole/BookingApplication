package clase;
import java.util.ArrayList;

public class Destinatie {
    private String tara;
    private String oras;
    ArrayList<Cazare> cazari;

    public Destinatie(String tara, String oras) {
        this.tara = tara;
        this.oras = oras;
        cazari = new ArrayList<>();
    }

    public void adaugaCazare(Cazare cazare) {
        cazari.add(cazare);
    }

    public void afisare() {
        System.out.println("Destinatie: " + tara + ", " + oras);
        for (Cazare cazare : cazari) {
            cazare.afisare();
        }
    }

    public void afisareDestinatii() {
        System.out.println("Destinatie: " + tara + ", " + oras);
    }

    public String getTara() {
        return tara;
    }

    public String getOras() {
        return oras;
    }


}
