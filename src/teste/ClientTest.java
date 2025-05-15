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
}
