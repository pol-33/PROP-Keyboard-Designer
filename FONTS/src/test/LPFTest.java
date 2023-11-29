package test;

import domini.classes.LPF;
import java.util.HashMap;

import org.junit.Test;
import static org.junit.Assert.*;

public class LPFTest {

    @Test
    public void provaConstructorAmbFrequenciesPositives() {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", 1);
        contingutEntrada.put("paraula2", 2);

        // Equivalent del mètode de JUnit 5 assertDoesNotThrow() amb JUnit 4
        try {
            new LPF("LPFTest", 1, contingutEntrada, 2);
            // Si no hi ha excepcions, el test passa
        } catch (Exception e) {
            // Si n'hi ha, es produeix un fail
            fail("Unexpected exception: " + e.getMessage());
        }
    }


    @Test
    public void provaConstructorAmbFrequenciaNegativa() {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", -1); // Frequencia negativa

        Exception excepcio = assertThrows(Exception.class, () -> new LPF("LPFTest", 1, contingutEntrada, 2));
        assertEquals("El missatge d'excepció hauria de ser correcte", excepcio.getMessage(), "Una paraula no pot tenir frequencia negativa");
    }
}
