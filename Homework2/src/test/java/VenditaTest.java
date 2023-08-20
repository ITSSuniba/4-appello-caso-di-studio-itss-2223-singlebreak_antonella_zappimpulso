import org.example.Vendita;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class VenditaTest {
    @ParameterizedTest
    @DisplayName("metodo che testa i valori ammissibili di setPrezzo")
    @ValueSource ( doubles = { 9, 12, 14.5,0, Double.MAX_VALUE, Double.MIN_VALUE} )
    void test1SetPrezzo(double n) {
        Vendita prodotto = new Vendita();
        Assertions.assertEquals(true, prodotto.setPrezzo(n));

    }

    @Test
    @DisplayName("metodo che testa i valori non ammissibili di setPrezzo (-8, null, empty)")
    void test2SetPrezzo() {
        Vendita prodotto = new Vendita();
        /*Double prezzonullo = null;*/
        /*Double prezzovuoto = new Double();*/
        Assertions.assertAll(() -> assertEquals(false, prodotto.setPrezzo(-8))
                /*() -> assertEquals(false, list.get(1).setPrezzo(prezzonullo))*/
                /*() -> assertEquals(false, list.get(2).setPrezzo(prezzovuoto))*/);

    }

    @Test
    @DisplayName ("metodo che testa i valori ammissibili e non ammissibili (null, empty) di setNome")
    void testSetNome(){
        Vendita prodotto = new Vendita();
        Assertions.assertAll(() -> assertEquals(true, prodotto.setNome("a")),
                () -> assertEquals(true, prodotto.setNome("penna")),
                () -> assertEquals(false, prodotto.setNome(null)),
                () -> assertEquals(false, prodotto.setNome("")));
    }

    @Test
    @DisplayName("test uguaglianza vendite")
    void testUguaglianzaVendite() {
        Vendita prodotto = new Vendita("penna", 10);
        Assertions.assertAll(() -> assertEquals(false, prodotto.uguaglianzaVendite(new Vendita())),
                () -> assertEquals(false, prodotto.uguaglianzaVendite(null)),
                () -> assertEquals(true, prodotto.uguaglianzaVendite(new Vendita("penna", 10))),
                () -> assertEquals(false, prodotto.uguaglianzaVendite(new Vendita("penna", 8.34))),
                () -> assertEquals(false, prodotto.uguaglianzaVendite(new Vendita("gomma", 10))),
                () -> assertEquals(false, prodotto.uguaglianzaVendite(new Vendita("matita", 12.5))));
    }

    @Test
    @DisplayName("test minore di ")
    void testMinoreDi() {
        Vendita prodotto = new Vendita("penna", 10);
        Assertions.assertAll(() -> assertEquals(false, prodotto.minoreDi(new Vendita())),
                () -> assertEquals(false, prodotto.minoreDi(null)),
                () -> assertEquals(true, prodotto.minoreDi(new Vendita("gomma", 13.67))),
                () -> assertEquals(false, prodotto.minoreDi(new Vendita("penna", 8.34))),
                () -> assertEquals(false, prodotto.minoreDi(new Vendita("gomma", 10))));

    }

    @Test
    @DisplayName("test equals")
    void testEquals() {
        Vendita prodotto = new Vendita("gomma", 12.50);
        Assertions.assertAll(() -> assertEquals(false, prodotto.equals(new Vendita())),
                () -> assertEquals(false, prodotto.equals(null)),
                () -> assertEquals(false, prodotto.equals(new String())),
                () -> assertEquals(true, prodotto.equals(new Vendita("gomma", 12.50))),
                () -> assertEquals(false, prodotto.equals(new Vendita("gomma", 11.05))),
                () -> assertEquals(false, prodotto.equals(new Vendita("matita", 12.50))),
                () -> assertEquals(false, prodotto.equals(new Vendita("cancellino", 7.49))));

    }
}
