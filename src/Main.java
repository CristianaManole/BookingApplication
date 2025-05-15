import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client persoana = new Client("Popescu", "Ion", 'M', "1234567890123", "0722222222", TipClient.TURIST);
        persoana.afisare();
        persoana.stergere();
        System.out.println();

        Client client = new Client("Ionescu", "Maria", 'F', "1234567890123", "0733333333", TipClient.CUPLU);
        client.afisare();
        System.out.println();
        ArrayList<Client> clienti = new ArrayList<>();
        clienti.add(persoana);
        clienti.add(client);

        Angajat angajat = new Angajat("Georgescu", "Vasile", 'M', "1234567890123", "0744444444", TipAngajat.Administrator);
        angajat.afisare();

        System.out.println("Esti client sau angajat?(A/C)");
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        if (s.equals("A")) {
            //SE COMPLETEAZA PT ANGAJAT
        }
        else if (s.equals("C")){
            System.out.println("Nume client: ");
            String nume = sc.nextLine();
            System.out.println("Prenume client: ");
            String prenume = sc.nextLine();
            System.out.println("Sex client M/F: ");
            String sex = sc.nextLine();
            System.out.println("CNP client: ");
            String cnp = sc.nextLine();
            System.out.println("Telefon client: ");
            String telefon = sc.nextLine();
            System.out.print("Alege AFACERIST, TURIST, PENSIONAR, FAMILIE, CUPLU, GRUP: ");
            TipClient t = TipClient.valueOf(sc.nextLine().toUpperCase());
            Client c1 = new Client(nume, prenume, sex.charAt(0), cnp, telefon, t);
            clienti.add(c1);
            System.out.println("Cont client efectuat cu succes!");
            for (Client c : clienti) {
                c.afisare();
            }
        }

    }
}