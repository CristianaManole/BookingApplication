public class Angajat extends Persoana {
    private TipAngajat tip;

    public Angajat(String nume, String prenume, char sex, String CNP, String telefon, TipAngajat tip) {
        super(nume, prenume, sex, CNP, telefon);
        this.tip = tip;
    }

    @Override
    public void afisare() {
        super.afisare();
        System.out.println("Tip Angajat: " + tip);
    }
}
