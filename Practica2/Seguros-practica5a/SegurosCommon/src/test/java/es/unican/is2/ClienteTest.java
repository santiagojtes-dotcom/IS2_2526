package es.unican.is2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente("Juan", "11111111A", false);
    }

    @Test
    public void testTotalSegurosSinMinusvalia() {
        // C1: Cliente con dos seguros sin minusvalía
        Seguro s1 = new Seguro("1111AAA", Cobertura.TERCEROS, LocalDate.now(), 80); // 400
        Seguro s2 = new Seguro("2222BBB", Cobertura.TODO_RIESGO, LocalDate.now(), 80); // 600
        cliente.getSeguros().add(s1);
        cliente.getSeguros().add(s2);

        assertEquals(1000.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSegurosConMinusvalia() {
        // C2: Cliente con minusvalía (Descuento 25%)
        cliente.setMinusvalia(true);
        Seguro s1 = new Seguro("1111AAA", Cobertura.TERCEROS, LocalDate.now(), 80); // 400
        cliente.getSeguros().add(s1);

        // 400 * 0.75 = 300
        assertEquals(300.0, cliente.totalSeguros(), 0.001);
    }

    @Test
    public void testTotalSegurosListaVacia() {
        // C3: Caso borde - Cliente sin seguros
        assertEquals(0.0, cliente.totalSeguros(), 0.001);
    }
}