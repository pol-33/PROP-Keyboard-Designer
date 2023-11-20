package test;

import domini.classes.Entrada;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EntradaTest {
    @Test
    public void testVincularTeclat() {
        Entrada entrada = new EntradaStub();
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> entrada.vincularTeclat(finalI));
            assertTrue(entrada.idTeclats.contains(finalI));
        }
    }

    @Test
    public void testDesvincularTeclat() {
        Entrada entrada = new EntradaStub();
        for (int i = 0; i < 3; i++) {
            entrada.idTeclats.add(i);
        }
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            assertDoesNotThrow(() -> entrada.desvincularTeclat(finalI));
            assertFalse(entrada.idTeclats.contains(finalI));
        }
    }

    @Test
    public void testVincularTeclatWithExistingKey() {
        Entrada entrada = new EntradaStub();
        for (int i = 0; i < 3; i++) {
            entrada.idTeclats.add(i);
        }
        for (int i = 0; i < 3; i++) {
            int finalI = i;
            Exception exception = assertThrows(Exception.class, () -> entrada.vincularTeclat(finalI));
            assertEquals("L'entrada ja té aquest teclat vinculat", exception.getMessage());
        }
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
