public class Destinatie {
    private String tara;
    private String oras;

    public Destinatie(String tara, String oras) {
        this.tara = tara;
        this.oras = oras;
    }

    public void afisare() {
        System.out.println("Destinatie: " + tara + ", " + oras);
    }
}
