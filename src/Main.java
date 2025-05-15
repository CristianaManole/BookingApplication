
public class Main {
    public static void main(String[] args) {
        Persoana persoana = new Persoana("Popescu", "Ion", 'M', "1234567890123", "0722222222");
        persoana.afisare();
        persoana.stergere();
        System.out.println();

        Client client = new Client("Ionescu", "Maria", 'F', "1234567890123", "0733333333", TipClient.Cuplu);
        client.afisare();
        System.out.println();

        Angajat angajat = new Angajat("Georgescu", "Vasile", 'M', "1234567890123", "0744444444", TipAngajat.Administrator);
        angajat.afisare();


    }
}