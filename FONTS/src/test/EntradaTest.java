package test;

import domini.classes.Entrada;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.*;

public class EntradaTest {
    @Test
    public void testVincularTeclat() {
        Entrada entrada = new EntradaStub();

        int finalI = 1;

        // Equivalent del mètode de JUnit 5 assertDoesNotThrow() amb JUnit 4
        try {
            entrada.vincularTeclat(finalI);
            // Si no hi ha excepcions, el test passa
        } catch (Exception e) {
            // Si n'hi ha, es produeix un fail
            fail("Unexpected exception: " + e.getMessage());
        }

        assertTrue(entrada.getIdTeclats().contains(finalI));

    }

    @Test
    public void testDesvincularTeclat() {
        Entrada entrada = new EntradaStub();

        // Equivalent del mètode de JUnit 5 assertDoesNotThrow() amb JUnit 4
        try {
            entrada.vincularTeclat(1);
            // Si no hi ha excepcions, el test passa
        } catch (Exception e) {
            // Si n'hi ha, es produeix un fail
            fail("Unexpected exception: " + e.getMessage());
        }

        // Equivalent del mètode de JUnit 5 assertDoesNotThrow() amb JUnit 4
        try {
            entrada.desvincularTeclat(1);
            // Si no hi ha excepcions, el test passa
        } catch (Exception e) {
            // Si n'hi ha, es produeix un fail
            fail("Unexpected exception: " + e.getMessage());
        }

        assertFalse(entrada.getIdTeclats().contains(1));
    }

    @Test
    public void testVincularTeclatWithExistingKey() {
        Entrada entrada = new EntradaStub();

        // Equivalent del mètode de JUnit 5 assertDoesNotThrow() amb JUnit 4
        try {
            entrada.vincularTeclat(1);
            // Si no hi ha excepcions, el test passa
        } catch (Exception e) {
            // Si n'hi ha, es produeix un fail
            fail("Unexpected exception: " + e.getMessage());
        }

        Exception exception = assertThrows(Exception.class, () -> entrada.vincularTeclat(1));
        assertEquals("L'entrada ja té aquest teclat vinculat", exception.getMessage());
    }


    @Test
    public void testDesvincularTeclatWithNonExistingKey() {
        Entrada entrada = new EntradaStub();
        for (int i = 3; i < 6; i++) {
            int finalI = i;
            Exception exception = assertThrows(Exception.class, () -> entrada.desvincularTeclat(finalI));
            assertEquals("L'entrda no tenia aquest teclat vinculat", exception.getMessage());
        }
    }

    // Stub class to allow instantiation of the abstract Entrada class for testing
    private static class EntradaStub extends Entrada {
        public EntradaStub() {
            this.nom = "TestNom";
            this.id = 1;
            this.lpf = new HashMap<>();
            this.idTeclats = new ArrayList<>();
            this.idAlfabet = 0;
        }

        @Override
        public void vincularTeclat(Integer idTeclat) throws Exception {
            super.vincularTeclat(idTeclat);
        }

        @Override
        public void desvincularTeclat(Integer idTeclat) throws Exception {
            super.desvincularTeclat(idTeclat);
        }
    }
}
