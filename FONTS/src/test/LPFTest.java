package test;

import domini.classes.LPF;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class LPFTest {

    @Test
    public void provaConstructorAmbFrequenciesPositives() throws Exception {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", 1);
        contingutEntrada.put("paraula2", 2);

        LPF lpf = new LPF("LPFTest", 1, new ArrayList<>(), contingutEntrada, 2);
        assertNotNull(lpf, "L'objecte LPF no hauria de ser null si es proporcionen frequencies positives");
    }

    @Test
    public void provaConstructorAmbFrequenciaNegativa() {
        HashMap<String, Integer> contingutEntrada = new HashMap<>();
        contingutEntrada.put("paraula1", -1); // Frequencia negativa

        Exception excepcio = assertThrows(Exception.class, () -> new LPF("LPFTest", 1, new ArrayList<>(), contingutEntrada, 2));
        assertEquals("Una paraula no pot tenir frequencia negativa", excepcio.getMessage());
    }
}
