package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class SeguroTest {

    private Seguro s;

    @Test
    public void testPrecioCajaNegra() {
        // S1: TODO_RIESGO, potencia 80 (sin recargo), fecha antigua (con descuento)
        // base 600 * 0.9 = 540
        s = new Seguro("1234ABC", Cobertura.TODO_RIESGO, LocalDate.now().minusMonths(2), 80);
        assertEquals(540.0, s.precio(), 0.001);

        // S2: TERCEROS, potencia 100 (recargo 5%), fecha reciente (sin descuento)
        // base 400 + (400 * 0.05) = 420
        s = new Seguro("1234ABC", Cobertura.TERCEROS, LocalDate.now(), 100);
        assertEquals(420.0, s.precio(), 0.001);

        // S3: TODO_RIESGO, ootencia 120 (recargo 8%), hoy (sin descuento)
        // base 600 + (600 * 0.08) = 648
        s = new Seguro("1234ABC", Cobertura.TODO_RIESGO, LocalDate.now(), 120);
        assertEquals(648.0, s.precio(), 0.001);
    }

    @Test
    public void testPrecioAVL() {
        // S4: limite inferior de recargo (potencia 89)
        s = new Seguro("1234ABC", Cobertura.TERCEROS, LocalDate.now(), 89);
        assertEquals(400.0, s.precio(), 0.001);

        // S5: limite exacto recargo 5% (potencia 90)
        s = new Seguro("1234ABC", Cobertura.TERCEROS, LocalDate.now(), 90);
        assertEquals(420.0, s.precio(), 0.001);

        // S6: limite exacto recargo 8% (potencia 111)
        s = new Seguro("1234ABC", Cobertura.TODO_RIESGO, LocalDate.now(), 111);
        assertEquals(648.0, s.precio(), 0.001);
    }
}