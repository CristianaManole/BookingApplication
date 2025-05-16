package clase;
import java.util.ArrayList;
import java.util.Scanner;

public class Cazare {
    static final int NR_MAX_FACILITATI = 7;
    static final int NR_MAX_CAMERE = 30;
    private String nume;
    private String adresa;
    private String nrStele;
    private String tip;
    private int nrMasini;
    ArrayList<String> masini;
    private int NrBileteAvion;
    private Facilitati[] facilitati;
    private int nrFacilitati;
    private Camera[] camere;
    private int nrCamere;

    public Cazare(String nume, String adresa, String nrStele, String tip, int nrMasini, int NrBileteAvion) {
        this.nume = nume;
        this.adresa = adresa;
        this.nrStele = nrStele;
        this.tip = tip;
        this.nrMasini = nrMasini;
        this.NrBileteAvion = NrBileteAvion;
        masini = new ArrayList<String>();
        nrFacilitati = 0;
        facilitati = new Facilitati[NR_MAX_FACILITATI];
        nrCamere = 0;
        camere = new Camera[NR_MAX_CAMERE];
    }

    public boolean DispMasini() {
        if (nrMasini != 0)
            return true;
        else
            return false;
    }

    public boolean DispAvioane() {
        if (NrBileteAvion!= 0)
            return true;
        else
            return false;
    }

    public void afisareMasini() {
        for (int i = 0; i < nrMasini; i++)
            System.out.println(masini.get(i));
    }

    public void afisareAvioane() {
            System.out.println("Sunt disponibile " + NrBileteAvion + "bilete de avion");
    }

    public void adaugareFacilitati(Facilitati fac) {
        this.facilitati[nrFacilitati++] = fac;
    }

    public void adaugareCamere(Camera c) {
        this.camere[nrCamere++] = c;
    }

    public void stergereCamera(Camera c) {
        for (int i = 0; i < nrCamere; i++) {
            if (camere[i] == c) {
                for (int j = i; j < nrCamere - 1; j++) {
                    camere[j] = camere[j + 1];
                }
                camere[nrCamere - 1] = null;
                nrCamere--;
                break;
            }
        }
    }

    public void editeazaCamera(Camera c) {
        for (int i = 0; i < nrCamere; i++) {
            if (camere[i] == c) {
                Scanner sc = new Scanner(System.in);

                System.out.print("Vrei sa modifici tipul camerei? (Y/N): ");
                String input = sc.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    System.out.print("Introdu tipul (SINGLE, DOUBLE, SUITE etc.): ");
                    try {
                        TipCamera t = TipCamera.valueOf(sc.nextLine().toUpperCase());
                        camere[i].setTip(t);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tip invalid!");
                    }
                }

                System.out.print("Vrei sa modifici pretul camerei? (Y/N): ");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    System.out.print("Introdu noul pret: ");
                    try {
                        float pret = Float.parseFloat(sc.nextLine());
                        camere[i].setPret(pret);
                    } catch (NumberFormatException e) {
                        System.out.println("Pret invalid!");
                    }
                }

                System.out.print("Vrei sa modifici disponibilitatea camerei? (Y/N): ");
                input = sc.nextLine();
                if (input.equalsIgnoreCase("Y")) {
                    System.out.print("Este disponibila? (true/false): ");
                    boolean disponibil = Boolean.parseBoolean(sc.nextLine());
                    camere[i].setDisponibilitate(disponibil);
                }

                break;
            }
        }
    }

    public void afisare() {
        System.out.println("Nume cazare: " + nume);
        System.out.println("Adresa cazare: " + adresa);
        System.out.println("Numar stele cazare: " + nrStele);
        System.out.println("Tip cazare: " + tip);
        for (int i = 0; i < nrFacilitati; i++)
            facilitati[i].afisare();
        for (int i = 0; i < nrCamere; i++)
            camere[i].afisare();

    }
    public int getNrMasini(){ return nrMasini; }
    public int getNrCamere(){ return nrCamere; }
    public String getNume(){return nume;}
    public int getNrBileteAvion(){ return NrBileteAvion; }

    public void setNume(String nume){ this.nume = nume; }
    public void setAdresa(String adresa){ this.adresa = adresa; }
    public void setNrStele(String nrStele){ this.nrStele = nrStele; }
    public void setTip(String tip){ this.tip = tip; }
    public void setNrMasini(int n){ this.nrMasini = n; }
    public void setNrBileteAvion(int n){ this.NrBileteAvion = n; }


}
