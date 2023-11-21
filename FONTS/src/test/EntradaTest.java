package test;

import domini.classes.Entrada;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class EntradaTest {
    @Test
    public void testVincularTeclat() {
        Entrada entrada = new EntradaStub();

        int finalI = 1;
        assertDoesNotThrow(() -> entrada.vincularTeclat(finalI));
        assertTrue(entrada.getIdTeclats().contains(finalI));

    }

    @Test
    public void testDesvincularTeclat() {
        Entrada entrada = new EntradaStub();

        assertDoesNotThrow(() -> entrada.vincularTeclat(1));
        assertDoesNotThrow(() -> entrada.desvincularTeclat(1));
        assertFalse(entrada.getIdTeclats().contains(1));
    }

    @Test
    public void testVincularTeclatWithExistingKey() {
        Entrada entrada = new EntradaStub();

        assertDoesNotThrow(() -> entrada.vincularTeclat(1));

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
