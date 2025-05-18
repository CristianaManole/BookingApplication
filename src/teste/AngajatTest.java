package teste;

import clase.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AngajatTest {

    @Test
    public void testGestionareDestinatii() {

        ArrayList<Destinatie> destinatii = new ArrayList<>();
        Destinatie barcelona = new Destinatie("Spania", "Barcelona");
        destinatii.add(barcelona);
        Angajat admin = new Angajat("Ionescu", "Ana", 'F', "1234567890123", "0711111111", TipAngajat.Administrator, destinatii);
        Destinatie roma = new Destinatie("Italia", "Roma");

        // Teste la adăugare
        assertTrue(admin.AdaugaDestinatie(roma), "Adaugare destinatie noua (‘Italia, Roma’) ar trebui sa returneze true");

        //Duplicat
        assertFalse(admin.AdaugaDestinatie(new Destinatie("Spania", "Barcelona")), "Adaugare destinatie duplicat (‘Spania, Barcelona’) nu trebuie acceptata");

        // Teste la editare
        assertTrue(admin.EditeazaDestinatie(roma, null, "Milano"), "Editare oras valida (schimbam ‘Roma’ în ‘Milano’) ar trebui sa returneze true");

        //Valori invalide
        assertFalse(admin.EditeazaDestinatie(roma, "", ""), "Editare cu valori goale („tara” și „oras” goale) trebuie respinsa");

        assertTrue(admin.EditeazaDestinatie(roma, "Italia de Nord", null), "Editare tara valida (schimbam ‘Italia’ în ‘Italia de Nord’) ar trebui sa returneze true");

        // Teste la ștergere
        assertTrue(admin.StergeDestinatie(roma), "Stergere destinatie existenta (‘Italia de Nord, Milano’) ar trebui sa returneze true");

        //Destinatie inexistenta
        assertFalse(admin.StergeDestinatie(roma), "Stergere destinatie deja eliminata trebuie sa returneze false");
    }
    @Test
    public void testModificaDetaliiVacanta() {
        Angajat angajat = new Angajat("Popescu", "Ana", 'F', "1234567890123", "0712345678", TipAngajat.Administrator,new ArrayList<Destinatie>());
        Rezervare r = new Rezervare("2025-06-10", "2025-06-15", 2, 1);
        angajat.AdaugaRezervare(r);

        // Caz valid: modificare completa
        boolean modificareCompletValida = angajat.ModificaDetaliiVacanta(r, 4, 2, LocalDate.of(2025, 6, 12), LocalDate.of(2025, 6, 18), 100.0F);
        assertTrue(modificareCompletValida, "Modificare completă validă trebuie să returneze true");

        // Caz valid: doar numar persoane
        boolean modificareNrPersoane = angajat.ModificaDetaliiVacanta(r, 3, null, null, null,100.0F);
        assertTrue(modificareNrPersoane, "Modificare doar nrPersoane valid trebuie să returneze true");

        // Caz valid: doar date valide
        boolean modificareDateValide = angajat.ModificaDetaliiVacanta(r, null, null, LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 5),100.0F);
        assertTrue(modificareDateValide, "Modificare doar date valide trebuie să returneze true");

        // Caz invalid: rezervare inexistenta
        Rezervare rezervareNoua = new Rezervare("2025-06-01", "2025-06-05", 1, 2);
        boolean rezervareInexistenta = angajat.ModificaDetaliiVacanta(rezervareNoua, 2, 2, null, null,100.0F);
        assertFalse(rezervareInexistenta, "Rezervare inexistentă trebuie să returneze false");

        // Caz invalid: nr persoane negativ
        boolean nrPersoaneInvalid = angajat.ModificaDetaliiVacanta(r, -1, null, null, null,100.0F);
        assertFalse(nrPersoaneInvalid, "Nr persoane negativ trebuie să returneze false");

        // Caz invalid: nr camere 0
        boolean nrCamereInvalid = angajat.ModificaDetaliiVacanta(r, null, 0, null, null,100.0F);
        assertFalse(nrCamereInvalid, "Nr camere 0 trebuie să returneze false");

        // Caz invalid: check-out înainte de check-in
        boolean dateInvalide = angajat.ModificaDetaliiVacanta(r, null, null, LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 15),100.0F);
        assertFalse(dateInvalide, "Check-out înainte de check-in trebuie să returneze false");

        // Caz invalid: doar check-in setat
        boolean doarCheckin = angajat.ModificaDetaliiVacanta(r, null, null, LocalDate.of(2025, 6, 10), null,100.0F);
        assertFalse(doarCheckin, "Doar check-in fără check-out trebuie să returneze false");

        // Caz invalid: doar check-out setat
        boolean doarCheckout = angajat.ModificaDetaliiVacanta(r, null, null, null, LocalDate.of(2025, 6, 15),100.0F);
        assertFalse(doarCheckout, "Doar check-out fără check-in trebuie să returneze false");
    }
}
