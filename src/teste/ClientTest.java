package teste;

import clase.Client;
import clase.TipClient;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ClientTest {
    @Test
    public void testEfectuarePlataToateCazurile() {
        Client client = new Client("Popescu", "Ion", 'M', "1234567890123", "0711111111", TipClient.TURIST);

        // Caz valid
        boolean rezultatValid = client.efectuarePlata("Ion Popescu", "1234567812345678", "12/25", 123);
        assertTrue(rezultatValid, "Plata validă ar trebui să returneze true");

        // Card gol
        boolean rezultatCardGol = client.efectuarePlata("Ion Popescu", "", "12/25", 123);
        assertFalse(rezultatCardGol, "Card gol nu trebuie acceptat");

        // Card prea scurt
        boolean rezultatCardScurt = client.efectuarePlata("Ion Popescu", "1234", "12/25", 123);
        assertFalse(rezultatCardScurt, "Card cu mai puțin de 16 cifre nu trebuie acceptat");

        // CCV invalid
        boolean rezultatCCV = client.efectuarePlata("Ion Popescu", "1234567812345678", "12/25", 12);
        assertFalse(rezultatCCV, "CCV invalid trebuie respins");

        // Data expirare invalidă
        boolean rezultatData = client.efectuarePlata("Ion Popescu", "1234567812345678", "Decembrie", 123);
        assertFalse(rezultatData, "Dată expirare invalidă trebuie respinsă");
    }

    @Test
    public void testRezervareCameraToateCazurile() {
        Client client = new Client("Popescu", "Ion", 'M', "1234567890123", "0711111111", TipClient.TURIST);

        // Caz valid
        boolean rezultatValid = client.rezervareCamera("Italia", "Roma", "10.06.2024", "15.06.2024", 2, 1);
        assertTrue(rezultatValid, "Rezervare valida ar trebui sa returneze true");

        // Tara goala
        boolean taraGoala = client.rezervareCamera("", "Roma", "10.06.2024", "15.06.2024", 2, 1);
        assertFalse(taraGoala, "Tara goala nu trebuie acceptata");

        // Oras gol
        boolean orasGol = client.rezervareCamera("Italia", "", "10.06.2024", "15.06.2024", 2, 1);
        assertFalse(orasGol, "Orasul gol nu trebuie acceptat");

        // Data check-in invalida (format gresit)
        boolean dataInvalida = client.rezervareCamera("Italia", "Roma", "10/06/2024", "15.06.2024", 2, 1);
        assertFalse(dataInvalida, "Data check-in invalida trebuie respinsa");

        // Check-out inainte de check-in
        boolean checkOutInainte = client.rezervareCamera("Italia", "Roma", "15.06.2024", "10.06.2024", 2, 1);
        assertFalse(checkOutInainte, "Check-out inainte de check-in nu e permis");

        // Numar de persoane invalid
        boolean persoaneInvalide = client.rezervareCamera("Italia", "Roma", "10.06.2024", "15.06.2024", 0, 1);
        assertFalse(persoaneInvalide, "Numar persoane <= 0 nu trebuie acceptat");

        // Numar de camere invalid
        boolean camereInvalide = client.rezervareCamera("Italia", "Roma", "10.06.2024", "15.06.2024", 2, 0);
        assertFalse(camereInvalide, "Numar camere <= 0 nu trebuie acceptat");

        // Luna prea mare
        boolean lunaPreaMare = client.rezervareCamera("Italia", "Roma", "10.13.2024", "15.06.2024", 2, 1);
        assertFalse(lunaPreaMare, "Luna 13 nu este valida");

        // Zi prea mare pentru luna
        boolean ziPreaMare = client.rezervareCamera("Italia", "Roma", "32.02.2024", "15.03.2024", 2, 1);
        assertFalse(ziPreaMare, "31 februarie nu e o data valida");
    }
}



