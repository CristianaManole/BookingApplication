public class Persoana {
    private String nume;
    private String prenume;
    private char sex;
    private String CNP;
    private String telefon;

    public Persoana(String nume, String prenume, char sex, String CNP, String telefon) {
        this.nume = nume;
        this.prenume = prenume;
        this.sex = sex;
        this.CNP = CNP;
        this.telefon = telefon;
    }

    public String getCNP() {
        return CNP;
    }

    public void afisare() {
        System.out.println("Nume: " + nume + ", Prenume: " + prenume + ", Sex: " + sex + ", CNP: " + CNP + ", Telefon: " + telefon);
    }

    public void stergere() {
        System.out.println("Stergere persoana: " + nume + " " + prenume);
    }
}
