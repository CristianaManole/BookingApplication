public class Client extends Persoana{
    private TipClient tip;

    public Client(String nume, String prenume, char sex, String CNP, String telefon, TipClient tip) {
        super(nume, prenume, sex, CNP, telefon);
        this.tip = tip;
    }

    @Override
    public void afisare() {
        super.afisare();
        System.out.println("Tip Client: " + tip);
    }
}
