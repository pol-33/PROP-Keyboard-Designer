package test;

import domini.classes.LPF;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class LPFTest {

    @Test
    public void provaConstructorAmbFrequenciesPositives() {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", 1);
        contingutEntrada.put("paraula2", 2);

        assertDoesNotThrow(() -> new LPF("LPFTest", 1, contingutEntrada, 2),
                "No hauria d'haver excepció amb frequencies positives");
    }


    @Test
    public void provaConstructorAmbFrequenciaNegativa() {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", -1); // Frequencia negativa

        Exception excepcio = assertThrows(Exception.class, () -> new LPF("LPFTest", 1, contingutEntrada, 2));
        assertEquals("Una paraula no pot tenir frequencia negativa", excepcio.getMessage(), "El missatge d'excepció hauria de ser correcte");
    }
}
