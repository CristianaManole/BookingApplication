package teste;

import clase.*;
import org.junit.jupiter.api.Test;
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

        /* Teste la adaugare */
        assertTrue(admin.AdaugaDestinatie(roma));

        // Duplicat
        assertFalse(admin.AdaugaDestinatie(new Destinatie("Spania", "Barcelona")));

        /* Teste la editare*/
        /* Schimbam doar orasul */
        assertTrue(admin.EditeazaDestinatie(roma, null, "Milano"));

        /* Incercam sa punem valori invalide */
        assertFalse(admin.EditeazaDestinatie(roma, "", ""));

        /* Schimbam doar tara */
        assertTrue(admin.EditeazaDestinatie(roma, "Italia de Nord", null));

        /* Teste la stergere */
        assertTrue(admin.StergeDestinatie(roma));

        //Destinatia este deja eliminata
        assertFalse(admin.StergeDestinatie(roma));
    }
}
