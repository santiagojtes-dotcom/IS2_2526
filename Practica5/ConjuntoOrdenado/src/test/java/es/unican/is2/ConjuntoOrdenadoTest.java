package es.unican.is2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConjuntoOrdenadoTest {

    private ConjuntoOrdenado<Integer> conjunto;

    @BeforeEach
    public void setUp() {
        conjunto = new ConjuntoOrdenado<>();
    }

    // --- Tests de add() ---

    @Test
    public void testAddNullLanzaExcepcion() {
        assertThrows(NullPointerException.class, () -> conjunto.add(null));
    }

    @Test
    public void testAddEnListaVacia() {
        assertTrue(conjunto.add(5));
        assertEquals(1, conjunto.size());
        assertEquals(5, conjunto.get(0));
    }

    @Test
    public void testAddMantienOrdenAscendente() {
        conjunto.add(5);
        conjunto.add(3);
        conjunto.add(7);
        // Orden esperado: [3, 5, 7]
        assertEquals(3, conjunto.get(0));
        assertEquals(5, conjunto.get(1));
        assertEquals(7, conjunto.get(2));
    }

    @Test
    public void testAddDuplicadoRetornaFalse() {
        conjunto.add(5);
        assertFalse(conjunto.add(5));
        assertEquals(1, conjunto.size()); // no se añade
    }

    // --- Tests de get() ---

    @Test
    public void testGetIndiceValido() {
        conjunto.add(10);
        assertEquals(10, conjunto.get(0));
    }

    @Test
    public void testGetIndiceNegativoLanzaExcepcion() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(-1));
    }

    @Test
    public void testGetIndiceFueraRangoLanzaExcepcion() {
        conjunto.add(10);
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.get(5));
    }

    // --- Tests de remove() ---

    @Test
    public void testRemoveIndiceValido() {
        conjunto.add(10);
        assertEquals(10, conjunto.remove(0));
        assertEquals(0, conjunto.size());
    }

    @Test
    public void testRemoveIndiceInvalidoLanzaExcepcion() {
        assertThrows(IndexOutOfBoundsException.class, () -> conjunto.remove(-1));
    }

    // --- Tests de size() y clear() ---

    @Test
    public void testSizeInicial() {
        assertEquals(0, conjunto.size());
    }

    @Test
    public void testClearVaciaLaLista() {
        conjunto.add(1);
        conjunto.add(2);
        conjunto.clear();
        assertEquals(0, conjunto.size());
    }
}