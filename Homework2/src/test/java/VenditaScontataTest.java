import org.example.VenditaScontata;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class VenditaScontataTest {

    @ParameterizedTest
    @DisplayName("metodo che testa i valori ammissibili di setSconto")
    @ValueSource( doubles = { 2, 11, 14.5,0,100, Double.MAX_VALUE, Double.MIN_VALUE} )
    void test1SetSconto(double n) {
        VenditaScontata prodotto = new VenditaScontata();
        Assertions.assertEquals(true, prodotto.setSconto(n));

    }

    @Test
    @DisplayName("test equals")
    void testEquals() {
        VenditaScontata prodotto = new VenditaScontata("penna", 21.50, 10);
        assertAll(() -> assertEquals(false, prodotto.equals(new VenditaScontata())),
                () -> assertEquals(false, prodotto.equals(null)),
                () -> assertEquals(false, prodotto.equals(new String())),
                () -> assertEquals(true, prodotto.equals(new VenditaScontata("penna", 21.50, 10))),
                () -> assertEquals(false, prodotto.equals(new VenditaScontata("penna", 11.05, 10))),
                () -> assertEquals(false, prodotto.equals(new VenditaScontata("matita", 21.50, 10))),
                () -> assertEquals(false, prodotto.equals(new VenditaScontata("cancellino", 7.49, 30))),
                () -> assertEquals(false, prodotto.equals(new VenditaScontata("penna", 21.5, 17.40))));

    }
    //test che ho ritenuto opportuni osservando quanto fatto
    @Test
    @DisplayName("sconto massimo")
    void testScontoAndTotale() {
        VenditaScontata prodotto1 = new VenditaScontata("Prodotto", 100.0, 100.0);
        VenditaScontata prodotto2 = new VenditaScontata();
        assertAll(
                () -> assertEquals(0.0, prodotto1.totale()),
                () -> assertTrue(prodotto2.setSconto(100.0)),
                () -> assertEquals(100.0, prodotto2.getSconto())
        );
    }
    @Test
    @DisplayName("sconto negativo")
    void testSetScontoNegativo() {
        VenditaScontata prodotto = new VenditaScontata();
        assertFalse(prodotto.setSconto(-10.0));
        assertEquals(0.0, prodotto.getSconto());
    }

    @Test
    @DisplayName("caso stringa")
    void testToStringSenzaSconto() {
        VenditaScontata prodotto = new VenditaScontata("Prodotto", 100.0, 0.0);
        String expected = "Componente = Prodotto, Prezzo = E100.0 Sconto = 0.0%\nCosto totale = E100.0";
        assertEquals(expected, prodotto.toString());
    }


}
